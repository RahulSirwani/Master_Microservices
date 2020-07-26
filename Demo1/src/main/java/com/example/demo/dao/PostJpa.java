package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.PostsDTO;

@Repository
public interface PostJpa extends JpaRepository<PostsDTO,Integer>
{
	
}
