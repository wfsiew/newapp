package newapp


class SecurityInterceptor {

	int order = HIGHEST_PRECEDENCE

	SecurityInterceptor() {
		match(controller: 'home')
        .excludes(action: 'login')
        .excludes(action: 'auth')
        .excludes(action: 'hello')
	}

    boolean before() {
    	if (!session.user) {
    		redirect(controller: "home", action: "login")
    		return false
    	}

    	return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
