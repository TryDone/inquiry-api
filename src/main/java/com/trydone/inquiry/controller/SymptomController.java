package com.trydone.inquiry.controller;

import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.service.ISymptomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "症状接口")
@RestController
@RequestMapping("/api/v1/symptom")
public class SymptomController {

    @Autowired
    private ISymptomService symptomService;

    @ApiOperation(value = "症状新增")
    @PostMapping("/insert")
    public int insert(@RequestBody Symptom symptom) {
        return symptomService.insert(symptom);
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/select")
    public List<Symptom> select(@RequestBody Symptom symptom) {
        return symptomService.select(symptom);
    }

    @ApiOperation(value = "症状修改")
    @PutMapping("/update")
    public int update(@RequestBody Symptom symptom) {
        return symptomService.update(symptom);
    }

    @ApiOperation(value = "症状删除")
    @DeleteMapping("/delate")
    public int insert(String id) {
        return symptomService.delate(id);
    }

    @ApiOperation(value = "主键查询")
    @GetMapping("/get")
    public int get(String id) {
        return symptomService.delate(id);
    }
}
