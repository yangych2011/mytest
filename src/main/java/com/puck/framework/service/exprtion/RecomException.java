/**
 * 
 */
package com.puck.framework.service.exprtion;

/**
 * @title RecomException.java
 * @description 推荐系统自定义异常
 * @author yangyongchao
 * @date 2016年11月2日 
 */
public class RecomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4161972182458354320L;

	public RecomException() {
		super();
	}

	public RecomException(String message) {
		super(message);
	}

	public RecomException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecomException(Throwable cause) {
		super(cause);
	}

}
