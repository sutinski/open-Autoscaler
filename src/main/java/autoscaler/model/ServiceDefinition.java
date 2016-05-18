package autoscaler.model;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDefinition {
	/**
	 * An identifier used to correlate this service in future requests to the catalog. This must be unique within
	 * a Cloud Foundry deployment. Using a GUID is recommended.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("id")
	private String id;

	/**
	 * A CLI-friendly name of the service that will appear in the catalog. The value should be all lowercase,
	 * with no spaces.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("name")
	private String name;

	/**
	 * A user-friendly short description of the service that will appear in the catalog.
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("description")
	private String description;

	/**
	 * Indicates whether the service can be bound to applications.
	 */
	@JsonSerialize
	@JsonProperty("bindable")
	private boolean bindable;

	/**
	 * Indicates whether the service supports requests to update instances to use a different plan from the one
	 * used to provision a service instance.
	 */
	@JsonSerialize
	@JsonProperty("plan_updateable")
	private boolean planUpdateable;

	/**
	 * A list of plans for this service.
	 */
	@NotEmpty
	@JsonSerialize(nullsUsing = EmptyListSerializer.class)
	@JsonProperty("plans")
	private List<Plan> plans;

	/**
	 * A list of tags to aid in categorizing and classifying services with similar characteristics.
	 */
	@JsonSerialize(nullsUsing = EmptyListSerializer.class)
	@JsonProperty("tags")
	private List<String> tags;

	/**
	 * A map of metadata to further describe a service offering.
	 */
	@JsonSerialize(nullsUsing = EmptyMapSerializer.class)
	@JsonProperty("metadata")
	private Map<String, Object> metadata;

	/**
	 * A list of permissions that the user would have to give the service, if they provision it. See
	 * {@link ServiceDefinitionRequires} for supported permissions.
	 */
	@JsonSerialize(nullsUsing = EmptyListSerializer.class)
	@JsonProperty("requires")
	private List<String> requires;

	/**
	 * Data necessary to activate the Dashboard SSO feature for this service.
	 */
	@JsonSerialize
	@JsonProperty("dashboard_client")
	private DashboardClient dashboardClient;

	public ServiceDefinition() {
	}

	public ServiceDefinition(String id, String name, String description, boolean bindable, List<Plan> plans) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.bindable = bindable;
		this.plans = plans;
	}

	public ServiceDefinition(String id, String name, String description, boolean bindable, boolean planUpdateable,
							 List<Plan> plans, List<String> tags, Map<String, Object> metadata, List<String> requires,
							 DashboardClient dashboardClient) {
		this(id, name, description, bindable, plans);
		this.tags = tags;
		this.metadata = metadata;
		this.requires = requires;
		this.planUpdateable = planUpdateable;
		this.dashboardClient = dashboardClient;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isBindable() {
		return bindable;
	}

	public boolean isPlanUpdateable() {
		return planUpdateable;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public List<String> getTags() {
		return tags;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public List<String> getRequires() {
		return requires;
	}

	public DashboardClient getDashboardClient() {
		return dashboardClient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bindable ? 1231 : 1237);
		result = prime * result + ((dashboardClient == null) ? 0 : dashboardClient.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((metadata == null) ? 0 : metadata.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (planUpdateable ? 1231 : 1237);
		result = prime * result + ((plans == null) ? 0 : plans.hashCode());
		result = prime * result + ((requires == null) ? 0 : requires.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		ServiceDefinition other = (ServiceDefinition) obj;
		if (bindable != other.bindable)
			return false;
		if (dashboardClient == null) {
			if (other.dashboardClient != null)
				return false;
		} else if (!dashboardClient.equals(other.dashboardClient))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (metadata == null) {
			if (other.metadata != null)
				return false;
		} else if (!metadata.equals(other.metadata))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (planUpdateable != other.planUpdateable)
			return false;
		if (plans == null) {
			if (other.plans != null)
				return false;
		} else if (!plans.equals(other.plans))
			return false;
		if (requires == null) {
			if (other.requires != null)
				return false;
		} else if (!requires.equals(other.requires))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceDefinition [id=" + id + ", name=" + name + ", description=" + description + ", bindable=" + bindable + ", planUpdateable="
				+ planUpdateable + ", plans=" + plans + ", tags=" + tags + ", metadata=" + metadata + ", requires=" + requires + ", dashboardClient="
				+ dashboardClient + "]";
	}
}
