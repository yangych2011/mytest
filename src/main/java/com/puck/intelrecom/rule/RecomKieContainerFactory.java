/**
 * 
 */
package com.puck.intelrecom.rule;

import javax.annotation.PostConstruct;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Service;

/**
 * @title RecomKieContainerFactory.java
 * @description 
 * @author yangyongchao
 * @date 2016年11月2日 
 */
@Service("recomKieContainerFactory")
public class RecomKieContainerFactory {

	private static KieContainer kContainer = null;

	/**
	 * 获取KieContainer
	 * @return
	 */
	public KieContainer getKieContainer() {
		if (kContainer == null) {
			kieContainerInit();
		}
		return kContainer;
	}

	/**
	 * 初始化KieContainer
	 */
	@PostConstruct
	public void kieContainerInit() {
		KieServices ks = KieServices.Factory.get();
		kContainer = ks.getKieClasspathContainer();
	}

}
