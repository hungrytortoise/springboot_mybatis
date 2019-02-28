package com.bojoy.dataservice.controller;



import com.bojoy.dataservice.service.FunnelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/funnel")
@Slf4j
public class FunnelController {


    @Autowired
    private FunnelService funnelService;


    @PostMapping("addfunnel")
    @ResponseBody
    public Map<String, Object> addFunnel(@RequestBody Map<String, Object> param) {
        return funnelService.addFunnel(param);
    }

    @GetMapping("getfunnel")
    @ResponseBody

    public  Map<String,Object> getfunnel() {
        return null;
    }
    @GetMapping("test")
    @ResponseBody
    public Object test(){
        return funnelService.test();

    }


}
