package com.ktc.ausiankou.hibernate;

import com.ktc.ausiankou.DAO.AccountDAO;
import com.ktc.ausiankou.DAO.DepartmentDAO;
import com.ktc.ausiankou.DAO.EmployeeDAO;
import com.ktc.ausiankou.model.Account;

public class TestHibernateUtil {
    public static void main(String[] args) {
        try{
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccountByLogin("employee1");
            System.out.println(account);

            DepartmentDAO departmentDAO = new DepartmentDAO();
            departmentDAO.getList().stream().forEach(System.out::println);
            System.out.println();

            EmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.getListByDepartment(departmentDAO.getById(3).getId()).stream()
                    .forEach(System.out::println);
            System.out.println();
        }finally {
            HibernateUtil.shutdown();
        }
    }
}
