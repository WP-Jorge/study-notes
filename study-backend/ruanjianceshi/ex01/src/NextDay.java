import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class NextDay {
	private int year;
	private int month;
	private int day;
	private ArrayList<Integer> leapMonth;
	private ArrayList<Integer> commonMonth;
	
	public NextDay(int year, int month, int day) {
		
		// 判断年份是否合法
		if (year < 1900 || year > 3000) {
			System.out.println("年份格式不正确，请重新输入！");
		} else {
			this.year = year;
		}
		if (month < 1 || month > 12) {
			System.out.println("月份格式不正确，请重新输入！");
		} else {
			this.month = month;
		}
		
		leapMonth = new ArrayList();
		leapMonth.add(31);
		leapMonth.add(29);
		leapMonth.add(31);
		leapMonth.add(30);
		leapMonth.add(31);
		leapMonth.add(30);
		leapMonth.add(31);
		leapMonth.add(31);
		leapMonth.add(30);
		leapMonth.add(31);
		leapMonth.add(30);
		leapMonth.add(31);
		commonMonth = new ArrayList();
		commonMonth.add(31);
		commonMonth.add(28);
		commonMonth.add(31);
		commonMonth.add(30);
		commonMonth.add(31);
		commonMonth.add(30);
		commonMonth.add(31);
		commonMonth.add(31);
		commonMonth.add(30);
		commonMonth.add(31);
		commonMonth.add(30);
		commonMonth.add(31);
		
		if (this.year % 4 == 0 && this.year % 100 != 0 || this.year % 400 == 0) {
			if (month == 2) {
				if (day > leapMonth.get(month - 1) || day < 0) {
					System.out.println("日期格式不正确，请重新输入！");
				}
			} else if (day < 0 || day > leapMonth.get(month - 1)) {
				System.out.println("日期格式不正确，请重新输入！");
			} else {
				this.day = day;
			}
		} else {
			if (month == 2) {
				if (day > commonMonth.get(month - 1) || day < 0) {
					System.out.println("日期格式不正确，请重新输入！");
					return;
				} else {
					this.day = day;
				}
			} else if (day < 0 || day > commonMonth.get(month - 1)) {
				System.out.println("日期格式不正确，请重新输入！");
				return;
			} else {
				this.day = day;
			}
		}
		this.day = day;
	}
	
	public boolean isLeapYear() {
		if (this.year % 4 == 0 && this.year % 100 != 0 || this.year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isMonthEnd() {
		if (isLeapYear()) {
			int monthDay = leapMonth.get(month - 1);
			if (day == monthDay) {
				return true;
			} else {
				return false;
			}
		} else {
			int monthDay = commonMonth.get(month - 1);
			if (day == monthDay) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public String getNextDay() {
		if (day != 0) {
			if (isLeapYear()) {
				int mouthDay = leapMonth.get(month - 1);
				if (day < mouthDay) {
					day += 1;
				} else if (day == mouthDay) {
					day = 1;
					if (month < 12) {
						month++;
					} else if (month == 12) {
						month = 1;
						year++;
					}
				}
			} else {
				int mouthDay = commonMonth.get(month - 1);
				if (day < mouthDay) {
					day += 1;
				} else if (day == mouthDay) {
					day = 1;
					if (month < 12) {
						month++;
					} else if (month == 12) {
						month = 1;
						year++;
					}
				}
			}
			return year + "年" + month + "月" + day + "日";
		} else {
			return "";
		}
	}
}
