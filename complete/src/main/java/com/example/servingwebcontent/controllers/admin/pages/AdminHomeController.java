package com.example.servingwebcontent.controllers.admin.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @GetMapping
    public RedirectView index() {
        return new RedirectView("/admin/trains/create");
    }
}
