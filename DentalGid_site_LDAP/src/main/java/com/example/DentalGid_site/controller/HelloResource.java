package com.example.DentalGid_site.controller;


import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.logging.Logger;

@Controller

public class HelloResource {

    int k =0;
    private static Logger logger = Logger.getGlobal();


    @GetMapping("/")
    public String check(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = context.getAuthentication();

        Object user = authentication.getPrincipal();

        logger.info("user = " + user);
        System.out.println("user + " + user);


        return "greeting";
    }

    @GetMapping("/login")
    public String menu(){

        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = context.getAuthentication();
        String str = "/login";
        if (authentication != null) {

            Object user = authentication.getPrincipal();
            System.out.println("user + " + user);


            if (user.equals("anonymousUser")){
                k++;
                logger.warning("auth fail " + user);
                if(k>=5){
                    logger.warning("block " + user);

                    str = "block";
                      }
            }
        }

       System.out.println("Get /login");
        return str;
    }


}