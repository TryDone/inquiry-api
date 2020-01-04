package com.trydone.inquiry.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理后台接口")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final static Log log = LogFactory.getLog(AdminController.class);

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
}
