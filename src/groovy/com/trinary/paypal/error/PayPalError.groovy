package com.trinary.paypal.error

class PayPalError {
	protected String name
	protected List<PayPalErrorDetail> details = []
	protected String message
	protected String informationLink
	protected String debugId

	PayPalError() {}

	PayPalError(String name, List<PayPalErrorDetail> details, String message, String informationLink, String debugId) {
		this.name = name
		this.details = details
		this.message = message
		this.informationLink = informationLink
		this.debugId = debugId
	}

	static PayPalError createFromResponse(Map errorMap) {
		PayPalError error = new PayPalError()
		error.name = errorMap["name"] ?: error.name
		errorMap["details"].each {
			error.details.add(new PayPalErrorDetail(it))
		}
		error.message = errorMap["message"] ?: error.message
		error.informationLink = errorMap["information_link"] ?: error.informationLink
		error.debugId = errorMap["debugId"] ?: error.debugId

		return error
	}

	String getName() {
		return name
	}

	List<PayPalErrorDetail> getDetails() {
		return details
	}

	String getMessage() {
		return message
	}

	String getInformationLink() {
		return informationLink
	}

	String getDebugId() {
		return debugId
	}
}
