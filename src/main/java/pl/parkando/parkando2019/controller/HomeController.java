package pl.parkando.parkando2019.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HomeController {

//    @RequestMapping("/")
//    public String home(){
//        return "To jest Parkando - aplikacja do rezerwowania miejsc parkingowych";
//    }

//    @GetMapping("/")
    @RequestMapping("/api")
    public String hello() {
        return "Hello, the time at the server is now !!!!!!!!!!!!!!!!!!!!!";
    }
}