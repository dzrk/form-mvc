package org.sushisushi.sushiform.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("signup")
public class FormController {
    static ArrayList<String> userList = new ArrayList<>();

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("title","Signup form");
        model.addAttribute("list", userList);
        return "form/index"; //html file name
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddForm(Model model){
        model.addAttribute("title", "Signup form");
        return "form/add";
    }
    @RequestMapping(value="add", method= RequestMethod.POST)
    public String processAddForm(@RequestParam String firstName ){ //request param must match html
        userList.add(firstName);
        return "redirect:";
    }
}
