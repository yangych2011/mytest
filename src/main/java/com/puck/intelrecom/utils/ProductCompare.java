/**
 * 
 */
package com.puck.intelrecom.utils;

import java.util.Comparator;

import com.puck.intelrecom.domain.BsProduct;

/**
 * @title ProductCompare.java
 * @description
 * @author yangyongchao
 * @date 2016年10月26日
 */
public class ProductCompare implements Comparator<BsProduct> {

	@Override
	public int compare(BsProduct product1, BsProduct product2) {
		// 按照权重积分降序排列
		if (product1.getIntegral() > product2.getIntegral()) {
			return -1;
		} else if (product1.getIntegral() < product2.getIntegral()) {
			return 1;
		}
		return 0;
	}

}
