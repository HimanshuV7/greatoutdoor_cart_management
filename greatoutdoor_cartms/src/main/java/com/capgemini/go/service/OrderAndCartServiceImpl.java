package com.capgemini.go.service;


import com.capgemini.go.dao.OrderProductMapDao;
import com.capgemini.go.dao.ProductUINMapDao;
import com.capgemini.go.dto.ProductUINMap;
import com.capgemini.go.exception.EmptyCartException;
import com.capgemini.go.util.CartUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.OrderProductMapDTO;
import com.capgemini.go.dao.CartDao;
import com.capgemini.go.dao.OrderDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderAndCartServiceImpl implements OrderAndCartService {

    private static final Logger Log = LoggerFactory.getLogger(OrderAndCartServiceImpl.class);

    @Autowired
    private CartDao cartDao;
    @Autowired
    private OrderProductMapDao orderProductMapDao;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductUINMapDao productUINMapDao;

    @Override
    public boolean addItemToCart(CartDTO cartItem) {
        String id = CartDTO.id(cartItem.getUserId(), cartItem.getProductId());
        cartItem.setId(id);
        cartDao.save(cartItem);
        return true;
    }

    @Override
    public boolean insertOrderProductMapEntity(OrderProductMapDTO orderProductMapEntity) {
        String id = OrderProductMapDTO.id(orderProductMapEntity.getOrderId(), orderProductMapEntity.getProductId());
        orderProductMapEntity.setId(id);
        orderProductMapDao.save(orderProductMapEntity);
        return true;
    }

    @Override
    public boolean removeItemFromCart(CartDTO cartItem) {
        boolean exists = cartDao.existsById(cartItem.getId());
        if (!exists) {
            return false;
        }
        cartDao.delete(cartItem);
        return true;

    }

    @Override
    public boolean updateItemQuantity(CartDTO cartItem) {
        boolean exists = cartDao.existsById(cartItem.getId());
        if (exists) {
            cartItem = cartDao.save(cartItem);
            return true;
        }
        return false;
    }

    @Override
    public boolean registerOrder(OrderDTO order) {
        String orderId = CartUtil.generateId("order-", 10);
        order.setOrderId(orderId);
        order = orderDao.save(order);
        return true;
    }

    @Override
    public boolean deleteOrderProductMapEntity(OrderProductMapDTO orderProductMapEntity) {
        orderProductMapDao.delete(orderProductMapEntity);
        return true;
    }


    /**
     * Does two things
     * 1) adds order
     * 2)Deletes items in cart
     */
    @Override
    public OrderDTO addOrder(String userId, String addressId) {
        List<CartDTO> cartItems = cartDao.findByUserId(userId);
        if(cartItems.isEmpty()){
            throw new EmptyCartException("cant create order as cart is empty");
        }
        String orderId = CartUtil.generateId("order-", 9);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderId(orderId);
        orderDTO.setAddressId(addressId);
        orderDTO.setUserId(userId);
        orderDTO.setOrderInitiateTime(new Date());
        orderDTO = orderDao.save(orderDTO);
        //
        //for multiple cart items which user might have selected for different products
        //
        for (CartDTO cartItem : cartItems) {
            OrderProductMapDTO orderProduct = new OrderProductMapDTO();
            orderProduct.setOrderId(orderId);
            String productId = cartItem.getProductId();
            ProductUINMap productUINMap = productUINMapDao.getOne(productId);
            OrderProductMapDTO orderProductMap = new OrderProductMapDTO();
            orderProductMap.setOrderId(orderId);
            orderProductMap.setProductId(productId);
            orderProductMap.setProductUIN(productUINMap.getProductUin());
            insertOrderProductMapEntity(orderProductMap);
        }
        cartDao.deleteAll(cartItems);
        return orderDTO;
    }


    @Override
    public ProductUINMap save(ProductUINMap productUINMap) {
        productUINMap = productUINMapDao.save(productUINMap);
        return productUINMap;
    }

    @Override
    public List<OrderDTO>fetchAllOrdersByUserId(String userId){
       List<OrderDTO>orders =orderDao.findByUserId(userId);
       return orders;
    }


}
