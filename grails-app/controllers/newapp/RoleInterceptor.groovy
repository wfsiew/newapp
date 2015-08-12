package newapp


class RoleInterceptor {

	int order = HIGHEST_PRECEDENCE + 5

	RoleInterceptor() {
		match(controller: 'home', action: 'admin')
	}

    boolean before() {
    	println "jjj"
    	def k = session.user

    	def q = Users.where {
            userName == k.userName
        }
        Users o = q.find()

    	def roles = o.roles
    	println roles

    	boolean b = o.roles?.any { it.name == "admin" }

    	if (!b) {
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
