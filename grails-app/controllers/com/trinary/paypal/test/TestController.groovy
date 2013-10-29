package com.trinary.paypal.test

class TestController {
    TestService testService

    def index() {
        testService.test()
    }
}
