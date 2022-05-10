package domain;

// import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
// @Data
public class Log {
	Integer lId;
	String type;
	String op;
	String des;
	Time time;
	
	public Integer getlId() {
		return lId;
	}
	
	public void setlId(Integer lId) {
		this.lId = lId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getOp() {
		return op;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public String getDes() {
		return des;
	}
	
	public void setDes(String des) {
		this.des = des;
	}
	
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}
}
