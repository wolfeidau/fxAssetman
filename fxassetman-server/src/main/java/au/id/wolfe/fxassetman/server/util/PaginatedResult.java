package au.id.wolfe.fxassetman.server.util;

import java.util.Set;

public class PaginatedResult<T> {
	
	private Class<T> type;
	private Set<T> resultSet;
	private long totalRecords;
	
	public PaginatedResult(Class<T> type){
		this.type = type;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public Set<T> getResultSet() {
		return resultSet;
	}

	public void setResultSet(Set<T> resultSet) {
		this.resultSet = resultSet;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
}
