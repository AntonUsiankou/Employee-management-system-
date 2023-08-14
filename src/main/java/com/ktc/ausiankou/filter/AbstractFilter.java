package com.ktc.ausiankou.filter;

import com.ktc.ausiankou.DAO.AccountDAO;
import com.ktc.ausiankou.model.Account;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ktc.ausiankou.Constants.LOGIN;

public abstract class AbstractFilter extends HttpFilter {

    protected abstract boolean isLevelOk(String login, String path);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{
        boolean isLogged = false;
        Account account = null;
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(LOGIN)){
                    account = new AccountDAO().getAccountByLogin(cookie.getValue());
                    if(account != null){
                        isLogged = true;
                        break;
                    }
                }
            }
        }
        if (isLogged && isLevelOk(account.getLogin(), req.getRequestURI())){
            chain.doFilter(req, res);
        } else {
            res.setStatus(40);
            //req.getRequestDispatcher("/login").forward(req, res);
            res.sendRedirect("/login");
        }
    }

}
