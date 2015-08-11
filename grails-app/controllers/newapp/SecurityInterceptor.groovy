package newapp


class SecurityInterceptor {

	SecurityInterceptor() {
		matchAll()
		.excludes(controller: 'home', action: 'index')
		.excludes(controller: 'users')
	}

    boolean before() {
    	if (!session.user) {
    		println "false"
    		redirect(controller: "home", action: "login")
    		return false
    	}

        println session.user
    	return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
