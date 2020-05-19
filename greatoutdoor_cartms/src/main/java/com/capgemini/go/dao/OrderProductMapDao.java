package com.capgemini.go.dao;



import org.springframework.data.repository.CrudRepository;

import com.capgemini.go.dto.OrderProductMapDTO;

public interface OrderProductMapDao extends CrudRepository<OrderProductMapDTO, String>{
	

}
