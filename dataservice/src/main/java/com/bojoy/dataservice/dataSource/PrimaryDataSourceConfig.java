package com.bojoy.dataservice.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 主要查询的数据库
 */
@Configuration
// TODO: 2019/2/27  1、修改 MapperScan 2、修改ConfigurationProperties配置
@MapperScan(basePackages = "com.bojoy.dataservice.mapper.primary", sqlSessionTemplateRef  = "SqlSession1")
public class PrimaryDataSourceConfig {

    @Bean(name = "DataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "SqlSessionFactory1")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("DataSource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/primary/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "TransactionManager1")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("DataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "SqlSession1")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("SqlSessionFactory1") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
