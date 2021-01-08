package com.itqf.service.impl;

import com.itqf.dao.AddressDao;
import com.itqf.dao.impl.AddressDaoImpl;
import com.itqf.entity.Address;
import com.itqf.service.AddressService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午10:11
 */
public class AddressServiceImpl implements AddressService {

    AddressDao addressDao = new AddressDaoImpl();//以后--->解耦 Spring

    @Override
    public List<Address> findAllAddress(int uid) throws SQLException {

        return addressDao.findAllAddress(uid);
    }
}
