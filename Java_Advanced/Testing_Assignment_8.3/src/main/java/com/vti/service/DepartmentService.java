package com.vti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vti.entity.Department;
import com.vti.filter.DepartmentFilter;
import com.vti.repository.IDepartmentRepository;
import com.vti.specification.DepartmentSpecificationBuilder;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository repository;

	@Override
	public List<Department> getAllDepartments(DepartmentFilter filter, String search) {
		DepartmentSpecificationBuilder specificationBuilder = new DepartmentSpecificationBuilder(filter, search);
		return repository.findAll(specificationBuilder.build());
	}
	
}