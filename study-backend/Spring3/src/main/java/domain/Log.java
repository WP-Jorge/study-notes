package domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Time;
@Component
@Data
public class Log {
	Integer lId;
	String type;
	String op;
	String des;
	Time time;
}
