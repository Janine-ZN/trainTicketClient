package com.trainticket.dto;

public class StationCode {
	
	// Janine.Z:一些数据常量
	public static final String TABLE_NAME = "station_code";

	public static final String ID = "_id";
	public static final String NAME = "name";
	public static final String CODE = "code";
	public static final String JIANPIN = "jianpin";
	public static final String QUANPIN = "quanpin";

	// Janine.Z:表属性，即字段名
	private int _id;
	private String name;
	private String code;
	private String jianPin;
	private String quanPin;

	public StationCode() {
		super();
	}

	public StationCode(String name, String code, String jianPin, String quanPin) {
		super();
		this.name = name;
		this.code = code;
		this.jianPin = jianPin;
		this.quanPin = quanPin;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getJianPin() {
		return jianPin;
	}

	public void setJianPin(String jianPin) {
		this.jianPin = jianPin;
	}

	public String getQuanPin() {
		return quanPin;
	}

	public void setQuanPin(String quanPin) {
		this.quanPin = quanPin;
	}

	@Override
	public String toString() {
		return "StationCode [name=" + name + ", code=" + code + ", jianPin=" + jianPin + ", quanPin=" + quanPin + "]";
	}

}
