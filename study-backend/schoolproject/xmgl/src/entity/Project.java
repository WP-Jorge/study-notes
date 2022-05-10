package entity;
import dao.*;
/**
 * @author ***
 * @date 2021��5��18�� ����1:36:51
 */
public class Project {
	private int pid;
	private String pTitle;
	private String pIntroduce;
	private int pTeacher;
	private int pStatus;
	/**
	 * �걨�����ļ���
	 */
	private String pApplicationStuff;
	/**
	 * ��������ļ���
	 */
	private String pOverStuff;
	/**
	 * ���������ר��id��u_roleΪ3��u_id
	 */
	private int pExpert;
	/**
	 * С���Ա
	 */
	private String pStu1;
	private String pStu2;
	private String pStu3;
	private String pStu4;
	
	
	
	public String getTeacherName(){
		return new UserDao().getUserById(pTeacher).getUrealname();
		
	}
	public String getExpertName(){
		String str=null;
		if(pExpert!=0){
			str=new UserDao().getUserById(pExpert).getUrealname();
		}else{
			//str="δ����";
			str="<span style='color: red'>δ����</span>";
		}
		return str; 
	}
	public String getPsTitle(){
		String sTitle=null;
		switch(this.pStatus){
		case 1: sTitle="����������";break;
		case 2: sTitle="�걨�ɹ�";break;
		case 3: sTitle="�걨ʧ��";break;
		case 4: sTitle="����������";break;
		case 5: sTitle="��ɽ���";break;
		case 6: sTitle="�����ν���";break;
		case 7: sTitle="��ֹ����";break;
		case 8: sTitle="���ڽ���";break;
		default: break;
		}
		return sTitle;
	}
	public int getPid() {
		return pid;
	}

	public void setPid(int pId) {
		pid = pId;
	}

	public String getPtitle() {
		return pTitle;
	}

	public void setPtitle(String pTitle) {
		pTitle = pTitle;
	}

	public String getPintroduce() {
		return pIntroduce;
	}

	public void setPintroduce(String pIntroduce) {
		pIntroduce = pIntroduce;
	}

	public int getPteacher() {
		return pTeacher;
	}

	public void setPteacher(int pTeacher) {
		pTeacher = pTeacher;
	}

	public int getPstatus() {
		return pStatus;
	}

	public void setPstatus(int pStatus) {
		pStatus = pStatus;
	}

	public String getPapplicationStuff() {
		return pApplicationStuff;
	}

	public void setPapplicationStuff(String pApplicationStuff) {
		pApplicationStuff = pApplicationStuff;
	}

	public String getPoverStuff() {
		return pOverStuff;
	}

	public void setPoverStuff(String pOverStuff) {
		pOverStuff = pOverStuff;
	}

	public int getPexpert() {
		return pExpert;
	}

	public void setPexpert(int pExpert) {
		pExpert = pExpert;
	}

	public String getPstu1() {
		return pStu1;
	}

	public void setPstu1(String pStu1) {
		pStu1 = pStu1;
	}

	public String getPstu2() {
		return pStu2;
	}

	public void setPstu2(String pStu2) {
		pStu2 = pStu2;
	}

	public String getPstu3() {
		return pStu3;
	}

	public void setPstu3(String pStu3) {
		pStu3 = pStu3;
	}

	public String getPstu4() {
		return pStu4;
	}

	public void setPstu4(String pStu4) {
		pStu4 = pStu4;
	}
}
