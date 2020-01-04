package com.trydone.inquiry.dao;

import com.trydone.inquiry.data.Symptom;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SymptomMapper extends Mapper<Symptom> {
    List<Symptom> querySymptom(String id);
}
