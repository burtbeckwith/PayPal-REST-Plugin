class PayPalRESTGrailsPlugin {
	def version = "1.0.3"
	def grailsVersion = "2.0 > *"
	def pluginExcludes = [
		'src/docs/**',
		'**/test/**'
	]
	def title = "Grails PayPal REST Plugin"
	def description = 'Simplifies the PayPal REST API'
	def documentation = 'http://grails.org/plugin/paypal-rest'
	def license = 'APACHE'
	def developers = [
		[name: 'Michael C. Main', email: 'deusprogrammer@gmail.com']
	]
	def issueManagement = [system: 'GITHUB', url: 'https://github.com/deusprogrammer/PayPal-REST-Plugin/issues']
	def scm = [url: 'https://github.com/deusprogrammer/PayPal-REST-Plugin']
}
