package com.trydone.inquiry.service.impl;

import com.trydone.inquiry.dao.SymptomMapper;
import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.service.ISymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("symptomService")
public class SymptomServiceImpl implements ISymptomService {

    @Autowired
    private SymptomMapper symptomMapper;

    public int insert(Symptom symptom) {
        return symptomMapper.insertSelective(symptom);
    }

    public List<Symptom> select(Symptom symptom) {
        return symptomMapper.select(symptom);
    }

    public int update(Symptom symptom) {
        return symptomMapper.updateByPrimaryKeySelective(symptom);
    }

    public int delate(String id) {
        return symptomMapper.deleteByPrimaryKey(id);
    }
}
