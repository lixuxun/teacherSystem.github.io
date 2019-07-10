package org.shop.pojo;

import java.math.BigDecimal;
import java.util.Date;




public class User{
	private int  id;
	private String name;
	private String password;
	private int sex;
	private String phone;
	private String year;
	private Date  date;
	private int position;
	private int idPass;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getIdPass() {
		return idPass;
	}

	public void setIdPass(int idPass) {
		this.idPass = idPass;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", sex=" + sex +
				", phone='" + phone + '\'' +
				", year='" + year + '\'' +
				", date=" + date +
				", position=" + position +
				", idPass=" + idPass +
				'}';
	}
}

