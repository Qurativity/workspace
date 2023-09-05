package com.example.demo.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.constants;
import com.example.demo.entity.User;
import com.example.demo.repository.Userrepository;

@Service
public class Userservices{
	@Autowired
	private Userrepository repository;

	public User saveUser(User user,String Message)
	{

		sendMessage(user,Message);
		return repository.save(user);


	}
	public User getUserbyid(int id)
	{
		return repository.findById(id).orElse(null);
	}
	public List<User> getallusers()
	{
		return repository.findAll();
	}
	public String deleteuserbyid(int id)
	{
		repository.deleteById(id);
		return "User Deleted "+ id;
	}
	public User Getuserbyname(String user_name)
	{
		return repository.findByname(user_name);
	}
	public User updateUser(User user)
	{
		User existinguser = repository.findById(user.getId()).orElse(null);
		existinguser.setName(user.getName());
		existinguser.setPassword(user.getPassword());
		existinguser.setStatus(user.getStatus());
		return repository.save(existinguser);
	}

	public void sendMessage(User user,String Message) {


		String url="token="+constants.token+"&to=+91"+user.getPhone()+"&body="+Message+"";
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
		    .uri(URI.create(constants.URL))
		    .POST(BodyPublishers.ofString(url))
		    .setHeader("content-type", "application/x-www-form-urlencoded")
		    .build();
			try {
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
