package com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WifiInfoDTO {

	@SerializedName("TbPublicWifiInfo")
	@Expose
	private TbPublicWifiInfo tbPublicWifiInfo;

	public TbPublicWifiInfo getTbPublicWifiInfo() {
		return tbPublicWifiInfo;
	}

	public void setTbPublicWifiInfo(TbPublicWifiInfo tbPublicWifiInfo) {
		this.tbPublicWifiInfo = tbPublicWifiInfo;
	}

}