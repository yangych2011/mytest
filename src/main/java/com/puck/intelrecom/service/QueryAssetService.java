/**
 * 
 */
package com.puck.intelrecom.service;

import java.util.List;

import com.puck.intelrecom.domain.BsAsset;

/**
 * @title RecommendController.java
 * @description
 * @author yangyongchao
 * @date 2016年10月24日
 */
public interface QueryAssetService {

	public List<BsAsset> queryAsset(String productId);

}
