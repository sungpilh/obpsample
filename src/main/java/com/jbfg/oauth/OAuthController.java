package com.jbfg.oauth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jbfg.APIClient;
import com.jbfg.api.API;
import com.jbfg.api.Account;
import com.jbfg.api.Balance;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * OAuth 1.0a 인증 및 API 샘플
 * OAuth 1.0를 통해 OBP API 호출 시 헤더에 입력할 내용을 생성해주는 샘플 애플리케이션을 구현하였음
 * <p>
 * OAuth 인증 이용시 순서는 처음 서버에서 Oauth initiate endpoint를 통해 접근을읗
 * <p>
 * <p>
 * Created by Sungpil Hyun on 2016. 11. 8..
 */
@Controller
public class OAuthController {

    static Gson gson = new GsonBuilder().create();

    @Resource
    OAuthAuthenticator authAuthenticator;

    @Resource
    APIClient client;

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping("/oauth")
    public String oauthStart(HttpServletRequest request) throws Exception {

        //Request Token 요청
        String authorizationHeader = authAuthenticator.generateOauthInitHeader();
        client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));

        HashMap params = new HashMap();

        String result = client.post(OAuthAuthenticator.host_url + OAuthAuthenticator.init_uri, params);

        String[] tokens = result.split("&");
        for (int i = 0; i < tokens.length; i++) {
            String[] keyvalue = tokens[i].split("=");
            if (keyvalue.length == 2) {
                request.getSession().setAttribute(keyvalue[0], keyvalue[1]);
            }
        }

        return "redirect:" + OAuthAuthenticator.host_url + "/oauth/authorize?" + result;
    }

    /**
     * Access Token 요청을 수행한다.
     *
     * @param params
     * @return
     */
    @RequestMapping("/callback")
    @ResponseBody
    public String oauthCallback(
            HttpServletRequest request,
            @RequestParam HashMap params) throws Exception {

        String authorizationHeader = authAuthenticator.generateOauthAuthorizeHeader(String.valueOf(params.get("oauth_token")), String.valueOf(request.getSession().getAttribute("oauth_token_secret")), String.valueOf(params.get("oauth_verifier")));
        client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));
        String result = client.post(OAuthAuthenticator.host_url + OAuthAuthenticator.token_uri, params);

        String[] tokens = result.split("&");
        for (int i = 0; i < tokens.length; i++) {
            String[] keyvalue = tokens[i].split("=");

            if (keyvalue.length == 2) {
                request.getSession().setAttribute(keyvalue[0], keyvalue[1]);
            }
        }

        return result;
    }


    @RequestMapping("/apiTest/{accountId}")
    @ResponseBody
    public String apiTest(@PathVariable(value = "accountId") String accountId, HttpServletRequest request) {

        String result = "";

        try {
            String oauthToken = (String) request.getSession().getAttribute("oauth_token");
            String oauthTokenSecret = (String) request.getSession().getAttribute("oauth_token_secret");

            String authorizationHeader = authAuthenticator.generateOauthAPIHeader(API.GetUserCurrent, "GET", oauthToken, oauthTokenSecret, new String[]{});
            client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));
            JSONObject currentUser = new JSONObject(client.get(API.host + API.GetUserCurrent, new HashMap()));
            System.out.println(currentUser.toString(3));

            String userId = currentUser.getString("user_id");

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Account account = new Account(userId, "CURRENT", "label", new Balance("EUR", "0"));
            String json = gson.toJson(account);

            String api = API.applyAPIParameter(API.CreateAccount, "jbfg.01.kr", accountId);
            authorizationHeader = authAuthenticator.generateOauthAPIHeader(api, "PUT", oauthToken, oauthTokenSecret, new String[]{});
            client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));
            client.setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));   //중요! 없으면 서버에서 인지를 못함
            result = client.putJson(API.host + api, json);

            JSONObject accountResult = new JSONObject(result);

            result = accountResult.toString(3);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
