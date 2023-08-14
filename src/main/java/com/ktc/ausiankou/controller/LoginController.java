package com.ktc.ausiankou.controller;

import com.ktc.ausiankou.DAO.AccountDAO;
import com.ktc.ausiankou.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ktc.ausiankou.Constants.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = ERROR, required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute(ERROR, true);
        }
        return LOGIN;
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute Account acc, HttpServletResponse resp) {
        Account account = new AccountDAO().getAccountByLogin(acc.getLogin());

        if (account != null && account.getPass().equals(acc.getPass())) {
            Cookie cookie = new Cookie(LOGIN, account.getLogin());
            cookie.setPath(SLASH);
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
            return "redirect:/";
        } else {
            return "redirect:/login?error=" + INVALID_LOGIN_OR_PASSWORD;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(NO_SPACE);
                cookie.setPath(SLASH);
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        return "redirect:/";
    }
}
