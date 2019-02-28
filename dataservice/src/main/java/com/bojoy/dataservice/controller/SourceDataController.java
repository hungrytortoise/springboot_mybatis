package com.bojoy.dataservice.controller;



import com.bojoy.dataservice.entity.sourcedata.EventDomain;
import com.bojoy.dataservice.entity.sourcedata.PropertyDomain;
import com.bojoy.dataservice.service.SourceDataService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/sourcedata")
@Slf4j
public class SourceDataController {

    @Autowired
    private SourceDataService sourceDataService;

    @ResponseBody
    @PostMapping("/eventinfo")
    //根据游戏id获取事件信息
    public List<EventDomain> getEventinfo(@RequestBody Map<String, Object> param) {
        Integer game_id = Integer.parseInt(param.get("game_id").toString());
        log.info("前段传过来的gameid是：{}", game_id);
        return sourceDataService.getEventinfo(game_id);

    }

    //根据事件id获取属性信息
    @PostMapping("/propertyinfo")
    @ResponseBody
    public List<PropertyDomain> getPropertyinfo(@RequestBody @NonNull Map<String, Object> param) {
        String event_id = param.get("event_id").toString();
        log.info("前端接受到的eventid是：{}", event_id);
        return sourceDataService.getPropertyinfo(event_id);
    }


    //新增游戏信息
    @PostMapping("addgame")
    @ResponseBody
    public Map<String, Object> addGame(@RequestBody Map<String, Object> param) {

        return sourceDataService.addGame(param);
    }

    //    删除游戏信息
    @PostMapping("delegame")
    @ResponseBody
    public Map<String, Object> deleGame(@RequestBody Map<String, Object> param) {


        return sourceDataService.deleGame(param);

    }

    //更新游戏的信息

    @PostMapping("updategame")
    @ResponseBody

    public Map<String, Object> updateGame(@RequestBody Map<String, Object> param) {

        return sourceDataService.updateGame(param);

    }

    //    获取游戏的展示信息
    @GetMapping("getallgame")
    @ResponseBody
    public Map<String, Object> getAllGame() {


        return sourceDataService.getAllGame();

    }

    //新增属性(同一游戏下，不能有重复的属性name)
    @PostMapping("/addproperty")
    @ResponseBody
    public Map<String, Object> addProperty(@RequestBody @NotNull Map<String, Object> param) {
        return sourceDataService.addProperty(param);

    }

    //属性删除
    @PostMapping("deleproperty")
    @ResponseBody
    public Map<String, Object> deleProperty(@RequestBody Map<String, Object> param) {
        return sourceDataService.deleProperty(param);
    }

    //属性修改
    @PostMapping("updateproperty")
    @ResponseBody

    public Map<String, Object> updateProperty(@RequestBody Map<String, Object> param) {
        return sourceDataService.updateProperty(param);
    }


    @GetMapping("getallproperty")
    @ResponseBody
    public Map<String, Object> getAllProperty() {


        return sourceDataService.getAllProperty();


    }


    //    新增分组
    @PostMapping("addgroup")
    @ResponseBody
    public Map<String, Object> addGroup(@RequestBody @NotNull Map<String, Object> param) {
        return sourceDataService.addGroup(param);

    }

    //更新分组
    @PostMapping("/updategroup")
    @ResponseBody
    public Map<String, Object> updateGroup(@RequestBody @NotNull Map<String, Object> param) {
        return sourceDataService.updateGroup(param);
    }

    //删除分组
    @PostMapping("/delegroup")
    @ResponseBody
    public Map<String, Object> deleGroup(@RequestBody Map<String, Object> param) {


        return sourceDataService.deleGroup(param);
    }

    //查看全部的分组信息
    @GetMapping("/getallgroup")
    @ResponseBody

    public Map<String, Object> getAllGroup() {
        return sourceDataService.getAllGroup();
    }

    //新增事件
    @PostMapping("/addevent")
    @ResponseBody
    public Map<String, Object> addEvent(@RequestBody @NotNull Map<String, Object> param) {
//  同一个游戏下的不同分组中可以有相同的事件，支持自定义事件
        return sourceDataService.addEvent(param);
    }

    //更新事件
    @PostMapping("/updateevent")
    @ResponseBody
    public Map<String, Object> updateEvent(@RequestBody Map<String, Object> param) {

        return sourceDataService.updateEvent(param);

    }

    //删除事件
    @PostMapping("/deleevent")
    @ResponseBody
    public Map<String, Object> deleEvent(@RequestBody Map<String, Object> param) {

        return sourceDataService.deleEvent(param);

    }

    //    获取全部事件信息
    @GetMapping("/getallevent")
    @ResponseBody
    public Map<String, Object> getAllEvent() {
        return sourceDataService.getAllEvent();
    }

    //根据游戏获取事件
    @PostMapping("/geteventbygameid")
    @ResponseBody
    public Map<String, Object> getEventByGameid(@RequestBody Map<String, Object> param) {

        return sourceDataService.getEventByGameid(param);
    }

//    根据事件id获取事件信息和对应的属性信息

    @PostMapping("/getpropertybyeventid")
    @ResponseBody

    public Map<String, Object> getPropertyByEventId(@RequestBody Map<String, Object> param) {

        return sourceDataService.getPropertyByEventId(param);

    }
//根据eventid修改信息

    @PostMapping("/updateepinfobyeventid")
    @ResponseBody


    public Map<String, Object> updateEPinfoByEventid(@RequestBody Map<String, Object> param) {

        return sourceDataService.updateEPinfoByEventId(param);

    }
//    用户属性查询

    @GetMapping("/getuserproperty/{game_id}")
    @ResponseBody

    public Map<String, Object> getUserProperty(@PathVariable @NotNull String game_id) {
        return sourceDataService.getUserProperty(game_id);

    }

    @PostMapping("/adduserproperty")
    @ResponseBody
    public Map<String, Object> addUserProperty(@RequestBody Map<String, Object> param) {


        return sourceDataService.addUserProperty(param);

    }

    @PostMapping("/deleuserproperty")
    @ResponseBody

    public Map<String, Object> deleUserProperty(@RequestBody Map<String, Object> param) {
        return sourceDataService.deleUserProperty(param);
    }

    @PostMapping("updateuserproperty")
    @ResponseBody
    public Map<String, Object> updateUserProperty(@RequestBody Map<String, Object> param) {
        return sourceDataService.updateUserProperty(param);

    }
}
