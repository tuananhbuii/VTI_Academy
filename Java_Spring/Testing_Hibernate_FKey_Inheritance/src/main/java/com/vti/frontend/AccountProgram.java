package com.vti.frontend;

import java.util.List;

import com.vti.entity.Account;
import com.vti.entity.DetailDepartment;
import com.vti.repository.AccountRepository;
import com.vti.repository.DetailDepartmentRepository;
import com.vti.utils.ScannerUltis;

public class AccountProgram {
	public static void main(String[] args) {
		while (true) {
			System.out.println("------MỜI BẠN CHỌN CHỨC NĂNG------");
			String leftAlignFormat = "| %-72s |%n";
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format("|                        Choose please                                     |%n");
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format(leftAlignFormat, "1. Danh sách Account trên hệ thống");
			System.out.format(leftAlignFormat, "2. Danh sách DetailDepartment");
			System.out.format(leftAlignFormat, "3. Exit");
			System.out.format("+--------------------------------------------------------------------------+%n");
			switch (ScannerUltis.inputIntPositive()) {
			case 1:
				AccountList();
				break;
			case 2:
				DetailDeparment();
				break;
			case 3:
				return;
			default:
				System.err.println("Nhập lại !");
				break;
			}
		}
	}

	private static void AccountList() {
		System.out.println("Danh sách Account trên hệ thống");
		AccountRepository accRepository = new AccountRepository();
		List<Account> listAcc = accRepository.getAllAccount();
		String leftAlignFormat = "| %-2d | %-21s | %-15s | %n";
		System.out.format("+----+-----------------------+-----------------+%n");
		System.out.format("|ID  | Username              | Department      |%n");
		System.out.format("+----+-----------------------+-----------------+%n");
		for (Account account : listAcc) {
			System.out.format(leftAlignFormat,
					account.getId() ,account.getUsername() , account.getDepartment().getName());
		}
		System.out.format("+----+-----------------------+-----------------+%n");
	}

	private static void DetailDeparment() {

		System.out.println("Danh sách DetailDepartment trên hệ thống");
		DetailDepartmentRepository detailDepartmentRepository = new DetailDepartmentRepository();
		List<DetailDepartment> listDetailDepartment = detailDepartmentRepository.getDetailDepartment();
		String leftAlignFormat = "| %-2d | %-14s | %-15s | %n";
		System.out.format("+----+----------------+-----------------%n");
		System.out.format("|ID  | Department     | Address         |%n");
		System.out.format("+----+----------------+-----------------+%n");
		for (DetailDepartment detailDepartment : listDetailDepartment) {
			System.out.format(leftAlignFormat, detailDepartment.getId(), detailDepartment.getName(),
					detailDepartment.getAddress().getName());
		}
		System.out.format("+----+----------------+-----------------+%n");
	}
}
