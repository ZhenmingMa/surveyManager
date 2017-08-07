package com.cby.controller;

import com.cby.entity.SystemInfo;
import javafx.geometry.Pos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ma on 2017/8/4.
 */
@RestController
public class GetInfoController {

    @PostMapping(value = "/summitInfo")
    public void submitInfo(SystemInfo systemInfo){
        System.out.println(systemInfo.toString());
    }
}
