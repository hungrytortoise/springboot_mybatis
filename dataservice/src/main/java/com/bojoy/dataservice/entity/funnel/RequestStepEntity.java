package com.bojoy.dataservice.entity.funnel;

import lombok.Data;

import java.util.List;

@Data
public class RequestStepEntity {

    private String event_name;
    private String event_id;
    private String custom_name;
    private String relevance_field;
    private FilterEntity filter;
    private String sort_id;
    private String relation ;


}
