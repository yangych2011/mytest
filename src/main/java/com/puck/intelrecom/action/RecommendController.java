/**
 * 
 */
package com.puck.intelrecom.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puck.intelrecom.domain.BsAsset;
import com.puck.intelrecom.domain.BsProduct;
import com.puck.intelrecom.service.QueryAssetService;
import com.puck.intelrecom.service.QueryProductService;

/**
 * @title RecommendController.java
 * @description
 * @author yangyongchao
 * @date 2016年10月24日
 */
@Controller
@RequestMapping("/recommend")
public class RecommendController {

	@Autowired
	private QueryProductService queryProductService;
	@Autowired
	private QueryAssetService queryAssetService;

	@RequestMapping("/queryProduct4Customer")
	public @ResponseBody List<BsProduct> queryProduct4Customer() {

		List<BsProduct> result = queryProductService.queryProduct4Customer("001");
		return result;
	}

	@RequestMapping("/queryProduct4Asset")
	public @ResponseBody List<BsProduct> queryProduct4Asset() {

		List<BsProduct> result = queryProductService.queryProduct4Asset("003");
		return result;
	}

	@RequestMapping(value = "/queryAsset4Product/{productId}")
	public @ResponseBody List<BsAsset> queryAsset4Product(@PathVariable String productId) {// 测试
																							// 001

		List<BsAsset> result = queryAssetService.queryAsset(productId);
		return result;
	}

}
