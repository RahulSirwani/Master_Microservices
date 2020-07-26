package com.example.demo.ctl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PostJpa;
import com.example.demo.dao.UserJpa;
import com.example.demo.dto.PostsDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.service.UserService;



@RestController
public class UserCtl 
{
	@Autowired
	UserService service;
	
	@Autowired
	private UserJpa dao;
	
	@Autowired
	private PostJpa postDao;
	
	@Autowired
	MessageSource messagesource;
	
	@GetMapping("/users")
	public List<UserDTO> getAll()
	{
		List<UserDTO> list = dao.findAll();
		return list;
//		return service.getAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<UserDTO> getById(@PathVariable int id)
	{
		Optional<UserDTO> dto = dao.findById(id);
//		UserDTO dto = service.getById(id);
		if(!dto.isPresent())
		{
			throw new CustomException("This id Not Found");
		}
		//Hateoas
		/*
		 *In case you are using HATEOAS v1.0 and above (Spring boot >= 2.2.0), do 
		 *note that the classnames have changed. Notably the below classes have 4
		 *been renamed:
		 *  ResourceSupport   -->  changed to RepresentationModel
		 *  Resource          -->  changed to EntityModel
		 *  Resources         -->  changed to CollectionModel
		 *  PagedResources    -->  changed to PagedModel
		 *  ResourceAssembler -->  changed to RepresentationModelAssembler 
		 **/
//		EntityModel<UserDTO> resource =EntityModel.of(dto);
		EntityModel<UserDTO> resource =EntityModel.of(dto.get());
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAll());
		
		resource.add(linkTo.withRel("all-users"));
		
//		return service.getById(id);
		return resource;
	}
	
	@PostMapping("/users")
	public void save(@Valid @RequestBody UserDTO dto)
	{
		dao.save(dto);
//		service.save(dto);
	}
	
	@GetMapping("/delete/{id}")
	public void delete(@PathVariable int id)
	{
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dao.delete(dto);
//		UserDTO dto = service.delete(id);
		
//		if(dto==null)
//		{
//			throw new CustomException("This User doesn't Exists");
//		}
	}
	
//	@GetMapping("/i18n")
//	public String getMessage(@RequestHeader(name = "Accept-Language",required = false) Locale locale )
//	{
//		return messagesource.getMessage("good.morning.message",null, locale);
//	}
	
	@GetMapping("/i18n")
	public String getMessage()
	{
		return messagesource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/users/{id}/posts")
	public List<PostsDTO> getAllPost(@PathVariable int id)
	{
		Optional<UserDTO> dto =  dao.findById(id);
		
		if(!dto.isPresent())
		{
			throw new CustomException("this "+id+" id User not Found");
		}
		return dto.get().getPosts();
	}
	
	@PostMapping("/users/{id}/posts")
	public EntityModel<PostsDTO> savePost(@PathVariable int id , @RequestBody PostsDTO post)
	{
		Optional<UserDTO> dto = dao.findById(id);
		if(!dto.isPresent())
		{
			throw new CustomException("this "+id+" id User not Found");
		}
		
		UserDTO user = dto.get();
		post.setUser(user);
		
		postDao.save(post);
		
		/*
		 *A simple EntityModel wrapping a domain object and adding 
		 *links to it. 
		 */
		EntityModel<PostsDTO> resource =EntityModel.of(post);
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllPost(id));
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
}
