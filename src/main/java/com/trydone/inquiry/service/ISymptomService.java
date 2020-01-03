package com.trydone.inquiry.service;

import com.trydone.inquiry.data.Symptom;

import java.util.List;

public interface ISymptomService {

    int insert(Symptom symptom);

    List<Symptom> select(Symptom symptom);

    int update(Symptom symptom);

    int delate(String id);
}
