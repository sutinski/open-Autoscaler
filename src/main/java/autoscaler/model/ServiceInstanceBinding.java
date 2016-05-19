package autoscaler.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * An instance of a ServiceDefinition.
 * 
 */
@Entity
@Table(name = "service_instance_binding")
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ServiceInstanceBinding implements Serializable {

	@Column(nullable = false)
	@JsonSerialize
	@JsonProperty("service_id")
	private String serviceInstanceId;

	@Id
	@JsonSerialize
	@JsonProperty("binding_id")
	private String bindingId;

	@Column(nullable = false)
	@JsonSerialize
	@JsonProperty("app_id")
	private String appId;

	@Column
	@JsonSerialize
	@JsonProperty("timestamp")
	private long timestamp;

}
