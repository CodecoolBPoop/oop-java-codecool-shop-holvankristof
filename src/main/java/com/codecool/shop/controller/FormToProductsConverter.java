package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import org.thymeleaf.context.WebContext;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FormToProductsConverter {
    public void ConvertPostDataToProducts(WebContext context, Map params, Map<Integer, Integer> productData, Iterator i, List<Product> relevantProducts, ProductDao productDataStore) {
        while(i.hasNext()){
            String key = (String) i.next();
            productData.put(Integer.parseInt(key), Integer.parseInt(((String[]) params.get( key ))[ 0 ]));

        }
        for (Integer key : productData.keySet()) {
            relevantProducts.add(productDataStore.find(key));
        }

        context.setVariable("relevantProducts",relevantProducts);
    }
}
