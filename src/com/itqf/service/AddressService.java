package com.itqf.service;

import com.itqf.entity.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午10:10
 */
public interface AddressService {

    public List<Address>  findAllAddress(int uid) throws SQLException;

}
