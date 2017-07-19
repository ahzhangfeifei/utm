package com.utm.basic.action;

/** 
 * 	
 * callbackType如果是closeCurrent就会关闭当前tab
 * 只有callbackType="forward"时需要forwardUrl值
 * 
 * form提交后返回json数据结构statusCode=DWZ.statusCode.ok表示操作成功, 做页面跳转等操作. 
 * statusCode=DWZ.statusCode.error表示操作失败, 提示错误原因. 
 * statusCode=DWZ.statusCode.timeout表示session超时，下次点击时跳转到DWZ.loginUrl
 * {"statusCode":"200", "message":"操作成功", "navTabId":"navNewsLi", "forwardUrl":"", "callbackType":"closeCurrent"}
 * {"statusCode":"300", "message":"操作失败"}
 * {"statusCode":"301", "message":"会话超时"}
 * 
 */

public class AjaxObject {
	// 状态码
	public final static int STATUS_CODE_SUCCESS = 200;
	public final static int STATUS_CODE_FAILURE = 300;
	public final static int STATUS_CODE_TIMEOUT = 301;
	public final static int STATUS_CODE_FORBIDDEN = 403;
	
	// callbackType类型
	public final static String CALLBACK_TYPE_CLOSE_CURRENT = "closeCurrent";
	public final static String CALLBACK_TYPE_FORWARD = "forward";
	
	private int statusCode = STATUS_CODE_SUCCESS;
	private String message = "";
	private String navTabId = "";
	private String forwardUrl = "";
	private String rel = "";
	private String callbackType = CALLBACK_TYPE_CLOSE_CURRENT;
	
	public AjaxObject() {
		
	}
	
	public AjaxObject(String message) {
		this.message = message;
	}
	
	/**  
	 * 构造函数
	 * @param statusCode  
	 */ 
	public AjaxObject(int statusCode) {
		super();
		this.statusCode = statusCode;
	}
	
	/**  
	 * 构造函数
	 * @param statusCode
	 * @param message  
	 */ 
	public AjaxObject(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	/**  
	 * 构造函数
	 * @param statusCode
	 * @param message
	 * @param callbackType  
	 */ 
	public AjaxObject(int statusCode, String message, String callbackType) {
		this.statusCode = statusCode;
		this.message = message;
		this.callbackType = callbackType;
	}
	
	/**  
	 * 构造函数
	 * @param statusCode
	 * @param message
	 * @param forwardUrl
	 * @param rel
	 * @param callbackType  
	 */ 
	public AjaxObject(int statusCode, String message, String navTabId, String forwardUrl,
			String rel, String callbackType) {
		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.forwardUrl = forwardUrl;
		this.rel = rel;
		this.callbackType = callbackType;
	}

	/**  
	 * 返回 statusCode 的值   
	 * @return statusCode  
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**  
	 * 设置 statusCode 的值  
	 * @param statusCode
	 */
	public AjaxObject setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	/**  
	 * 返回 message 的值   
	 * @return message  
	 */
	public String getMessage() {
		return message;
	}

	/**  
	 * 设置 message 的值  
	 * @param message
	 */
	public AjaxObject setMessage(String message) {
		this.message = message;
		return this;
	}

	/**  
	 * 返回 forwardUrl 的值   
	 * @return forwardUrl  
	 */
	public String getForwardUrl() {
		return forwardUrl;
	}

	/**  
	 * 设置 forwardUrl 的值  
	 * @param forwardUrl
	 */
	public AjaxObject setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
		return this;
	}

	/**  
	 * 返回 rel 的值   
	 * @return rel  
	 */
	public String getRel() {
		return rel;
	}

	/**  
	 * 设置 rel 的值  
	 * @param rel
	 */
	public AjaxObject setRel(String rel) {
		this.rel = rel;
		return this;
	}

	/**  
	 * 返回 callbackType 的值   
	 * @return callbackType  
	 */
	public String getCallbackType() {
		return callbackType;
	}

	/**  
	 * 设置 callbackType 的值  
	 * @param callbackType
	 */
	public AjaxObject setCallbackType(String callbackType) {
		this.callbackType = callbackType;
		return this;
	}
	
	/**  
	 * 返回 navTabId 的值   
	 * @return navTabId  
	 */
	public String getNavTabId() {
		return navTabId;
	}

	/**  
	 * 设置 navTabId 的值  
	 * @param navTabId
	 */
	public AjaxObject setNavTabId(String navTabId) {
		this.navTabId = navTabId;
		return this;
	}
	
	public static AjaxObject newOk(String message) {
		return new AjaxObject(STATUS_CODE_SUCCESS, message);
	}
	
	public static AjaxObject newError(String message) {
		return new AjaxObject(STATUS_CODE_FAILURE, message);
	}
	
	public static AjaxObject newTimeout(String message) {
		return new AjaxObject(STATUS_CODE_TIMEOUT, message);
	}	
	
	public static AjaxObject newForbidden(String message) {
		return new AjaxObject(STATUS_CODE_TIMEOUT, message);
	}
	
	public static AjaxObject newRefreshNavtab(String navTabId, String message) {
		AjaxObject ajaxObject = new AjaxObject(message);
		ajaxObject.navTabId = navTabId;
		return ajaxObject;
	}
	
	public static AjaxObject newRefreshRel(String rel, String message) {
		AjaxObject ajaxObject = new AjaxObject(message);
		ajaxObject.rel = rel;
		return ajaxObject;
	}
	
	public static AjaxObject newForward(String forwardUrl) {
		AjaxObject ajaxObject = new AjaxObject(CALLBACK_TYPE_FORWARD);
		ajaxObject.forwardUrl = forwardUrl;
		return ajaxObject;
	}

	/**   
	 * 转换成JSONObject，以便传到客户端显示
	 * @return  
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{")
			  .append( "\"statusCode\":\"" + statusCode + "\",")
			  .append("\"message\":\"" + message + "\",")
			  .append("\"navTabId\":\"" + navTabId + "\",")
			  .append("\"rel\":\"" + rel + "\",")
			  .append("\"callbackType\":\"" + callbackType + "\",")
			  .append("\"forwardUrl\":\"" + forwardUrl + "\"")
			  .append("}");
		return buffer.toString();
	}

}
