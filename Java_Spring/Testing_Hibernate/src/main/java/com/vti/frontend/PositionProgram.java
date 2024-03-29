package com.vti.frontend;

import java.util.List;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.repository.AccountRepository;
import com.vti.repository.DepartmentRepository;
import com.vti.utils.ScannerUltis;

public class PositionProgram {

	public static void main(String[] args) {
			while (true) {
				System.out.println("------MỜI BẠN CHỌN CHỨC NĂNG------");
				String leftAlignFormat = "| %-72s |%n";
				System.out.format("+--------------------------------------------------------------------------+%n");
				System.out.format("|                        Choose please                                     |%n");
				System.out.format("+--------------------------------------------------------------------------+%n");
				System.out.format(leftAlignFormat, "1. Danh sách Account trên hệ thống");
				System.out.format(leftAlignFormat, "2. Danh sách Account Theo ID");
				System.out.format(leftAlignFormat, "3. Tạo mới Account");
				System.out.format(leftAlignFormat, "4. Xóa Account");
				System.out.format(leftAlignFormat, "5. Update Account");
				System.out.format(leftAlignFormat, "6.	Exit");
				System.out.format("+--------------------------------------------------------------------------+%n");
				switch (ScannerUltis.inputIntPositive()) {
				case 1:
					getAllAccount();
					break;
				case 2:
					getAccountByID();
					break;
				case 3:
					createAccount();
					break;
				case 4:
					DeleteAccount();
					break;
				case 5:
					updateAccount();
					break;
				case 6:
					return;
				default:
					System.out.println("Nhập lại:");
					break;
				}
			}
		}

		private static void getAccountByID() {
			System.out.println("Tìm kiếm Account theo ID: ");
			System.out.println("Nhập vào ID cần tìm kiếm: ");
			int idFind = ScannerUltis.inputIntPositive();
			AccountRepository accRepository = new AccountRepository();
			Account acc = accRepository.getAccountByID((short) idFind);
			if (acc != null) {
				String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-16s | %n";
				System.out.format(
						"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");
				System.out.format(
						"|ID  | Email                 | Username        |   FullName            | Department     | Create Date            |%n");
				System.out.format(
						"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");

				System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
						acc.getDepartment().getName(), acc.getCreateDate());

				System.out.format(
						"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");
			} else {
				System.out.println("Không tồn tại account này trên HT");
			}

		}

		private static void updateAccount() {
			AccountRepository accRepository = new AccountRepository();
			System.out.println("Nhập vào Id cần Update: ");
			int id = ScannerUltis.inputIntPositive();
			System.out.println("Nhập vào tên cần Updare: ");
			String newName = ScannerUltis.inputString();
			accRepository.updateAccount_FullName((short) id, newName);
			getAllAccount();

		}

		private static void DeleteAccount() {
			AccountRepository accRepository = new AccountRepository();
			int id = getIdDel();
			accRepository.deleteAccount((short) id);
		}

		private static int getIdDel() {
			AccountRepository accRepository = new AccountRepository();
			while (true) {
				System.out.println("Nhập vào ID Account cần xóa: ");
				int id = ScannerUltis.inputIntPositive();
				if (accRepository.getAccountByID((short) id) != null) {
					return id;
				} else {
					System.out.println("Không có Account này trên hệ thống, Nhập lại: ");
				}
			}
		}

		private static void createAccount() {
			Account acc = new Account();
			System.out.println("Nhập vào Email: ");
			acc.setEmail(ScannerUltis.inputEmail());
			System.out.println("Nhập vào UserName: ");
			acc.setUsername(ScannerUltis.inputString());
			System.out.println("Nhập vào FullName: : ");
			acc.setFullname(ScannerUltis.inputString());
			System.out.println("Hãy chọn phòng nhân viên: ");
			Department dep = getDep();
			acc.setDepartment(dep);
			AccountRepository accRepository = new AccountRepository();
			accRepository.createAccount(acc);
			getAllAccount();
		}

		private static Department getDep() {
			while (true) {
				DepartmentRepository depRepository = new DepartmentRepository();
				List<Department> listDep = depRepository.getAllDepartments();
				String leftAlignFormat = "| %-6d | %-21s |%n";

				System.out.format("+--------+-----------------------+%n");
				System.out.format("|   ID   | Depament Name         |%n");
				System.out.format("+--------+-----------------------+%n");
				for (Department department : listDep) {
					System.out.format(leftAlignFormat, department.getId(), department.getName());
				}
				System.out.format("+--------+-----------------------+%n");
				System.out.println("Chọn phòng theo ID:");
				int chooseDep = ScannerUltis.inputIntPositive();
				Department dep = depRepository.getDepartmentByID((short) chooseDep);
				if (dep != null) {
					return dep;
				} else {
					System.out.println("Không có phòng này, hãy chọn lại: ");
				}
			}
		}

		private static void getAllAccount() {

			System.out.println("Danh sách Account trên hệ thống");
			AccountRepository accRepository = new AccountRepository();
			List<Account> listAcc = accRepository.getAllAccount();

			String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-14s | %-16s | %n";
			System.out.format(
					"+----+-----------------------+-----------------+-----------------------+----------------+----------------+------------------------+%n");
			System.out.format(
					"|ID  | Email                 | Username        |   FullName            | Department     | Possition      | Create Date            |%n");
			System.out.format(
					"+----+-----------------------+-----------------+-----------------------+----------------+----------------+------------------------+%n");
			//PositionNameConvert pnc = new PositionNameConvert();
			for (Account acc : listAcc) {
				System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
						acc.getDepartment().getName(), acc.getPosition().getName(),
						acc.getCreateDate());
			}
			System.out.format(
					"+----+-----------------------+-----------------+-----------------------+----------------+----------------+------------------+%n");
		}

}
