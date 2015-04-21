package com.intuit.traveldiary.dao;

import com.google.common.base.CaseFormat;
import com.intuit.traveldiary.dto.BaseDO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 * @author Vijayan Srinivasan
 * @since Jan 3, 2015 7:54:06 PM
 *
 */

@Repository
public abstract class GenericDAOImpl<T extends BaseDO> implements GenericDAO<T> {

	protected  Log logger = LogFactory.getLog(getClass());

	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedJdbcTemplate;
	protected BeanPropertyRowMapper<T> rowMapper;
	protected PropertyDescriptor[] pds;

	@Value("${generic.dao.camel.case.singular:true}")
	private Boolean camelCaseSingular;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private Class<T> type;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericDAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
		init();
	}

	public void init() {
		rowMapper = new BeanPropertyRowMapper<T>(type);
		pds = BeanUtils.getPropertyDescriptors(type);
	}

	

	public T create(final T t) {

		t.setCreatedTs(new Date());
		t.setLastUpdatedTs(null);

		 StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" ( ");
		for (PropertyDescriptor pd : pds) {
			if (!isIgnoreProperty(pd)) {
				String columnName = pd.getName();
				if (!camelCaseSingular) {
					columnName = CaseFormat.LOWER_CAMEL.to(
							CaseFormat.LOWER_UNDERSCORE, pd.getName());
				}
				sqlBuilder.append(columnName);
				sqlBuilder.append(", ");
			}
		}
		if (!camelCaseSingular) {
			sqlBuilder.append("created_ts");
		} else {
			sqlBuilder.append("createdTs");
		}

		sqlBuilder.append(" ) VALUES ( ");
		for (PropertyDescriptor pd : pds) {
			if (!isIgnoreProperty(pd)) {
				sqlBuilder.append("?, ");
			}
		}
		sqlBuilder.append("?");
		sqlBuilder.append(" )");
		final String sql = sqlBuilder.toString();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				int parameterIndex = 1;
				BeanWrapper bw = PropertyAccessorFactory
						.forBeanPropertyAccess(t);
				for (PropertyDescriptor pd : pds) {
					if (!isIgnoreProperty(pd)) {
						ps.setObject(parameterIndex++,
								bw.getPropertyValue(pd.getName()));
					}
				}
				ps.setObject(parameterIndex, t.getCreatedTs());
				return ps;
			}
		};

		logger.info(sql);
		jdbcTemplate.update(psc, keyHolder);

		t.setId(new Long(keyHolder.getKey().toString()));

		return t;
	}

	public void deleteById( Long id) {
		StringBuilder sqlBuilder = new StringBuilder("DELETE FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(getWhereClause("id"));
		String sql = sqlBuilder.toString();
		logger.info(sql);
		jdbcTemplate.update(sql, id);
	}

	public void deleteByIds(List<Long> ids) {
		StringBuilder sqlBuilder = new StringBuilder("DELETE FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(getWhereClause(ids));
		String sql = sqlBuilder.toString();
		logger.info(sql);

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		if (ids == null || ids.isEmpty()) {
			return;
		}
		parameters.addValue("ids", ids);
		namedJdbcTemplate.update(sql, parameters);
	}

	public T findById( Long id) {
		return findBy("id", id);
	}

	public T findBy( String columnName,  Object value) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(columnName, value);
		return findBy(params );
	}

	public T findBy( Map<String, Object> params) {
		List<T> ts = findManyBy(params);
		if (ts != null && ts.size() == 1) {
			return ts.get(0);
		}
		return null;
	}

	public List<T> findManyBy( String columnName,  Object value) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(columnName, value);
		return findManyBy(params);
	}

	public List<T> findManyBy( Map<String, Object> params) {
		 StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(getWhereClause(params));
		String sql = sqlBuilder.toString();
		logger.info(sql);
		return params == null ? namedJdbcTemplate.query(sql, rowMapper)
				: namedJdbcTemplate.query(sql, params, rowMapper);
	}

	

	public T updateById( T t) {
		if (t.getId() == null) {
			return null;
		}

		t.setLastUpdatedTs(new Date());

		 StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(" SET ");
		for (PropertyDescriptor pd : pds) {
			if (!isIgnoreProperty(pd)) {
				String columnName = pd.getName();
				if (!camelCaseSingular) {
					columnName = CaseFormat.LOWER_CAMEL.to(
							CaseFormat.LOWER_UNDERSCORE, pd.getName());
				}

				sqlBuilder.append(columnName);
				sqlBuilder.append(" = ");
				sqlBuilder.append("?, ");
			}
		}
		if (!camelCaseSingular) {
			sqlBuilder.append("Last_updated_ts = ? ");
		} else {
			sqlBuilder.append("LastUpdatedTs = ? ");
		}
		sqlBuilder.append(getWhereClause("id"));
		String sql = sqlBuilder.toString();

		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(t);

		ArrayList<Object> values = new ArrayList<Object>();
		for (PropertyDescriptor pd : pds) {
			if (!isIgnoreProperty(pd)) {
				values.add(bw.getPropertyValue(pd.getName()));
			}
		}
		values.add(t.getLastUpdatedTs());
		values.add(t.getId());

		logger.info(sql);
		jdbcTemplate.update(sql, values.toArray());
		return t;
	}

	/**
	 * Assumes Dtos are named with suffix of DO
	 */
	public String getTableName() {
		if (type != null) {
			String dtoName = type.getSimpleName();
			int length = dtoName.length();
			String tableName = dtoName.substring(0, length - 2);
			tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,
					tableName).toLowerCase();

	        return tableName;
		}
		return null;
	}

	private boolean isIgnoreProperty(PropertyDescriptor pd) {
		Method writeMethod = pd.getWriteMethod();
		return pd == null || writeMethod == null
				|| writeMethod.isAnnotationPresent(Formula.class)
				|| "id".equalsIgnoreCase(pd.getName())
				|| "lastUpdatedTs".equalsIgnoreCase(pd.getName())
				|| "createdTs".equalsIgnoreCase(pd.getName());
	}
	
	public Long count() {
		StringBuilder sqlBuilder = new StringBuilder(
				"SELECT COUNT(*) FROM ");
		sqlBuilder.append(getTableName());
		String sql = sqlBuilder.toString();
		logger.info(sql);
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	public Long countBy(String columnName, Object value) {
		StringBuilder sqlBuilder = new StringBuilder(
				"SELECT COUNT(*) FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(getWhereClause(columnName));
		String sql = sqlBuilder.toString();
		logger.info(sql);
		return jdbcTemplate.queryForObject(sql, Long.class, value);
	}

	public Long countBy(Map<String, Object> params) {
		StringBuilder sqlBuilder = new StringBuilder(
				"SELECT COUNT(*) FROM ");
		sqlBuilder.append(getTableName());
		sqlBuilder.append(getWhereClause(params));
		String sql = sqlBuilder.toString();
		logger.info(sql);
		return params == null ? jdbcTemplate.queryForObject(sql, Long.class)
				: namedJdbcTemplate.queryForObject(sql, params, Long.class);
	}
	
	private String getWhereClause(Map<String, Object> params) {
		StringBuilder sqlBuilder = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			sqlBuilder.append(" WHERE ");
			Set<String> keys = params.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				sqlBuilder.append(key).append(" = :").append(key);
				if (iterator.hasNext()) {
					sqlBuilder.append(" AND ");
				}
			}
		}
		return sqlBuilder.toString();
	}
	
	
	private String getWhereClause(String columnName) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append(columnName);
		sqlBuilder.append(" = ? ");
		return sqlBuilder.toString();
	}
	
	private String getWhereClause(List<Long> id) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append(" id IN (:ids )");
		return sqlBuilder.toString();
	}
	
	
}