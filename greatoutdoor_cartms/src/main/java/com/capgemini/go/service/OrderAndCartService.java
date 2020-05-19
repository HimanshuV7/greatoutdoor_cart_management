package com.capgemini.go.service;

import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.OrderProductMapDTO;
import com.capgemini.go.dto.ProductUINMap;

import java.util.List;

public interface OrderAndCartService {

	/*
	 * name - add to cart
	 * description - It will add an item to the cart.
	 */
	boolean addItemToCart(CartDTO cartItem) ;
	
	
	/*
	 * name - insert order-product map entity
	 * description - register an item against a particular order
	 */
	boolean insertOrderProductMapEntity(OrderProductMapDTO orderProductMapEntity) ;
	
	
	/*
	 * name - remove item from the cart
	 * description - it will remove available item from the cart
	 */
	boolean removeItemFromCart(CartDTO cartItem);
	
	
	/*
	 * name - updateItemQuantity
	 * description - update the amount of existing product
	 */
	boolean updateItemQuantity(CartDTO cartItem) ;
	
	
	/*
	 * name - registerOrder
	 * description - register a new order
	 */
	boolean registerOrder(OrderDTO order);
	
	
	/*
	 * name - delete order-product map entity
	 * description - delete an item against a particular order
	 */
	boolean deleteOrderProductMapEntity(OrderProductMapDTO orderProductMapEntity);


	OrderDTO addOrder(String userId, String addressId);


	ProductUINMap save(ProductUINMap productUINMap);
	List<OrderDTO> fetchAllOrdersByUserId(String userId);
}
