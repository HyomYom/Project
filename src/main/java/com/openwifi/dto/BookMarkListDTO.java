package com.openwifi.dto;


public class BookMarkListDTO {
	private int ROWID;
	private int GROUP_ID;
	private String WIFI_ID;
	private String GROUP_NAME;
	private String WIFI_NAME;
	private String ADD_DATE;
	
	public String getWIFI_ID() {
		return WIFI_ID;
	}
	public void setWIFI_ID(String wIFI_ID) {
		WIFI_ID = wIFI_ID;
	}
	
	public int getROWID() {
		return ROWID;
	}
	public void setROWID(int rOWID) {
		ROWID = rOWID;
	}
	public String getGROUP_NAME() {
		return GROUP_NAME;
	}
	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}
	public String getADD_DATE() {
		return ADD_DATE;
	}
	public void setADD_DATE(String aDD_DATE) {
		ADD_DATE = aDD_DATE;
	}
	public String getWIFI_NAME() {
		return WIFI_NAME;
	}
	public void setWIFI_NAME(String wIFI_NAME) {
		WIFI_NAME = wIFI_NAME;
	}
	
	
	
	public int getGROUP_ID() {
		return GROUP_ID;
	}
	public void setGROUP_ID(int gROUP_ID) {
		GROUP_ID = gROUP_ID;
	}
	@Override
	public String toString() {
		return "BookMarkListDTO [ROWID=" + ROWID + ", GROUP_ID=" + GROUP_ID + ", WIFI_ID=" + WIFI_ID + ", GROUP_NAME="
				+ GROUP_NAME + ", WIFI_NAME=" + WIFI_NAME + ", ADD_DATE=" + ADD_DATE + "]";
	}
	
	
	
	
	
	
}
