package wu.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 网关报告ats_gate 数据源配置
 * @author wdhua
 */
@Configuration
@MapperScan(basePackages = AdgateDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "adgateSqlSessionFactory")
public class AdgateDataSourceConfig extends BaseDataSourceConfig{

    static final String PACKAGE = "wu.dao";

    @Value("${ats_gate.orcl.user}")
    private String name;
    @Value("${ats_gate.orcl.password}")
    private String password;
    @Value("${ats_gate.orcl.url}")
    private String url;
    @Value("${ats_gate.orcl.driver}")
    private String driver;

    @Bean(name = "adgateDataSource")
    public DataSource adgateDataSource() {
        return initDataSource(url, driver, name, password);
    }

    @Bean("adgateSqlSessionFactory")
    public SqlSessionFactory adgateSqlSessionFactory(@Qualifier("adgateDataSource") DataSource dataSource) throws Exception {
        final String locationPattern = "classpath*:mapping/*.xml";
        return sqlSessionFactory(dataSource, locationPattern);
    }



}
