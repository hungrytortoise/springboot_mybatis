package com.bojoy.dataservice.entity.sourcedata;

public class PropertyDomain {
    private String game_id;
    private String event_id;
    private String id;
    private String property_name;
    private String property_second_name;
    private String property_data_type;
    private Integer property_is_show;
    private Integer property_type;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public String getProperty_second_name() {
        return property_second_name;
    }

    public void setProperty_second_name(String property_second_name) {
        this.property_second_name = property_second_name;
    }

    public String getProperty_data_type() {
        return property_data_type;
    }

    public void setProperty_data_type(String property_data_type) {
        this.property_data_type = property_data_type;
    }

    public Integer getProperty_is_show() {
        return property_is_show;
    }

    public void setProperty_is_show(Integer property_is_show) {
        this.property_is_show = property_is_show;
    }

    public Integer getProperty_type() {
        return property_type;
    }

    public void setProperty_type(Integer property_type) {
        this.property_type = property_type;
    }
}
