import java.util.ArrayList;
import java.util.List;

/**
 * Author Admin
 * Create 2021/4/26 17:53
 */
public class College extends OrganizationCompoent {
	
	List<OrganizationCompoent> organizationCompoents = new ArrayList<>();
	
	public College(String name) {
		super(name);
	}
	
	@Override
	protected void add(OrganizationCompoent organizationCompoent) {
		organizationCompoents.add(organizationCompoent);
	}
	
	@Override
	protected void sendMessage(String message, OrganizationCompoent organizationCompoent) {
		if (organizationCompoents.contains(organizationCompoent)) {
			System.out.println(getName() + "发送信息给 " + organizationCompoent.getName() + "，信息为：" + message);
		}
	}
	
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
