package com.trydone.inquiry.service.impl;

import com.trydone.inquiry.dao.UserMapper;
import com.trydone.inquiry.data.User;
import com.trydone.inquiry.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User getByOpenId(String openId) {
        return userMapper.getByOpenId(openId);
    }

    public List<User> getRelationUserByOpenId(String openId) {
        return userMapper.getRelationUserByOpenId(openId);
    }
    public User get(String id){
       return userMapper.selectByPrimaryKey(id);
    }
    public boolean insert(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        userMapper.insertSelective(user);
        return true;
    }

    public List<User> select(User user) {
        return userMapper.select(user);
    }

    public boolean update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return true;
    }

    public boolean delete(String id) {
        userMapper.deleteByPrimaryKey(id);
        return true;
    }
}
