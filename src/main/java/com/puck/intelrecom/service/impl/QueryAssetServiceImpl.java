/**
 * 
 */
package com.puck.intelrecom.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puck.framework.service.exprtion.RecomException;
import com.puck.intelrecom.common.Constants;
import com.puck.intelrecom.domain.BsAsset;
import com.puck.intelrecom.domain.BsProduct;
import com.puck.intelrecom.mapper.BsAssetMapper;
import com.puck.intelrecom.mapper.BsProductMapper;
import com.puck.intelrecom.rule.AssetRuleService;
import com.puck.intelrecom.service.QueryAssetService;
import com.puck.intelrecom.service.RefRulesService;
import com.puck.intelrecom.utils.AssetCompare;

/**
 * @title QueryProductServiceImpl.java
 * @description
 * @author yangyongchao
 * @date 2016年10月25日
 */
@Service("queryAssetService")
public class QueryAssetServiceImpl implements QueryAssetService {

	@Autowired
	private BsProductMapper bsProductMapper;
	@Autowired
	private BsAssetMapper bsAssetMapper;
	@Autowired
	private RefRulesService refRulesService;
	@Autowired
	private AssetRuleService assetRuleService;

	private static final String target = "bs_product";
	private static final String recomdSource = "bs_asset";

	@Override
	public List<BsAsset> queryAsset(String productId) {

		BsProduct product = bsProductMapper.selectBsProductByProductId(productId);

		if (product == null) {
			throw new RecomException("查询产品为空！编号" + productId + "的产品不存在！");
		}
		
		System.out.println("==========================");

		// 类别
		String type = product.getGuaranteeType();
		BigDecimal amount = product.getProductAmount();

		Map<String, Object> params = new HashMap<String, Object>();

		// 查询权重
		Map<String, Integer> weight = refRulesService.getRuleWeight(recomdSource, target);
		params.put(Constants.RULE_KEY_WEIGHT, weight);

		Map<String, BigDecimal> amount_range = refRulesService.getNumericalRange(recomdSource, target, "asset_amount",
				amount);
		params.putAll(amount_range);

		List<BsAsset> assets = bsAssetMapper.selectAllAssets();

		// 匹配规则
		assetRuleService.rule(assets, type, params);

		// 排序
		Collections.sort(assets, new AssetCompare());

		// 截取数据
		if (assets.size() > Constants.DEFAULT_MAX_RESULT_NUM) {
			assets = assets.subList(0, Constants.DEFAULT_MAX_RESULT_NUM);
		}

		return assets;
	}

}
