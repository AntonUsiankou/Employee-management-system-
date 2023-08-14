package com.ktc.ausiankou.hibernate;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import static com.ktc.ausiankou.Constants.INITIAL_SESSION_FACTORY_CREATION_FAILED;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println(INITIAL_SESSION_FACTORY_CREATION_FAILED + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
