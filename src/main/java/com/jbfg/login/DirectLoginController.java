package com.jbfg.login;

import com.jbfg.APIClient;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
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

        try {
            login.processLogin( token -> {
                client.removeHeader(HttpHeaders.AUTHORIZATION);
                client.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, "DirectLogin token=\""+ token+"\""));
            });
        }catch(Exception e){
            return e.getMessage();
        }

        return "SUCCESS";
    }

}
