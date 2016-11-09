package com.jbfg.login;

import com.jbfg.APIClient;
import com.jbfg.api.API;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Sungpil Hyun on 2016. 11. 3..
 */
@Component
public class Login {

    @Resource
    APIClient client;

    private static String consumerKey = "byjfav0qi53eqri25fkqz5ao5wp2bdkdgk3exi2d";

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String processLogin() throws Exception {

        String header = "DirectLogin username=\"" + username + "\",password=\"" + password + "\", consumer_key=\"" + consumerKey + "\"";

        client.setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, header));

        String resultString = client.post(API.host + "/my/logins/direct", null);
        JSONObject result = new JSONObject(resultString);

        return result.getString("token");
    }

}
