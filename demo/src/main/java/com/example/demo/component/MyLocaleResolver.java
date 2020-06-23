package com.example.demo.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在连接上携带区域信息
 */
public class MyLocaleResolver implements LocaleResolver {
    
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        String language = request.getHeader("Accept-Language");
        Locale locale = Locale.getDefault();
        String[] split = null;
        if(!StringUtils.isEmpty(l)){
            split = l.split("_");
        }
        else {
            split = language.split(",")[0].split("-");
        }
//        for (String t : split)
//            System.out.print(t + " ");
        locale = new Locale(split[0],split[1]);
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
