/**
 * 
 */
package com.puck.intelrecom.common;

/**
 * @title DilatationTypeEnum.java
 * @description 
 * @author yangyongchao
 * @date 2016年10月27日 
 */
public enum DilatationTypeEnum {

	/**
	 * 等于指定值
	 */
	A1("A1"),

	/**
	 * 等于目标对象值
	 */
	A2("A2"),

	/**
	 * 数值扩容
	 */
	B("B"),

	/**
	 * 指定数据列表
	 */
	C1("C1"),

	/**
	 * 目标对象值扩展列表
	 */
	C2("C2");

	private String value;

	DilatationTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
