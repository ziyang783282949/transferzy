package com.spirit.porker.dao;

public class DatabaseOperationType {

	private DatabaseOperationType() {
	}

	private static final ThreadLocal<DatabaseOperationType> localContext = new ThreadLocal<DatabaseOperationType>();

	private DatabaseOperationEnum value;

	public DatabaseOperationEnum getValue() {
		return value;
	}

	public void setValue(DatabaseOperationEnum value) {
		this.value = value;
	}

	public static DatabaseOperationType getOperType() {
		DatabaseOperationType ctx = (DatabaseOperationType) localContext.get();
		if (ctx == null) {
			ctx = new DatabaseOperationType();
			localContext.set(ctx);
		}
		return ctx;
	}

}
