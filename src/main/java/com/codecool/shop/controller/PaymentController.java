package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Map<Integer, Integer> productData = new HashMap<>();
        Map params = req.getParameterMap();
        List<Product> relevantProducts = new ArrayList<>();
        Iterator i = params.keySet().iterator();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        FormToProductsConverter converter = new FormToProductsConverter();
        converter.ConvertPostDataToProducts(context, params,productData,i,relevantProducts,productDataStore);
        float sumPrice = 0;
        for (Map.Entry<Integer, Integer> entry : productData.entrySet()) {
            for(Product product : relevantProducts){
                if(entry.getKey() == product.getId() ){
                    sumPrice += entry.getValue() * product.getDefaultPrice();
                }
            }

        }

        context.setVariable("total_price",sumPrice);
        engine.process("product/payment.html", context, resp.getWriter());
    }
}
