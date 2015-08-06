package newapp

class Users {
    String userName
	String firstName
	String lastName

    static hasMany = [todos: Todo, categories: Category, roles: Role]

    static constraints = {
    	userName(blank:false, unique:true)
    	firstName(blank:false)
    	lastName(blank:false)
    }

    String toString() {
    	"$lastName, $firstName"
    }
}
