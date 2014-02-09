package com.trinary.paypal.error

class PayPalErrorDetail {
	protected String field
	protected String issue

	PayPalErrorDetail() {}

	PayPalErrorDetail(String field, String issue) {
		this.field = field
		this.issue = issue
	}

	PayPalErrorDetail(Map detailMap) {
		this.field = detailMap["field"] ?: field
		this.issue = detailMap["issue"] ?: issue
	}

	String getField() {
		return field
	}

	String getIssue() {
		return issue
	}

	String toString() {
		return "${field}: ${issue}"
	}
}
