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
            try {
                productData.put(Integer.parseInt(key), Integer.parseInt(((String[]) params.get(key))[0]));

            }catch (NumberFormatException e){
                System.out.println("cannot convert this data!");
            }
        }
        for (Integer key : productData.keySet()) {
            relevantProducts.add(productDataStore.find(key));
        }
        System.out.println(productData);

        context.setVariable("relevantProducts",relevantProducts);
    }
}
