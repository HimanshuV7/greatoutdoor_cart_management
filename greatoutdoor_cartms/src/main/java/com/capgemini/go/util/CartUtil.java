package com.capgemini.go.util;

import com.capgemini.go.dto.CartDTO;

import java.util.Map;

public class CartUtil {

    public static String generateId(String prefix,int digits){
        StringBuilder builder=new StringBuilder(prefix);
        for(int i=0;i<digits;i++){
         builder.append(i);
        }
        return builder.toString();
    }

    public static CartDTO createCartItem(Map<String,Object>requestData){
        CartDTO cartDTO=new CartDTO();
        String productId=(String)requestData.get("productId");
        cartDTO.setProductId(productId);
        int quantity=(int)requestData.get("quantity");
        cartDTO.setQuantity(quantity);
        String userId=(String)requestData.get("userId");
        cartDTO.setUserId(userId);
        return cartDTO;
    }
}
