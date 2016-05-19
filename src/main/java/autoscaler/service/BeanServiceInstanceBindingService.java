package autoscaler.service;

import org.springframework.beans.factory.annotation.Autowired;

import autoscaler.model.CreateServiceInstanceBindingRequest;
import autoscaler.model.CreateServiceInstanceBindingResponse;
import autoscaler.model.ServiceInstanceBinding;
import autoscaler.repository.ServiceInstanceBindingRepository;

public class BeanServiceInstanceBindingService implements ServiceInstanceBindingService {

	@Autowired
	private ServiceInstanceBindingRepository repository;

	@Override
	public CreateServiceInstanceBindingResponse createServiceInstanceBinding(
			CreateServiceInstanceBindingRequest request) {
		
		ServiceInstanceBinding sib = new ServiceInstanceBinding();
		sib.setBindingId(request.getBindingId());
		sib.setAppId(request.getAppGuid());
		sib.setServiceInstanceId(request.getServiceInstanceId());
		repository.save(sib);
		int rows = 1;
		return new CreateServiceInstanceBindingResponse().withBindingExisted(rows == 1);
		
	}

}
