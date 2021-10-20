package br.idp.starwarsapi.starwarsAPI.config.validation;

public class ErrorForm {

	private String field;
	private String message;

	public ErrorForm(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
