package newapp

class Category {
	String name
	String description
	Users user

	static belongsTo = Users
	static hasMany = [todos: Todo]

    static constraints = {
    	name(blank:false)
    }

    String toString() {
    	name
    }
}
