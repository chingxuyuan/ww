
package com.woyaofa.exchange.web;

import java.util.List;

public class RecordSetResult extends Result {

	protected long		total		= 0;
	protected long		start		= 0;
	protected long		pageIndex	= 0;
	protected long		pageCount	= 0;
	protected long		pageSize	= 0;
	protected List<?>	object		= null;

	public long getTotal () {

		return total;
	}

	public void setTotal (long total) {

		if (total < 0) {
			total = 0;
		}

		this.total = total;
	}

	public long getStart () {

		return start;
	}

	public void setStart (long start) {

		if (start < 0) {
			start = 0;
		}

		this.start = start;
	}

	public long getPageIndex () {

		return pageIndex;
	}

	public void setPageIndex (long pageIndex) {

		if (pageIndex < 1) {
			pageIndex = 1;
		}

		this.pageIndex = pageIndex;
	}

	public long getPageCount () {

		return pageCount;
	}

	public void setPageCount (long pageCount) {

		if (pageCount < 0) {
			pageCount = 0;
		}

		this.pageCount = pageCount;
	}

	public long getPageSize () {

		return pageSize;
	}

	public void setPageSize (long pageSize) {

		if (pageSize < 1) {
			pageSize = 1;
		}

		this.pageSize = pageSize;
	}

	public List<?> getObject () {

		return object;
	}

	public void setObject (List<?> object) {

		this.object = object;
	}
}
