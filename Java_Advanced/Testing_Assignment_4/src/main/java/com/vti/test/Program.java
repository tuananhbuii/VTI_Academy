package com.vti.test;

import java.util.List;

import com.vti.entity.Department;
import com.vti.repository.DepartmentRepository;
import com.vti.utils.ScannerUltis;

public class Program {

	public static void main(String[] args) {
		DepartmentRepository repository = new DepartmentRepository();
		while (true) {
			System.out.println("\t\t --- MENU --- \t\t");
			System.out.println("1. Get All Departments");
			System.out.println("2. Get Departments By ID");
			System.out.println("3. Get Departments By Name");
			System.out.println("4. Create Departments");
			System.out.println("5. Update Departments 1");
			System.out.println("6. Update Departments 2");
			System.out.println("7  Delete Departments");
			System.out.println("8. Check Departments Exist By ID");
			System.out.println("9. Check Departments Exist By Name");
			System.out.println("10. Exit");
			System.out.print("Mời nhập lựa chọn : ");
			int n = ScannerUltis.inputInt();
			switch (n) {
			case 1:
				System.out.println("***********GET ALL DepartmentS***********");

				List<Department> departments = repository.getAllDepartments();

				for (Department department : departments) {
					System.out.println(department);
				}
				break;
			case 2:
				System.out.println("\n\n***********GET DepartmentS BY ID***********");
				
				Department DepartmentById = repository.getDepartmentByID((short) 2);
				System.out.println(DepartmentById);
			
				break;
			case 3:
				System.out.println("\n\n***********GET DepartmentS BY NAME***********");

				Department DepartmentByName = repository.getDepartmentByName("Marketting");
				System.out.println(DepartmentByName);
				break;
			case 4:
				System.out.println("\n\n***********CREATE DepartmentS***********");

				Department DepartmentCreate = new Department();
				DepartmentCreate.setName("Waiting");
				repository.createDepartment(DepartmentCreate);
				System.out.println("Create Success !");
				break;
			case 5:
				System.out.println("\n\n***********UPDATE DepartmentS 1***********");

				repository.updateDepartment((short) 3, "Security");
				System.out.println("Update Success !");
				break;
			case 6:
				System.out.println("\n\n***********UPDATE DepartmentS 2***********");

				Department DepartmentUpdate = new Department();
				DepartmentUpdate.setId((short) 2);
				DepartmentUpdate.setName("Security2");
				repository.updateDepartment(DepartmentUpdate);
				System.out.println("Update Success !");
				break;
			case 7:
				System.out.println("\n\n***********DELETE DepartmentS***********");
				repository.deleteDepartment((short) 2);
				System.out.println("Delete Success !");
				break;
			case 8:
				System.out.println("***********CHECK DepartmentS EXISTS BY ID***********");
				System.out.println(repository.isDepartmentExistsByID((short) 1));
				break;
			case 9:
				System.out.println("***********CHECK DepartmentS EXISTS BY NAME***********");
				System.out.println(repository.isDepartmentExistsByName("Security"));
				break;
			case 10:
				System.exit(n);
				break;

			default:
				System.err.println("Nhập lại !");
				break;
			}
		}

	}
}
