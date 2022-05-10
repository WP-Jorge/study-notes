import java.util.ArrayList;
import java.util.List;

/**
 * Author Admin
 * Create 2021/4/26 17:46
 */
public class University extends OrganizationCompoent {
	
	List<OrganizationCompoent> organizationCompoents = new ArrayList<>();
	
	public University(String name) {
		super(name);
	}
	
	@Override
	protected void add(OrganizationCompoent organizationCompoent) {
		organizationCompoents.add(organizationCompoent);
	}
	
	@Override
	protected void remove(OrganizationCompoent organizationCompoent) {
		organizationCompoents.remove(organizationCompoent);
	}
	
	@Override
	protected void sendMessage(String message, OrganizationCompoent organizationCompoent) {}
	
	@Override
	protected void display() {
		System.out.println(getName());
		for (OrganizationCompoent organizationCompoent : organizationCompoents) {
			organizationCompoent.display();
		}
	}
	
	@Override
	public String getName() {
		return super.getName();
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
	}
}
