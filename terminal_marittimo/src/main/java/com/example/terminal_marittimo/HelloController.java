package com.example.terminal_marittimo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.terminal_marittimo.Classi.User;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name,
                        @RequestParam(name = "greeting", defaultValue = "Hello") String greeting) {
        return greeting + ", " + name + "!";
    }

    @GetMapping("/user")
    @ResponseBody
    public User getUser() {
        return new User("Mario", 18);
    }
}
