package autoscaler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import autoscaler.model.CreateServiceInstanceRequest;
import autoscaler.model.CreateServiceInstanceResponse;

public class BeanServiceInstanceService implements ServiceInstanceService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
		int rows = jdbcTemplate.update("insert into service_instance(service_id, space_id, org_id) values (?,?,?)", request.getServiceInstanceId(),
				request.getSpaceGuid(), request.getOrganizationGuid());
		return new CreateServiceInstanceResponse().withInstanceExisted(rows == 1);
	}
}
