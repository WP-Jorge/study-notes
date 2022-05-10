/**
 * Author Admin
 * Create 2021/4/26 17:55
 */
public class Department extends OrganizationCompoent {
	public Department(String name) {
		super(name);
	}
	
	@Override
	public String getName() {
		return super.getName();
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
	}
	
	@Override
	protected void display() {
		System.out.println(getName());
	}
	
	@Override
	protected void sendMessage(String message, OrganizationCompoent organizationCompoent) {}
}
