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