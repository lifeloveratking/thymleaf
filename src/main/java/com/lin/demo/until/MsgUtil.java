package com.lin.demo.until;


import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MsgUtil implements LocaleResolver {
//解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取参数
        String language=request.getParameter("lang");
        Locale locale=Locale.getDefault();//如果没有就使用默认的
        //如果携带国际化的参数，则取出参数
        if (!StringUtils.isEmpty(language)){
            String[] splist=language.split("_");
            locale=new Locale(splist[0],splist[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
