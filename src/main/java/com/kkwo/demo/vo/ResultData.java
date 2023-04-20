package com.kkwo.demo.vo;

import lombok.Getter;

public class ResultData<DT> {
	@Getter
	private String resultCode;
	@Getter
	private String resultMsg;
	@Getter
	private DT data1;

	public static <DT> ResultData<DT> from(String resultCode, String resultMsg) {
		return ResultData.from(resultCode, resultMsg, null);
	}

	public static <DT> ResultData<DT> from(String resultCode, String resultMsg, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultCode = resultCode;
		rd.resultMsg = resultMsg;
		rd.data1 = data1;

		return rd;
	}
	
	public static <DT> ResultData<DT> newData(ResultData rd, DT newData){
		return from(rd.getResultCode(), rd.getResultMsg(), newData);
	}

	// 성립 조건 : resultCode가 'S-' 로 시작
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
}