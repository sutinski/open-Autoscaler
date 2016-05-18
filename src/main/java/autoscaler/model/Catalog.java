package autoscaler.model;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalog {
	/**
	 * A list of service offerings provided by the service broker.
	 */
	@NotEmpty
	@JsonSerialize(nullsUsing = EmptyListSerializer.class)
	@JsonProperty("services")
	private final List<ServiceDefinition> serviceDefinitions;

	public Catalog() {
		this.serviceDefinitions = null;
	}

	public Catalog(List<ServiceDefinition> serviceDefinitions) {
		this.serviceDefinitions = serviceDefinitions;
	}

	public List<ServiceDefinition> getServiceDefinitions() {
		return serviceDefinitions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serviceDefinitions == null) ? 0 : serviceDefinitions.hashCode());
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
		Catalog other = (Catalog) obj;
		if (serviceDefinitions == null) {
			if (other.serviceDefinitions != null)
				return false;
		} else if (!serviceDefinitions.equals(other.serviceDefinitions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Catalog [serviceDefinitions=" + serviceDefinitions + "]";
	}
}
