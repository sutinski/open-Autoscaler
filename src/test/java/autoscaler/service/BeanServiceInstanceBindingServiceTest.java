package autoscaler.service;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import autoscaler.model.CreateServiceInstanceBindingRequest;
import autoscaler.model.CreateServiceInstanceBindingResponse;
import autoscaler.model.CreateServiceInstanceRequest;
import autoscaler.model.CreateServiceInstanceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({ autoscaler.Application.class })
@WebAppConfiguration
public class BeanServiceInstanceBindingServiceTest {
	
	@Autowired
	ServiceInstanceBindingService bindingService;
	
	
	@Test
	public void itBoundToApp() {
		CreateServiceInstanceBindingRequest req = new CreateServiceInstanceBindingRequest("service-definition-id", "plan-id","app-id", null, null).withBindingId(UUID.randomUUID().toString()).withServiceInstanceId("service-instance-id");
		CreateServiceInstanceBindingResponse rsp = bindingService.createServiceInstanceBinding(req);
		Assert.assertTrue(rsp.isBindingExisted());
	}
}
