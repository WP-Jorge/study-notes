package entity;
/**
 * @author ***
 * @date 2021��5��18�� ����1:37:12
 */
public class User {
	private int uid;
	/**
	 * ��¼��
	 */
	private String uMail;
	private String uPassword;
	private int uRole;
	private String uRealname;
	private String uPhone;
	private String uQq;
	/**
	 * �˻��Ƿ�ɾ����1��ʾ�˻���Ч��0��ʶ�˻�ʧЧ
	 */
	private int uStatus;

	public String getRtitle() {
		String rTitle = null;
		final int one=1;
		final int two=2;
		final int three=3;
		if (uRole == one) {
			rTitle = "ѧ����";
		}
		if (uRole == two) {
			rTitle = "����ר��";
		}
		if (uRole == three) {
			rTitle = "ָ����ʦ";
		}
		return rTitle;
	}

	public String getStitle() {
		String sTitle = null;
		if (uStatus == 1) {
			sTitle = "��Ч";
		} else {
			sTitle = "��Ч";
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
