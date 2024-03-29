package com.vti.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.repository.DepartmentRepository;
import com.vti.validation.CheckDepartmentExists;
import com.vti.validation.CheckEmailNotExists;

public class AccountDTO {
	@NotBlank(message = "Email không được để trống")
	@Length(min = 6, max = 50, message = "Độ dài email không hợp lệ")
	@Email(message = "Format Email không hợp lệ")
	@CheckEmailNotExists(message = "Email này đã có trên hệ thống, hãy lựa chọn Email khác!!")
	private String email;

	@NotBlank(message = "Username không được để trống")
	@Length(min = 6, max = 50, message = "Độ dài Username không hợp lệ")
	private String username;

	@NotBlank(message = "FullName không được để trống")
	@Length(min = 6, max = 50, message = "Độ dài FullName không hợp lệ")
	private String fullname;

	@Positive(message = "Lựa chọn ID là số dương")
	@CheckDepartmentExists
	private int departmentID;

	public AccountDTO(String email, String username, String fullname) {
		super();
		this.email = email;
		this.username = username;
		this.fullname = fullname;
	}

	public AccountDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the departmentID
	 */
	public int getDepartmentID() {
		return departmentID;
	}

	/**
	 * @param departmentID the departmentID to set
	 */
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public Account toEntity() {
		DepartmentRepository departmentRepository = new DepartmentRepository();
		Department department = departmentRepository.getDepartmentByID((short) departmentID);
		return new Account(email, username, fullname, department);
	}
}

