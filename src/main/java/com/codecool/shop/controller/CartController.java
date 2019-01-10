package com.codecool.shop.controller;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.expression.Numbers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet{



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map params = req.getParameterMap();
        Iterator i = params.keySet().iterator();
        Map<Integer, Integer> productData  = new HashMap<Integer, Integer>();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        List<Product> relevantProducts = new ArrayList<>();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Numbers numbers = new Numbers(Locale.US);

        FormToProductsConverter converter = new FormToProductsConverter();
        converter.ConvertPostDataToProducts(context,params,productData,i,relevantProducts,productDataStore);
        context.setVariable("productData",productData);
        context.setVariable("numbers",numbers);



        engine.process("product/cart.html", context, resp.getWriter());




    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/cart.html", context, resp.getWriter());
    }
}


