package com.trydone.inquiry.service;

import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.data.SymptomExt;

import java.util.List;
import java.util.Map;

public interface ISymptomService {

    boolean insert(Symptom symptom);

    boolean selectInsert(SymptomExt symptomExt);

    boolean update(Symptom symptom);

    boolean delete(String id);

    Symptom get(String id);

    List<Symptom> querySymptom(String id);

    List<Map<String,Object>> selectCommon();
}
