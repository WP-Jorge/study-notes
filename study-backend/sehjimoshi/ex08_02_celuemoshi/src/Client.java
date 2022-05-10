/**
 * Author Admin
 * Create 2021/6/7 10:56
 */
public class Client {
	public static void main(String[] args) {
		System.out.println("使用冒泡排序---");
		Context context = new Context(new MaoPaoSort());
		context.sort();
		System.out.println("使用二元排序---");
		context.setSort(new ErYuanSort());
		context.sort();
		System.out.println("使用快速排序---");
		context.setSort(new QuickSort());
		context.sort();
	}
}
