package com.bojoy.dataservice.entity.funnel;

import lombok.Data;

@Data
public class FunnelEntity {
    private String game_id;
    private String funnel_id;
    private String funnel_name;
    private String max_convert_time;
    private String step_id;
    private String sort_id;

}
