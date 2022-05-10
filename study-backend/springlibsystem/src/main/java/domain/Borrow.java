package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Admin
 * @Description // TODO 
 * @Date 20:02 2021/4/21
 * @Param 
 * @return 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
	private Integer uid;
	private Integer bid;
	private String bname;
	private String byear;
	private String btype;
	private String bdes;
}
