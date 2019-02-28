package com.bojoy.dataservice.service.impl;


import com.bojoy.dataservice.entity.sourcedata.EventDomain;
import com.bojoy.dataservice.entity.sourcedata.GameDomain;
import com.bojoy.dataservice.entity.sourcedata.GroupDomain;
import com.bojoy.dataservice.entity.sourcedata.PropertyDomain;
import com.bojoy.dataservice.mapper.custom.SourceDataDao;
import com.bojoy.dataservice.service.SourceDataService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service(value = "sourceService")
@Slf4j
public class SourceDataServiceImpl implements SourceDataService {


    @Autowired
    private SourceDataDao sourceDataDao;

    @Override
    public List<EventDomain> getEventinfo(Integer game_id) {
        return sourceDataDao.getEventinfo(game_id);
    }

    @Override
    public List<PropertyDomain> getPropertyinfo(String event_id) {
        return sourceDataDao.getPropertyinfo(event_id);
    }

    @Override
    public Map<String, Object> addEvent(Map<String, Object> param) {
//        获取游戏id，分组id，事件name  同一个游戏下的一个事件可以属于多个分组（有自定义分组）所以事件name在整个游戏下只能定义一次
        //1、判断该游戏下的事件是否存在 根据事件名称
        Map<String, Object> resMap = Maps.newHashMap();
        List<EventDomain> eventList = sourceDataDao.getEventByGameidAndEventname(param);
        if (eventList.size() > 0) {
            //event已存在
            resMap.put("status", 0);
            resMap.put("msg", "事件已存在，请勿重新添加");
            return resMap;
        }


        //新增


        try {

            //生成一个随机的eventname
            param.put("event_id", UUID.randomUUID().toString().replace("-", ""));
            sourceDataDao.addEvent(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "数据插入异常");
            return resMap;
        }


    }

    @Override
    public Map<String, Object> addGroup(Map<String, Object> param) {

//        在游戏下插入分组
        Map<String, Object> resMap = Maps.newHashMap();
        //生成随机的group_id
        param.put("group_id", UUID.randomUUID().toString().replace("-", ""));

        Integer count = sourceDataDao.getGroupByGameidAndGroupId(param);
        if (count > 0) {

            resMap.put("status", 0);
            resMap.put("msg", "该分组已存在");
            return resMap;

        }
//        新增
        try {
            Integer insertcount = sourceDataDao.addGroup(param);
            log.info("插入的记录数：{}", insertcount);
            if (insertcount == 0) {
                resMap.put("status", 0);
                resMap.put("msg", "fail");
                return resMap;
            }
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            resMap.put("status", 0);
            resMap.put("msg", "插入异常");
            return resMap;
        }


    }

    @Override
    public Map<String, Object> addProperty(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();
        Integer count = sourceDataDao.getPropertyByNameandGameId(param);
        if (count > 0) {
//            已存在
            resMap.put("status", 0);
            resMap.put("msg", "fail 已存在");
            return resMap;
        }
        try {
            sourceDataDao.addProperty(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            log.info("属性新增成功...");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "插入数据发生异常");
            log.info("属性插入异常...");
            return resMap;
        }


    }

    @Override
    public Map<String, Object> updateGroup(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();


        Integer count = sourceDataDao.getGroupByGameidAndGroupId(param);
        log.info("查询到的结果是：{}", count);
        if (count == 0) {
            //不存在
            resMap.put("status", 0);
            resMap.put("msg", "该分组不存在");
            return resMap;
        }

        try {
            sourceDataDao.updateGroup(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "fail");
            return resMap;

        }
    }

    @Override
    public Map<String, Object> deleGroup(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();

        Integer count = sourceDataDao.getGroupByGameidAndGroupId(param);
        log.info("查询到的结果是：{}", count);
        if (count == 0) {
            //不存在
            resMap.put("status", 0);
            resMap.put("msg", "该分组不存在");
            return resMap;
        }

        try {
            Integer resCode = sourceDataDao.deleGroup(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "执行删除时异常");
            return resMap;
        }
    }

    @Override
    public Map<String, Object> addGame(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();
        //根据游戏id和游戏name判断该游戏是否存在
        List<GameDomain> gameList = sourceDataDao.getGameInfoByIdOrName(param);
        if (gameList.size() > 0) {
            resMap.put("status", 0);
            resMap.put("msg", "游戏name或者id已存在");
            return resMap;
        }

        try {
            sourceDataDao.addGame(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "游戏信息插入异常");
            return resMap;
        }

    }

    @Override
    public Map<String, Object> deleGame(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();

        try {
            sourceDataDao.deleGame(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "fail：执行删除操作时异常");
            return resMap;
        }

    }

    @Override
    public Map<String, Object> updateGame(Map<String, Object> param) {

        Map<String, Object> resMap = Maps.newHashMap();
        try {
            sourceDataDao.updateGame(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {

            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "fail：执行删除操作时异常");
            return resMap;
        }

    }

    @Override
    public Map<String, Object> deleProperty(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();

        try {
            sourceDataDao.deleProperty(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "fail,删除时 异常");
            return resMap;
        }
    }

    @Override
    public Map<String, Object> updateProperty(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();

        try {
            sourceDataDao.updateProperty(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "fail,更新时 异常");
            return resMap;
        }
    }

    @Override
    public Map<String, Object> updateEvent(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();

        try {
            sourceDataDao.updateEvent(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "插入表异常");
            return resMap;
        }

    }

    @Override
    public Map<String, Object> deleEvent(Map<String, Object> param) {


        Map<String, Object> resMap = Maps.newHashMap();

        try {
            sourceDataDao.deleEvent(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "删除异常");
            return resMap;
        }
    }

    @Override
    public Map<String, Object> getAllGame() {
        Map<String, Object> resMap = Maps.newHashMap();

        try {
            List<GameDomain> gameDomainList = sourceDataDao.getAllGame();
            resMap.put("status", 1);
            resMap.put("msg", "success");
            resMap.put("data", gameDomainList);
            return resMap;

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "查询数据库异常");
            log.error("查询数据库异常...");
            return resMap;
        }


    }

