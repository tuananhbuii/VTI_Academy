
import java.time.LocalDate;
import java.util.Scanner;

public class Ex5_Input_from_console {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Question1();
		Question2();
		Question3();
		Question4();
		Question5();
		Question6();
		Question7();
		Question8();
		Question9();
		Question10();
		Question11();

	}

	private static void Question1() {
		// Viết lệnh cho phép người dùng nhập 3 số nguyên vào chương trình
		System.out.println("\t --- Question 1 --- \t");
		int arrayInt[] = new int[3];
		for (int i = 0; i < 3; i++) {
			System.out.print("Mời nhập vào số thứ " + (i + 1 + " : "));
			arrayInt[i] = scanner.nextInt();
		}

		for (int i = 0; i < 3; i++) {
			System.out.println("Số thứ " + (i + 1) + " là: " + arrayInt[i]);
		}
	}

	private static void Question2() {
		// Viết lệnh cho phép người dùng nhập 2 số thực vào chương trình
		System.out.println("\t --- Question 2 --- \t");
		float array[] = new float[2];
		for (int i = 0; i < array.length; i++) {
			System.out.print("Nhập vào số thực thứ " + (i + 1) + " : ");
			array[i] = scanner.nextFloat();
		}
		for (int i = 0; i < array.length; i++) {
			System.out.println("Số thứ " + (i + 1) + " là : " + array[i]);
		}
	}

	private static void Question3() {
		// Viết lệnh cho phép người dùng nhập họ và tên
		System.out.println("\t --- Question 3 --- \t");
		System.out.print("Nhập vào Họ tên : ");
		String Hoten = scanner.nextLine();
		System.out.println("Họ tên là : " + Hoten);
	}

	private static void Question4() {
		// Viết lệnh cho phép người dùng nhập vào ngày sinh nhật của họ
		System.out.println("\t --- Question 4 --- \t");
		System.out.println("Question 4: ");
		System.out.print("Mời bạn nhập năm sinh : ");
		int year = scanner.nextInt();
		System.out.print("Mời bạn nhập tháng sinh : ");
		int month = scanner.nextInt();
		System.out.print("Mời bạn nhập ngày sinh : ");
		int day = scanner.nextInt();
		LocalDate dateBirth = LocalDate.of(day, month, year);
		System.out.println("Ngày sinh của bạn là: " + dateBirth);

	}

	private static void Question5() {
		// Viết lệnh cho phép người dùng tạo account (viết thành method)
		// Đối với property Position, Người dùng nhập vào 1 2 3 4 5 và vào
		// chương trình sẽ chuyển thành Position.Dev, Position.Test,
		// Position.ScrumMaster, Position.PM
		System.out.println("\t --- Question 5 --- \t");
		Account account = new Account();
		System.out.print("AccountID : ");
		account.AccountID = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Email : ");
		account.Email = scanner.nextLine();
		System.out.print("FullName : ");
		account.FullName = scanner.nextLine();
		System.out.print("Nhập vào Position : ");
		int choose = scanner.nextInt();
		switch (choose) {
		case 1:
			Position position1 = new Position();
			position1.PositionName = PositionName.DEV;
			account.Position = position1;
			break;
		case 2:
			Position position2 = new Position();
			position2.PositionName = PositionName.DEV;
			account.Position = position2;
			break;
		case 3:
			Position position3 = new Position();
			position3.PositionName = PositionName.DEV;
			account.Position = position3;
			break;
		case 4:
			Position position4 = new Position();
			position4.PositionName = PositionName.DEV;
			account.Position = position4;
			break;
		default:
			System.out.println("Bạn nhập sai rồi !");
			break;
		}
		System.out.println("\nThông tin Account bạn vừa nhập là : " + "\n AccountID : " + account.AccountID
				+ "\n Email : " + account.Email + "\n FullName : " + account.FullName + "\n Position : "
				+ account.Position.PositionName);

	}

	private static void Question6() {
		// Viết lệnh cho phép người dùng tạo department (viết thành method)
		System.out.println("\t --- Question 6 --- \t");
		Department department = new Department();
		System.out.print("DepartmentID : ");
		department.DepartmentID = scanner.nextInt();
		scanner.nextLine();
		System.out.print("DepartmentName : ");
		department.DepartmentName = scanner.nextLine();

		System.out.println("\nDepartment vừa nhập là : " + "\n DepartmentID : " + department.DepartmentID
				+ "\n DepartmentName : " + department.DepartmentName);
	}

	private static void Question7() {
		// Nhập số chẵn từ console
		System.out.println("\t --- Question 7 --- \t");
		System.out.print("Nhập vào số chẵn : ");
		int n = scanner.nextInt();
		if (n % 2 == 0) {
			System.out.println("Số bạn vừa nhập là : " + n);
		} else {
			System.out.println("Bạn nhập sai rồi !");
		}
	}

	private static void Question8() {
		// Viết chương trình thực hiện theo flow sau:
		/*
		 * Bước 1: Chương trình in ra text "mời bạn nhập vào chức năng muốn sử dụng"
		 * Bước 2: Nếu người dùng nhập vào 1 thì sẽ thực hiện tạo account Nếu người dùng
		 * nhập vào 2 thì sẽ thực hiện chức năng tạo department Nếu người dùng nhập vào
		 * số khác thì in ra text "Mời bạn nhập lại" và quay trở lại bước 1
		 */
		while (true) {
			System.out.println("\t --- Question 8 --- \t");
			System.out.println("\t\t--- Chương trình ---\t\t");
			System.out.println("\t 1. Tạo Account ");
			System.out.println("\t 2. Tạo Department ");
			System.out.println("\t -------------- \t");
			System.out.print("Mời bạn đưa ra lựa chọn : ");
			int n = scanner.nextInt();
			switch (n) {
			case 1:
				Question5();
				break;
			case 2:
				Question6();
				break;
			default:
				System.out.println("Mời bạn nhập lại : ");
				break;
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	private static void Question9() {
		// Viết method cho phép người dùng thêm group vào account theo flow sau:
		/*
		 * Bước 1: In ra tên các usernames của user cho người dùng xem Bước 2: Yêu cầu
		 * người dùng nhập vào username của account Bước 3: In ra tên các group cho
		 * người dùng xem Bước 4: Yêu cầu người dùng nhập vào tên của group Bước 5: Dựa
		 * vào username và tên của group người dùng vừa chọn, hãy thêm account vào group
		 * đó .
		 */
		// Create group
		Group group1 = new Group();
		group1.GroupID = 1;
		// group1.GroupName = "Java Fresher";
		group1.CreateDate = LocalDate.of(2020, 04, 21);

		Group group2 = new Group();
		group2.GroupID = 2;
		// group2.GroupName = "DB Fresher";
		group2.CreateDate = LocalDate.of(2020, 05, 22);

		Group group3 = new Group();
		group3.GroupID = 3;
		// group3.GroupName = "C++ Fresher";
		group3.CreateDate = LocalDate.of(2020, 06, 23);

		// Create account
		Account account1 = new Account();
		account1.AccountID = 1;
		account1.CreateDate = LocalDate.of(2020, 3, 17);
		account1.Email = "tuananh28@gmail.com";
		account1.FullName = "Bùi Tuấn Anh";

		Account account2 = new Account();
		account2.AccountID = 2;
		account2.CreateDate = LocalDate.of(2020, 1, 9);
		account2.Email = "Linh22@gmail.com";
		account2.FullName = "Nguyễn Thùy Linh";

		Account account3 = new Account();
		account3.AccountID = 3;
		account3.CreateDate = LocalDate.of(2020, 3, 19);
		account3.Email = "hapham@gmail.com";
		account3.FullName = "Phạm Thị Hà";
		// Array
		Account[] accArr = { account1, account2, account3 };
		Group[] grArr = { group1, group2, group3 };

		// MAIN
		System.out.println("\t --- Question 9 --- \t");
		while (true) {
			System.out.println("\t\t--- Chương trình ---\t\t");
			System.out.println("\t 1. In ra User ");
			System.out.println("\t 2. Nhập UserName cho Account ");
			System.out.println("\t 3. In ra tên Group ");
			System.out.println("\t 4. Nhập tên Group ");
			System.out.println("\t 5. Thêm Account vào Group ");
			System.out.println("\t -------------- \t");
			System.out.print("Mời bạn đưa ra lựa chọn : ");
			int n = Integer.parseInt(scanner.nextLine());
			switch (n) {
			case 1:
				System.out.println("Danh sách các username trong hệ thống: ");
				for (int i = 0; i < accArr.length; i++) {
					System.out.println(accArr[i].Username);
				}
				break;
			case 2:
				System.out.print("Bạn muốn nhập User cho Account nào : ");
				int a = scanner.nextInt();
				scanner.nextLine();
				if (a == 1) {
					System.out.print("Mời bạn nhập UserName cho Account 1 : ");
					account1.Username = scanner.nextLine();
				}
				if (a == 2) {
					System.out.print("Mời bạn nhập UserName cho Account 2 : ");
					account2.Username = scanner.nextLine();
				}
				if (a == 3) {
					System.out.print("Mời bạn nhập UserName cho Account 3 : ");
					account3.Username = scanner.nextLine();
				}
				break;
			case 3:
				System.out.println("Danh sách các group có trong hệ thống: ");
				for (int i = 0; i < grArr.length; i++) {
					System.out.println(grArr[i].GroupName);
				}
				break;
			case 4:
				for (int i = 0; i < grArr.length; i++) {
					System.out.println("Group thứ " + (i + 1));
					System.out.print("Group Name : ");
					grArr[i].GroupName = scanner.nextLine();
				}
				break;
			case 5:
				int indexAccount = -1;
				for (int i = 0; i < accArr.length; i++) {
					if (accArr[i].Username.equals(accArr)) {
						indexAccount = i;
					}
				}

				int indexGroup = -1;
				for (int j = 0; j < grArr.length; j++) {
					if (grArr[j].GroupName.equals(grArr)) {
						indexGroup = j;
					}
				}

				if (indexAccount < 0 || indexGroup < 0) {
					System.out.println("Account hoặc Group không tồn tại. Mời bạn kiểm tra lại.");
				} else {
					for (int i = 0; i < accArr.length; i++) {
						if (accArr[i].Username.equals(accArr)) {
							Group[] grAdd = { grArr[indexGroup] };
							accArr[i].groups = grAdd;
							System.out.println("Bạn vừa add Group: " + accArr[indexAccount].groups[0].GroupName
									+ " cho Account: " + accArr[indexAccount].Username);
						}
					}
				}
				break;
			default:
				System.out.println("Mời bạn nhập lại !");
				break;
			}
		}
	}

	private static void Question10() {
		// Tiếp tục Question 8 và Question 9
		/*
		 * Bổ sung thêm vào bước 2 của Question 8 như sau: Nếu người dùng nhập vào 3 thì
		 * sẽ thực hiện chức năng thêm group vào account Bổ sung thêm Bước 3 của
		 * Question 8 như sau: Sau khi người dùng thực hiện xong chức năng ở bước 2 thì
		 * in ra dòng text để hỏi người dùng "Bạn có muốn thực hiện chức năng khác
		 * không?". Nếu người dùng chọn "Có" thì quay lại bước 1, nếu người dùng chọn
		 * "Không" thì kết thúc chương trình (sử dụng lệnh return để kết thúc chương
		 * trình)
		 */
		System.out.println("\t --- Question 10 --- \t");
	}

	private static void Question11() {
		// Tiếp tục Question 10
		/*
		 * Bổ sung thêm vào bước 2 của Question 8 như sau: Nếu người dùng nhập vào 4 thì
		 * sẽ thực hiện chức năng thêm account vào 1 nhóm ngẫu nhiên, chức năng sẽ được
		 * cài đặt như sau: Bước 1: In ra tên các usernames của user cho người dùng xem6
		 * Bước 2: Yêu cầu người dùng nhập vào username của account Bước 3: Sau đó
		 * chương trình sẽ chọn ngẫu nhiên 1 group Bước 4: Thêm account vào group chương
		 * trình vừa chọn ngẫu nhiên
		 */
		System.out.println("\t --- Question 11 --- \t");
	}

}
