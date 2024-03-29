package com.vti.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
	@Min(value = 1, message = "ID không hợp lệ !!")
	private int id;
	@Length(min = 6, max = 12, message = "Tên không hợp lệ")
	private String name;
	@Email(message = "Thông tin email không hợp lệ !")
	private String email;
	@Min(value = 18, message = "Chưa đủ tuổi !!")
	private int age;
}
