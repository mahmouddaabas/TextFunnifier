package com.funnifier.TextFunnifier;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

/**
 * Class to pirateify text.
 */

public class Pirate {

    private Facebook fa;
    private String message;

    /**
     * Constructor to create the facebook object.
     */
    public Pirate(){
        fa = new Facebook();
    }

    /**
     * Function to send the text to the pirate api to pirateify it.
     * After pirateifying the text checks for permission to post on the facebook page.
     * @param text
     * @return message
     */
    public String pirateify(String text){
        //Extract the message and publish permission from the JSON that was sent with the POST Request.
        try {
            JsonObject jobj = new Gson().fromJson(text, JsonObject.class);
            message = jobj.get("message").getAsString();
            boolean publish = jobj.get("publish").getAsBoolean();

            //Replace spaces from the message with %20
            String newMessage = message.replaceAll(" ", "%20");

            //Send message to Pirate Talk API
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("https://pirate.monkeyness.com/api/translate?english="+newMessage)
                    .asString();

            //Checks the permission to publish to the facebook page.
            if(publish == true){
                fa.postFacebookStatus(response.getBody());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //Return message as String
        return message;
    }
}
