package newapp


class SecurityInterceptor {

	SecurityInterceptor() {
		match(controller: 'home')
        .excludes(action: 'login')
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
