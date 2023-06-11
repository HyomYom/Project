package com.openwifi.dto;


public class BookMarkDTO {
	private int ROWID;
	private String GROUP_NAME;
	private int ORDER;
	private String ADD_DATE;
	private String MODI_DATE;
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
	public int getORDER() {
		return ORDER;
	}
	public void setORDER(int oRDER) {
		ORDER = oRDER;
	}
	public String getADD_DATE() {
		return ADD_DATE;
	}
	public void setADD_DATE(String aDD_DATE) {
		ADD_DATE = aDD_DATE;
	}
	public String getMODI_DATE() {
		return MODI_DATE;
	}
	public void setMODI_DATE(String mODI_DATE) {
		MODI_DATE = mODI_DATE;
	}
	@Override
	public String toString() {
		return "BookMarkDTO [ROWID=" + ROWID + ", GROUP_NAME=" + GROUP_NAME + ", ORDER=" + ORDER + ", ADD_DATE="
				+ ADD_DATE + ", MODI_DATE=" + MODI_DATE + "]";
	}
	
	
	
	
}
