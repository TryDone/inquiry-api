package com.trydone.inquiry.service;

import com.trydone.inquiry.data.User;

import java.util.List;

public interface IUserService {

    User getByOpenId(String openId);

    List<User> getRelationUserByOpenId(String openId);

    User get(String id);

    boolean insert(User user);

    List<User> select(User user);

    boolean update(User user);

    boolean delete(String id);
}
