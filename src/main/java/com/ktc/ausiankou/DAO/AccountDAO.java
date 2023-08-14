package com.ktc.ausiankou.DAO;

import com.ktc.ausiankou.hibernate.SessionUtil;
import com.ktc.ausiankou.model.Account;
import com.ktc.ausiankou.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AccountDAO extends SessionUtil implements IDAO<Account> {

    private Session session;

    @Override
    public void add(Account account) {
        openTransactionSession();

        session = getSession();
        session.save(account);

        closeTransactionSession();
    }

    @Override
    public void remove(int id) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("DELETE from Account WHERE id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void update(Account account) {
        openTransactionSession();

        session = getSession();
        session.update(account);

        closeTransactionSession();
    }

    @Override
    public Account getById(int id) {
        openTransactionSession();

        session = getSession();
        Account account = session.get(Account.class, id);

        closeTransactionSession();

        return account;
    }

    @Override
    public List<Account> getList() {
        openTransactionSession();

        session = getSession();

        List<Account> list = session.createNativeQuery("SELECT * from Account", Account.class).list();

        closeTransactionSession();

        return list;
    }

    public Account getAccountByEmployee(Employee employee) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("SELECT * from Account WHERE employee_id = ?1", Account.class);
        query.setParameter(1, employee.getId());
        List<Account> list = query.getResultList();

        closeTransactionSession();

        return list.size() == 0 ? null : list.get(0);
    }

    public Account getAccountByLogin(String login) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("SELECT * from Account WHERE login = ?1", Account.class);
        query.setParameter(1, login);
        List<Account> list = query.getResultList();

        closeTransactionSession();

        return list.size() == 0 ? null : list.get(0);
    }

    public void removeByEmployeeId(int employeeId) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("DELETE from Account WHERE employee_id = ?1");
        query.setParameter(1, employeeId);
        query.executeUpdate();

        closeTransactionSession();
    }

    public boolean loginIsExist(String login) {
        return getAccountByLogin(login) != null;
    }
}
