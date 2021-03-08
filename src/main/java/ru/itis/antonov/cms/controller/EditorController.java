package ru.itis.antonov.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.antonov.cms.dto.PageDto;
import ru.itis.antonov.cms.models.Page;
import ru.itis.antonov.cms.service.SitePagesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class EditorController {

    private SitePagesService sitePagesService;

    @Autowired
    public EditorController(SitePagesService sitePagesService) {
        this.sitePagesService = sitePagesService;
    }

    @ModelAttribute
    public void addPages(Model model){
        model.addAttribute("pages", sitePagesService.pages());
    }

    @GetMapping("/editor")
    public String getView(){
        return "editor";
    }

    @PostMapping("/editor")
    public void postPage(@RequestParam String content,
                         @RequestParam String path,
                         @RequestParam String title,
                         @RequestParam String locale,
                         @RequestParam String parentId,
                         HttpServletRequest request,
                         HttpServletResponse response){
        sitePagesService.savePage(sitePagesService.buildPage(content, title, path, locale, parentId, request.getContextPath()));
        response.setStatus(200);
    }
}
