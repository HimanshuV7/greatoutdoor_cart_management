package com.capgemini.go.controller;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.service.OrderAndCartService;
import com.capgemini.go.dto.ProductUINMap;
import com.capgemini.go.util.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capgemini.go.dto.OrderDTO;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

//localhost:8150
@RestController
@RequestMapping("/cartitems")
public class OrderController {

    @Autowired
    private OrderAndCartService orderAndCartService;


    @PostMapping("/add")
    public String addItemToCart(@RequestBody Map<String, Object> requestData) {
        CartDTO cartItem = CartUtil.createCartItem(requestData);
        orderAndCartService.addItemToCart(cartItem);
        String status = "Item added to cart successfully!";
        return status;
    }


    @PostMapping("/checkout")
    public String placeOrder(@RequestBody Map<String, Object> requestData) {
        String userId = (String) requestData.get("userId");
        String addressId = (String) requestData.get("addressId");
        OrderDTO orderDTO = orderAndCartService.addOrder(userId, addressId);
        String status = "your order id is=" + orderDTO.getOrderId();
        return status;
    }

    /**
     * adding product id, productuin map, let's assume productid-uin map exists in system
     */
    @PostConstruct
    public void productUIn() {
        ProductUINMap productUINMap1 = new ProductUINMap();
        productUINMap1.setProductId("p1");
        productUINMap1.setProductUin("pu1");
        productUINMap1 = orderAndCartService.save(productUINMap1);

        ProductUINMap productUINMap2 = new ProductUINMap();
        productUINMap2.setProductId("p2");
        productUINMap2.setProductUin("pu2");
        productUINMap2 = orderAndCartService.save(productUINMap2);

    }

    @GetMapping("/user/orders/{userId}")
    public List<OrderDTO> getOrdersByUserId(@PathVariable("userId") String userId) {
        List<OrderDTO> orders = orderAndCartService.fetchAllOrdersByUserId(userId);
        return orders;
    }

}
