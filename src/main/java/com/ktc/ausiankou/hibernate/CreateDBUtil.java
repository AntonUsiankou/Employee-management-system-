package com.ktc.ausiankou.hibernate;

import com.ktc.ausiankou.model.*;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class CreateDBUtil {

    public static void main (String[] args){
        SessionFactory factory = null;
        try{
            String xml = "templates/hibernate-create.cfg.xml";

            if(args.length > 0){
                xml = args[0];
            }

            Configuration configuration = new Configuration();
            configuration.configure(xml);

            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(Department.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Holiday.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Salary.class);
            configuration.addAnnotatedClass(Status.class);

            factory = configuration.buildSessionFactory();
        } finally {
            if (factory != null){
                factory.close();
            }
        }
    }
}
