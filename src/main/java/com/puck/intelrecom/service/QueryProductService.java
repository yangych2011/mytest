/**
 * 
 */
package com.puck.intelrecom.service;

import java.util.List;

import com.puck.intelrecom.domain.BsProduct;

/**
 * @title RecommendController.java
 * @description
 * @author yangyongchao
 * @date 2016年10月24日
 */
public interface QueryProductService {

	public List<BsProduct> queryProduct4Customer(String customerId);

	public List<BsProduct> queryProduct4Asset(String assetId);
}
