package com.bojoy.dataservice.entity.sourcedata;

public class EventDomain {
    private String game_id;
    private String event_id;
    private String event_name;
    private String event_second_name;
    private Integer event_is_show;
    private String remark_content;
    private String event_index;
    private String name;
    private String group_name;

    public String getRemark_content() {
        return remark_content;
    }

    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_second_name() {
        return event_second_name;
    }

    public void setEvent_second_name(String event_second_name) {
        this.event_second_name = event_second_name;
    }

    public Integer getEvent_is_show() {
        return event_is_show;
    }

    public void setEvent_is_show(Integer event_is_show) {
        this.event_is_show = event_is_show;
    }



    public String getEvent_index() {
        return event_index;
    }

    public void setEvent_index(String event_index) {
        this.event_index = event_index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
