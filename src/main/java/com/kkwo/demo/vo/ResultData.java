package com.kkwo.demo.vo;

import lombok.Getter;

public class ResultData<DT> {
	@Getter
	private String resultCode;
	@Getter
	private String resultMsg;
	@Getter
	private String data1Name;
	@Getter
	private DT data1;
	@Getter
	private String data2Name;
	@Getter
	private Object data2;
	

	public static <DT> ResultData<DT> from(String resultCode, String resultMsg) {
		return ResultData.from(resultCode, resultMsg, null, null);
	}

	public static <DT> ResultData<DT> from(String resultCode, String resultMsg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultCode = resultCode;
		rd.resultMsg = resultMsg;
		rd.data1Name = data1Name;
		rd.data1 = data1;

		return rd;
	}
	
	public static <DT> ResultData<DT> newData(ResultData rd, String data1Name, DT newData){
		return from(rd.getResultCode(), rd.getResultMsg(), data1Name, newData);
	}

	// 성립 조건 : resultCode가 'S-' 로 시작
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}

	public void setData2(String data2Name, Object data2) {
		this.data2Name = data2Name;
		this.data2 = data2;
	}
}