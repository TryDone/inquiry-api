package com.trydone.inquiry.controller;

import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.data.SymptomExt;
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
    public boolean insert(@RequestBody Symptom symptom) {
        return symptomService.insert(symptom);
    }

    @ApiOperation(value = "症状选择新增")
    @PostMapping("/selectInsert/")
    public boolean selectInsert(@RequestBody SymptomExt symptomExt) {
        return symptomService.selectInsert(symptomExt);
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/select")
    public List<Symptom> select(@RequestBody Symptom symptom) {
        return symptomService.select(symptom);
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
