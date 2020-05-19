package com.capgemini.go.service;

import com.capgemini.go.dao.ProductUINMapDao;
import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.OrderProductMapDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.ProductUINMap;
import com.capgemini.go.exception.EmptyCartException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(OrderAndCartServiceImpl.class)
public class OrderAndCartServiceImplTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderAndCartService service;

    @Autowired
    private ProductUINMapDao productUINMapDao;

    @BeforeEach
    public void productUIn(){
        ProductUINMap productUINMap1=new ProductUINMap();
        productUINMap1.setProductId("p1");
        productUINMap1.setProductUin("pu1");
        productUINMap1=productUINMapDao.save(productUINMap1);

        ProductUINMap productUINMap2=new ProductUINMap();
        productUINMap2.setProductId("p2");
        productUINMap2.setProductUin("pu2");
        productUINMap2=productUINMapDao.save(productUINMap2);

    }


    /**
     * case when Order created successfully
     */
    @Test
    public void testAddOrder_1(){
        String retailerId="u1";
        String addressId="a1";
        String productId="p1";
        int quantity=10;
        //
        //adding cartitem for user
        //
        CartDTO cartDTO=new CartDTO();
        cartDTO.setId("c1");
        cartDTO.setUserId(retailerId);
        cartDTO.setProductId(productId);
        cartDTO.setQuantity(quantity);
        cartDTO=entityManager.merge(cartDTO);
        OrderDTO result=service.addOrder(retailerId,addressId);
        //
        // verifying order created as expected
        //
        List<OrderDTO>createdOrders=entityManager.createQuery("from OrderDTO ",OrderDTO.class).getResultList();
        Assertions.assertEquals(1,createdOrders.size());
        OrderDTO createdOrder=createdOrders.get(0);
        Assertions.assertEquals(createdOrder.getOrderId(),result.getOrderId());
        Assertions.assertEquals(retailerId,createdOrder.getUserId());
        //
        //verifying orderproductmap stored as expected
        //
        List<OrderProductMapDTO>storedOrderProductMaps=entityManager.createQuery("from OrderProductMapDTO",OrderProductMapDTO.class).getResultList();
        Assertions.assertEquals(1,storedOrderProductMaps.size());
        OrderProductMapDTO orderProductMap=storedOrderProductMaps.get(0);
        Assertions.assertEquals(result.getOrderId(),orderProductMap.getOrderId());
        Assertions.assertEquals(addressId,result.getAddressId());

        //
        //verifying product removed from cart
        //
        List<CartDTO>existingCartItems=entityManager.createQuery("from CartDTO",CartDTO.class).getResultList();
        Assertions.assertEquals(0,existingCartItems.size());

    }


    /**
     * case when order not created succesfully because cart is empty
     * verify EmptyCartException is thrown
     */
    @Test
    public void testAddOrder_2(){
        String retailerId="u1";
        String addressId="a1";
        Executable executable=()->{
            OrderDTO result=service.addOrder(retailerId,addressId);
        };
        Assertions.assertThrows(EmptyCartException.class,executable);
    }




}
