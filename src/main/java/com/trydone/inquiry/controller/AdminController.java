package com.trydone.inquiry.controller;

import com.trydone.inquiry.dao.AdminUserMapper;
import com.trydone.inquiry.data.AdminUser;
import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.data.SymptomExt;
import com.trydone.inquiry.service.ISymptomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "管理后台接口")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final static Log log = LogFactory.getLog(AdminController.class);

    @Autowired
    private ISymptomService symptomService;
    @Autowired
    private BBossESStarter bbossESStarter;
    @Autowired
    private AdminUserMapper adminUserMapper;

    @ApiOperation(value = "创建Elasticsearch映射")
    @GetMapping("/{index}/mapping")
    public String createMapping(@PathVariable String index) {

        ClientInterface clientUtil = bbossESStarter.getConfigRestClient("dsl/mapping.xml");
        try {
            boolean exist = clientUtil.existIndice(index);
            if (exist) {
                clientUtil.dropIndice(index);
            }
        } catch (ElasticSearchException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        clientUtil.createIndiceMapping(index, "mapping");
        String indexMapping = clientUtil.getIndice(index);

        return indexMapping;
    }

    @ApiOperation(value = "症状新增")
    @PostMapping("/symptom/insert")
    public boolean insert(@RequestBody Symptom symptom) {
        return symptomService.insert(symptom);
    }

    @ApiOperation(value = "症状选择新增")
    @PostMapping("/symptom/selectInsert/")
    public boolean selectInsert(@RequestBody SymptomExt symptomExt) {
        return symptomService.selectInsert(symptomExt);
    }

    @ApiOperation(value = "症状修改")
    @PutMapping("/symptom/update")
    public boolean update(@RequestBody Symptom symptom) {
        return symptomService.update(symptom);
    }

    @ApiOperation(value = "症状删除")
    @DeleteMapping("/symptom/delete")
    public boolean delete(String id) {
        return symptomService.delete(id);
    }

    @ApiOperation(value = "主键查询")
    @GetMapping("/symptom/get/{id}")
    public Symptom get(@PathVariable String id) {
        return symptomService.get(id);
    }

    @ApiOperation(value = "认证用户")
    @PostMapping(value = "/user/login")
    public Map<String, String> login(@RequestBody Map param) {
        Map<String, String> data = new HashMap<>();
        String token = "null";
        if ((!"".equals(param.get("userId")) && null != param.get("userId")) && (!"".equals(param.get("password")) && null != param.get("password"))) {
            AdminUser adminUser = adminUserMapper.selectByPrimaryKey(param.get("userId"));
            if (param.get("password").equals(adminUser.getPassword())) {
                token = adminUser.getPassword();
            }
        }
        data.put("token", token);
        return data;
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping(value = "/user/getInfo")
    public Map<String, Object> getUserInfo(String token) {
        Map<String, Object> data = new HashMap<>();
        data.put("userInfo", adminUserMapper.selectByPrimaryKey(token));
        return data;
    }

}
