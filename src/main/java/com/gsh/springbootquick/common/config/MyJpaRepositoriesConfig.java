package com.gsh.springbootquick.common.config;

import com.slyak.spring.jpa.GenericJpaRepositoryFactoryBean;
import com.slyak.spring.jpa.GenericJpaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author GSH
 * @create 2023/2/3 11:01
 */
//@Configuration
//@EnableJpaRepositories(basePackages = "com.gsh.springbootquick.repository")
@EnableJpaRepositories(basePackages = "com.gsh.springbootquick",
//        entityManagerFactoryRef = "myEntityManagerFactory",
//        transactionManagerRef = "myTransactionManager",
        repositoryBaseClass = GenericJpaRepositoryImpl.class,
        repositoryFactoryBeanClass = GenericJpaRepositoryFactoryBean.class)
//@EntityScan("com.gsh.springbootquick.entity")
//@EnableTransactionManagement
//@EnableJpaAuditing
public class MyJpaRepositoriesConfig {

    @Autowired
    @Qualifier("druid")
    private DataSource druidDataSource;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        //这个扫描的是Entity(JavaBean)的位置，注意与上方的repository区别开
        bean.setPackagesToScan("com.**.entity");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        bean.setDataSource(druidDataSource);
//        bean.setJpaProperties(prop);
        return bean;
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(entityManagerFactory);
        return jtm;
    }

    //    @Primary
    //    @Bean
    //    public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
    //        return entityManagerFactory.createEntityManager();
    //    }

    //    @Bean
    //    public FreemarkerSqlTemplates freemarkerSqlTemplates() {
    //        FreemarkerSqlTemplates templates = new FreemarkerSqlTemplates();
    //        templates.setSuffix(".sftl");
    //        return templates;
    //    }

    //这4个value与application.properties中对应；冒号后是默认值
    //    @Value("${spring.datasource.username:root}")
    //    private String username;
    //    @Value("${spring.datasource.password:root}")
    //    private String password;
    //    @Value("${spring.datasource.driver-class-name}")
    //    private String driverClassName;
    //    @Value("${spring.datasource.url}")
    //    private String url;

    //    @Bean
    //    public HikariDataSource dataSource(){
    //        HikariDataSource hds = new HikariDataSource();
    //        hds.setUsername(username);
    //        hds.setPassword(password);
    //        hds.setJdbcUrl(url);
    //        hds.setDriverClassName(driverClassName);
    //        hds.setAutoCommit(true);
    ////        hds.setConnectionTestQuery(connectionTestQuery);
    ////        hds.setConnectionTimeout(connectionTimeout);
    ////        hds.setIdleTimeout(idleTimeout);
    ////        hds.setMaxLifetime(maxLifetime);
    ////        hds.setMaximumPoolSize(maximumPoolSize);
    ////        hds.setMinimumIdle(minimumIdle);
    //        return hds;
    //    }

    //    @Bean
    //    public Properties prop(){
    //        Properties prop = new Properties();
    //        //prop.put("hibernate.connection.driver_class",mydriver);
    //        //prop.put("hibernate.connection.url",myurl);
    //        //prop.put("hibernate.connection.username",myuser);
    //        //prop.put("hibernate.connection.password",mypass);
    //        prop.put("hibernate.show_sql","true");
    //        prop.put("hibernate.connection.userUnicode","true");
    //        prop.put("hibernate.connection.characterEncoding","UTF-8");
    //        prop.put("hibernate.format_sql","true");
    //        prop.put("hibernate.use_sql_comments","true");
    //        prop.put("hibernate.hbm2ddl.auto","update");
    //        prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
    //        prop.put("hibernate.connection.autoReconnect","true");
    //        prop.put("hibernate.connection.autoReconnectForPools","true");
    //        prop.put("hibernate.connection.is-connection-validation-required","true");
    //        prop.put("validationQuery","SELECT 1");
    //        prop.put("testOnBorrow","true");
    //        return prop;
    //    }
}

