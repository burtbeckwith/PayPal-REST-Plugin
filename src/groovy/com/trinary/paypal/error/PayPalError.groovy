package com.trinary.paypal.error

class PayPalError {
	protected String name
	protected ArrayList<PayPalErrorDetail> details = new ArrayList<PayPalErrorDetail>()
	protected String message
	protected String informationLink
	protected String debugId
	
	public PayPalError() {}
	
	public PayPalError(String name, ArrayList<PayPalErrorDetail> details, String message, String informationLink, String debugId) {
		this.name = name
		this.details = details
		this.message = message
		this.informationLink = informationLink
		this.debugId = debugId	
	}
	
	public static PayPalError createFromResponse(Map errorMap) {
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
	
	public String getName() {
		return name
	}
	
	public ArrayList<PayPalErrorDetail> getDetails() {
		return details
	}
	
	public String getMessage() {
		return message
	}
	
	public String getInformationLink() {
		return informationLink
	}
	
	public String getDebugId() {
		return debugId
	}
}