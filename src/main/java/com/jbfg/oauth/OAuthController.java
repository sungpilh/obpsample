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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    OAuthAuthenticator authAuthenticator;

    @Resource
    APIClient client;

    /**
     * 진입 포인트
     * OAuth 인증이 필요한 API에 접근을 하기위해 사용자를 인증페이지로 이동시키는 역할을 합니다.
     * @return
     * @throws Exception
     */
    @RequestMapping("/oauth")
    public String oauthStart(HttpServletRequest request) throws Exception {

        //초기에 사용할 Header를 생성합니다.
        String authorizationHeader = authAuthenticator.generateOauthInitHeader("http://localhost:8080/callback");

        //API를 호출하기 전에  Authorization Header 를 추가합니다.
        client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));

        HashMap params = new HashMap();

        //POST 요청을 수행하여 결과를 받습니다.
        String result = client.post(OAuthAuthenticator.host_url + OAuthAuthenticator.init_uri, params);

        //받은 결과 중 oauth_token  과 oauth_token_secret  을 세션에 저장한다.
        String[] tokens = result.split("&");
        for (int i = 0; i < tokens.length; i++) {
            String[] keyvalue = tokens[i].split("=");
            if (keyvalue.length == 2) {
                request.getSession().setAttribute(keyvalue[0], keyvalue[1]);
            }
        }

        //사용자의 브라우저의 경로를 인증페이지로 리다이렉션 시킨다.
        return "redirect:" + OAuthAuthenticator.host_url + "/oauth/authorize?oauth_token=" + request.getSession().getAttribute("oauth_token");
    }

    /**
     * 사용자 인증페이지에서 인증을 수행하고 나면
     * Access Token 요청을 수행합니다.
     *
     * @param params
     * @return
     */
    @RequestMapping("/callback")
    @ResponseBody
    public String oauthCallback(
            HttpServletRequest request,
            @RequestParam HashMap params) throws Exception {

        //Access Token 요청을 위한 헤더를 생성합니다. Handshake 에서 받은 oauth_token_secret 이 필요합니다.
        String authorizationHeader = authAuthenticator.generateAccessTokenHeader(String.valueOf(params.get("oauth_token")), String.valueOf(request.getSession().getAttribute("oauth_token_secret")), String.valueOf(params.get("oauth_verifier")));
        client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));

        //API에 사용할 token과 token_secret을 요청합니다.
        String result = client.post(OAuthAuthenticator.host_url + OAuthAuthenticator.token_uri, params);

        String[] tokens = result.split("&");

        //받은 token과 token_secret을 저장하여 API 호출에 사용할 수 있도록 합니다. 여기서는 세션에 저장했습니다.
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

            //저장된 oauth_token과 oauth_token_secret을 가지고 api 를 호출하기 위한 헤더를 생성합니다.
            //api를 호출 할때 마다 다른 header 가 생성됩니다.
            String authorizationHeader = authAuthenticator.generateOauthAPIHeader(API.GetUserCurrent, "GET", oauthToken, oauthTokenSecret, new String[]{});

            client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader));

            JSONObject currentUser = new JSONObject(client.get(API.host + API.GetUserCurrent, new HashMap()));
            System.out.println(currentUser.toString(3));

            String userId = currentUser.getString("user_id");

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //새로운 어카운트를 등록하기 위해 Account 오브젝트를 생성합니다.
            Account account = new Account(userId, "CURRENT", "label", new Balance("EUR", "0"));
            String json = gson.toJson(account);

            //CreateAccount에 필요한 파라미터를 지정합니다.
            String api = API.applyAPIParameter(API.CreateAccount, "jbfg.01.kr", accountId);

            //파라미터가 적용된 api와 method, token, token_secret 을 사용하여 헤더를 생성합니다.
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
