package com.trydone.inquiry.controller;

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

@Api(tags = "管理后台接口")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final static Log log = LogFactory.getLog(AdminController.class);

    @Autowired
    private ISymptomService symptomService;
    @Autowired
    private BBossESStarter bbossESStarter;

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
    @PostMapping("/insert")
    public boolean insert(@RequestBody Symptom symptom) {
        return symptomService.insert(symptom);
    }

    @ApiOperation(value = "症状选择新增")
    @PostMapping("/selectInsert/")
    public boolean selectInsert(@RequestBody SymptomExt symptomExt) {
        return symptomService.selectInsert(symptomExt);
    }

    @ApiOperation(value = "症状修改")
    @PutMapping("/update")
    public boolean update(@RequestBody Symptom symptom) {
        return symptomService.update(symptom);
    }

    @ApiOperation(value = "症状删除")
    @DeleteMapping("/delete")
    public boolean delete(String id) {
        return symptomService.delete(id);
    }

    @ApiOperation(value = "主键查询")
    @GetMapping("/get/{id}")
    public Symptom get(@PathVariable String id) {
        return symptomService.get(id);
    }
}
