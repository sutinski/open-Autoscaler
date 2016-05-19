package autoscaler.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Details of a response to a request to create a new service instance.
 *
 * @author Scott Frederick
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class CreateServiceInstanceResponse {
	/**
	 * The URL of a web-based management user interface for the service instance. Can be <code>null</code> to indicate
	 * that a management dashboard is not provided.
	 */
	@JsonSerialize
	@JsonProperty("dashboard_url")
	private String dashboardUrl;

	/**
	 * <code>true</code> to indicated that the service instance already existed with the same parameters as the
	 * requested service instance, <code>false</code> to indicate that the instance was created as new
	 */
	@JsonIgnore
	private boolean instanceExisted;

	public CreateServiceInstanceResponse withDashboardUrl(final String dashboardUrl) {
		this.dashboardUrl = dashboardUrl;
		return this;
	}

	public CreateServiceInstanceResponse withInstanceExisted(final boolean instanceExisted) {
		this.instanceExisted = instanceExisted;
		return this;
	}

	public String getDashboardUrl() {
		return dashboardUrl;
	}

	public boolean isInstanceExisted() {
		return instanceExisted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dashboardUrl == null) ? 0 : dashboardUrl.hashCode());
		result = prime * result + (instanceExisted ? 1231 : 1237);
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
		CreateServiceInstanceResponse other = (CreateServiceInstanceResponse) obj;
		if (dashboardUrl == null) {
			if (other.dashboardUrl != null)
				return false;
		} else if (!dashboardUrl.equals(other.dashboardUrl))
			return false;
		if (instanceExisted != other.instanceExisted)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateServiceInstanceResponse [dashboardUrl=" + dashboardUrl + ", instanceExisted=" + instanceExisted + "]";
	}

}