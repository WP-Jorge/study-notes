package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author Admin
 * @Description // TODO
 * @Date 20:01 2021/4/21
 * @Param
 * @return
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private Integer bid;
	private String bname;
	private String btype;
	private String bdes;
	private String byear;
	private Integer borrowed;
}
