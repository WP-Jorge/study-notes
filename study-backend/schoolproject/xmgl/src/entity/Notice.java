package entity;

import java.util.Date;
/**
 * @author ***
 * @date 2021年5月18日 下午1:36:35
 */
public class Notice {
	private int nid;
	private String nTitle;
	private String nContents;
	private Date nDate;
	private int nByWho;

	public int getNid() {
		return nid;
	}

	public void setNid(int nId) {
		nid = nId;
	}

	public String getNtitle() {
		return nTitle;
	}

	public void setNtitle(String nTitle) {
		nTitle = nTitle;
	}

	public String getNcontents() {
		return nContents;
	}

	public void setNcontents(String nContents) {
		nContents = nContents;
	}

	public Date getNdate() {
		return nDate;
	}

	public void setNdate(Date nDate) {
		nDate = nDate;
	}

	public int getNbywho() {
		return nByWho;
	}

	public void setNbywho(int nBywho) {
		nByWho = nBywho;
	}
}
