/**
 * 
 */
package com.puck.intelrecom.utils;

import java.util.Comparator;

import com.puck.intelrecom.domain.BsAsset;

/**
 * @title AssetCompare.java
 * @description
 * @author yangyongchao
 * @date 2016年10月26日
 */
public class AssetCompare implements Comparator<BsAsset> {

	@Override
	public int compare(BsAsset asset1, BsAsset asset2) {
		// 按照权重积分降序排列
		if (asset1.getIntegral() > asset2.getIntegral()) {
			return -1;
		} else if (asset1.getIntegral() < asset2.getIntegral()) {
			return 1;
		}
		return 0;
	}

}
