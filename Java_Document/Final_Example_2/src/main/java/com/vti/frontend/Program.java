package com.vti.frontend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.vti.Ultis.ScannerUltis;

public class Program {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		while (true) {
			AccountFuction accountFunction = new AccountFuction();
			System.out.println("-----------| Mời bạn chọn chức năng |----------");
			String leftAlignFormat = "| %-43s |%n";
			System.out.format("+---------------------------------------------+%n");
			System.out.format("|                 Choose please               |%n");
			System.out.format("+---------------------------------------------+%n");
			System.out.format(leftAlignFormat, "1. Login Admin ");
			System.out.format(leftAlignFormat, "2. Login User ");
			System.out.format(leftAlignFormat, "3. Get List Account By ProjectName ");
			System.out.format(leftAlignFormat, "4. Exit ");
			System.out.format("+---------------------------------------------+%n");
			System.out.print("Mời bạn chọn chức năng: ");
			int lc = ScannerUltis.inputInt();
			switch (lc) {
			case 1:
				accountFunction.getLoginAdmin();;
				break;
			case 2:
				accountFunction.getLogin();;
				break;
			case 3:
				accountFunction.getListMemberByProjectName();
				break;
			case 4:
				return;
			default:
				System.out.println("Không có lựa chọn này. Mời bạn nhập lại: ");
				break;
			}
		}
	}
}
