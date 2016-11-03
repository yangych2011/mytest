/**
 * 
 */
package com.puck.intelrecom.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @title RulesService.java
 * @description
 * @author yangyongchao
 * @date 2016年10月26日
 */
public interface RefRulesService {

	/**
	 * 获取权重
	 * @param entity
	 * @return
	 */
	public Map<String, Integer> getRuleWeight(String recomdSource, String target);

	/**
	 * 清除缓存中权重信息
	 * 入参都为空时，清楚全部
	 * 
	 * @param recomdSource
	 * @param target
	 */
	public void clearRuleWeight(String recomdSource, String target);

	/**
	 * 获取数值型参数范围值
	 * @param recomdSource
	 * @param target
	 * @param recomdSourceCol
	 * @param targetColValue
	 * @return
	 */
	public Map<String, BigDecimal> getNumericalRange(String recomdSource, String target, String recomdSourceCol,
			BigDecimal targetColValue);
}
