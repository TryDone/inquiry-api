package com.trydone.inquiry.service;

import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.data.SymptomExt;

import java.util.List;

public interface ISymptomService {

    boolean insert(Symptom symptom);

    boolean selectInsert(SymptomExt symptomExt);

    List<Symptom> select(Symptom symptom);

    boolean update(Symptom symptom);

    boolean delete(String id);

    Symptom get(String id);
}
