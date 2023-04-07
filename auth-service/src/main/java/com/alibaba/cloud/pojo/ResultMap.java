package com.alibaba.cloud.pojo;

import lombok.Data;

/**
 * @Auth tom
 * @Date 2023-04-07 18:07:12
 */
@Data
public class ResultMap<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultMap<T> success(T data){
        ResultMap<T> tResultMap = new ResultMap<>();
        tResultMap.setCode(200);
        tResultMap.setData(data);
        tResultMap.setMsg("success");
        return tResultMap;
    }

    public ResultMap<T> fail(T data){
        ResultMap<T> tResultMap = new ResultMap<>();
        tResultMap.setCode(500);
        tResultMap.setData(data);
        tResultMap.setMsg("fail");
        return tResultMap;
    }
}
