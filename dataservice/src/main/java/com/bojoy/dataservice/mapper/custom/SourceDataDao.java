package com.bojoy.dataservice.mapper.custom;




import com.bojoy.dataservice.entity.sourcedata.EventDomain;
import com.bojoy.dataservice.entity.sourcedata.GameDomain;
import com.bojoy.dataservice.entity.sourcedata.GroupDomain;
import com.bojoy.dataservice.entity.sourcedata.PropertyDomain;

import java.util.List;
import java.util.Map;

public interface SourceDataDao {

    List<EventDomain> getEventinfo(Integer game_id);

    List<PropertyDomain> getPropertyinfo(String event_id);

    List<EventDomain> getEventByGameidAndEventname(Map<String, Object> param);

    int addEvent(Map<String, Object> param);


    Integer addGroup(Map<String, Object> param);

    Integer addProperty(Map<String, Object> param);

    Integer updateGroup(Map<String, Object> param);

    Integer deleGroup(Map<String, Object> param);

    Integer addGame(Map<String, Object> param);

    Integer deleGame(Map<String, Object> param);

    Integer updateGame(Map<String, Object> param);

    Integer deleProperty(Map<String, Object> param);

    Integer updateProperty(Map<String, Object> param);

    Integer updateEvent(Map<String, Object> param);

    Integer deleEvent(Map<String, Object> param);

    Integer getGroupByGameidAndGroupId(Map<String, Object> param);

    Integer getPropertyByNameandGameId(Map<String, Object> param);

    List<GameDomain> getGameInfoByIdOrName(Map<String, Object> param);

    List<GameDomain> getAllGame();

    List<GroupDomain> getAllGroup();

    List<EventDomain> getAllEvent();

    List<PropertyDomain> getAllProperty();

    List<EventDomain> getEventByGameid(Map<String, Object> param);

    List<EventDomain> getPropertyByEventId(Map<String, Object> param);

    Integer updateEPinfoByEventId(List<Map<String, String>> param);

    Integer deleEPinfoByEventId(Map<String, Object> param);

    List<PropertyDomain> getUserProperty(String param);

    Integer addUserProperty(Map<String, Object> param);

    Integer deleUserProperty(Map<String, Object> param);

}
