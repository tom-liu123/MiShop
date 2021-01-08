package com.itqf.service;

import com.itqf.entity.Types;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午3:42
 */
public interface TypesService {

    public List<Types> findAll ()throws SQLException;
}
