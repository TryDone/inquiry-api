package com.trydone.inquiry.service.impl;

import com.trydone.inquiry.dao.SymptomExtMapper;
import com.trydone.inquiry.dao.SymptomMapper;
import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.data.SymptomExt;
import com.trydone.inquiry.service.ISymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("symptomService")
@Transactional
public class SymptomServiceImpl implements ISymptomService {

    @Autowired
    private SymptomMapper symptomMapper;

    @Autowired
    private SymptomExtMapper symptomExtMapper;

    public boolean insert(Symptom symptom) {
        symptom.setId(UUID.randomUUID().toString().replace("-", ""));
        if (!"".equals(symptom.getParentId()) && null != symptom.getParentId()) {
            SymptomExt symptomExt = new SymptomExt();
            symptomExt.setId(UUID.randomUUID().toString().replace("-", ""));
            symptomExt.setSrcId(symptom.getParentId());
            symptomExt.setTargetId(symptom.getId());
            symptomExtMapper.insertSelective(symptomExt);
        }
        symptomMapper.insertSelective(symptom);
        return true;
    }

    public boolean selectInsert(SymptomExt symptomExt) {
        symptomExt.setId(UUID.randomUUID().toString().replace("-", ""));
        symptomExtMapper.insertSelective(symptomExt);
        return true;
    }

    public List<Symptom> select(Symptom symptom) {
        return symptomMapper.select(symptom);
    }

    public boolean update(Symptom symptom) {
        symptomMapper.updateByPrimaryKeySelective(symptom);
        return true;

    }

    public boolean delete(String id) {
        //判断该节点是否为父节点，父节点不允许删除
        SymptomExt symptomExt = new SymptomExt();
        symptomExt.setSrcId(id);
        if (symptomExtMapper.selectCount(symptomExt) > 0) {
            return false;
        } else {
            //删除该节点作为子节点的关联数据
            symptomExt.setSrcId("");
            symptomExt.setTargetId(id);
            symptomExtMapper.delete(symptomExt);
            //删除该节点信息
            symptomMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    public Symptom get(String id) {
        return symptomMapper.selectByPrimaryKey(id);
    }
}
