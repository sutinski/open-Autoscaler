package autoscaler.service;

import autoscaler.controller.CreateServiceInstanceResponse;
import autoscaler.model.CreateServiceInstanceRequest;

public interface ServiceInstanceService {
	/**
	 * Create (provision) a new service instance.
	 *
	 * @param request
	 *            containing the details of the request
	 * @return the details of the completed request
	 * @throws ServiceInstanceExistsException
	 *             if a service instance with the given ID is already known to the broker
	 * @throws ServiceBrokerAsyncRequiredException
	 *             if the broker requires asynchronous processing of the request
	 */
	CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request);
}
