package com.ktc.ausiankou.filter;

import com.ktc.ausiankou.Security;

import javax.servlet.annotation.WebFilter;

@WebFilter("/department/*")
public class DepartmentFilter extends AbstractFilter{

    private static final String DEPARTMENT_VIEW = "/department/view/";
    private static final int DEPARTMENT_VIEW_LENGTH = DEPARTMENT_VIEW.length();


    @Override
    protected boolean isLevelOk(String login, String path) {
        boolean isOK = false;

        if(path.contains(DEPARTMENT_VIEW) && Security.getRoleId(login) == Security.RoleId.LEAD){
            if((path.length() > DEPARTMENT_VIEW_LENGTH)){
                isOK = Security.checkLoginToDepartmentId(login, Integer.parseInt(path.substring(DEPARTMENT_VIEW_LENGTH)));
            }else {
                isOK = true;
            }
        }
        return isOK || Security.getRoleId(login) == Security.RoleId.MANAGER;
    }
}
