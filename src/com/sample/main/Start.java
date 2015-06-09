package com.sample.main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.sample.bean.Message;
import com.sample.function.Values;
import com.sample.service.DroolsService;
import com.sample.service.ksessionThread;

public class Start {

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		show();
	}

	public static void show() {
		Set<Message> set = new HashSet<Message>();
		Message m1 = new Message(1, 0.9);
		Message m9 = new Message(9, 0.9);
		Message m10 = new Message(10, 0.9);
		Message m11 = new Message(11, 0.9);
		Message m14 = new Message(14, 0.9);
		Message m15 = new Message(15, 0.9);
		Message m3 = new Message(3, 0.9);
		Message m4 = new Message(4, 0.9);
		Message m5 = new Message(5, 0.9);
		Message m6 = new Message(6, 0.9);
		Message m7 = new Message(7, 0.9);
		Message m8 = new Message(8, 0.9);
		set.add(m1);
		set.add(m9);
		set.add(m10);
		set.add(m11);
		set.add(m14);
		set.add(m15);
		set.add(m3);
		set.add(m4);
		set.add(m5);
		set.add(m6);
		set.add(m7);
		set.add(m8);
		
		DroolsService droolsService = new DroolsService();
		
		ksessionThread kThread = new ksessionThread(droolsService);
		
		new Thread(kThread).start();
		int choseNum = 1;
		while (true) {
			Scanner scanner=new Scanner(System.in); 
			int num=scanner.nextInt();
			long startTime = System.currentTimeMillis();
			droolsService.startDrools(set);
			System.out.println("reasoning use (" + (System.currentTimeMillis() - startTime) + "ms)");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
