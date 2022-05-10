package domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Account {
	Integer id;
	String name;
	Double money;
}
