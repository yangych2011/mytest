/**
 * 
 */
package com.puck.intelrecom.utils;

import java.util.Random;

/**
 * @author yangyongchao
 * @descript
 * @name RandomUtils.java
 * @date 2015年12月24日
 */
public class RandomUtils {

	private static final String nums = "0123456789";
	private static final String alphabets = "abcdefghijklmnopqrstuvwxyz";
	private static final String complex = "0123456789abcdefghijklmnopqrstuvwxyz";

	/**
	 * 获取指定长度的的随机数字组合
	 * 
	 * @param length
	 *            必须大于0
	 * @return
	 */
	public static String getRandomNums(int length) {

		if (length < 1) {
			return "";
		}

		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int randomNum = random.nextInt(10);// 0-10之间的随机整数，不包括10
			sb.append(nums.charAt(randomNum));
		}

		return sb.toString();
	}

	/**
	 * 获取指定长度的的随机字母组合
	 * 
	 * @param length
	 *            length必须大于0
	 * @return
	 */
	public static String getRandomAlphabet(int length) {
		return getRandomAlphabet(length, false);
	}

	/**
	 * 获取指定长度的的随机字母组合
	 * 
	 * @param length
	 *            length必须大于0
	 * @param upperCase
	 *            字母是否转换为大写，默认小写
	 * @return
	 */
	public static String getRandomAlphabet(int length, boolean upperCase) {

		if (length < 1) {
			return "";
		}

		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int randomNum = random.nextInt(26);// 0-26之间的随机整数，不包括26
			sb.append(alphabets.charAt(randomNum));
		}

		String result = sb.toString();
		if (upperCase) {
			result = result.toUpperCase();
		}

		return result;
	}

	/**
	 * 获取指定长度的的随机数字或字母组合
	 * 
	 * @param length
	 *            length必须大于0
	 * @return
	 */
	public static String getRandomComplex(int length) {
		return getRandomComplex(length, false);
	}

	/**
	 * 获取指定长度的的随机数字或字母组合
	 * 
	 * @param length
	 *            length必须大于0
	 * @param upperCase
	 *            字母是否转换为大写，默认小写
	 * @return
	 */
	public static String getRandomComplex(int length, boolean upperCase) {

		if (length < 1) {
			return "";
		}

		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int randomNum = random.nextInt(36);// 0-36之间的随机整数，不包括36
			sb.append(complex.charAt(randomNum));
		}

		String result = sb.toString();
		if (upperCase) {
			result = result.toUpperCase();
		}

		return result;
	}

}
