package ru.itis.antonov.cms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.antonov.cms.dto.PageDto;
import ru.itis.antonov.cms.service.SitePagesService;

import java.io.IOException;
import java.io.Writer;

@Controller
public class SiteNavigationController {

    private SitePagesService sitePagesService;

    @Autowired
    public SiteNavigationController(SitePagesService sitePagesService) {
        this.sitePagesService = sitePagesService;
    }

    @GetMapping("/site/{path}")
    public void getPage(Writer wr, @PathVariable String path){
        PageDto page = sitePagesService.getPage(path);
        try {
            wr.write(page.getContent());
        } catch (IOException ioException) {
            throw new IllegalArgumentException(ioException);
        }
    }
}
