package com.trydone.inquiry.data;

import com.frameworkset.orm.annotation.ESId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@ApiModel("症状")
@Table(name = "inquiry_symptom")
public class Symptom {

    @ApiModelProperty("父节点id")
    private String parentId;
    /**
     * 主键
     */
    @ESId
    @ApiModelProperty("id")
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 症状
     */
    @ApiModelProperty("症状")
    @Column(name = "content")
    private String content;

    /**
     * 是否常见症状:1是,0否
     */
    @ApiModelProperty("是否常见症状:1是,0否")
    @Column(name = "common")
    private String common;

    /**
     * 症状类型:0症状,1诊断
     */
    @ApiModelProperty("症状类型:0症状,1诊断")
    @Column(name = "type")
    private String type;

    /**
     * 紧急程度
     */
    @ApiModelProperty("紧急程度")
    @Column(name = "level")
    private String level;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
