package web.chat.backend.exception;

public class NotFoundException extends RuntimeException {

	private final Object id;

	public NotFoundException(Object id) {
		this.id = id;
	}

	@Override
	public String getMessage() {
		return String.format("%s does not exits.", id);
	}
}
