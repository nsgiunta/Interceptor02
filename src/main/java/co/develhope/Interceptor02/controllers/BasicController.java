package co.develhope.Interceptor02.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BasicController {

    public String welcome(){
        return "Welcome";
    }
}
