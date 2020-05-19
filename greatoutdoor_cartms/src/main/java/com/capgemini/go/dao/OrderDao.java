package com.capgemini.go.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.go.dto.OrderDTO;

import java.util.List;


public interface OrderDao extends CrudRepository<OrderDTO, String>{

    List<OrderDTO>findByUserId(String userId);

}