    @Override
    public Map<String, Object> getAllGroup() {


        Map<String, Object> resMap = Maps.newHashMap();

        try {
            List<GroupDomain> groupDomainList = sourceDataDao.getAllGroup();

            resMap.put("status", 1);
            resMap.put("msg", "success");
            resMap.put("data", groupDomainList);
            return resMap;

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "查询数据库异常");
            log.error("查询数据库异常...");
            return resMap;
        }


    }

    @Override
    public Map<String, Object> getAllEvent() {

        Map<String, Object> resMap = Maps.newHashMap();

        try {
            List<EventDomain> eventList = sourceDataDao.getAllEvent();

            resMap.put("status", 1);
            resMap.put("msg", "success");
            resMap.put("data", eventList);
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "查询数据库异常");
            log.error("查询数据库异常...");
            return resMap;
        }


    }

    @Override
    public Map<String, Object> getAllProperty() {
        Map<String, Object> resMap = Maps.newHashMap();

        try {
            List<PropertyDomain> propertyList = sourceDataDao.getAllProperty();
            ;

            resMap.put("status", 1);
            resMap.put("msg", "success");
            resMap.put("data", propertyList);
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "查询数据库异常");
            log.error("查询数据库异常...");
            return resMap;
        }

    }

    @Override
    public Map<String, Object> getPropertyByEventId(Map<String, Object> param) {

        Map<String, Object> resMap = Maps.newHashMap();

        try {
            List<EventDomain> eventPropertyList = sourceDataDao.getPropertyByEventId(param);

            resMap.put("status", 1);
            resMap.put("msg", "success");
            resMap.put("data", eventPropertyList);
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "查询数据库异常");
            log.error("查询数据库异常...");
            return resMap;
        }


    }


    @Override
    public Map<String, Object> getEventByGameid(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();

        try {
            List<EventDomain> eventList = sourceDataDao.getEventByGameid(param);

            resMap.put("status", 1);
            resMap.put("msg", "success");
            resMap.put("data", eventList);
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "查询数据库异常");
            log.error("查询数据库异常...");
            return resMap;
        }


    }

    @Override
    public synchronized Map<String, Object> updateEPinfoByEventId(Map<String, Object> param) {

        Map<String, Object> resMap = Maps.newHashMap();
        String event_id = param.get("event_id").toString();
        String game_id = param.get("game_id").toString();
        List<String> property_ids = (List<String>) param.get("property_id");
        List<Map<String, String>> paramList = Lists.newArrayList();
        for (String property_id : property_ids) {
            Map<String, String> paramMap = Maps.newHashMap();
            paramMap.put("event_id", event_id);
            paramMap.put("game_id", game_id);
            paramMap.put("property_id", property_id);
            paramList.add(paramMap);

        }

//先执行删除再插入操作

        try {
            sourceDataDao.deleEPinfoByEventId(param);
            sourceDataDao.updateEPinfoByEventId(paramList);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("status", 0);
            resMap.put("msg", "fail更新事件属性异常...");
            return resMap;
        }


    }

    @Override
    public Map<String, Object> getUserProperty(String param) {
        Map<String, Object> resMap = Maps.newHashMap();

        try {
            List<PropertyDomain> propertyDomains = sourceDataDao.getUserProperty(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            resMap.put("data", propertyDomains);
            return resMap;
        } catch (Exception e) {
            resMap.put("status", 0);
            resMap.put("msg", "查询异常");
            e.printStackTrace();
            return resMap;
        }
    }

    @Override
    public Map<String, Object> addUserProperty(Map<String, Object> param) {

        Map<String, Object> resMap = Maps.newHashMap();
        try {
            sourceDataDao.addUserProperty(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            resMap.put("status", 0);
            resMap.put("msg", "插入数据异常");
            e.printStackTrace();
            return resMap;
        }


    }

    @Override
    public Map<String, Object> deleUserProperty(Map<String, Object> param) {

        Map<String, Object> resMap = Maps.newHashMap();

        try {
            sourceDataDao.deleUserProperty(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            resMap.put("status", 0);
            resMap.put("msg", "删除数据异常");
            e.printStackTrace();
            return resMap;
        }


    }

    @Override
    public synchronized  Map<String, Object>   updateUserProperty(Map<String, Object> param) {
        Map<String, Object> resMap = Maps.newHashMap();
        try {
            sourceDataDao.deleUserProperty(param);
            sourceDataDao.addUserProperty(param);
            resMap.put("status", 1);
            resMap.put("msg", "success");
            return resMap;
        } catch (Exception e) {
            resMap.put("status", 0);
            resMap.put("msg", "删除数据异常");
            e.printStackTrace();
            return resMap;
        }


    }
}
