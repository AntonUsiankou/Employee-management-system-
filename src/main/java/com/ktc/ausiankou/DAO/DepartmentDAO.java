package com.ktc.ausiankou.DAO;

import com.ktc.ausiankou.hibernate.SessionUtil;
import com.ktc.ausiankou.model.Department;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.ktc.ausiankou.Constants.NO_METHOD;
import static javax.swing.UIManager.get;

public class DepartmentDAO extends SessionUtil implements IDAO<Department> {

    private Session session;
    @Override
    public void add(Department department) {
        openTransactionSession();

        session = getSession();
        session.save(department);

        closeTransactionSession();
    }

    @Override
    public void remove(int id) {
        throw new IllegalStateException(NO_METHOD);
    }

    @Override
    public void update(Department department) {
        throw new IllegalStateException(NO_METHOD);
    }

    @Override
    public Department getById(int id) {
        openTransactionSession();

        session = getSession();
        Department department = session.get(Department.class, id);

        closeTransactionSession();
        return department;
    }

    @Override
    public List<Department> getList() {
        openTransactionSession();

        session = getSession();
        List<Department> list = session.createNativeQuery("SELECT * FROM Department", Department.class).list();

        closeTransactionSession();

        return list;
    }

    public Department getByName(String name){
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("SELECT * FROM Department WHERE name = ?1", Department.class);
        query.setParameter(1, name);
        List<Department> list = query.getResultList();

        closeTransactionSession();

        return list.size() == 0 ? null : list.get(0);
    }
}
