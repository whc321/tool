package com.whc.model;

import java.io.Serializable;

/**
 * @author wang_haichun
 * @date 2018/6/8
 */
public class RepResultParam implements Serializable {

    private String resultCode;
    private String resultMsg;
    private Object resultData;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "RepResultParam{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", resultData='" + resultData + '\'' +
                '}';
    }
}
