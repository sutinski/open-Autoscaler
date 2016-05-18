package autoscaler.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import autoscaler.model.Catalog;
import autoscaler.model.ServiceDefinition;

public class BeanCatalogService implements CatalogService {

	private Catalog catalog;
	private Map<String, ServiceDefinition> serviceDefs = new HashMap<String, ServiceDefinition>();

	@Autowired
	public BeanCatalogService(Catalog catalog) {
		this.catalog = catalog;
		for (ServiceDefinition def : catalog.getServiceDefinitions()) {
			serviceDefs.put(def.getId(), def);
		}

	}

	@Override
	public Catalog getCatalog() {
		return catalog;
	}

	@Override
	public ServiceDefinition getServiceDefinition(String serviceId) {
		return serviceDefs.get(serviceId);
	}
}
