package com.funnifier.TextFunnifier;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class Pirate {

    private Facebook fa;

    //Create a facebook object once the class is constructed.
    public Pirate(){
        fa = new Facebook();
    }

    //Send the text recieved through the parameter to the API.
    public void pirateify(String text){
        //Extract the message and publish permission from the JSON that was sent with the POST Request.
        try {
            JsonObject jobj = new Gson().fromJson(text, JsonObject.class);
            String message = jobj.get("message").getAsString();
            boolean publish = jobj.get("publish").getAsBoolean();
            //System.out.println(message);
            //System.out.println(publish);

            //Replace spaces from the message with %20
            String newMessage = message.replaceAll(" ", "%20");
            //System.out.println(newMessage);

            //Send message to Pirate Talk API
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("https://pirate.monkeyness.com/api/translate?english="+newMessage)
                    .asString();
            //System.out.println(response.getBody());

            //Checks the permission to publish to the facebook page.
            if(publish == true){
                fa.postFacebookStatus(response.getBody());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
