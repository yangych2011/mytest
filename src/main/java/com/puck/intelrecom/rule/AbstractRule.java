/**
 * 
 */
package com.puck.intelrecom.rule;

/**
 * @title AbstractRule.java
 * @description
 * @author yangyongchao
 * @date 2016年10月26日
 */
public abstract class AbstractRule {

	/**
	 * 获取ksession名称
	 * 
	 * @return
	 */
	public abstract String getRuleKsessionName();

	/**
	 * 获取规则过滤前缀名
	 * 
	 * @return
	 */
	public abstract String getRuleFilterName();

}
