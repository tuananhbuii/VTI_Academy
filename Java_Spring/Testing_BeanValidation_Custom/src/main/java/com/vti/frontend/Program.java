package com.vti.frontend;

import java.util.List;

import com.vti.controller.AccountController;
import com.vti.controller.DepartmentController;
import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.utils.ScannerUltis;

public class Program {

	public static void main(String[] args) {

		while (true) {
			System.out.println("------MỜI BẠN CHỌN CHỨC NĂNG------");
			String leftAlignFormat = "| %-72s |%n";
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format("|                        Choose please                                     |%n");
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format(leftAlignFormat, "1. Danh sách Account trên hệ thống");
			System.out.format(leftAlignFormat, "2. Tạo mới Account");
			System.out.format(leftAlignFormat, "3.	Exit");
			System.out.format("+--------------------------------------------------------------------------+%n");
			switch (ScannerUltis.inputIntPositive()) {
			case 1:
				getAllAccount();

				break;
			case 2:
				createAccount();

				break;
			case 3:

				return;
			default:
				System.out.println("Nhập lại:");
				break;
			}
		}

	}

	private static void createAccount() {
		AccountController controller = new AccountController();
		System.out.println("\n\n***********CREATE ACCOUNT***********");
		AccountDTO acc = new AccountDTO();
		System.out.println("Nhập vào Email: ");
		acc.setEmail(ScannerUltis.inputEmail());
		System.out.println("Nhập vào UserName: ");
		acc.setUsername(ScannerUltis.inputString());
		System.out.println("Nhập vào FullName: : ");
		acc.setFullname(ScannerUltis.inputString());

		System.out.println("Hãy chọn phòng nhân viên: ");
		int depId = getDep();
		acc.setDepartmentID(depId);
		controller.createAccount(acc);
//		getAllAccount();

	}

	private static int getDep() {
		DepartmentController depController = new DepartmentController();
		List<Department> listDep = depController.getAllDepartments();
		String leftAlignFormat = "| %-6d | %-21s |%n";

		System.out.format("+--------+-----------------------+%n");
		System.out.format("|   ID   | Depament Name         |%n");
		System.out.format("+--------+-----------------------+%n");
		for (Department department : listDep) {
			System.out.format(leftAlignFormat, department.getId(), department.getName());
		}
		System.out.format("+--------+-----------------------+%n");
		System.out.println("Chọn phòng theo ID:");
		int chooseDep = ScannerUltis.inputInt();
		return chooseDep;
	}

	private static void getAllAccount() {
		System.out.println("Danh sách Account trên hệ thống");
		AccountController accController = new AccountController();
		List<Account> listAcc = accController.getAllAccount();

		String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-16s | %-16s | %n";
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------+------------------+%n");
		System.out.format(
				"|ID  | Email                 | Username        |   FullName            | Department     | Position         | Create Date      |%n");
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------+------------------+%n");

		for (Account acc : listAcc) {
			System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
					acc.getDepartment().getName(), acc.getPosition().getName(), acc.getCreateDate());
		}
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------+------------------+%n");

	}

}

