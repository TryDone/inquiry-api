package com.trydone.inquiry.controller;

import com.trydone.inquiry.data.User;
import com.trydone.inquiry.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户新增")
    @PostMapping("/insert")
    public int insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/select")
    public List<User> select(@RequestBody User user) {
        return userService.select(user);
    }

    @ApiOperation(value = "用户修改")
    @PutMapping("/update")
    public int update(@RequestBody User user) {
        return userService.update(user);
    }

    @ApiOperation(value = "用户删除")
    @DeleteMapping("/delate")
    public int insert(String id) {
        return userService.delate(id);
    }

    @ApiOperation(value = "主键查询")
    @GetMapping("/get")
    public int get(String id) {
        return userService.delate(id);
    }
}
