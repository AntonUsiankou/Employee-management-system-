package com.ktc.ausiankou.hibernate;

public class ReCreateDBUtil {
    public static void main(String[] args) {
        CreateDBUtil.main(new String[]{"templates/hibernate-create_drop.cfg.xml"});
        CreateDBUtil.main(new String[]{"templates/hibernate-create.cfg.xml"});
    }
}
