package com.bojoy.dataservice.service;


import com.bojoy.dataservice.entity.funnel.FunnelEntity;

import java.util.List;
import java.util.Map;

public interface FunnelService {

    Map<String, Object> addFunnel(Map<String, Object> param);

    List<FunnelEntity> test();
}
