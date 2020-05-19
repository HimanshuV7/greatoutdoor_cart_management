package com.capgemini.go.dao;

import com.capgemini.go.dto.CartDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartDao extends CrudRepository<CartDTO, String>{

    List<CartDTO>findByUserId(String userId);

}
