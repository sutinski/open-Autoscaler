package autoscaler.exception;

public class ServiceDefinitionDoesNotExistException extends RuntimeException {
	private static final long serialVersionUID = -62090827040416788L;

	public ServiceDefinitionDoesNotExistException(String serviceDefinitionId) {
		super("Service definition does not exist: id=" + serviceDefinitionId);
	}
}
