package model;

import java.io.Serializable;

public class UpdateMessage implements Serializable{

	/**
	 * 更新数据
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private Object value;
	
	public UpdateMessage(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
