package com.puck.intelrecom.rule;

import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puck.framework.service.exprtion.RecomException;
import com.puck.intelrecom.domain.BsAsset;

/**
 * @title AssetRule.java
 * @description 
 * @author yangyongchao
 * @date 2016年10月27日
 */
@Service("assetRuleService")
public class AssetRuleService extends AbstractRule implements IRuleService {

	@Autowired
	private RecomKieContainerFactory recomKieContainerFactory;

	private static final String RULE_FILTER_NAME = "asset_";
	private static final String RULE_KSESSION_NAME = "asset-rules";

	public void rule(List<BsAsset> assets, String type, Map<String, Object> params) {
		try {
			KieContainer kContainer = recomKieContainerFactory.getKieContainer();
			KieSession kSession = kContainer.newKieSession(RULE_KSESSION_NAME);

			kSession.setGlobal("type", type);
			kSession.setGlobal("params", params);
			for (BsAsset bsAsset : assets) {
				kSession.insert(bsAsset);
			}
			kSession.fireAllRules(new AppointRuleFilter(RULE_FILTER_NAME));
			kSession.dispose();
		} catch (Throwable t) {
			throw new RecomException("执行规则出错!", t);
		}
	}

	@Override
	public String getRuleKsessionName() {
		return RULE_KSESSION_NAME;
	}

	@Override
	public String getRuleFilterName() {
		return RULE_FILTER_NAME;
	}

}
