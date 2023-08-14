package com.ktc.ausiankou.hibernate;

import com.ktc.ausiankou.DAO.*;
import com.ktc.ausiankou.model.*;

import java.sql.Date;

import static com.ktc.ausiankou.Constants.*;

public class FillConstantsUtil {
    public static void main(String[] args) {
        try{
            fill();
        }finally{
            HibernateUtil.shutdown();
        }
    }

    public static void fill(){
        IDAO dao;

        dao = new StatusDAO();
        dao.add(new Status(PENDING));
        dao.add(new Status(ACCEPTED));
        dao.add(new Status(DENIED));

        dao = new RoleDAO();
        dao.add(new Role("manager"));
        dao.add(new Role("lead"));
        dao.add(new Role("regular"));

        dao = new DepartmentDAO();
        dao.add(new Department("Management"));
        dao.add(new Department("Finance"));
        dao.add(new Department("IT"));

        //1
        Salary salary = new Salary(1);
        new SalaryDAO().add(salary);

        Employee employee = new Employee();
        employee.setBirthday(Date.valueOf("1991-01-01"));
        employee.setName("admin");
        employee.setRole(new RoleDAO().getById(1));
        employee.setDepartment(new DepartmentDAO().getById(1));
        employee.setSalary(salary);
        new EmployeeDAO().add(employee);

        Account account = new Account();
        account.setEmployee(employee);
        account.setLogin("admin");
        account.setPass("admin");
        new AccountDAO().add(account);
    }
}
