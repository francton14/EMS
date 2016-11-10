package com.margallo.database.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by franc on 11/10/2016.
 */
public interface GenericDao<T> {

    public List<T> all() throws SQLException;

    public List<T> all(String... filters);

    public String insert(T model);

    public T show(Long id);

    public String update(T model);

    public String delete(Long id);

    public String exists(Long id);

}
