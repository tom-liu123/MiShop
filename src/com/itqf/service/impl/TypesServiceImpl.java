package com.itqf.service.impl;

import com.itqf.dao.TypesDao;
import com.itqf.dao.impl.TypesDaoImpl;
import com.itqf.entity.Types;
import com.itqf.service.TypesService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午3:42
 */
public class TypesServiceImpl implements TypesService {
    TypesDao typesDao = new TypesDaoImpl();

    @Override
    public List<Types> findAll() throws SQLException {
        return typesDao.findAll();
    }
}
