package com.projet.mytherapy.controllers;

import org.springframework.stereotype.Controller;;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class AccueilController {

    @GetMapping
    public String accueil(Model model) {
        return "accueil";
    }
}
