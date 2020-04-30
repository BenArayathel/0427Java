package com.april29;

public class StringManipulation {

	public static void main(String[] args) {
		/*
		 * 1)try converting last letter of every word to uppercase 2)if the length of
		 * the word is odd then convert middle letter of the word as uppercase else
		 * print as it is
		 */

		// 1)-----------------------------------------------------
		String s = "hello hi how are you doing today";
		StringBuilder sb = new StringBuilder();

		String ar[] = s.split(" ");
		for (String str : ar) {
			sb.append(str.substring(0, str.length() - 1)).append(str.substring(str.length() - 1).toUpperCase());
		}
		System.out.println(sb);

		// 2)-----------------------------------------------------
		sb.replace(0, sb.length(), "");

		for (String str : ar) {
			if (str.length() % 2 == 0) {
				sb.append(str);
			} else {
				sb.append(new StringBuilder(str).replace(str.length() / 2, str.length() / 2 + 1,
						Character.toString(str.charAt(str.length() / 2)).toUpperCase()));
			}
		}
		System.out.println(sb);
	}
}
