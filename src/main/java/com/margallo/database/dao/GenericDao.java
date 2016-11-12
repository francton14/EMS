package com.margallo.database.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by franc on 11/10/2016.
 */
public interface GenericDao<T> {

    public List<T> all() throws Exception;

    public void insert(T model) throws Exception;

    public T show(long id) throws Exception;

    public void update(T model) throws Exception;

    public void delete(long id) throws Exception;

}
