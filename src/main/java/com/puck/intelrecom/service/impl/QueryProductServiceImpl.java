/**
 * 
 */
package com.puck.intelrecom.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puck.intelrecom.common.Constants;
import com.puck.intelrecom.domain.BsAsset;
import com.puck.intelrecom.domain.BsCustomer;
import com.puck.intelrecom.domain.BsProduct;
import com.puck.intelrecom.mapper.BsAssetMapper;
import com.puck.intelrecom.mapper.BsCustomerMapper;
import com.puck.intelrecom.mapper.BsProductMapper;
import com.puck.intelrecom.rule.ProductRuleService;
import com.puck.intelrecom.service.QueryProductService;
import com.puck.intelrecom.service.RefRulesService;
import com.puck.intelrecom.utils.ProductCompare;

/**
 * @title QueryProductServiceImpl.java
 * @description
 * @author yangyongchao
 * @date 2016年10月25日
 */
@Service("queryProductService")
public class QueryProductServiceImpl implements QueryProductService {

	@Autowired
	private BsProductMapper bsProductMapper;
	@Autowired
	private BsCustomerMapper bsCustomerMapper;
	@Autowired
	private BsAssetMapper bsAssetMapper;
	@Autowired
	private RefRulesService refRulesService;
	@Autowired
	private ProductRuleService productRuleService;

	private static final String target_c = "bs_customer";
	private static final String target_a = "bs_asset";
	private static final String recomdSource = "bs_product";

	@Override
	public List<BsProduct> queryProduct4Customer(String customerId) {

		long start = System.currentTimeMillis();
		BsCustomer customers = bsCustomerMapper.selectCustomerWithAsset(customerId);
		List<BsAsset> assets = customers.getAssets();

		Set<String> assetTypes = new HashSet<String>();
		BigDecimal amountSum = BigDecimal.ZERO;
		for (BsAsset asset : assets) {
			assetTypes.add(asset.getAssetType());
			amountSum = amountSum.add(asset.getAssetAmount());
		}

		Map<String, Object> params = new HashMap<String, Object>();

		// 查询权重
		Map<String, Integer> weight = refRulesService.getRuleWeight(recomdSource, target_c);
		params.put(Constants.RULE_KEY_WEIGHT, weight);

		Map<String, BigDecimal> amount_range = refRulesService.getNumericalRange(recomdSource, target_c, "amount",
				amountSum);
		params.putAll(amount_range);

		List<BsProduct> products = bsProductMapper.selectAllProduct();

		// 匹配规则
		productRuleService.rule(products, assetTypes, params);

		// 排序
		Collections.sort(products, new ProductCompare());

		// 截取数据
		if (products.size() > Constants.DEFAULT_MAX_RESULT_NUM) {
			products = products.subList(0, Constants.DEFAULT_MAX_RESULT_NUM);
		}

		long end = System.currentTimeMillis();
		System.out.println("共消耗时间（毫秒）：" + (end - start));
		return products;
	}

	@Override
	public List<BsProduct> queryProduct4Asset(String assetId) {

		BsAsset bsAsset = bsAssetMapper.selectBsAssetByAssetId(assetId);

		Set<String> assetTypes = new HashSet<String>();
		assetTypes.add(bsAsset.getAssetType());

		Map<String, Object> params = new HashMap<String, Object>();

		// 查询权重
		Map<String, Integer> weight = refRulesService.getRuleWeight(recomdSource, target_a);
		params.put(Constants.RULE_KEY_WEIGHT, weight);

		Map<String, BigDecimal> amount_range = refRulesService.getNumericalRange(recomdSource, target_a, "amount",
				bsAsset.getAssetAmount());
		params.putAll(amount_range);

		List<BsProduct> products = bsProductMapper.selectAllProduct();

		// 匹配规则
		productRuleService.rule(products, assetTypes, params);

		// 排序
		Collections.sort(products, new ProductCompare());

		// 截取数据
		if (products.size() > Constants.DEFAULT_MAX_RESULT_NUM) {
			products = products.subList(0, Constants.DEFAULT_MAX_RESULT_NUM);
		}

		return products;
	}

}
