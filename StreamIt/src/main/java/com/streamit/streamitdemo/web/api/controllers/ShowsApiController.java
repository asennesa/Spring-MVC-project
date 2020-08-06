package com.streamit.streamitdemo.web.api.controllers;

import com.streamit.streamitdemo.model.responsemodels.ShowResponseModel;
import com.streamit.streamitdemo.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowsApiController {
    private ShowService showService;

    public ShowsApiController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping(value = "/api/shows")
    public List<ShowResponseModel>allShows (){
        return this.showService.findAllShows();
    }

}
