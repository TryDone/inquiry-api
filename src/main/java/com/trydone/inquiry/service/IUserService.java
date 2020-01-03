package com.trydone.inquiry.service;

import com.trydone.inquiry.data.User;

import java.util.List;

public interface IUserService {

    int insert(User user);

    List<User> select(User user);

    int update(User user);

    int delate(String id);
}
