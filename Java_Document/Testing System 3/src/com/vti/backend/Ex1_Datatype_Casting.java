package com.vti.backend;

import java.util.Random;
import java.util.Scanner;

public class Ex1_Datatype_Casting {
	private static Scanner scanner;

	public static void main(String[] args) {
		// Question1();
		// Question2();
		// Question3();
		// Question4();
		Question5();
	}

	private static int Question5() {
		int counter = 1;
		while (counter <= 10) {
			System.out.println("Hello");
			counter++;
		}
		return 0;

	}

	public static void Question1() {
		/*
		 * Khai bÃ¡o 2 sá»‘ lÆ°Æ¡ng cÃ³ kiá»ƒu dá»¯ liá»‡u lÃ  float. Khá»Ÿi táº¡o
		 * LÆ°Æ¡ng cá»§a Account 1 lÃ  5240.5 $ Khá»Ÿi táº¡o LÆ°Æ¡ng cá»§a Account 2 lÃ 
		 * 10970.055$ Khai bÃ¡o 1 sá»‘ int Ä‘á»ƒ lÃ m trÃ²n LÆ°Æ¡ng cá»§a Account 1 vÃ 
		 * in sá»‘ int Ä‘Ã³ ra Khai bÃ¡o 1 sá»‘ int Ä‘á»ƒ lÃ m trÃ²n LÆ°Æ¡ng cá»§a
		 * Account 2 vÃ  in sá»‘ int Ä‘Ã³ ra
		 */
		float salary1 = 5240.5f;
		float salary2 = 10970.055f;

		int Salary1 = (int) salary1;
		int Salary2 = (int) salary2;

		System.out.println("LÆ°Æ¡ng Account 1 : " + Salary1);
		System.out.println("LÆ°Æ¡ng Account 2 : " + Salary2);
	}

	public static void Question2() {
		/*
		 * Láº¥y ngáº«u nhiÃªn 1 sá»‘ cÃ³ 5 chá»¯ sá»‘ (nhá»¯ng sá»‘ dÆ°á»›i 5 chá»¯
		 * sá»‘ thÃ¬ sáº½ thÃªm cÃ³ sá»‘ 0 á»Ÿ Ä‘áº§u cho Ä‘á»§ 5 chá»¯ sá»‘)
		 */
		Random random = new Random();
		int number = random.nextInt(99999);
		if (number / 10000 == 0) {
			System.out.println("Number : 0" + number);
		} else if (number / 1000 == 0) {
			System.out.println("Number : 00" + number);
		} else if (number / 100 == 0) {
			System.out.println("Number : 000" + number);
		} else if (number / 10 == 0) {
			System.out.println("Number : 0000" + number);
		} else {
			System.out.println("Number : " + number);
		}
	}

	public static void Question3() {
		/*
		 * Láº¥y 2 sá»‘ cuá»‘i cá»§a sá»‘ á»Ÿ Question 2 vÃ  in ra. Gá»£i Ã½: CÃ¡ch 1:
		 * convert sá»‘ cÃ³ 5 chá»¯ sá»‘ ra String, sau Ä‘Ã³ láº¥y 2 sá»‘ cuá»‘i CÃ¡ch
		 * 2: chia láº¥y dÆ° sá»‘ Ä‘Ã³ cho 100
		 */
		Random random = new Random();
		int number = random.nextInt(99999);
		if (number / 10000 == 0) {
			System.out.println("Number : 0" + number);
		} else if (number / 1000 == 0) {
			System.out.println("Number : 00" + number);
		} else if (number / 100 == 0) {
			System.out.println("Number : 000" + number);
		} else if (number / 10 == 0) {
			System.out.println("Number : 0000" + number);
		} else {
			System.out.println("Number : " + number);
		}
		// CÃ¡ch 1
		String numberString = String.valueOf(number);
		System.out.println("2 Sá»‘ cuá»‘i lÃ Â  : " + numberString.substring(3));

		// CÃ¡ch 2
		int newNumber = number % 100;
		System.out.println("2 Sá»‘ cuá»‘i lÃ Â  : " + newNumber);
	}

	public static void Question4() {
		scanner = new Scanner(System.in);
		float a;
		float b;
		System.out.print("Nháº­p a : ");
		a = scanner.nextFloat();
		do {
			System.out.print("Nháº­p b : ");
			b = scanner.nextFloat();
			if (b == 0) {
				System.out.println("Má»�i nháº­p sá»‘  b # 0");
			} else {
				System.out.println("ThÆ°Æ¡ng = " + (a / b));
			}
		} while (b == 0);
	}
}
