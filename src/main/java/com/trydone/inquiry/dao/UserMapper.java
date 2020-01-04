package com.trydone.inquiry.dao;

import com.trydone.inquiry.data.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    User getByOpenId(String opneId);

    List<User> getRelationUserByOpenId(String openId);
}
