package ru.itis.antonov.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itis.antonov.cms.service.SitePagesService;

@Controller
public class WelcomeController {

    private SitePagesService sitePagesService;

    @Autowired
    public WelcomeController(SitePagesService sitePagesService) {
        this.sitePagesService = sitePagesService;
    }


    @ModelAttribute
    public void addPages(Model model){
        model.addAttribute("pages", sitePagesService.pages());
    }

}
