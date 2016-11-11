package com.margallo.database.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by franc on 11/10/2016.
 */
public interface GenericDao<T> {

    public List<T> all() throws SQLException;

    public void insert(T model) throws SQLException;

    public T show(long id) throws SQLException;

    public void update(T model) throws SQLException;

    public void delete(long id) throws SQLException;

    public boolean exists(long id) throws SQLException;

}
