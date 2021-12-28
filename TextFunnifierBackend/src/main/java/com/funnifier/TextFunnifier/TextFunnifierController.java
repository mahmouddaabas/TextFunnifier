package com.funnifier.TextFunnifier;

import org.springframework.web.bind.annotation.*;

/**
 * Controller class to recieve requests.
 */

@RestController
public class TextFunnifierController {

    private Pirate pi = new Pirate();

    /**
     * POST request that allows sending a JSON body to the server.
     * Once body is recieved send it to the pirateify function through the parameter.
     * @param text
     * @return text
     */
    @RequestMapping(value = "/pirateify", method = RequestMethod.POST)
    public String postText(@RequestBody String text){
        return pi.pirateify(text);
    }

}
