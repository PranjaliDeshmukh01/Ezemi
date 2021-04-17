package com.ezemi.helper;

public class RandomPinGenerator {
	public static String generate4digitPin(){
		return ""+((int)(Math.random()*9000)+1000);
	}
	
	public static String generate16digitNumber(){
		StringBuilder sb = new StringBuilder();
		sb.append(generate4digitPin());
		sb.append(generate4digitPin());
		sb.append(generate4digitPin());
		sb.append(generate4digitPin());
		return sb.toString();
	}
}
