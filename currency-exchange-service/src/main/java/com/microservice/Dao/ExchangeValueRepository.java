package com.microservice.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dto.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Integer>
{
	ExchangeValue findByFromAndTo(String from , String To);
}
