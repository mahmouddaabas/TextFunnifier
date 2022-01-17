package com.funnifier.TextFunnifier;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Class to manage the Facebook API.
 * Currently allows posting a status.
 */

public class Facebook {

    private String FACEBOOK_TOKEN;

    /**
     * Function to ruturn the API Key stored in the application.properties file.
     * @return FACEBOOK_TOKEN
     */
    public String getApiKey() {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "application.properties";
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            FACEBOOK_TOKEN = appProps.getProperty("FACEBOOK_TOKEN");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return FACEBOOK_TOKEN;
    }

    /**
     * Function to post the text sent in the parameter to the facebook page.
     * @param text
     */
    public void postFacebookStatus(String text){
        String newText = text.replaceAll(" ", "%20");
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("https://graph.facebook.com/v12.0/104346012152345/feed?message="
                            +newText
                            +"&access_token=" + getApiKey())
                    .asString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
