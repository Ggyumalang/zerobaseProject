package com.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TbPublicWifiInfo {

	@SerializedName("list_total_count")
	@Expose
	private long listTotalCount;
	@SerializedName("RESULT")
	@Expose
	private Result result;
	@SerializedName("row")
	@Expose
	private List<Row> row = null;

	public long getListTotalCount() {
		return listTotalCount;
	}

	public void setListTotalCount(long listTotalCount) {
		this.listTotalCount = listTotalCount;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public List<Row> getRow() {
		return row;
	}

	public void setRow(List<Row> row) {
		this.row = row;
	}

}
