package com.model;

import java.sql.Timestamp;

public class LocInqHistDTO {
	int inqNo;
	double lat;
	double lnt;
	Timestamp inq_dtm;

	public int getInqNo() {
		return inqNo;
	}

	public void setInqNo(int inqNo) {
		this.inqNo = inqNo;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLnt() {
		return lnt;
	}

	public void setLnt(double lnt) {
		this.lnt = lnt;
	}

	public Timestamp getInq_dtm() {
		return inq_dtm;
	}

	public void setInq_dtm(Timestamp inq_dtm) {
		this.inq_dtm = inq_dtm;
	}

}
