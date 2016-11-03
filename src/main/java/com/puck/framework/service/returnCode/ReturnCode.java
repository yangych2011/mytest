/**
 * 
 */
package com.puck.framework.service.returnCode;

/**
 * @author yangyongchao
 * @descript 平台统一响应结构体
 * @name ReturnCode.java
 * @date 2015年12月24日
 */
public class ReturnCode {

	/**成功*/
	public static final String RETURN_TYPE_SUCCESS = "S";
	/**失败*/
	public static final String RETURN_TYPE_ERROR = "E";
	/**警告*/
	public static final String RETURN_TYPE_WARN = "W";
	/**成功标识码*/
	public static final String RETURN_CODE_0 = "000000";
	/**失败标识码*/
	public static final String RETURN_CODE_1 = "111111";

	private String type;// 返回类型
	private String code;// 返回码
	private String message;// 返回信息
	private String domain;// 域

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
