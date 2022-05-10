/**
 * Author Admin
 * Create 2021/5/24 10:32
 */
public class Client {
	public static void main(String[] args) {
		HeadTeacher headTeacher = new HeadTeacher("班主任");
		DepartmentHead departmentHead = new DepartmentHead("系主任");
		Dean dean = new Dean("院长");
		headTeacher.setNextHandler(departmentHead);
		departmentHead.setNextHandler(dean);
		
		headTeacher.handleRequest(1);
		headTeacher.handleRequest(3);
		headTeacher.handleRequest(7);
		headTeacher.handleRequest(10);
	}
}
