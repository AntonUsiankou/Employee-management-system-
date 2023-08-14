package com.ktc.ausiankou.controller;

import com.ktc.ausiankou.DAO.AccountDAO;
import com.ktc.ausiankou.DAO.HolidayDAO;
import com.ktc.ausiankou.DAO.StatusDAO;
import com.ktc.ausiankou.Security;
import com.ktc.ausiankou.Utils;
import com.ktc.ausiankou.model.Holiday;
import com.ktc.ausiankou.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.ktc.ausiankou.Constants.*;

@Controller
public class HolidayController extends AbstractController {

    private static final AccountDAO ACCOUNT_DAO = new AccountDAO();
    private static final HolidayDAO HOLIDAY_DAO = new HolidayDAO();
    private static final StatusDAO STATUS_DAO = new StatusDAO();

    @GetMapping("/holiday")
    public String root() {
        return "redirect:/holiday/list";
    }

    @GetMapping("/holiday/list")
    public String list(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {
        model = accountForJSP(login, model);

        model.addAttribute("list", true);

        switch (Security.getRoleId(login)) {
            case LEAD:
                model.addAttribute(HOLIDAYS, HOLIDAY_DAO.getListByDepartment(ACCOUNT_DAO.getAccountByLogin(login).getEmployee().getDepartment()));
                break;
            case MANAGER:
                model.addAttribute(HOLIDAYS, HOLIDAY_DAO.getList());
                break;
            default:
                return "redirect:/holiday/employee/" + ACCOUNT_DAO.getAccountByLogin(login).getEmployee().getId();
        }
        return "holiday/list";
    }

    @GetMapping("/holiday/employee")
    public String employee_() {
        return "redirect:/holiday/list";
    }

    @GetMapping("/holiday/view")
    public String view_() {
        return "redirect:/holiday/view/";
    }

    @GetMapping("/holiday/view/")
    public String view(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login) {
        return "redirect:/holiday/employee/" + ACCOUNT_DAO.getAccountByLogin(login).getEmployee().getId();
    }

    @GetMapping("/holiday/view/{id}")
    public String viewById(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                           @PathVariable(value = ID) int id, Model model) {
        model = accountForJSP(login, model);

        model.addAttribute(HOLIDAY, HOLIDAY_DAO.getById(id));
        return "holiday/view";
    }

    @GetMapping("/holiday/add")
    public String getAdd(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login, Model model) {
        model = accountForJSP(login, model);
        return "holiday/add";
    }

    @PostMapping("/holiday/add")
    public String addEmployee(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                              @RequestParam(value = DATE_FROM) String dateFrom,
                              @RequestParam(value = DATE_TO) String dateTo) {

        boolean isError = false;
        StringBuffer errorMsg = new StringBuffer();
        Holiday holiday = new Holiday();

        try {
            isError = isWrongDateToOrDateFrom(dateFrom, dateTo, errorMsg);

            if (!isError) {
                holiday.setDateFrom(Utils.getDate(dateFrom));
                holiday.setDateTo(Utils.getDate(dateTo));
                holiday.setStatus(STATUS_DAO.getById(1));
                holiday.setEmployee(ACCOUNT_DAO.getAccountByLogin(login).getEmployee());
            }
        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append(NEXT_STRING);
        }

        if (!isError) {
            HOLIDAY_DAO.add(holiday);
            return "redirect:/holiday/list";
        } else {
            return "redirect:/holiday/add?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/holiday/edit/{id}")
    public String getEdit(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                          @PathVariable(value = ID) int id, Model model) {
        model = accountForJSP(login, model);

        model.addAttribute(HOLIDAY, HOLIDAY_DAO.getById(id));

        return "/holiday/edit";
    }

    private static boolean isWrongDateToOrDateFrom(String dateFrom, String dateTo, StringBuffer errorMsg) {
        boolean isWrong = false;
        if (Utils.dateIsMoreThanToday(dateFrom)) {
            isWrong = true;
            errorMsg.append(DATE_FROM_BEFORE_TODAY);
        }

        if (Utils.dateTwoIsMoreThanDateOne(dateFrom, dateTo)) {
            isWrong = true;
            if (errorMsg.length() == 0) {
                errorMsg.append(SPACE);
            }
            errorMsg.append(DATE_TO_BEFORE_FROM);
        }
        return isWrong;
    }

    @PostMapping("/holiday/edit")
    public String editEmployee(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                               @RequestParam(value = DATE_FROM) String dateFrom,
                               @RequestParam(value = DATE_TO) String dateTo,
                               @RequestParam(value = HOLIDAY_ID) String id) {
        int holidayId = Integer.parseInt(id);

        Holiday holiday = null;
        boolean isError = false;
        StringBuffer errorMsg = new StringBuffer();

        try {
            isError = isWrongDateToOrDateFrom(dateFrom, dateTo, errorMsg);

            holiday = HOLIDAY_DAO.getById(holidayId);
            holiday.setDateFrom(Utils.getDate(dateFrom));
            holiday.setDateTo(Utils.getDate(dateTo));
        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append(NEXT_STRING);
        }

        if (!isError) {
            HOLIDAY_DAO.update(holiday);

            return "redirect:/holiday/view/" + holiday.getId();
        } else {
            return "redirect:/holiday/edit/" + holiday.getId() + "?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/holiday/delete")
    public String getDelete() {
        return "error";
    }

    @PostMapping("/holiday/accepted")
    public String accepted(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                           @RequestParam(value = HOLIDAY_ID) String id) {
        return changeStatus(Integer.parseInt(id), new StatusDAO().getById(2), login);
    }

    private static String changeStatus(int id, Status status, String login) {

        if (!(Security.getRoleId(login) == Security.RoleId.MANAGER || Security.getRoleId(login) == Security.RoleId.LEAD))
            return "redirect:/login";

        Holiday holiday = HOLIDAY_DAO.getById(id);
        holiday.setStatus(status);

        HOLIDAY_DAO.update(holiday);

        return "redirect:/holiday/view/" + id;
    }

    @PostMapping("/holiday/denied")
    public String denied(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                         @RequestParam(value = HOLIDAY_ID) String id) {

        return changeStatus(Integer.parseInt(id), new StatusDAO().getById(3), login);
    }

    @PostMapping("/holiday/delete")
    public String add(@CookieValue(value = LOGIN, defaultValue = NO_SPACE) String login,
                      @RequestParam(value = HOLIDAY_ID) String id) {
        int holidayId = Integer.parseInt(id);

        if (!Security.checkLoginToEmployeeId(login, holidayId)) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        try {
            HOLIDAY_DAO.remove(HOLIDAY_DAO.getById(holidayId).getId());
        } catch (Exception e) {
            isError = true;
            errorMsg.append(e.getMessage()).append(NEXT_STRING);
        }

        if (!isError) {
            return "redirect:/holiday/list/";
        } else {
            return "redirect:/holiday/edit/" + holidayId + "?error=" + errorMsg.toString();
        }
    }


}
