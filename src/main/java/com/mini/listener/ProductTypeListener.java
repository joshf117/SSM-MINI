package com.mini.listener;

import com.mini.pojo.ProductType;
import com.mini.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProductTypeListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //get productTypeService bean from spring container
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductTypeService productTypeService = (ProductTypeService) context.getBean("ProductTypeServiceImpl");
        List<ProductType> typeList = productTypeService.getAll();

        servletContextEvent.getServletContext().setAttribute("typeList",typeList);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
