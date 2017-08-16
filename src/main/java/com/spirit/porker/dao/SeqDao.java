package com.spirit.porker.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.spirit.porker.util.LoggerUtil;

@Repository
public class SeqDao{

	@Resource
	DataSource dataSource;

	/**
	 * 利用mysql的replace使得数据库的主键id自增来获取唯一的id标识
	 * @param tableName
	 * @return
	 */
	public int querySeqId(String tableName) {

		int seq = 11702;

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate("REPLACE INTO " + tableName + "(stub) VALUES('a')");
			rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			while (rs.next()) {
				seq = rs.getInt(1);
			}

		} catch (Exception e) {
			LoggerUtil.error("getSeqId failure", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					LoggerUtil.error("getSeqId.rs failure", e);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					LoggerUtil.error("getSeqId.stmt failure", e);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					LoggerUtil.error("getSeqId.con failure", e);
				}
			}
		}
		return seq;
	}

	public int querySeqId() {
		final String DEFAULT_SEQ_TABLE = "tb_req";
		return querySeqId(DEFAULT_SEQ_TABLE);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
