package com.bojoy.dataservice.service;


import com.bojoy.dataservice.entity.sourcedata.EventDomain;
import com.bojoy.dataservice.entity.sourcedata.PropertyDomain;

import java.util.List;
import java.util.Map;

public interface SourceDataService {
    List<EventDomain> getEventinfo(Integer game_id);

    List<PropertyDomain> getPropertyinfo(String event_id);

    Map<String, Object> addEvent(Map<String, Object> param);

    Map<String, Object> addGroup(Map<String, Object> param);

    Map<String, Object> addProperty(Map<String, Object> param);

    Map<String, Object> updateGroup(Map<String, Object> param);

    Map<String, Object> deleGroup(Map<String, Object> param);

    Map<String, Object> addGame(Map<String, Object> param);

    Map<String, Object> deleGame(Map<String, Object> param);

    Map<String, Object> updateGame(Map<String, Object> param);

    Map<String, Object> deleProperty(Map<String, Object> param);

    Map<String, Object> updateProperty(Map<String, Object> param);

    Map<String, Object> updateEvent(Map<String, Object> param);

    Map<String, Object> deleEvent(Map<String, Object> param);

    Map<String, Object> getAllGame();

    Map<String, Object> getAllGroup();

    Map<String, Object> getAllEvent();

    Map<String, Object> getAllProperty();

    Map<String, Object> getEventByGameid(Map<String, Object> param);

    Map<String, Object> getPropertyByEventId(Map<String, Object> param);

    Map<String, Object> updateEPinfoByEventId(Map<String, Object> param);

    Map<String, Object> getUserProperty(String param);

    Map<String, Object> addUserProperty(Map<String, Object> param);

    Map<String, Object> deleUserProperty(Map<String, Object> param);

    Map<String, Object> updateUserProperty(Map<String, Object> param);


}
