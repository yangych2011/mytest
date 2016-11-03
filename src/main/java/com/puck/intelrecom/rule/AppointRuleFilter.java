/**
 * 
 */
package com.puck.intelrecom.rule;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

/**
 * @title AppointRuleFilter.java
 * @description
 * @author yangyongchao
 * @date 2016年10月26日
 */
public class AppointRuleFilter implements AgendaFilter {

	private String startName;

	public AppointRuleFilter(String startName) {
		this.startName = startName;
	}

	@Override
	public boolean accept(Match arg0) {

		String ruleName = arg0.getRule().getName();
		if (ruleName.startsWith(startName)) {
			return true;
		} else {
			return false;
		}
	}

}
