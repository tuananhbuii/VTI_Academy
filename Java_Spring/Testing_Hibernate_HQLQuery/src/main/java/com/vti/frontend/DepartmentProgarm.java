package com.vti.frontend;

import java.util.List;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.repository.DepartmentRepository;
import com.vti.utils.ScannerUltis;

public class DepartmentProgarm {
	public static void main(String[] args) {

		while (true) {
			System.out.println("------MỜI BẠN CHỌN CHỨC NĂNG------");
			String leftAlignFormat = "| %-72s |%n";
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format("|                        Choose please                                     |%n");
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format(leftAlignFormat, "1. Danh sách Department trên hệ thống");
			System.out.format(leftAlignFormat, "2. Danh sách Department Theo ID");
			System.out.format(leftAlignFormat, "3. Tạo mới Department");
			System.out.format(leftAlignFormat, "4. Xóa Department");
			System.out.format(leftAlignFormat, "5. Update Department");
			System.out.format(leftAlignFormat, "6. Lấy danh sách nhân viên phòng theo ID Department");
			System.out.format(leftAlignFormat, "7. Exit");
			System.out.format("+--------------------------------------------------------------------------+%n");
			switch (ScannerUltis.inputIntPositive()) {
			case 1:
				getAllDepartment();
				break;
			case 2:
				getDepartmentByID();

				break;
			case 3:
				createDepartment();

				break;
			case 4:
				deleteDepartment();

				break;
			case 5:
				updateDepartment();

				break;
			case 6:
				getAccountDepartmentByID();

				break;
			case 7:

				return;
			default:
				System.out.println("Nhập lại:");
				break;
			}
		}
	}

	private static void getAccountDepartmentByID() {
		DepartmentRepository depRepository = new DepartmentRepository();
		int idDep = getIdUpdate();
		Department dep = depRepository.getDepartmentByID((short) idDep);
		List<Account> listAcc = dep.getAccount();
		String leftAlignFormat = "| %-6d | %-21s |%n";
		System.out.format("+--------+-----------------------+%n");
		System.out.format("|   ID   | Email                 |%n");
		System.out.format("+--------+-----------------------+%n");

		for (Account account : listAcc) {
			System.out.format(leftAlignFormat, account.getId(), account.getEmail());
		}

		System.out.format("+--------+-----------------------+%n");
	}

	private static void updateDepartment() {
		DepartmentRepository depRepository = new DepartmentRepository();
		int updateID = getIdUpdate();
		System.out.println("Nhập vào tên cần Updare: ");
		String newName = ScannerUltis.inputString();
		Department dep = new Department();
		dep.setId((short) updateID);
		dep.setName(newName);
		depRepository.updateDepartment(dep);
		getAllDepartment();
	}

	private static void deleteDepartment() {
		DepartmentRepository depRepository = new DepartmentRepository();
		int updateID = getIdUpdate();
		depRepository.deleteDepartment((short) updateID);
		getAllDepartment();

	}

	private static int getIdUpdate() {
		DepartmentRepository depRepository = new DepartmentRepository();
		while (true) {
			System.out.println("Nhập ID phòng cần thao tác: ");
			int id = ScannerUltis.inputIntPositive();
			Department dep = depRepository.getDepartmentByID((short) id);
			if (dep == null) {
				System.out.println("Không có ID này trên HT");
			} else {
				return id;
			}
		}
	}

	private static void getDepartmentByID() {
		System.out.println("Tìm kiếm phòng theo ID: ");
		System.out.println("Nhập vào ID cần tìm kiếm: ");
		int idFind = ScannerUltis.inputIntPositive();
		DepartmentRepository depRepository = new DepartmentRepository();
		Department depQues3 = depRepository.getDepartmentByID((short) idFind);
		if (depQues3 != null) {
			String leftAlignFormat = "| %-6d | %-21s |%n";
			System.out.format("+--------+-----------------------+%n");
			System.out.format("|   ID   | Department Name       |%n");
			System.out.format("+--------+-----------------------+%n");
			System.out.format(leftAlignFormat, depQues3.getId(), depQues3.getName());
			System.out.format("+--------+-----------------------+%n");
		} else {
			System.out.println("Không tồn tại phòng này trên HT");
		}

	}

	private static void createDepartment() {
		DepartmentRepository depRepository = new DepartmentRepository();
		String newNameDep = getNewName();
		Department dep = new Department();
		dep.setName(newNameDep);
		depRepository.createDepartment(dep);
		depRepository.getAllDepartments();
	}

	private static String getNewName() {
		DepartmentRepository depRepository = new DepartmentRepository();
		while (true) {
			System.out.println("Nhập vào tên phòng cần tạo: ");
			String newName = ScannerUltis.inputString();
			Department depQues3 = depRepository.getDepartmentByName(newName);
			if (depQues3 != null) {
				System.out.println("Đã có phòng trên hệ thống");
			} else {
				return newName;
			}
		}
	}

	private static void getAllDepartment() {
		System.out.println("Danh sách Department trên hệ thống");
		DepartmentRepository depRepository = new DepartmentRepository();
		List<Department> listdep = depRepository.getAllDepartments();
		String leftAlignFormat = "| %-5s | %-25s |%n";
		System.out.format("+-------+---------------------------+%n");
		System.out.format("| ID	   |   Department              |%n");
		System.out.format("+-------+---------------------------+%n");

		for (Department dep : listdep) {
			System.out.format(leftAlignFormat, dep.getId(), dep.getName());
		}

	}

}
