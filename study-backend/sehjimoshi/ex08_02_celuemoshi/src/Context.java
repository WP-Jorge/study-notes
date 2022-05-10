/**
 * Author Admin
 * Create 2021/6/7 10:56
 */
public class Context {
	private Sort sort;
	public Context(Sort sort) {
		this.sort = sort;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public void sort() {
		this.sort.sort();
	}
}
