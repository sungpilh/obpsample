package com.jbfg.oauth;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Sungpil Hyun on 2016. 11. 8..
 */
@Component
public class OAuthAuthenticator {
    public static String host_url = "https://jbfg.openbankproject.com";
    public static String init_uri = "/oauth/initiate";
    public static String token_uri = "/oauth/token";
    private String consumerKey = "byjfav0qi53eqri25fkqz5ao5wp2bdkdgk3exi2d";
    private String consumerSecret = "xwtwzeolmwvh1dkhhlgwy0yftxumbku2yrskzktt";


    public String generateOauthHeader(String uri,
                                      String method,
                                      String callback,
                                      String token,
                                      String tokenSecret,
                                      String verifier,
                                      String[] additionalParameters) throws Exception {
        long timestamp = new Date().getTime() / 1000;

        String nonce = "" + Long.toString(timestamp).hashCode();

        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + nonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        // Note this is URL encoded twice

        if (token != null) {
            parameters.add("oauth_token=" + token);
        }
        if (verifier != null) {
            parameters.add("oauth_verifier=" + verifier);
        }
        if (callback != null) {
            // Note this is URL encoded twice
            parameters.add("oauth_callback=" + URLEncoder.encode(callback));
        }

        parameters.add("oauth_version=1.0");

        for (String additionalParameter : additionalParameters) {
            parameters.add(additionalParameter);
        }

        Collections.sort(parameters);

        StringBuffer parametersList = new StringBuffer();

        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }

        String signatureString =
                method + "&" +
                        URLEncoder.encode(host_url + uri) + "&" +
                        URLEncoder.encode(parametersList.toString());

        String signature = null;

        try {
            SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&"
                    + (tokenSecret == null ? "" : tokenSecret)
            ).getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());

            signature = Base64.encodeBase64String(rawHMAC);
        } catch (Exception e) {
            throw e;
        }

        String authorizationLine =
                "OAuth " +
                        "oauth_consumer_key=\"" + consumerKey + "\", " +
                        "oauth_nonce=\"" + nonce + "\", " +
                        "oauth_timestamp=\"" + timestamp + "\", " +
                        "oauth_signature_method=\"HMAC-SHA1\", " +
                        "oauth_signature=\"" + URLEncoder.encode(signature) + "\", " +
                        "oauth_version=\"1.0\"";
        if (token != null) {
            authorizationLine += ", oauth_token=\"" + token + "\"";
        }
        if (verifier != null) {
            authorizationLine += ", oauth_verifier=\"" + verifier + "\"";
        }
        if (callback != null) {
            authorizationLine += ", oauth_callback=\"" +
                    URLEncoder.encode(callback) + "\"";
        }

        return authorizationLine;
    }
}