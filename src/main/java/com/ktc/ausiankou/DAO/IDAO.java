package com.ktc.ausiankou.DAO;

import java.util.List;

public interface IDAO<T> {

    void add(T t);

    void remove(int id);

    void update(T t);

    T getById(int id);

    List<T> getList();

}
