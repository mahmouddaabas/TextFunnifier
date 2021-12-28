package com.funnifier.TextFunnifier;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private FunnifyText f = new FunnifyText();

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String postText(@RequestBody String text){
        f.funnifyText(text);
        return text;
    }

}
