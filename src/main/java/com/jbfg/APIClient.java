package com.jbfg;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Created by Sungpil Hyun on 2016. 8. 2..
 */

@Component
public class APIClient {

    private List<Header> headers = new ArrayList<>();

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    public String get(String url, Map params) {
        List<NameValuePair> paramList = convertParam(params);
        String requestUrl = paramList.size() == 0 ? url : url + "?" + URLEncodedUtils.format(paramList, "UTF-8");
        HttpGet get = new HttpGet(requestUrl);
        return httpRequestWithoutBody(get);
    }

    public String delete(String url) {
        HttpDelete delete = new HttpDelete(url);
        return httpRequestWithoutBody(delete);
    }

    public String post(String url, Map params) throws Exception {
        return httpRequest(new HttpPost(url), params, "UTF-8");
    }

    public String put(String url, Map params) throws Exception {
        return httpRequest(new HttpPut(url), params, "UTF-8");
    }

    public String postJson(String url, String json) throws Exception {
        return httpRequestEntity(new HttpPost(url), new StringEntity(json));
    }

    public String putJson(String url, String json) throws Exception {
        return httpRequestEntity(new HttpPut(url), new StringEntity(json));
    }

    public String httpRequestWithoutBody(HttpRequestBase request) {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try {

            request.setHeaders(getHeaders());
            CloseableHttpResponse response = client.execute(request);

            result = EntityUtils.toString(response.getEntity());
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String httpRequest(HttpEntityEnclosingRequestBase method, Map params, String encoding) {

        String result = null;
        List<NameValuePair> paramList = convertParam(params);
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, encoding);
            result = httpRequestEntity(method, entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String httpRequestEntity(HttpEntityEnclosingRequestBase method, HttpEntity entity) {

        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        method.setHeaders(getHeaders());

        try {
            System.out.println(method.getMethod() + " : " + method.getURI());
            method.setEntity(entity);
            CloseableHttpResponse response = client.execute(method);
            result = EntityUtils.toString(response.getEntity());
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<NameValuePair> convertParam(Map params) {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if (params != null) {

            Iterator<String> keys = params.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                paramList.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
        }

        return paramList;
    }


    public Header[] getHeaders() {
        return headers.toArray(new Header[headers.size()]);
    }

    public void setHeader(Header header) {

        synchronized (headers) {
            removeHeader(header.getName());
            headers.add(header);
        }

    }

    public void removeHeader(String key) {

        Iterator<Header> iterator = headers.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(key)) {
                iterator.remove();
            }
        }
    }

    public void emptyHeaders() {
        headers.removeAll(headers);
    }


}
