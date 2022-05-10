/**
 * Author Admin
 * Create 2021/4/26 11:15
 */
public abstract class OrganizationCompoent {
	private String name;
	protected void add (OrganizationCompoent organizationCompoent) {
		throw new UnsupportedOperationException();
	}
	protected void remove(OrganizationCompoent organizationCompoent) {
		throw new UnsupportedOperationException();
	}
	
	public OrganizationCompoent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	protected abstract void display();
	
	protected void sendMessage(String message, OrganizationCompoent organizationCompoent) {
		throw new UnsupportedOperationException();
	};
}
