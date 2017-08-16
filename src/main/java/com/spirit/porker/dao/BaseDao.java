package com.spirit.porker.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import com.spirit.porker.dao.pagination.PaginationInfo;
import com.spirit.porker.dao.pagination.PaginationList;

/**
 * @author user
 *
 * @param <Entity>
 */
public class BaseDao<Entity> extends DaoSupport implements ICommonCRUDDao<Entity> {

	// 多数据源扩展开始
	private SqlSession sqlSession;

	private SqlSession rSqlSession;

	private ThreadLocal<SqlSession> realSession = new ThreadLocal<SqlSession>();

	private boolean externalSqlSession;

	private void setRealSession(DatabaseOperationEnum operation) {
		if (null != operation) {
			switch (operation) {
			case READ:
				realSession.set(rSqlSession);
				break;
			case WRITE:
				realSession.set(sqlSession);
				break;
			case READWRITE:
				realSession.set(sqlSession);
				break;
			default:
				realSession.set(sqlSession);
				break;
			}
		} else {
			realSession.set(sqlSession);
		}
	}

	@Autowired(required = false)
	public final void setSqlSessionFactory(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		if (!this.externalSqlSession) {
			this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
			this.realSession.set(sqlSession);
		}
	}

	@Autowired(required = false)
	public final void setRSqlSessionFactory(@Qualifier("sqlSessionFactoryR") SqlSessionFactory SqlSessionFactoryR) {
		if (!this.externalSqlSession) {
			this.rSqlSession = new SqlSessionTemplate(SqlSessionFactoryR);
		}
	}

	/**
	 * Users should use this method to get a SqlSession to call its statement
	 * methods This is SqlSession is managed by spring. Users should not
	 * commit/rollback/close it because it will be automatically done.
	 *
	 * @return Spring managed thread safe SqlSession
	 */
	public final SqlSession getSqlSession() {
		setRealSession(DatabaseOperationType.getOperType().getValue());
		return this.realSession.get();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void checkDaoConfig() {
		Assert.notNull(this.realSession.get(), "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
	}

	// 多数据源扩展结束

	private Class<Entity> entityClass = null;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.entityClass = null;
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<Entity>) parameterizedType[0];
		}
	}

	public Class<Entity> getEntityClass() {
		return entityClass;
	}

	@Autowired
	public void setMySqlSessionFactory(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		this.setSqlSessionFactory(sqlSessionFactory);
	}

	@SuppressWarnings("unchecked")
	public PaginationList selectPaginationList(String statement, Object parameter, PaginationInfo paginationInfo) {
		PaginationList paginationList = new PaginationList();

		if (parameter == null) {
			throw new RuntimeException("parameter can not be null");
		}
		if (parameter instanceof Map<?, ?>) {
			((Map) parameter).put("paginationInfo", paginationInfo);
		}
		List result = this.getSqlSession().selectList(statement, parameter);

		paginationList.addAll(result);
		if (paginationInfo == null) {
			paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPage(1);
			paginationInfo.setTotalRecord(result.size());
			if(result.size()%paginationInfo.getRecordPerPage().intValue() !=0){
				paginationInfo.setTotalPage(result.size()/paginationInfo.getRecordPerPage().intValue()+1 );
			}else{
				paginationInfo.setTotalPage(result.size()/paginationInfo.getRecordPerPage().intValue());
			}
		}
		paginationList.setPaginationInfo(paginationInfo);

		return paginationList;
	}

	@Override
	public boolean deleteEntity(Entity entity) {
		int result = this.getSqlSession().delete(this.getEntityClass().getSimpleName() + ".deleteEntity", entity);
		return result == 1;
	}

	@Override
	public List<Entity> deleteEntityList(List<Entity> entityList) {
		List<Entity> result = new ArrayList<Entity>();
		for (Entity entity : entityList) {
			int operationResult = this.getSqlSession().delete(this.getEntityClass().getSimpleName() + ".deleteEntity",
					entity);
			if (operationResult == 1) {
				result.add(entity);
			}
		}
		return result;
	}

	public Entity addEntity(Entity entity) {
		this.getSqlSession().insert(this.getEntityClass().getSimpleName() + ".addEntity", entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Entity findEntityById(Entity entity) {
		return (Entity) this.getSqlSession().selectOne(this.getEntityClass().getSimpleName() + ".findEntityById",
				entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationList<Entity> findEntityListByCond(Map<String, Object> cond, PaginationInfo paginationInfo) {
		String name=this.getEntityClass().getSimpleName() + ".findEntityListByCond";
		return this.selectPaginationList(this.getEntityClass().getSimpleName() + ".findEntityListByCond", cond,
				paginationInfo);
	}

	@Override
	public boolean updateEntity(Entity entity) {
		int result = this.getSqlSession().update(this.getEntityClass().getSimpleName() + ".updateEntity", entity);
		return result == 1;
	}

	@Override
	public List<Entity> updateEntityList(List<Entity> entityList) {
		List<Entity> result = new ArrayList<Entity>();
		for (Entity entity : entityList) {
			int operationResult = this.getSqlSession().update(this.getEntityClass().getSimpleName() + ".updateEntity",
					entity);
			if (operationResult == 1) {
				result.add(entity);
			}
		}
		return result;
	}

}
