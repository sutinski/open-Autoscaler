package autoscaler.service;

import autoscaler.model.CreateServiceInstanceBindingRequest;
import autoscaler.model.CreateServiceInstanceBindingResponse;


public interface ServiceInstanceBindingService {

	/**
	 * Create a new binding to a service instance.
	 *
	 * @param request containing parameters sent from Cloud Controller
	 * @return a CreateServiceInstanceBindingResponse
	 * @throws ServiceInstanceBindingExistsException if a binding with the given ID is already known to the broker
	 * @throws ServiceInstanceDoesNotExistException if a service instance with the given ID is not known to the broker
	 * @throws ServiceBrokerException on internal failure
	 */
	CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest request);

	/**
	 * Delete a service instance binding.
	 *
	 * @param request containing parameters sent from Cloud Controller
	 * @throws ServiceInstanceDoesNotExistException if a service instance with the given ID is not known to the broker
	 * @throws ServiceInstanceBindingDoesNotExistException if a binding with the given ID is not known to the broker
	 */
	//void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request);
}
