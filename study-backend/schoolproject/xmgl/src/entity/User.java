package entity;
/**
 * @author ***
 * @date 2021年5月18日 下午1:37:12
 */
public class User {
	private int uid;
	/**
	 * 登录名
	 */
	private String uMail;
	private String uPassword;
	private int uRole;
	private String uRealname;
	private String uPhone;
	private String uQq;
	/**
	 * 账户是否被删除？1表示账户有效；0标识账户失效
	 */
	private int uStatus;

	public String getRtitle() {
		String rTitle = null;
		final int one=1;
		final int two=2;
		final int three=3;
		if (uRole == one) {
			rTitle = "学生处";
		}
		if (uRole == two) {
			rTitle = "评审专家";
		}
		if (uRole == three) {
			rTitle = "指导教师";
		}
		return rTitle;
	}

	public String getStitle() {
		String sTitle = null;
		if (uStatus == 1) {
			sTitle = "有效";
		} else {
			sTitle = "无效";
		}
		return sTitle;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uId) {
		uid = uId;
	}

	public String getUmail() {
		return uMail;
	}

	public void setUmail(String uMail) {
		uMail = uMail;
	}

	public String getUpassword() {
		return uPassword;
	}

	public void setUpassword(String uPassword) {
		uPassword = uPassword;
	}

	public int getUrole() {
		return uRole;
	}

	public void setUrole(int uRole) {
		uRole = uRole;
	}

	public String getUrealname() {
		return uRealname;
	}

	public void setUrealname(String uRealname) {
		uRealname = uRealname;
	}

	public String getUphone() {
		return uPhone;
	}

	public void setUphone(String uPhone) {
		uPhone = uPhone;
	}

	public String getUqq() {
		return uQq;
	}

	public void setUqq(String uQq) {
		uQq = uQq;
	}

	public int getUstatus() {
		return uStatus;
	}

	public void setUstatus(int uStatus) {
		uStatus = uStatus;
	}
}
