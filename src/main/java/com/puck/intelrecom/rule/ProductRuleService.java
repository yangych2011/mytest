package com.puck.intelrecom.rule;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puck.framework.service.exprtion.RecomException;
import com.puck.intelrecom.domain.BsProduct;

/**
 * This is a sample class to launch a rule.
 */
@Service("productRuleService")
public class ProductRuleService extends AbstractRule implements IRuleService {

	@Autowired
	private RecomKieContainerFactory recomKieContainerFactory;

	private static final String RULE_FILTER_NAME = "product4c_";
	private static final String RULE_KSESSION_NAME = "product-rules";

	public void rule(List<BsProduct> products, Set<String> assetTypes, Map<String, Object> params) {
		try {
			KieContainer kContainer = recomKieContainerFactory.getKieContainer();
			KieSession kSession = kContainer.newKieSession(RULE_KSESSION_NAME);

			kSession.setGlobal("types", assetTypes);
			kSession.setGlobal("params", params);
			for (BsProduct bsProduct : products) {
				kSession.insert(bsProduct);
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
