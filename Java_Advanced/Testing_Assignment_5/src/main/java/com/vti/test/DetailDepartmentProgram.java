package com.vti.test;

import java.util.List;

import com.vti.entity.Address;
import com.vti.entity.DetailDepartment;
import com.vti.repository.AddressRepository;
import com.vti.repository.DetailDepartmentRepository;

public class DetailDepartmentProgram {
	public static void main(String[] args) {
		DetailDepartmentRepository repository = new DetailDepartmentRepository();
		AddressRepository addressRepository = new AddressRepository();
		System.out.println("--------------- GET ALL SALARIES ---------------");
		List<DetailDepartment> list = repository.getAllDetailDepartments();
		for (DetailDepartment detailDepartment : list) {
			System.out.println(detailDepartment);
		}
		System.out.println("--------------- CREATE SALARIES ---------------");
		DetailDepartment detailDepartment = new DetailDepartment();
		detailDepartment.setName("New Product");
		detailDepartment.setEmulationPoint((short) 7);
		//Address address = new Address("P777");
		Address address = addressRepository.getAddressByID((short)1);
		detailDepartment.setAddress(address);
		repository.createDetailDepartment(detailDepartment);
	}
}