package com.bojoy.dataservice.entity.sourcedata;

public class GroupDomain {

    private String game_id;
    private String name;
    private String group_id;
    private String group_name;
    private String group_second_name;
    private String group_index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_second_name() {
        return group_second_name;
    }

    public void setGroup_second_name(String group_second_name) {
        this.group_second_name = group_second_name;
    }

    public String getGroup_index() {
        return group_index;
    }

    public void setGroup_index(String group_index) {
        this.group_index = group_index;
    }
}
