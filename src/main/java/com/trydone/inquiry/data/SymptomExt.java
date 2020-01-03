package com.trydone.inquiry.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@ApiModel("症状节点关系表")
@Table(name = "inquiry_symptom_ext")
public class SymptomExt {

    /**
     * 主键
     */
    @ApiModelProperty("id")
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 上级症状节点id
     */
    @ApiModelProperty("上级症状节点id")
    @Column(name = "src_id")
    private String srcId;

    /**
     * 下级症状节点id
     */
    @ApiModelProperty("下级症状节点id")
    @Column(name = "target_id")
    private String targetId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
