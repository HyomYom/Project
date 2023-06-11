package com.openwifi.dto;

public class HistoryDTO {
	private int ROWID;
	private String USER_X_COD;
	private String USER_Y_COD;
	private String INQUIRY_DATE;
	
	public int getROWID() {
		return ROWID;
	}
	public void setROWID(int ROWID) {
		this.ROWID = ROWID;
	}
	public String getUSER_X_COD() {
		return USER_X_COD;
	}
	public void setUSER_X_COD(String uSER_X_COD) {
		USER_X_COD = uSER_X_COD;
	}
	public String getUSER_Y_COD() {
		return USER_Y_COD;
	}
	public void setUSER_Y_COD(String uSER_Y_COD) {
		USER_Y_COD = uSER_Y_COD;
	}
	public String getINQUIRY_DATE() {
		return INQUIRY_DATE;
	}
	public void setINQUIRY_DATE(String iNQUIRY_DATE) {
		INQUIRY_DATE = iNQUIRY_DATE;
	}
	
	
	@Override
	public String toString() {
		return "HistoryDTO [rowid=" + ROWID + ", USER_X_COD=" + USER_X_COD + ", USER_Y_COD=" + USER_Y_COD
				+ ", INQUIRY_DATE=" + INQUIRY_DATE + "]";
	}
	

}
