package autoscaler.model.fixture;

import java.util.Collections;
import java.util.List;

import autoscaler.model.Catalog;
import autoscaler.model.ServiceDefinition;

public class CatalogFixture {

	public static Catalog getCatalog() {
		List<ServiceDefinition> services = Collections.singletonList(ServiceFixture.getSimpleService());
		return new Catalog(services);
	}

	public static Catalog getCatalogWithRequires() {
		List<ServiceDefinition> services = Collections.singletonList(ServiceFixture.getServiceWithRequires());
		return new Catalog(services);
	}

}
