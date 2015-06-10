package com.sample.main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import com.sample.bean.Message;
import com.sample.function.Values;
import com.sample.service.DroolsService;
import com.sample.service.ksessionThread;

/**
 * start the reasoning
 * @author liu
 * include program main entrance and some method for display
 */
public class Start {

	static int maxFactNum = 16;
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		start();
	}

	public static void start() {
		Set<Message> set = new HashSet<Message>();
		DroolsService droolsService = new DroolsService();
		ksessionThread kThread = new ksessionThread(droolsService);
		
		new Thread(kThread).start();
		boolean endFlag = true;
		
		while (true) {
			showFact();
			Scanner scanner=new Scanner(System.in); 
			
			while (endFlag) {
				Integer num = 0;
				String str=scanner.nextLine();
				Pattern pattern = Pattern.compile("[0-9]*");
				if (!pattern.matcher(str).matches() || (num = Integer.valueOf(str)) > Start.maxFactNum) {
					System.out.println("please input fact's num");
					continue;
				} else if (0 == num) {
					System.out.println();
					System.out.println("reasoning chain:");
					break;
				} else {
					Message message = new Message(num, Values.getRelibility(num));
					set.add(message);
				}
			}
			
			long startTime = System.currentTimeMillis();
			droolsService.startDrools(set);
			set.clear();
			System.out.println();
			System.out.println("# reasoning use (" + (System.currentTimeMillis() - startTime) + "ms)");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void showFact() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 9; i++) {
			sb.append(i + ". ");
			sb.append(Values.getName(i));
			sb.append(" ; ");
		}
		System.out.println(sb);
		sb.delete(0, sb.length());
		
		for (int i = 9; i < (Start.maxFactNum + 1); i++) {
			sb.append(i + ". ");
			sb.append(Values.getName(i));
			sb.append(" ; ");
		}
		sb.delete(sb.length() - 3, sb.length() - 1);
		System.out.println(sb);
	}
}
