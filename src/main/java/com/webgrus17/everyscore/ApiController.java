package com.webgrus17.everyscore;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    @RequestMapping(value="/api/v1/start", method= RequestMethod.GET)
    @ResponseStatus(value= HttpStatus.OK)
    
}
