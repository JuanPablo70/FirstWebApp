package com.nexus.springboot.myfirstwebapp.home;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToHomePage(ModelMap model) {
        model.put("name", getLoggedinUsername());
        return "home";
    }

    /***
     * Gets the name of teh current username
     * @return username
     */
    private String getLoggedinUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
