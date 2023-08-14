package com.ktc.ausiankou.controller;

import com.ktc.ausiankou.DAO.AccountDAO;
import com.ktc.ausiankou.DAO.DepartmentDAO;
import com.ktc.ausiankou.DAO.EmployeeDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.ktc.ausiankou.Constants.*;

@Controller
public class DepartmentController extends AbstractController {

    @GetMapping("/department")
    public String root() {
        return "redirect:/department/list";
    }

    @GetMapping("/department/list")
    public String list(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {
        model = accountForJSP(login, model);

        model.addAttribute(DEPARTMENTS, new DepartmentDAO().getList());
        return "department/list";
    }

    @GetMapping("/department/view")
    public String view_() {
        return "redirect:/department/view";
    }

    @GetMapping("/department/view/")
    public String view(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login) {
        return "redirect:/department/view/" + new AccountDAO().getAccountByLogin(login).getEmployee().getDepartment().getId();
    }

    @GetMapping("/department/view/{id}")
    public String viewById(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                           @PathVariable(value = ID) int id, Model model) {
        model = accountForJSP(login, model);

        if (new DepartmentDAO().getById(id) == null) {
            model.addAttribute(ERROR, true);
            model.addAttribute("error_message", "Department with ID '" + id + "' not found");
        } else {
            model.addAttribute(EMPLOYEES, new EmployeeDAO().getListByDepartment(id));
        }
        return "employee/list";
    }
}
