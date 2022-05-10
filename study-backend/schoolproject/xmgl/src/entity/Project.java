package entity;
import dao.*;
/**
 * @author ***
 * @date 2021年5月18日 下午1:36:51
 */
public class Project {
	private int pid;
	private String pTitle;
	private String pIntroduce;
	private int pTeacher;
	private int pStatus;
	/**
	 * 申报材料文件名
	 */
	private String pApplicationStuff;
	/**
	 * 结题材料文件名
	 */
	private String pOverStuff;
	/**
	 * 负责评审的专家id即u_role为3的u_id
	 */
	private int pExpert;
	/**
	 * 小组成员
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
			//str="未分配";
			str="<span style='color: red'>未分配</span>";
		}
		return str; 
	}
	public String getPsTitle(){
		String sTitle=null;
		switch(this.pStatus){
		case 1: sTitle="待立项评审";break;
		case 2: sTitle="申报成功";break;
		case 3: sTitle="申报失败";break;
		case 4: sTitle="待结题评审";break;
		case 5: sTitle="完成结题";break;
		case 6: sTitle="待二次结题";break;
		case 7: sTitle="终止资助";break;
		case 8: sTitle="延期结题";break;
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
