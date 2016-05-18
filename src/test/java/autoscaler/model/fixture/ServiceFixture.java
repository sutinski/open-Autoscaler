package autoscaler.model.fixture;

import java.util.Arrays;

import autoscaler.model.ServiceDefinition;

public class ServiceFixture {

	public static ServiceDefinition getSimpleService() {
		return new ServiceDefinition("service-one-id", "Service One", "Description for Service One", true, PlanFixture.getAllPlans());
	}

	public static ServiceDefinition getServiceWithRequires() {
		return new ServiceDefinition("service-one-id", "Service One", "Description for Service One", true, true, PlanFixture.getAllPlans(), null,
				null, Arrays.asList("syslog_drain", "route_forwarding"), null);
	}

}