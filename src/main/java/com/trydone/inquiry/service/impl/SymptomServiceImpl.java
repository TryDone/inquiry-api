package com.trydone.inquiry.service.impl;

import com.trydone.inquiry.dao.SymptomExtMapper;
import com.trydone.inquiry.dao.SymptomMapper;
import com.trydone.inquiry.data.Symptom;
import com.trydone.inquiry.data.SymptomExt;
import com.trydone.inquiry.service.ISymptomService;
import net.sf.json.JSONObject;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("symptomService")
@Transactional
public class SymptomServiceImpl implements ISymptomService {

    @Autowired
    private SymptomMapper symptomMapper;

    @Autowired
    private SymptomExtMapper symptomExtMapper;

    public boolean insert(Symptom symptom) {
        symptom.setId(UUID.randomUUID().toString().replace("-", ""));
        SymptomExt symptomExt = new SymptomExt();
        symptomExt.setId(UUID.randomUUID().toString().replace("-", ""));
        if (!"".equals(symptom.getParentId()) && null != symptom.getParentId()) {
            symptomExt.setSrcId(symptom.getParentId());
        } else {
            //所有一级节点默认都挂载虚拟节点-1上
            symptomExt.setSrcId("-1");
        }
        symptomExt.setTargetId(symptom.getId());
        symptomExtMapper.insertSelective(symptomExt);
        symptom.setParentId("");
        symptomMapper.insertSelective(symptom);
        ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
        clientUtil.addDocument("inquiry", symptom);
        return true;
    }

    public boolean selectInsert(SymptomExt symptomExt) {
        symptomExt.setId(UUID.randomUUID().toString().replace("-", ""));
        symptomExtMapper.insertSelective(symptomExt);
        return true;
    }

    public boolean update(Symptom symptom) {
        symptomMapper.updateByPrimaryKeySelective(symptom);
        ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
        clientUtil.addDocument("inquiry", symptom);
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
            ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
            clientUtil.deleteDocument("inquiry", "_doc", id);
        }
        return true;
    }

    public Symptom get(String id) {
        return symptomMapper.get(id);
    }

    public List<Symptom> querySymptom(String id) {
        return symptomMapper.querySymptom(id);
    }

    public List<Map<String,Object>> selectCommon() {
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<Symptom> symptomList = symptomMapper.selectCommon();
        symptomList.stream().forEach(symptom -> {
            Map<String,Object> map = new HashMap<>();
            map.put("id",symptom.getId());
            map.put("content",symptom.getContent());
            map.put("common",symptom.getCommon());
            map.put("type",symptom.getType());
            map.put("level",symptom.getLevel());
            map.put("subSymptom",symptomMapper.querySymptom(symptom.getId()));
            mapList.add(map);
        });
        return mapList;
    }
}
