package com.bojoy.dataservice.service.impl;


import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bojoy.dataservice.entity.funnel.FunnelEntity;
import com.bojoy.dataservice.entity.funnel.StepEntity;
import com.bojoy.dataservice.mapper.primary.PrimaryMapper;
import com.bojoy.dataservice.service.FunnelService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("funnelservice")
@Slf4j
public class FunnelServiceImpl implements FunnelService {
    @Autowired
    private PrimaryMapper primaryMapper;

    @Override
    public synchronized Map<String, Object> addFunnel(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();
        List<LinkedHashMap<String, Object>> steps = (List) param.get("steps");
        List<StepEntity> stepEntities = Lists.newArrayList();
        List<FunnelEntity> funnelList = Lists.newArrayList();
        //1、检查漏斗是否已存在

        try {
            List<FunnelEntity> funnelEntities = primaryMapper.getFunnelByFunnelName(param);
            if (funnelEntities.size() > 0) {
                log.info("{}，该漏斗名称已存在", param.get("funnel_name"));
                resMap.put("msg", "漏斗名称已存在。");
                resMap.put("status", 0);
                return resMap;
            }

            //生成一个随机的stepid
            String funnel_id = IdUtil.simpleUUID();
            for (LinkedHashMap<String, Object> rstepEntity : steps) {
                StepEntity stepEntity = new StepEntity();
                String step_id = IdUtil.simpleUUID();
//2、封装条件对象

                stepEntity.setStep_id(step_id);//设置stepid

                stepEntity.setEvent_name(rstepEntity.get("event_name").toString());
                stepEntity.setEvent_id(rstepEntity.get("event_id").toString());

                LinkedHashMap<String, Object> filter = (LinkedHashMap) rstepEntity.get("filter");
                stepEntity.setRelation(filter.get("relation").toString());
                List<LinkedHashMap<String, String>> conditions = (List) filter.get("conditions");
                List<JSONObject> conditionList = Lists.newArrayList();
                JSONObject jsonObject = new JSONObject();
                for (LinkedHashMap<String, String> condition : conditions) {
                    conditionList.add(JSON.parseObject(JSON.toJSONString(condition)));
                }
                jsonObject.put("conditions", conditionList);
                stepEntity.setConditions(jsonObject.toString());
                stepEntity.setRelevance_field(rstepEntity.get("relevance_field").toString());
                stepEntity.setCustom_name(rstepEntity.get("custom_name").toString());
                stepEntities.add(stepEntity);

//            3、封装漏斗和step的对应关系对象
                FunnelEntity funnelentity = new FunnelEntity();
                funnelentity.setFunnel_name(param.get("funnel_name").toString());
                funnelentity.setGame_id(param.get("game_id").toString());
                funnelentity.setMax_convert_time(param.get("max_convert_time").toString());
                funnelentity.setFunnel_id(funnel_id);
                funnelentity.setStep_id(step_id);
                funnelentity.setSort_id(rstepEntity.get("sort_id").toString()); //step再漏斗中的顺序
                funnelList.add(funnelentity);

            }

//        4、先插入漏斗对应关系表
            primaryMapper.addFunnelBatch(funnelList);
//            插入漏斗的步骤条件
//           5、 封装插入对象
            primaryMapper.addStepsBatch(stepEntities);
            resMap.put("msg", "success");
            resMap.put("status", 1);
            resMap.put("funnel_id", funnel_id);
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("msg", "插入漏斗和数据库交互异常！");
            resMap.put("status", 0);
            return resMap;
        }


    }


    @Override
    public List<FunnelEntity> test() {
        return primaryMapper.testfunc();
    }

//    抽象出的方法

}
