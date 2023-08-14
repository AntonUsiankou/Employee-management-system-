package com.ktc.ausiankou.controller;

import com.ktc.ausiankou.DAO.AccountDAO;
import org.springframework.ui.Model;

public class AbstractController {

    protected static Model accountForJSP(String login, Model model){
        if(!login.isEmpty()){
            model.addAttribute("currAccount", new AccountDAO().getAccountByLogin(login));
        }

        return model;
    }

}
