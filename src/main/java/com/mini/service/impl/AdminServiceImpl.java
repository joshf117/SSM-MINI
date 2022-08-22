package com.mini.service.impl;

import com.mini.mapper.AdminMapper;
import com.mini.pojo.Admin;
import com.mini.pojo.AdminExample;
import com.mini.service.AdminService;
import com.mini.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {
        //select admin information based on parameters given
        AdminExample example = new AdminExample();
        example.createCriteria().andANameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(example);
        //compare the result
        if (list.size()>0){
            Admin admin = list.get(0);
            String md5pwd = MD5Util.getMD5(pwd);
            if(md5pwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
