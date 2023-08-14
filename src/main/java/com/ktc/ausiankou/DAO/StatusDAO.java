package com.ktc.ausiankou.DAO;

import com.ktc.ausiankou.hibernate.SessionUtil;
import com.ktc.ausiankou.model.Status;
import org.hibernate.Session;

import java.util.List;

import static com.ktc.ausiankou.Constants.NO_METHOD;

public class StatusDAO extends SessionUtil implements IDAO<Status> {

    private Session session;
    @Override
    public void add(Status status) {
        openTransactionSession();

        session = getSession();
        session.save(status);

        closeTransactionSession();
    }

    @Override
    public void remove(int id) {
        throw new IllegalArgumentException(NO_METHOD);
    }

    @Override
    public void update(Status status) {
        throw new IllegalArgumentException(NO_METHOD);
    }

    @Override
    public Status getById(int id) {
        openTransactionSession();

        session = getSession();
        Status status = session.get(Status.class, id);

        closeTransactionSession();
        return status;
    }

    @Override
    public List<Status> getList() {
        openTransactionSession();

        session = getSession();

        List<Status> list = session.createNativeQuery("SELECT * from Status", Status.class).list();

        closeTransactionSession();
        return list;
    }

}
