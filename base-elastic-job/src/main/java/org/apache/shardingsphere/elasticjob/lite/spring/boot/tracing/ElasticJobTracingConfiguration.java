package org.apache.shardingsphere.elasticjob.lite.spring.boot.tracing;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;

/**
 * ElasticJob tracing auto configuration.
 */
@EnableConfigurationProperties(TracingProperties.class)
public class ElasticJobTracingConfiguration {

    /**
     * Create a bean of tracing DataSource.
     *
     * @param tracingProperties tracing Properties
     * @return tracing DataSource
     */
    @Bean("tracingDataSource")
    public DataSource tracingDataSource(final TracingProperties tracingProperties) {
        DataSourceProperties dataSource = tracingProperties.getDataSource();
        if (dataSource == null) {
            return null;
        }
        DruidDataSource tracingDataSource = new DruidDataSource();
        BeanUtils.copyProperties(dataSource, tracingDataSource);
        return tracingDataSource;
    }
    
    /**
     * Create a bean of tracing configuration.
     *
     * @param dataSource required by constructor
     * @return a bean of tracing configuration
     */
    @Bean
    @ConditionalOnBean(DataSource.class)
    @ConditionalOnProperty(name = "elasticjob.tracing.type", havingValue = "RDB")
    public TracingConfiguration<DataSource> tracingConfiguration(final DataSource dataSource, @Nullable final DataSource tracingDataSource) {
        DataSource ds = tracingDataSource;
        if (ds == null) {
            ds = dataSource;
        }
        return new TracingConfiguration<>("RDB", ds);
    }
}