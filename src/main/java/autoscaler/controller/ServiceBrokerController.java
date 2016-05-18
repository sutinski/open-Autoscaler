package autoscaler.controller;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autoscaler.exception.ServiceDefinitionDoesNotExistException;
import autoscaler.model.Catalog;
import autoscaler.model.CreateServiceInstanceRequest;
import autoscaler.model.ServiceDefinition;
import autoscaler.service.CatalogService;
import autoscaler.service.ServiceInstanceService;

@RestController
public class ServiceBrokerController {
	private Logger log = LogManager.getLogger(ServiceBrokerController.class);
	private ServiceInstanceService service;
	private CatalogService catalogService;

	@Autowired
	public ServiceBrokerController(CatalogService catalogService, ServiceInstanceService service) {
		this.catalogService = catalogService;
		this.service = service;
	}

	@RequestMapping(path = "/v2/catalog", method = RequestMethod.GET)
	public Catalog getCatalog() {
		return catalogService.getCatalog();
	}

	@RequestMapping(path = "/v2/service_instances/{instanceId}", method = RequestMethod.PUT)
	public ResponseEntity<?> createServiceInstance(@PathVariable("instanceId") String serviceInstanceId,
			@Valid @RequestBody CreateServiceInstanceRequest request,
			@RequestParam(value = "accepts_incomplete", required = false) boolean acceptsIncomplete) {
		log.debug("Creating a service instance: serviceInstanceId=" + serviceInstanceId);

		ServiceDefinition serviceDefinition = getRequiredServiceDefinition(request.getServiceDefinitionId());

		request.withServiceInstanceId(serviceInstanceId).withServiceDefinition(serviceDefinition);

		CreateServiceInstanceResponse response = service.createServiceInstance(request);

		log.debug("Creating a service instance succeeded: serviceInstanceId=" + serviceInstanceId);

		return new ResponseEntity<>(response, getCreateResponseCode(response));
	}

	private HttpStatus getCreateResponseCode(CreateServiceInstanceResponse response) {
		if (response.isInstanceExisted()) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.CREATED;
		}
	}

	protected ServiceDefinition getRequiredServiceDefinition(String serviceDefinitionId) {
		ServiceDefinition serviceDefinition = getServiceDefinition(serviceDefinitionId);
		if (serviceDefinition == null) {
			throw new ServiceDefinitionDoesNotExistException(serviceDefinitionId);
		}
		return serviceDefinition;
	}

	protected ServiceDefinition getServiceDefinition(String serviceDefinitionId) {
		return catalogService.getServiceDefinition(serviceDefinitionId);
	}

}
