package newapp

class Role {
	String name

    static belongsTo = Users
	static hasMany = [users: Users]

    static constraints = {
    	name(blank:false, unique:true, maxSize:50)
    }

    String toString() {
    	"$name"
    }
}
