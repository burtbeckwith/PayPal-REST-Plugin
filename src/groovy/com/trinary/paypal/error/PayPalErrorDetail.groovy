package com.trinary.paypal.error

class PayPalErrorDetail {
	protected String field
	protected String issue
	
	public PayPalErrorDetail() {}
	
	public PayPalErrorDetail(String field, String issue) {
		this.field = field
		this.issue = issue
	}
	
	public PayPalErrorDetail(Map detailMap) {
		this.field = detailMap["field"] ?: field
		this.issue = detailMap["issue"] ?: issue
	}
	
	public String getField() {
		return field
	}
	
	public String getIssue() {
		return issue
	}
	
	public String toString() {
		return "${field}: ${issue}"
	}
}
