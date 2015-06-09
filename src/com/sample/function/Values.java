package com.sample.function;

import java.util.HashMap;
import java.util.Map;

public class Values {
	
	private static Map<Integer, NameAndReliability> nameMap = 
			new HashMap<Integer, NameAndReliability>();
		
	public static String num2Words(Integer num) {
		return nameMap.get(num).name;
	}
	
	
	static {
		nameMap.put(1, new Values().new NameAndReliability("responsibility", "82"));
		nameMap.put(2, new Values().new NameAndReliability("revolution", "70"));
		nameMap.put(3, new Values().new NameAndReliability("unity", "75"));
		nameMap.put(4, new Values().new NameAndReliability("trumpet", "80"));
		nameMap.put(5, new Values().new NameAndReliability("spray", "86"));
		nameMap.put(6, new Values().new NameAndReliability("ant", "87"));
		nameMap.put(7, new Values().new NameAndReliability("tray", "88"));
		nameMap.put(8, new Values().new NameAndReliability("student", "89"));
		
		nameMap.put(9, new Values().new NameAndReliability("teacher", "85"));
		nameMap.put(10, new Values().new NameAndReliability("architect", "85"));
		nameMap.put(11, new Values().new NameAndReliability("coder", "75"));
		nameMap.put(12, new Values().new NameAndReliability("steak", "75"));
		nameMap.put(13, new Values().new NameAndReliability("huawei", "95"));
		nameMap.put(14, new Values().new NameAndReliability("apple", "70"));
		nameMap.put(15, new Values().new NameAndReliability("mi", "80"));
		nameMap.put(16, new Values().new NameAndReliability("samsung", "77"));
		
		nameMap.put(17, new Values().new NameAndReliability("A", "100"));
		nameMap.put(18, new Values().new NameAndReliability("B", "99"));
		nameMap.put(19, new Values().new NameAndReliability("C", "99"));
		nameMap.put(20, new Values().new NameAndReliability("D", "99"));
		nameMap.put(21, new Values().new NameAndReliability("E", "99"));
		nameMap.put(22, new Values().new NameAndReliability("F", "99"));
		nameMap.put(23, new Values().new NameAndReliability("G", "98"));
		nameMap.put(24, new Values().new NameAndReliability("H", "98"));
		
		nameMap.put(25, new Values().new NameAndReliability("I", "98"));
		nameMap.put(26, new Values().new NameAndReliability("J", "97"));
		nameMap.put(27, new Values().new NameAndReliability("K", "97"));
		nameMap.put(28, new Values().new NameAndReliability("M", "97"));
		nameMap.put(29, new Values().new NameAndReliability("N", "96"));
		nameMap.put(30, new Values().new NameAndReliability("X", "96"));
		nameMap.put(31, new Values().new NameAndReliability("Y", "96"));
		nameMap.put(32, new Values().new NameAndReliability("Z", "96"));
	}
		
	class NameAndReliability {
		String name = null;
		String relibility = null;
		
		public NameAndReliability(String name, String relibility) {
			this.name = name;
			this.relibility = relibility;
		}
	}
}
