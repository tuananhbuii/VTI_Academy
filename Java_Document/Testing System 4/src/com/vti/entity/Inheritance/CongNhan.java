package com.vti.entity.Inheritance;

import java.util.Scanner;


public class CongNhan extends CanBo{
	int Level; // 1->10
	
	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}
	@Override
	public void Input() {
		Scanner sc = new Scanner(System.in);
		super.Input();
		System.out.print("Level : ");
		Level = sc.nextInt();
	}
	@Override
	public void Output() {
		super.Output();
		System.out.println("Level : "+Level);
	}
}