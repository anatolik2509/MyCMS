package ru.itis.antonov.cms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.antonov.cms.dto.PageDto;
import ru.itis.antonov.cms.models.Page;
import ru.itis.antonov.cms.service.SitePagesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
public class SiteNavigationController {

    private SitePagesService sitePagesService;

    @Autowired
    public SiteNavigationController(SitePagesService sitePagesService) {
        this.sitePagesService = sitePagesService;
    }

    @ModelAttribute
    public void addPages(Model model){
        model.addAttribute("pages", sitePagesService.pages());
    }

    @GetMapping("/site/**")
    public void getPage(Writer wr, HttpServletRequest request, HttpServletResponse response){
        String path = request.getServletPath();
        System.out.println(path);
        System.out.println(request.getRequestURL());
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        PageDto page = sitePagesService.getPage(path);
        if(page == null){
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            return;
        }
        try {
            wr.write(page.getContent());
        } catch (IOException ioException) {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
