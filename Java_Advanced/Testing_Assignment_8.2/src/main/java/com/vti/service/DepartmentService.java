package com.vti.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vti.entity.Department;
import com.vti.filter.DepartmentFilter;
import com.vti.repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository repository;

	@SuppressWarnings("deprecation")
	public List<Department> getAllDepartments(DepartmentFilter filter, String search) {
		Specification<Department> where = null;
		// search by name
		if (!StringUtils.isEmpty(search)) {
			where = searchByName(search);
		}
		// if there is filter by min id
		if (filter.getMinID() != 0) {
			if (where != null) {
				where = where.and(greaterThanByID(filter.getMinID()));
			} else {
				where = greaterThanByID(filter.getMinID());
			}
		}
		if (filter.getMaxID() != 0) {
			if (where != null) {
				where = where.and(lessThanByID(filter.getMaxID()));
			} else {
				where = lessThanByID(filter.getMaxID());
			}
		}
		return repository.findAll(where);
	}

	// id > ?
	@SuppressWarnings("serial")
	Specification<Department> greaterThanByID(int id) {
		return new Specification<Department>() {

			@Override
			public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return criteriaBuilder.greaterThan(root.get("id"), id);
			}
		};
	}

	@SuppressWarnings("serial")
	// id > ?
	Specification<Department> lessThanByID(int id) {
		return new Specification<Department>() {

			@Override
			public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return criteriaBuilder.lessThan(root.get("id"), id);
			}
		};
	}

	@SuppressWarnings("serial")
	// id > ?
	Specification<Department> searchByName(String name) {
		return new Specification<Department>() {

			@Override
			public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return criteriaBuilder.like(root.get("name"), "%" + name + "%");
			}
		};
	}
}