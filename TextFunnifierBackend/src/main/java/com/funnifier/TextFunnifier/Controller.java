package com.funnifier.TextFunnifier;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private Pirate pi = new Pirate();

    @RequestMapping(value = "/pirateify", method = RequestMethod.POST)
    public String postText(@RequestBody String text){
        pi.pirateify(text);
        return text;
    }

}
