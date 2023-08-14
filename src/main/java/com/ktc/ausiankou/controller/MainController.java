package com.ktc.ausiankou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import static com.ktc.ausiankou.Constants.LOGIN;
import static com.ktc.ausiankou.Constants.NO_SPACE;

@Controller
public class MainController extends AbstractController{

    @GetMapping("/")
    public String index(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model){

        model = accountForJSP(login, model);

        return "index";
    }
}
