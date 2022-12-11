package com.API.springboot.restapi.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class HelloWorldResource {

    @RequestMapping("/hello-world")
    public String helloWorld(){
        return "Hello World!";
    }
}
