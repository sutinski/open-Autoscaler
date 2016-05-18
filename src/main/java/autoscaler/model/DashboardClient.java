package autoscaler.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DashboardClient {

	/**
	 * The client ID of the dashboard OAuth2 client that the service intends to use. The name must be unique within a
	 * Cloud Foundry UAA deployment. If the name is already in use, Cloud Foundry will return an error to the operator
	 * when the service is registered.
	 */
	@JsonSerialize
	@JsonProperty("id")
	private String id;

	/**
	 * The client secret for the dashboard OAuth2 client.
	 */
	@JsonSerialize
	@JsonProperty("secret")
	private String secret;

	/**
	 * A domain for the service dashboard that will be whitelisted by the UAA to enable dashboard SSO.
	 */
	@JsonSerialize
	@JsonProperty("redirect_uri")
	private String redirectUri;

	public DashboardClient() {
	}

	public DashboardClient(String id, String secret, String redirectUri) {
		this.id = id;
		this.secret = secret;
		this.redirectUri = redirectUri;
	}

	public String getId() {
		return id;
	}

	public String getSecret() {
		return secret;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((redirectUri == null) ? 0 : redirectUri.hashCode());
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
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
		DashboardClient other = (DashboardClient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (redirectUri == null) {
			if (other.redirectUri != null)
				return false;
		} else if (!redirectUri.equals(other.redirectUri))
			return false;
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DashboardClient [id=" + id + ", secret=" + secret + ", redirectUri=" + redirectUri + "]";
	}
}
