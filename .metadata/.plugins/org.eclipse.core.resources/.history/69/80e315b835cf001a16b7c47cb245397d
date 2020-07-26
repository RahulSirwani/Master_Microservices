package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All de")
@Entity
public class UserDTO 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min = 2 , message = "Minimum two character required")
	@ApiModelProperty(notes = "Name should have Minimum two characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "dob should be in past")
	private Timestamp dob;
	
	/*User can make List of Posts
	 * 
	 */
	@OneToMany(mappedBy = "user")
	private List<PostsDTO> posts;
	
	public UserDTO() 
	{
		
	}
	
	public UserDTO(int id , String name , Timestamp dob) 
	{
		this.id=id;
		this.name=name;
		this.dob=dob;
	}
	
	
	
	public List<PostsDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostsDTO> posts) {
		this.posts = posts;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getDob() {
		return dob;
	}
	public void setDob(Timestamp dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
}
