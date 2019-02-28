package com.bojoy.dataservice.entity.funnel;

import lombok.Data;

@Data
public class StepEntity {
    private String step_id;
    private String event_name;
    private String event_id;
    private String custom_name;
    private String relevance_field;
    private String relation;
    private String conditions;

}
