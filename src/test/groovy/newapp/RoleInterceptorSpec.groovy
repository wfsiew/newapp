package newapp


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RoleInterceptor)
class RoleInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test role interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"role")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
