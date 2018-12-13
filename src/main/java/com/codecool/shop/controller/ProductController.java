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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        SupplierDaoMem supplierdao = SupplierDaoMem.getInstance();
        List<Supplier> suppliers = supplierdao.getAll();
        List<ProductCategory> categories = productCategoryDataStore.getAll();
//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
//        context.setVariables(params);
        context.setVariable("categories", categories);
        context.setVariable("suppliers",suppliers);
        context.setVariable("recipient", "World");

        int categoryFilter = 1;
        int supplierFilter = 1;
        if(req.getParameter("categoryFilter") != null) {
            categoryFilter = Integer.parseInt(req.getParameter("categoryFilter"));
        }
        if(req.getParameter("supplierFilter") != null) {
            supplierFilter = Integer.parseInt(req.getParameter("supplierFilter"));
        }
        if(req.getParameter("supplierFilter") == null && (req.getParameter("categoryFilter") == null)){
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        }else if(categoryFilter == 0 && supplierFilter != 0){
            context.setVariable("category",null);
            Supplier supplier = supplierdao.find(supplierFilter);
            context.setVariable("products",productDataStore.getBy(supplier));
        }else if(categoryFilter != 0 && supplierFilter == 0) {
            context.setVariable("category", productCategoryDataStore.find(categoryFilter));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(categoryFilter)));
        }else if(categoryFilter == 0 && supplierFilter == 0){
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            }
        else{
            context.setVariable("category",productCategoryDataStore.find(categoryFilter));
            List<Product> unfilteredProducts = productDataStore.getAll();
            List<Product> filteredProducts = new ArrayList<>();
            for(Product product : unfilteredProducts){
                if(product.getSupplier().getName().equals(supplierdao.find(supplierFilter).getName())){
                    if(product.getProductCategory().getName().equals(productCategoryDataStore.find(categoryFilter).getName())) {
                        filteredProducts.add(product);
                    }
                }
            }
            context.setVariable("products",filteredProducts);
        }


        engine.process("product/index_bootstrap.html", context, resp.getWriter());
    }

}
