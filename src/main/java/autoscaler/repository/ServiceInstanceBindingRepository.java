package autoscaler.repository;

import org.springframework.data.repository.CrudRepository;

import autoscaler.model.ServiceInstanceBinding;

public interface ServiceInstanceBindingRepository extends CrudRepository<ServiceInstanceBinding, Integer>{
	
	
}
