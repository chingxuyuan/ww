
package com.woyaofa.exchange.web;

import java.util.List;

public class Result {

	protected boolean	result	= false;
	protected String	message	= null;

	public boolean isResult () {

		return result;
	}

	public void setResult (boolean result) {

		this.result = result;
	}

	public String getMessage () {

		return message;
	}

	public void setMessage (String message) {

		this.message = message;
	}

	public static Result newErrorResult (String message) {

		Result result = new Result();
		result.setMessage(message);
		result.setResult(false);

		return result;
	}

	public static Result newSuccessResult () {

		Result result = new Result();
		result.setResult(true);

		return result;
	}

	public static Result newSuccessResult (String message) {

		Result result = new Result();
		result.setMessage(message);
		result.setResult(true);

		return result;
	}

	public static Result newObjectResult (Object object) {

		SimpleObjectResult result = new SimpleObjectResult();
		result.setObject(object);
		result.setResult(true);

		return result;
	}

	public static Result newObjectResult (List<?> items, int pageIndex, int total, int pageSize) {

		RecordSetResult result = new RecordSetResult();
		result.setObject(items);
		result.setStart(pageIndex * pageSize);
		result.setTotal(total);
		result.setPageIndex(pageIndex);
		result.setPageCount((total - 1) / pageSize + 1);
		result.setPageSize(pageSize);
		result.setResult(true);

		return result;
	}

}
