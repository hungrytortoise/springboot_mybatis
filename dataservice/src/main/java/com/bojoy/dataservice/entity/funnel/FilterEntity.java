package com.bojoy.dataservice.entity.funnel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.List;

public class FilterEntity {

    private String relation;
    private List<ConditionEntity> conditions;

    public List<ConditionEntity> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionEntity> conditions) {
        this.conditions = conditions;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
    @Override
    public String toString(){
        JSONObject resJsonObj = new JSONObject();
        List<String> list = Lists.newArrayList();
        for(ConditionEntity  conditionEntity :conditions){

            String condition = JSON.toJSONString(conditionEntity);
            list.add(condition);

        }
        resJsonObj.put("conditions", list);
        return resJsonObj.toString();
    }
}
