/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.weipeng.controller;

import com.weipeng.bean.User;
import com.weipeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

/**
 * spring-boot-demo-2-1
 * 
 * @author wujing
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

	@Autowired
	private  UserService userService;

	@RequestMapping
	public String index(){
		return "hello world";
	}

	// @RequestParam 简单类型的绑定，可以出来get和post
	@RequestMapping(value = "/get")
	public HashMap<String, Object> get(@RequestParam String name) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "hello world");
		map.put("name", name);
		return map;
	}

	// @PathVariable 获得请求url中的动态参数
	@RequestMapping(value = "/get/{id}/{name}")
	public User getUser(@PathVariable int id, @PathVariable String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setDate(new Date());
		userService.add(user);
		return user;
	}

}
