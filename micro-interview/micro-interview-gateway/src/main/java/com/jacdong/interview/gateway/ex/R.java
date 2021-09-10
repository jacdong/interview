package com.jacdong.interview.gateway.ex;

import java.util.HashMap;
import java.util.Map;

import com.jacdong.interview.common.ResultCode;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName: R
 * @Description: Response 通用对象
 * @author DongBin
 * @date 2021-09-07 10:47:02
 */
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Long code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data=new HashMap<String, Object>();

    //构造私有，只能使用静态方法的数据结构
    private R(){};

    //成功 返回数据结构
    public static R ok(){
        R r=new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage("成功");
        return r;
    }

    //失败 返回数据结构
    public static R error(){
        R r=new R();
        r.setSuccess(false);
        r.setCode(ResultCode.FAILED.getCode());
        r.setMessage("失败");
        return r;
    }

    //可以链式编程
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Long code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}

