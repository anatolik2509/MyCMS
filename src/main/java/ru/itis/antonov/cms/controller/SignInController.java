package ru.itis.antonov.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.antonov.cms.service.SitePagesService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignInController {

    private SitePagesService sitePagesService;

    @Autowired
    public SignInController(SitePagesService sitePagesService) {
        this.sitePagesService = sitePagesService;
    }

    @ModelAttribute
    public void addPages(Model model){
        model.addAttribute("pages", sitePagesService.pages());
    }

    @GetMapping("/signIn")
    public String getView(){
        return "signIn";
    }

    @GetMapping(value = "/signIn", params = {"login", "password"})
    public String signIn(@RequestParam String login, @RequestParam String password, HttpServletRequest request){
        if(login.equals("admin") && password.equals("admin")){
            request.getSession().setAttribute("auth", true);
            return "redirect:/editor";
        }
        return "signIn";
    }
}
