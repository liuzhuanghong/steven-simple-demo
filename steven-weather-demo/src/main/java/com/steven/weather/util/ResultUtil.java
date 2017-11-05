package com.steven.weather.util;

import com.steven.weather.domain.Result;

/**
 * 结果工具类
 * @author Steven
 *
 */
public class ResultUtil {
	public static Result<Object> success(Object object) {
		Result<Object> result = new Result<Object>();
		result.setCode(0);
		result.setMsg("成功");
		result.setData(object);
		return result;
	}

	public static Result<Object> success() {
		return success(null);
	}

	public static Result<Object> error(Integer code, String msg) {
		Result<Object> result = new Result<Object>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
