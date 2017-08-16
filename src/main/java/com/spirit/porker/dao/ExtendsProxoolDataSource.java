package com.spirit.porker.dao;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.logicalcobwebs.proxool.ProxoolDataSource;

public class ExtendsProxoolDataSource extends ProxoolDataSource{

	/**
	 * @param houseKeepingSleepTime
	 *            the houseKeepingSleepTime to set
	 */
	public void setHouseKeepingSleepTime(long houseKeepingSleepTime) {
	
		Long tp = new Long(houseKeepingSleepTime);
		super.setHouseKeepingSleepTime(tp.intValue());
	}
	
	/**
	 * @param maximumConnectionLifetime
	 *            the maximumConnectionLifetime to set
	 */
	public void setMaximumConnectionLifetime(long maximumConnectionLifetime) {
	
		Long tp = new Long(maximumConnectionLifetime);
		super.setHouseKeepingSleepTime(tp.intValue());
	}
	
	/**
	 * @param overloadWithoutRefusalLifetime
	 *            the overloadWithoutRefusalLifetime to set
	 */
	public void setOverloadWithoutRefusalLifetime(long overloadWithoutRefusalLifetime) {
	
		Long tp = new Long(overloadWithoutRefusalLifetime);
		super.setHouseKeepingSleepTime(tp.intValue());
	}


    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
