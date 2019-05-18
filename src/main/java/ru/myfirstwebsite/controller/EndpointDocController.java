package ru.myfirstwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class EndpointDocController {
    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public EndpointDocController(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.handlerMapping = requestMappingHandlerMapping;
    }

    @RequestMapping(value = "/endpointdoc", method = RequestMethod.GET)
    public void show(Model model) {
        model.addAttribute("handleMethods", this.handlerMapping.getHandlerMethods());
    }

}
