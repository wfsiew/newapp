package newapp.model

class Course {
	String cid
	String cname
	String sid
	String sname
}

class ListCourse {

	List<Course> getDataList() {
		List<Course> l = []
		l.add(produce("1", "IT", "2", "Ben"))
		l.add(produce("1", "IT", "3", "Lim"))
		l.add(produce("2", "LAW", "4", "Steve"))
		l.add(produce("2", "LAW", "5", "June"))
		l
	}

	private Course produce(String cid, String cname, String sid, String sname) {
		Course o = new Course(cid: cid, cname: cname, sid: sid, sname: sname)
		o
	}
}