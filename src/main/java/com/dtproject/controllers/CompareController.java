package com.dtproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompareController {

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

}
