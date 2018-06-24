package com.speed.common.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户端响应对象
 * @author Administrator
 *
 */
public class Response {

	private static final String OK = "ok";
	private static final String ERROR = "error";

	@ApiModelProperty("状态")
	private Meta meta;
	@ApiModelProperty("数据")
	private Object data;

	public Response success() {
		this.meta = new Meta(true, OK);
		return this;
	}

	public Response success(Object data) {
		this.meta = new Meta(true, OK);
		this.data = data;
		return this;
	}

	public Response failure() {
		this.meta = new Meta(false, ERROR);
		return this;
	}

	public Response failure(String message) {
		this.meta = new Meta(false, message);
		return this;
	}
	
	public Response failure(String message,Object data) {
		this.meta = new Meta(false, message);
		this.data = data;
		return this;
	}
	
	public Meta getMeta() {
		return meta;
	}

	public Object getData() {
		return data;
	}

	public class Meta {
		
		@ApiModelProperty("返回状态")
		private boolean success;
		@ApiModelProperty("消息")
		private String message;

		public Meta(boolean success) {
			this.success = success;
		}

		public Meta(boolean success, String message) {
			this.success = success;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public String getMessage() {
			return message;
		}
	}

}
