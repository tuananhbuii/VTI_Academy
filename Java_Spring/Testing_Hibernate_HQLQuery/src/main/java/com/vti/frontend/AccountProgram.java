package com.vti.frontend;

import java.util.List;

import com.vti.entity.Account;
import com.vti.repository.AccountRepository;
import com.vti.utils.ScannerUltis;

public class AccountProgram {
	static AccountRepository accountRepository = new AccountRepository();

	public static void main(String[] args) {
		while (true) {
			System.out.println("------MỜI BẠN CHỌN CHỨC NĂNG------");
			String leftAlignFormat = "| %-72s |%n";
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format("|                        Choose please                                     |%n");
			System.out.format("+--------------------------------------------------------------------------+%n");
			System.out.format(leftAlignFormat, "1. Danh sách Account trên hệ thống");
			System.out.format(leftAlignFormat, "2. Danh sách Account Theo ID");
			System.out.format(leftAlignFormat, "3. Tìm tên theo ID");
			System.out.format(leftAlignFormat, "4. Danh sách Account tháng hiện tại");
			System.out.format(leftAlignFormat, "5. Danh sách Account tháng hiện tại sắp xếp tăng/giảm dần");
			System.out.format(leftAlignFormat, "6. Số lượng Account theo tháng");
			System.out.format(leftAlignFormat, "7. Số lượng Account theo tháng > 2");
			System.out.format(leftAlignFormat, "8. Update Account");
			System.out.format(leftAlignFormat, "9. Delete Account");
			System.out.format(leftAlignFormat, "10.	Phân trang");
			System.out.format(leftAlignFormat, "11.	Exit");
			System.out.format("+--------------------------------------------------------------------------+%n");
			switch (ScannerUltis.inputIntPositive()) {
			case 1:
				getAllAccount();
				break;
			case 2:
				getAccountByID();
				break;
			case 3:
				getFullNameByID();
				break;
			case 4:
				getUserByMonth();
				break;
			case 5:
				getUserByMonthOrder();
				break;
			case 6:
				countUserByMonth();
				break;
			case 7:
				countUserByMonth2();
				break;
			case 8:
				update();
				break;
			case 9:
				delete();
				break;
			case 10:
				paging();
				break;
			case 11:
				return;
			default:
				System.out.println("Nhập lại:");
				break;
			}
		}
	}

	private static void getAllAccount() {

		List<Account> list = accountRepository.get_FROM();
		String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-16s | %n";
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");
		System.out.format(
				"|ID  | Email                 | Username        |   FullName            | Department     | Position               |%n");
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");

		for (Account acc : list) {
			System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
					acc.getDepartment().getName(), acc.getPosition().getName());
		}
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------+%n");
	}

	private static void getAccountByID() {
		System.out.println("Nhập vào ID của Account cần tìm kiếm: ");
		int id = ScannerUltis.inputIntPositive();
		Account acc = accountRepository.get_ByID((short) id);
		if (acc != null) {
			String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-16s | %n";
			System.out.format(
					"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");
			System.out.format(
					"|ID  | Email                 | Username        |   FullName            | Department     | Position               |%n");
			System.out.format(
					"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");

			System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
					acc.getDepartment().getName(), acc.getPosition().getName());

			System.out.format(
					"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");
		} else {
			System.out.println("Không tồn tại account này trên HT");
		}
	}

	private static void getFullNameByID() {
		System.out.println("Nhập vào ID của Account cần tìm kiếm: ");
		int id = ScannerUltis.inputIntPositive();
		String fullname = accountRepository.get_FullName((short) id);
		System.out.println("Fullname là: " + fullname);
	}

	private static void getUserByMonth() {
		System.out.println("Thông tin User được tạo trong tháng hiện tại.");
		List<Account> list = accountRepository.get_ByCurrentMonth();
		String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-16s | %n";
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");
		System.out.format(
				"|ID  | Email                 | Username        |   FullName            | Department     | Position               |%n");
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");

		for (Account acc : list) {
			System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
					acc.getDepartment().getName(), acc.getPosition().getName());
		}
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------+%n");

	}
	
	private static void getUserByMonthOrder() {
		System.out.println("Thông tin User được tạo trong tháng hiện tại.");
		List<Account> list = accountRepository.get_ByCurrentMonthOderBy();
		String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-16s | %n";
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");
		System.out.format(
				"|ID  | Email                 | Username        |   FullName            | Department     | Position               |%n");
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");

		for (Account acc : list) {
			System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
					acc.getDepartment().getName(), acc.getPosition().getName());
		}
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------+%n");

	}

	private static void countUserByMonth() {
		System.out.println("Thông tin số lượng Account được tạo theo các tháng.");
		List<Object[]> list = accountRepository.get_CountAccByMonthInYearCurrent();
//		Lặp các Array trong List sau đó in ra thông tin các phần tử mảng theo Index
		for (Object[] object : list) {
			System.out.println("Month: " + object[0] + "Count: " + object[1]);
		}
	}
	
	private static void countUserByMonth2() {
		System.out.println("Tìm tháng có số lượng Account được tạo >2.");
		List<Object[]> list = accountRepository.get_CountAccByMonthInYearCurrentgt2();
//		Lặp các Array trong List sau đó in ra thông tin các phần tử mảng theo Index
		for (Object[] object : list) {
			System.out.println("Month: " + object[0] + "Count: " + object[1]);
		}
	}
	
	private static void update() {

		System.out.println("Nhập vào ID cần Update: ");
		int id = ScannerUltis.inputIntPositive();
		System.out.println("Nhập vào NewEmail: ");
		String email = ScannerUltis.inputEmail();
		System.out.println("Nhập vào NewUsername: ");
		String username = ScannerUltis.inputString();

		accountRepository.updateEmailUsernameAccountByID(email, username, (short) id);

	}
	private static void delete() {
		System.out.println("Nhập vào email cần xóa: ");
		String email = ScannerUltis.inputEmail();
		accountRepository.deleteAccount(email);
	}
	
	private static void paging() {
		System.out.println("Demo Phân trang với Hibernate: ");
		List<Account> list = accountRepository.get_AccountByPaging();
		String leftAlignFormat = "| %-2d | %-21s | %-15s | %-21s | %-14s | %-16s | %n";
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");
		System.out.format(
				"|ID  | Email                 | Username        |   FullName            | Department     | Position               |%n");
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------------+%n");

		for (Account acc : list) {
			System.out.format(leftAlignFormat, acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullname(),
					acc.getDepartment().getName(), acc.getPosition().getName());
		}
		System.out.format(
				"+----+-----------------------+-----------------+-----------------------+----------------+------------------+%n");
	}
}
