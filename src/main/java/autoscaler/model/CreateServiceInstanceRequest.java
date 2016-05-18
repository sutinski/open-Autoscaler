package autoscaler.model;

import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Details of a request to create a new service instance.
 * 
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class CreateServiceInstanceRequest {

	/**
	 * The ID of the service to provision, from the broker catalog.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("service_id")
	private final String serviceDefinitionId;

	/**
	 * The ID of the plan to provision within the service, from the broker catalog.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("plan_id")
	private final String planId;

	/**
	 * The Cloud Controller GUID of the organization under which the service is to be provisioned.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("organization_guid")
	private final String organizationGuid;

	/**
	 * The Cloud Controller GUID of the space under which the service is to be provisioned.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("space_guid")
	private final String spaceGuid;

	/**
	 * The Cloud Controller GUID of the service instance to provision. This ID will be used for future requests for the
	 * same service instance (e.g. bind and deprovision), so the broker must use it to correlate any resource it
	 * creates.
	 */
	@JsonIgnore
	private transient String serviceInstanceId;

	/**
	 * The {@link ServiceDefinition} of the service to provision. This is resolved from the
	 * <code>serviceDefinitionId</code> as a convenience to the broker.
	 */
	@JsonIgnore
	private transient ServiceDefinition serviceDefinition;

	public CreateServiceInstanceRequest() {
		this.serviceDefinitionId = null;
		this.planId = null;
		this.organizationGuid = null;
		this.spaceGuid = null;
	}

	public CreateServiceInstanceRequest(String serviceDefinitionId, String planId, String organizationGuid, String spaceGuid,
			Map<String, Object> parameters) {
		this.serviceDefinitionId = serviceDefinitionId;
		this.planId = planId;
		this.organizationGuid = organizationGuid;
		this.spaceGuid = spaceGuid;
	}

	public CreateServiceInstanceRequest(String serviceDefinitionId, String planId, String organizationGuid, String spaceGuid) {
		this(serviceDefinitionId, planId, organizationGuid, spaceGuid, null);
	}

	public CreateServiceInstanceRequest withServiceDefinition(ServiceDefinition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
		return this;
	}

	public CreateServiceInstanceRequest withServiceInstanceId(final String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
		return this;
	}

	public String getServiceDefinitionId() {
		return serviceDefinitionId;
	}

	public String getPlanId() {
		return planId;
	}

	public String getOrganizationGuid() {
		return organizationGuid;
	}

	public String getSpaceGuid() {
		return spaceGuid;
	}

	public String getServiceInstanceId() {
		return serviceInstanceId;
	}

	public ServiceDefinition getServiceDefinition() {
		return serviceDefinition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((organizationGuid == null) ? 0 : organizationGuid.hashCode());
		result = prime * result + ((planId == null) ? 0 : planId.hashCode());
		result = prime * result + ((serviceDefinitionId == null) ? 0 : serviceDefinitionId.hashCode());
		result = prime * result + ((spaceGuid == null) ? 0 : spaceGuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateServiceInstanceRequest other = (CreateServiceInstanceRequest) obj;
		if (organizationGuid == null) {
			if (other.organizationGuid != null)
				return false;
		} else if (!organizationGuid.equals(other.organizationGuid))
			return false;
		if (planId == null) {
			if (other.planId != null)
				return false;
		} else if (!planId.equals(other.planId))
			return false;
		if (serviceDefinitionId == null) {
			if (other.serviceDefinitionId != null)
				return false;
		} else if (!serviceDefinitionId.equals(other.serviceDefinitionId))
			return false;
		if (spaceGuid == null) {
			if (other.spaceGuid != null)
				return false;
		} else if (!spaceGuid.equals(other.spaceGuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateServiceInstanceRequest [serviceDefinitionId=" + serviceDefinitionId + ", planId=" + planId + ", organizationGuid="
				+ organizationGuid + ", spaceGuid=" + spaceGuid + "]";
	}
}
