package com.trydone.inquiry.controller;

import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.data.User;
import com.trydone.inquiry.service.ISymptomService;
import com.trydone.inquiry.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "小程序接口")
@RestController
@RequestMapping("/api/v1/mini")
public class MiniController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ISymptomService symptomService;

    @Autowired
    private BBossESStarter bbossESStarter;

    @ApiOperation(value = "根据微信小程序用户标识查询用户信息")
    @GetMapping("/user/getByOpenId/{openId}")
    public User getUserByOpenId(@PathVariable String openId) {
        return userService.getByOpenId(openId);
    }

    @ApiOperation(value = "根据微信小程序用户标识查询家庭成员")
    @GetMapping("/user/getRelationByOpenId/{openId}")
    public List<User> getRelationUserByOpenId(@PathVariable String openId) {
        return userService.getRelationUserByOpenId(openId);
    }

    @ApiOperation(value = "根据id查询家庭成员信息")
    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable String id) {
        return userService.get(id);
    }

    @ApiOperation(value = "增加家庭成员信息")
    @PostMapping("/user/insert")
    public boolean insertUser(@RequestBody User user) {
        return userService.insert(user);
    }

    @ApiOperation(value = "家庭成员信息条件查询")
    @PostMapping("/user/select")
    public List<User> selectUser(@RequestBody User user) {
        return userService.select(user);
    }

    @ApiOperation(value = "家庭成员信息修改")
    @PutMapping("/user/update")
    public boolean updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @ApiOperation(value = "家庭成员信息删除")
    @DeleteMapping("/user/delete/{id}")
    public boolean deleteUser(@PathVariable String id) {
        return userService.delete(id);
    }

    @ApiOperation(value = "搜索症状")
    @GetMapping("/symptom/search")
    public List<Symptom> search(@RequestParam(name = "content", required = false) String content) {
        List<Symptom> symptomList = new ArrayList<>();
        ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("dsl/search.xml");
        if (!"".equals(content) && null != content) {
            Map<String, String> params = new HashMap<>();
            params.put("content", content);
            symptomList = clientUtil.searchList("inquiry/_search", "search", params, Symptom.class).getDatas();
        } else {
            symptomList = clientUtil.searchList("inquiry/_search", Symptom.class).getDatas();
        }
        return symptomList;
    }

    @ApiOperation(value = "根据id查询症状信息")
    @GetMapping("/symptom/get/{id}")
    public Symptom getSymptom(@PathVariable String id) {
        return symptomService.get(id);
    }

    @ApiOperation(value = "查询根据父症状id查询子症状，查询一级症状是id值为-1")
    @GetMapping("/symptom/query/{id}")
    public List<Symptom> querySymptom(@PathVariable String id) {
        return symptomService.querySymptom(id);
    }

    @ApiOperation(value = "条件查询症状信息")
    @GetMapping("/symptom/query/{id}")
    public List<Symptom> querySymptom(@RequestBody Symptom symptom) {
        return symptomService.select(symptom);
    }
}
