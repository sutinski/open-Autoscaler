package autoscaler.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import autoscaler.controller.CreateServiceInstanceResponse;
import autoscaler.model.CreateServiceInstanceRequest;

public class BeanServiceInstanceService implements ServiceInstanceService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BeanServiceInstanceService(DataSource ds) {
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(ds);
		this.jdbcTemplate = jdbc;
	}

	@Override
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
		int rows = jdbcTemplate.update("insert into service_instance(service_id, space_id, org_id) values (?,?,?)", request.getServiceInstanceId(),
				request.getSpaceGuid(), request.getOrganizationGuid());
		return new CreateServiceInstanceResponse().withInstanceExisted(rows == 1);
	}
}
