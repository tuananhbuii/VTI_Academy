package com.vti.frontend;

import java.util.Scanner;

import com.vti.backend.Ex5_Inheritance;
import com.vti.backend.QLCB;

public class Frontend_Inheritance {
	private static Scanner scanner;

	public static void main(String[] args) {
		//QLCB();
		Ex5_Inheritance ex5 = new Ex5_Inheritance();
		ex5.Question3();
	}

	private static void QLCB() {
		QLCB qlcb = new QLCB();
		int lc;
		do {
			System.out.println("=======MENU==========");
			System.out.println("  1. Thêm mới cán bộ. ");
			System.out.println("  2. Tìm kiếm theo họ tên.");
			System.out.println("  3. Hiện thị thông tin về danh sách các cán bộ.");
			System.out.println("  4. Nhập vào tên của cán bộ và delete cán bộ đó.");
			System.out.println("  0. Thoát khỏi chương trình.");
			System.out.println("=====================");
			System.out.print("Đưa ra lựa chọn : ");
			scanner = new Scanner(System.in);
			lc = scanner.nextInt();
			switch (lc) {
			case 1:
				qlcb.Them();
				break;
			case 2:
				qlcb.TimKiem();
				break;
			case 3:
				qlcb.HienThi();
				break;
			case 4:
				qlcb.XoaCanBo();
				break;
			case 0:
				System.out.println("Thoát chương trình");
				return;
			default:
				System.out.println("Bạn nhập sai, mời nhập lại !");
				break;
			}
		} while (lc!=0);	
	}
}