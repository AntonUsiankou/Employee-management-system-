package com.ktc.ausiankou.DAO;

import com.ktc.ausiankou.hibernate.SessionUtil;
import com.ktc.ausiankou.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAO extends SessionUtil implements IDAO<Employee> {

    private Session session;

    @Override
    public void add(Employee employee) {
        openTransactionSession();

        session = getSession();
        session.save(employee);

        closeTransactionSession();
    }

    @Override
    public void remove(int id) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("DELETE from Employee WHERE id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void update(Employee employee) {
        openTransactionSession();

        session = getSession();

        session.update(employee);

        closeTransactionSession();
    }

    @Override
    public Employee getById(int id) {
        openTransactionSession();

        session = getSession();
        Employee employee = session.get(Employee.class, id);

        closeTransactionSession();
        return employee;
    }

    @Override
    public List<Employee> getList() {
        openTransactionSession();

        session = getSession();

        List<Employee> list = session.createNativeQuery("SELECT * from Employee", Employee.class).list();

        closeTransactionSession();

        return list;
    }

    public List<Employee> getListByDepartment(int id) {
        openTransactionSession();

        String sql = "SELECT * FROM Employee WHERE department_id = ?1";

        session = getSession();

        Query query = session.createNativeQuery(sql, Employee.class);
        query.setParameter(1, id);

        List<Employee> list = query.getResultList();

        closeTransactionSession();

        return list;
    }

    public int getCountByRole(int id) {
        openTransactionSession();

        String sql = "SELECT * FROM Employee WHERE role_id = ?1";
        session = getSession();

        Query query = session.createNativeQuery(sql, Employee.class);
        query.setParameter(1, id);

        List<Employee> list = query.getResultList();

        closeTransactionSession();

        return list.size();
    }


}
