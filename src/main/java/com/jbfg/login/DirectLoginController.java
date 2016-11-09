package com.jbfg.login;

import com.jbfg.APIClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Sungpil Hyun on 2016. 11. 3..
 */
@RestController
public class DirectLoginController {

    @Resource
    Login login;

    @Resource
    APIClient client;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam String email, @RequestParam String password) {

        login.setUsername(email);
        login.setPassword(password);
        String token = "";
        try {
            token = login.processLogin();
            //client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, "DirectLogin token=\"" + token + "\""));
            //API test


        } catch (Exception e) {
            return e.getMessage();
        }

        return token;
    }

}
