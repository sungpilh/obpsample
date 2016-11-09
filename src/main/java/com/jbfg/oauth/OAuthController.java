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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
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

        String authorizationHeader = authAuthenticator.generateOauthHeader(OAuthAuthenticator.init_uri, "POST", "http://localhost:8080/callback", null, null, null, new String[]{});
        client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));

        HashMap params = new HashMap();

        String result = client.post(OAuthAuthenticator.host_url + OAuthAuthenticator.init_uri, params);

        String[] tokens = result.split("&");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
            String[] keyvalue = tokens[i].split("=");
            if (keyvalue.length == 2) {
                request.getSession().setAttribute(keyvalue[0], keyvalue[1]);
            }
        }

        return "redirect:" + OAuthAuthenticator.host_url + "/oauth/authorize?" + result;
    }

    /**
     * Request Token 요청을 수행한다.
     *
     * @param params
     * @return
     */
    @RequestMapping("/callback")
    public String oauthCallback(
            HttpServletRequest request,
            @RequestParam HashMap params) throws Exception {

        String authorizationHeader = authAuthenticator.generateOauthHeader(OAuthAuthenticator.token_uri, "POST", null, String.valueOf(params.get("oauth_token")), String.valueOf(request.getSession().getAttribute("oauth_token_secret")), String.valueOf(params.get("oauth_verifier")), new String[]{});

        client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));
        String result = client.post(OAuthAuthenticator.host_url + OAuthAuthenticator.token_uri, params);

        String[] tokens = result.split("&");
        for (int i = 0; i < tokens.length; i++) {
            String[] keyvalue = tokens[i].split("=");

            if (keyvalue.length == 2) {
                request.getSession().setAttribute(keyvalue[0], keyvalue[1]);
            }
        }

        return "redirect:/apiTest";
    }


    @RequestMapping("/apiTest")
    @ResponseBody
    public String apiTest(HttpServletRequest request) {

        String result = "";

        try {
            String oauthToken = (String) request.getSession().getAttribute("oauth_token");
            String oauthTokenSecret = (String) request.getSession().getAttribute("oauth_token_secret");

            String api = API.applyAPIParameter(API.GetUserCurrent);

            String authorizationHeader = authAuthenticator.generateOauthHeader(api, "GET", null, oauthToken, oauthTokenSecret, null, new String[]{});

            client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));

            JSONObject currentUser = new JSONObject(client.get(API.host + api, new HashMap()));

            System.out.println(currentUser.toString(3));

            String userId = currentUser.getString("user_id");

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Account account= new Account(userId, "CURRENT", "label", new Balance("EUR", "0"));
            String json= gson.toJson(account);
            api = API.applyAPIParameter(API.CreateAccount, "jbfg.01.kr", "821030859101");
            authorizationHeader = authAuthenticator.generateOauthHeader(api, "PUT", null, oauthToken, oauthTokenSecret, null, new String[]{});
            client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));
            client.setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));   //중요! 없으면 서버에서 인지를 못함
            result = client.putJson(API.host + api, json);

            JSONObject accountResult = new JSONObject(result);

            System.out.println(accountResult.toString(3));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
