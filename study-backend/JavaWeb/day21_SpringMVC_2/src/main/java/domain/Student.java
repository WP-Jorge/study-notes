package domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Student {
	private Integer id;
	private String name;
	private Integer age;
}
