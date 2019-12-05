package com.ual.blog.web.listener;

import com.ual.blog.component.CommonMap;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName GlobalListener
 * @Description TODO
 * @Author ual
 */
public class GlobalListener implements ServletContextListener {

    @Autowired
    private CommonMap commonMap;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("paramMap",commonMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute("paramMap");
        commonMap.clear();
    }
}
