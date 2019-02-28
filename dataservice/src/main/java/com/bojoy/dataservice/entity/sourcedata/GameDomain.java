package com.bojoy.dataservice.entity.sourcedata;

public class GameDomain {
    private String game_id;
    private String name;
    private String hdfs_path;
    private String remark_content;
    private String index;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHdfs_path() {
        return hdfs_path;
    }

    public void setHdfs_path(String hdfs_path) {
        this.hdfs_path = hdfs_path;
    }

    public String getRemark_content() {
        return remark_content;
    }

    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
