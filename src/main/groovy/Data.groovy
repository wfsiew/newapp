package newapp.model

class Data {
	String name
	String country
}

class ListData {

	List<Data> getDataList() {
		List<Data> l = []
		l.add(produce("Manisha", "India"))
		l.add(produce("Dennis Ritchie", "USA"))
		l.add(produce("V.Anand", "India"))
		l.add(produce("Shrinath", "California"))
		l
	}

	private Data produce(String name, String country) {
		Data o = new Data(name: name, country: country)
		o
	}
}

class Group {
	int custid
	double amount
}

class ListGroup {

	List<Group> getGroupList() {
		List<Group> l = []
		l.add(produce(1, 10))
		l.add(produce(1, 20))
		l.add(produce(2, 30))
		l.add(produce(2, 40))
		l
	}

	private Group produce(int custid, double amt) {
		Group o = new Group(custid: custid, amount: amt)
		o
	}
}