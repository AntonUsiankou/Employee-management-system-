package com.ktc.ausiankou.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter("/employee/*")
public class EmployeeFilter extends AbstractFilter {

    @Override
    protected boolean isLevelOk(String login, String path) {
        return true;
    }
}
