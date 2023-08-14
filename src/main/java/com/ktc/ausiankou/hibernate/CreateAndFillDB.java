package com.ktc.ausiankou.hibernate;

public class CreateAndFillDB {
    public static void main(String[] args){

        ReCreateDBUtil.main(new String[]{});

        try{
            FillConstantsUtil.fill();
        //    FillSampesUtil.fill();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
