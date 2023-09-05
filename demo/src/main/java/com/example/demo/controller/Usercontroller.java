package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.services.Userservices;

@RestController
public class Usercontroller {

	@Autowired
	private Userservices service;

	@PostMapping("signup")
	public ResponseEntity<User> saveUser(@RequestHeader("Messages") String Messages,@RequestBody User user){
		System.out.print(Messages);
		 return new ResponseEntity<User>(service.saveUser(user,Messages),HttpStatus.CREATED);
	}
	@GetMapping("getuserbyid")
	public User GetuserById(int id){
		 return service.getUserbyid(id);
	}
	@GetMapping("getallusers")
	public List<User> Getallusers(){
		 return service.getallusers();
	}
	@DeleteMapping("deleteuserbyid")
	public String Deleteuserbyid(int id){
		 return service.deleteuserbyid(id);
	}
	@GetMapping("getuserbyname")
	public User Getuserbyname(String name){
		return service.Getuserbyname(name);
	}
	@PutMapping("updateuser")

		public User Updateuser(@RequestBody User user) {
			return service.updateUser(user);
		}

}
