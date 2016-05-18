package autoscaler.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import autoscaler.model.Catalog;
import autoscaler.service.BeanCatalogService;
import autoscaler.service.BeanServiceInstanceService;
import autoscaler.service.CatalogService;
import autoscaler.service.ServiceInstanceService;

@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class ServiceBrokerAutoConfiguration {
	// @Bean
	// @ConditionalOnMissingBean(BrokerApiVersion.class)
	// public BrokerApiVersion brokerApiVersion() {
	// return new BrokerApiVersion();
	// }
	@Bean(name="datasource")
	@ConfigurationProperties(prefix = "service_broker.jdbc")
	public DataSource getServiceDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public ServiceInstanceService beanServiceInstanceService() {
		return new BeanServiceInstanceService(getServiceDataSource());
	}

	@Bean
	public CatalogService beanCatalogService(Catalog catalog) {
		return new BeanCatalogService(catalog);
	}

	// @Bean
	// @ConditionalOnMissingBean(ServiceInstanceBindingService.class)
	// public ServiceInstanceBindingService nonBindableServiceInstanceBindingService() {
	// return new NonBindableServiceInstanceBindingService();
	// }
}