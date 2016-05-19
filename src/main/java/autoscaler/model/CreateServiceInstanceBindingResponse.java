package autoscaler.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateServiceInstanceBindingResponse {

	/**
	 * A free-form hash of credentials that the bound application can use to access the service.
	 */
	@JsonSerialize
	@JsonProperty("credentials")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, Object> credentials;


	@JsonIgnore
	protected boolean bindingExisted;
	
	public CreateServiceInstanceBindingResponse withCredentials(final Map<String, Object> credentials) {
		this.credentials = credentials;
		return this;
	}

	public CreateServiceInstanceBindingResponse withBindingExisted(final boolean bindingExisted) {
		this.bindingExisted = bindingExisted;
		return this;
	}
}
