package wu.interceptor;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;

@Component
@Intercepts({
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
/**
 * mybatis数据库操作拦截器，记录执行了什么语句
 */
public class MybatisInterceptor implements Interceptor {
	private static final Logger LOGGER= LoggerFactory.getLogger(MybatisInterceptor.class);
	private Properties properties;

	/**
	 * 拦截器
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	public Object intercept(Invocation invocation) throws Exception {
		LOGGER.debug("Mybatis数据库操作拦截器拦截开始");
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		String sqlId = mappedStatement.getId();
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Configuration configuration = mappedStatement.getConfiguration();
		Object returnValue = null;
		long start = System.currentTimeMillis();
		returnValue = invocation.proceed();
		long end = System.currentTimeMillis();
		long time = (end - start);
		String sql = getSql(configuration, boundSql, sqlId, time);
		LOGGER.info(sql);
		LOGGER.debug("SqlId：{}", sqlId);
		LOGGER.debug("boundSql：{}", boundSql);
		LOGGER.debug("执行语句：{}", sql);
		LOGGER.debug("执行时间：{}", time);
		LOGGER.debug("Mybatis数据库操作拦截器拦截结束");
		return returnValue;
	}

	/**
	 * 获取执行的语句
	 * @param configuration
	 * @param boundSql
	 * @param sqlId
	 * @param time
	 * @return
	 */
	public String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
		Map<String,Object> map = new HashMap<String,Object>(0);
		String sql = showSql(configuration, boundSql, map);
		StringBuilder str = new StringBuilder("");
		str.append("\n执行方法:");
		str.append(sqlId);
		str.append("\n执行语句:");
		str.append(sql);
		str.append("\n执行时间:");
		str.append(time);
		str.append(" ms");
		return str.toString();
	}

	/**
	 * 显示SQL语句
	 * @param configuration
	 * @param boundSql
	 * @param map
	 * @return
	 */
	public String showSql(Configuration configuration, BoundSql boundSql, Map<String, Object> map) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterObject != null && parameterMappings.size() > 0) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				String objparam = getParameterValue(parameterObject);
				sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(objparam));
				map.put("0", objparam);
			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				int i = 0;
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						String parame = getParameterValue(obj);
						LOGGER.info("参数：{}", parame);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(parame));
						map.put(String.valueOf(i), parame);
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						String parame = getParameterValue(obj);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(parame));
						map.put(String.valueOf(i), parame);
					}
				}
			}
		}
		return sql;
	}

	/**
	 * 获取参数值
	 * @param obj
	 * @return
	 */
	private String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "''";
			}
		}
		return value;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties0) {
		this.properties = properties0;
	}
}
