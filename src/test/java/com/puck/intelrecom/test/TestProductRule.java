package com.puck.intelrecom.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.puck.intelrecom.common.Constants;
import com.puck.intelrecom.domain.BsProduct;
import com.puck.intelrecom.rule.AppointRuleFilter;

/**
 * This is a sample class to launch a rule.
 */
public class TestProductRule {

	public static void main(String[] args) {
		List<BsProduct> products = new ArrayList<BsProduct>();
		BsProduct product = new BsProduct();
		product.setId(1);
		product.setProductId("001");
		product.setGuaranteeType("1");
		product.setProductAmount(new BigDecimal("40000"));

		BsProduct product2 = new BsProduct();
		product2.setId(2);
		product2.setProductId("002");
		product2.setGuaranteeType("3");
		product2.setProductAmount(new BigDecimal("50000"));

		products.add(product);
		products.add(product2);

		Set<String> assetTypes = new HashSet<String>();
		assetTypes.add("1");
		assetTypes.add("2");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("max", new BigDecimal("80000"));
		params.put("min", new BigDecimal("10000"));

		Map<String, Integer> weight = new HashMap<String, Integer>();
		weight.put("amount", 5);
		weight.put("guarantee_type", 10);
		params.put(Constants.RULE_KEY_WEIGHT, weight);

		rule(products, assetTypes, params);
	}

	public static void rule(List<BsProduct> products, Set<String> assetTypes, Map<String, Object> params) {
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("product-rules");

			kSession.setGlobal("types", assetTypes);
			kSession.setGlobal("params", params);
			for (BsProduct bsProduct : products) {
				kSession.insert(bsProduct);
			}
			kSession.fireAllRules(new AppointRuleFilter("product"));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
