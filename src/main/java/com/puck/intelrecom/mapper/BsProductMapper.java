package com.puck.intelrecom.mapper;

import java.util.List;

import com.puck.intelrecom.domain.BsProduct;

public interface BsProductMapper {
	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table bs_product
	 *
	 * @mbggenerated Tue Oct 25 16:39:26 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table bs_product
	 *
	 * @mbggenerated Tue Oct 25 16:39:26 CST 2016
	 */
	int insert(BsProduct record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table bs_product
	 *
	 * @mbggenerated Tue Oct 25 16:39:26 CST 2016
	 */
	int insertSelective(BsProduct record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table bs_product
	 *
	 * @mbggenerated Tue Oct 25 16:39:26 CST 2016
	 */
	BsProduct selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table bs_product
	 *
	 * @mbggenerated Tue Oct 25 16:39:26 CST 2016
	 */
	int updateByPrimaryKeySelective(BsProduct record);

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table bs_product
	 *
	 * @mbggenerated Tue Oct 25 16:39:26 CST 2016
	 */
	int updateByPrimaryKey(BsProduct record);

	/**
	 * 查询所有产品
	 * @return
	 */
	public List<BsProduct> selectAllProduct();

	/**
	 * 根据产品标识查询产品
	 * @param productId
	 * @return
	 */
	public BsProduct selectBsProductByProductId(String productId);
}