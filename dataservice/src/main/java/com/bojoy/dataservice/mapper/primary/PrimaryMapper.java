package com.bojoy.dataservice.mapper.primary;


import com.bojoy.dataservice.entity.funnel.FunnelEntity;
import com.bojoy.dataservice.entity.funnel.StepEntity;

import java.util.List;
import java.util.Map;

public interface PrimaryMapper {
    List<FunnelEntity> testfunc();

    Integer addFunnelBatch(List<FunnelEntity> list);

    List<FunnelEntity> getFunnelByFunnelName(Map<String, Object> param);

    Integer addStepsBatch(List<StepEntity> list);
}
