package wu.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import wu.interceptor.MybatisInterceptor;

import javax.sql.DataSource;

/**
 * @author dihua.wu
 * @description
 * @create 2020/6/1
 */
@Configurable
public class BaseDataSourceConfig {


    protected DataSource initDataSource(String url, String driver, String name, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url.trim());
        dataSource.setDriverClassName(driver.trim());
        dataSource.setUsername(name.trim());
        dataSource.setPassword(password.trim());
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("select 1 from dual");
        return dataSource;
    }


    protected SqlSessionFactory sqlSessionFactory(DataSource dataSource, String locationPattern) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(locationPattern));
        sqlSessionFactory.setPlugins(new Interceptor[]{new MybatisInterceptor()});
        return sqlSessionFactory.getObject();
    }

}
