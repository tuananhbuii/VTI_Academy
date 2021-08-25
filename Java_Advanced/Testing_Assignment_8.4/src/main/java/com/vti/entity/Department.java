package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vti.entity.enumerate.PositionName;
import com.vti.entity.enumerate.PositionNameConvert;

@Entity
@Table(name = "Department", catalog = "Testing_System_8")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DepartmentID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "DepartmentName", length = 30, nullable = false, unique = true)
	private String name;

	@Column(name = "TotalMember")
	private short totalMember;

	@Column(name = "PositionName", nullable = false, unique = true)
	@Convert(converter = PositionNameConvert.class)
	private PositionName positionName;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public Department() {
	}

	public Department(String name) {
		this.name = name;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getTotalMember() {
		return totalMember;
	}

	public void setTotalMember(short totalMember) {
		this.totalMember = totalMember;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public PositionName getPositionName() {
		return positionName;
	}

	public void setPositionName(PositionName positionName) {
		this.positionName = positionName;
	}

}