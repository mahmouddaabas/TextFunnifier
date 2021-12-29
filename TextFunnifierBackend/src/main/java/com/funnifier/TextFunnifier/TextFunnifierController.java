package com.funnifier.TextFunnifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
     * @return objectNode
     */
    @RequestMapping(value = "/pirateify", method = RequestMethod.POST)
    public ObjectNode postPirate(@RequestBody String text){
        //Pirateify the sent text and store in pirateText variable.
        String pirateText = pi.pirateify(text);

        //Construct a JSON Object.
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        //Put the pirate text in the JSON object and return it.
        objectNode.put("message", pirateText);
        return objectNode;
    }

}
