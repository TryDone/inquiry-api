package com.trydone.inquiry.service.impl;

import com.trydone.inquiry.dao.UserMapper;
import com.trydone.inquiry.data.User;
import com.trydone.inquiry.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public int insert(User user) {
        return userMapper.insertSelective(user);
    }

    public List<User> select(User user) {
        return userMapper.select(user);
    }

    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public int delate(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
