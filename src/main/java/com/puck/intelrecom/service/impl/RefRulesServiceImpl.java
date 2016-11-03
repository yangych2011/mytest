/**
 * 
 */
package com.puck.intelrecom.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.puck.intelrecom.common.DilatationTypeEnum;
import com.puck.intelrecom.domain.RuDilatationType;
import com.puck.intelrecom.domain.RuRefRules;
import com.puck.intelrecom.mapper.RuDilatationTypeMapper;
import com.puck.intelrecom.mapper.RuRefRulesMapper;
import com.puck.intelrecom.service.RefRulesService;

/**
 * @title RuleServiceImpl.java
 * @description
 * @author yangyongchao
 * @date 2016年10月26日
 */
@Service("refRulesService")
public class RefRulesServiceImpl implements RefRulesService {

	private static final String SEPARTOR = "##";
	private static final String DEFAULT_NUMERICAL_RANGE_MAX_KEY = "_max";
	private static final String DEFAULT_NUMERICAL_RANGE_MIN_KEY = "_min";

	private static final BigDecimal HUNDRED = new BigDecimal("100");

	/**
	 * 权重集合容器
	 */
	private static Map<String, Map<String, Integer>> ruleWeight = new HashMap<String, Map<String, Integer>>();

	/**
	 * 扩容范围编号集合
	 */
	private static Map<String, Integer> dilatationIds = new HashMap<String, Integer>();

	@Autowired
	private RuRefRulesMapper ruRefRulesMapper;
	@Autowired
	private RuDilatationTypeMapper ruDilatationTypeMapper;

	@Override
	public Map<String, Integer> getRuleWeight(String recomdSource, String target) {

		Map<String, Integer> weight = new HashMap<String, Integer>();
		// 入参为空，返回空集合
		if (StringUtils.isEmpty(recomdSource) || StringUtils.isEmpty(target)) {
			return weight;
		}

		// 约定“推荐源”+“##”+“推荐目标”做为key
		String key = recomdSource + SEPARTOR + target;

		// 先从集合中取，为空则查表
		if (ruleWeight.get(key) != null) {
			return ruleWeight.get(key);
		}
		List<RuRefRules> rules = ruRefRulesMapper.selectEntityWeight(recomdSource, target);
		if (rules == null) {
			return weight;
		} else {
			for (RuRefRules ruRules : rules) {
				// 查询出结果，按照约定放入集合
				weight.put(ruRules.getRecomSourceCol(), ruRules.getWeight());

				// 约定“推荐源”+“##”+“推荐目标”+“##”+“推荐源属性”为key，保存扩展编号
				String dilatationKey = key + SEPARTOR + ruRules.getRecomSourceCol();
				dilatationIds.put(dilatationKey, ruRules.getDilatationId());
			}
			ruleWeight.put(key, weight);
		}

		return weight;
	}

	@Override
	public void clearRuleWeight(String recomdSource, String target) {

		if (StringUtils.isEmpty(recomdSource) && StringUtils.isEmpty(target)) {
			ruleWeight.clear();
		}
		if (StringUtils.hasText(recomdSource) && StringUtils.hasText(target)) {
			String key = recomdSource + SEPARTOR + target;
			ruleWeight.remove(key);
		}
		// TODO 其中一个参数为空时处理

	}

	// TODO 异常情况未全部考虑，代码待优化
	@Override
	public Map<String, BigDecimal> getNumericalRange(String recomdSource, String target, String recomdSourceCol,
			BigDecimal targetColValue) {

		if (StringUtils.isEmpty(recomdSource) || StringUtils.isEmpty(target) || StringUtils.isEmpty(recomdSourceCol)) {
			return null;
		}

		String dilatationKey = recomdSource + SEPARTOR + target + SEPARTOR + recomdSourceCol;

		Integer dilatationId = dilatationIds.get(dilatationKey);
		if (dilatationId == null) {
			RuRefRules rule = ruRefRulesMapper.selectRuRefRuleWith3Keys(recomdSource, target, recomdSourceCol);
			if (rule == null) {
				return null;
			}
			dilatationId = rule.getDilatationId();
			// 放入集合中
			dilatationIds.put(dilatationKey, dilatationId);
		}

		// 查询扩容范围表
		RuDilatationType ruDilatationType = ruDilatationTypeMapper.selectByPrimaryKey(dilatationId);

		/**
		 * 数值型扩展
		 */
		if (DilatationTypeEnum.B.getValue().equals(ruDilatationType.getDilatationType())) {

			BigDecimal dilatationScale = ruDilatationType.getDilatationScale();
			BigDecimal range = targetColValue.multiply(dilatationScale).divide(HUNDRED);
			BigDecimal max = targetColValue.add(range);
			BigDecimal min = targetColValue.subtract(range);

			// 属性名+“_max”,属性名+“_min”作为key
			Map<String, BigDecimal> numericalRange = new HashMap<String, BigDecimal>();
			numericalRange.put(recomdSourceCol + DEFAULT_NUMERICAL_RANGE_MAX_KEY, max);
			numericalRange.put(recomdSourceCol + DEFAULT_NUMERICAL_RANGE_MIN_KEY, min);

			return numericalRange;
		}

		return null;
	}
}
