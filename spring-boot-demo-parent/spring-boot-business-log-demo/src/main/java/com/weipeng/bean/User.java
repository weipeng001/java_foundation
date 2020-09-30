/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.weipeng.bean;

import lombok.Data;

import java.util.Date;

/**
 * 实体类
 * 
 * @author wujing
 */
@Data
public class User {
	private int id;
	private String name;
	private Date date;
}
