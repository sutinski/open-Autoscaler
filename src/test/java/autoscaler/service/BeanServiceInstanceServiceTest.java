package autoscaler.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import autoscaler.controller.CreateServiceInstanceResponse;
import autoscaler.model.CreateServiceInstanceRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({ autoscaler.Application.class })
@WebAppConfiguration
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
    		config=@SqlConfig(dataSource="datasource"),
            scripts = "file:sql/create_servicebroker.sql") ,
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, 
    		config=@SqlConfig(dataSource="datasource"),
    		scripts = "file:sql/drop_servicebroker.sql") 
    })
public class BeanServiceInstanceServiceTest {
	
	@Autowired
	ServiceInstanceService service;
	
	
	@Test
	public void itCreatesAServiceInstance() {
		CreateServiceInstanceRequest req = new CreateServiceInstanceRequest("service-definition-id", "plan-id", "org-id", "space-id", "service-instance-id");
		CreateServiceInstanceResponse rsp = service.createServiceInstance(req);
		Assert.assertTrue(rsp.isInstanceExisted());
	}
}
