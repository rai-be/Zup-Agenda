    package com.agenda.controller;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/")
    public class ControllerTest {
        @GetMapping
        public String healthCheck(){
            return "test de aplicação";
        }
    }
