package com.Nivesh.RestfulWebServicesPart1.HelloSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class HelloSpringController {

    @GetMapping( path = "/welcome-spring")
    public String helloWorld()
    {
        return "Welcome to spring boot";
    }


}
