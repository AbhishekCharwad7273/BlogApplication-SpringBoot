package com.Abhi.BlogApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Abhi.BlogApp.repository.UserRepo;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private UserRepo userRepo;//userrepo implementation class object call 
	
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest()
	{
		String className=this.userRepo.getClass().getName();
		System.out.println(className);//$proxy101 implemented Dynamically class Created 
		String packegeName=this.userRepo.getClass().getPackageName();
		System.out.println(packegeName);
	}

}
