package com.pruebasaberpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/benefits")
public class BenefitController {
    @GetMapping public String benefits() { return "benefits"; }
}
