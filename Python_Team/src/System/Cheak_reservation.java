package System;

import java.util.*;

public class Cheak_reservation extends Save {
	int k;

	public Cheak_reservation() {

	}

	public static String d_grade(int s_num) {
		String grade;

		if (s_num >= 0 && s_num <= 29) {
			grade = "V";
		} else if (s_num >= 30 && s_num <= 59) {
			grade = "S";
		} else if (s_num >= 60 && s_num <= 89) {
			grade = "A";
		} else if (s_num >= 90 && s_num <= 120) {
			grade = "B";
		} else {
			grade = "?";
		}

		return grade;
	}

	public static int d_num(String grade, int s_num) {
		int num;

		if (grade.equals("V")) {
			num = s_num + 1;
		} else if (grade.equals("S")) {
			num = s_num - 29;
		} else if (grade.equals("A")) {
			num = s_num - 59;
		} else if (grade.equals("B")) {
			num = s_num - 89;
		} else {
			num = 0;
		}

		return num;
	}
	
	public static int g_num(String grade, int seatNumber) {
		int num;

		if (grade.equals("V")) {
			num = seatNumber - 1;
		} else if (grade.equals("S")) {
			num = seatNumber + 29;
		} else if (grade.equals("A")) {
			num = seatNumber + 59;
		} else if (grade.equals("B")) {
			num = seatNumber + 89;
		} else {
			num = 0;
		}

		return num;
	}

	private void reCheak_num() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("잘못된 예매 번호 또는 비밀번호 입니다. ");
		System.out.println("예약 번호를 다시 입력하세요 : ");
		String r_num = scanner.next();
		System.out.println("비밀번호를 다시 입력하세요 : ");
		String pw = scanner.next();

		Cheak(r_num, pw);
	}

	private void reCheak_name() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("잘못된 입력 입니다. ");
		System.out.println("이름을 다시 입력하세요 : ");
		String name = scanner.next();
		System.out.println("전화번호를 다시 입력하세요 : ");
		String p_num = scanner.next();
		System.out.println("비밀번호를 다시 입력하세요 : ");
		String pw = scanner.next();

		Cheak(name, p_num, pw);
	}

	public static int get_i(String r_num) {
		Save saves = new Save();
		for (int k = 0; k < 39; k++) {
			for (int i = 0; i < 120; i++) {
				if (saves.get_save(k, i)[2].equals(r_num)) {
					return i;
				}
			}
		}
		return 0;
	}
	
	public static int get_k(String r_num) {
		Save saves = new Save();
		for (int k = 0; k < 39; k++) {
			for (int i = 0; i < 120; i++) {
				if (saves.get_save(k, i)[2].equals(r_num)) {
					return k;
				}
			}
		}
		return 0;
	}

	void Cheak(String name, String p_num, String pw) {
		Save saves = new Save();
		Boolean a = false;

		for (int k = 0; k < 39; k++) {
			for (int i = 0; i < 120; i++) {
				if (saves.get_save(k, i)[1].equals(name) && saves.get_save(k, i)[3].equals(p_num)
						&& saves.get_save(k, i)[4].equals(pw)) {
					System.out.println(name + " 고객님의 예매 좌석은 : " + "12월 " + get_Day(k) + "일, " + getTime(k) +" " + d_grade(i) + "("
							+ d_num(d_grade(i), i) + " 번) 좌석 입니다.");
					this.k = k;
					a = true;
				}
			}
		}
		if (a == false) {
			reCheak_name();
		}
	}

	void Cheak(String r_num, String pw) {
		Save saves = new Save();
		Boolean a = false;

		for (int k = 0; k < 39; k++) {

			for (int i = 0; i < 120; i++) {
				if (saves.get_save(k, i)[2].equals(r_num) && saves.get_save(k, i)[4].equals(pw)) {
					System.out.println(saves.get_save(k, i)[1] + " 고객님의 예매 좌석은 : " + "12월 " + get_Day(k) + "일, " + getTime(k) +" "+ d_grade(i) + "("
							+ d_num(d_grade(i), i) + " 번) 좌석 입니다.");
					this.k = k;
					a = true;
				}
			}
		}
		if (a == false) {
			reCheak_num();
		}
	}
	
	public boolean reservation_Cheak(int day, String grade, int seatNumber) {
		Save saves = new Save();
		Boolean a = false;
		int num = g_num(grade, seatNumber);
		String sn = Integer.toString(num);

		if(saves.get_save(day,num)[0].equals("0")) {
			return true;
		}
		else return false;
	}
	

	public static void initialize(int k, int i, String g) {
		saves[k][i][0] = g;
		saves[k][i][1] = "빈자리";
		saves[k][i][2] = "공란";
		saves[k][i][3] = "공란";
		saves[k][i][4] = " ";
	}

	public static int getConvertDay(int dayInfo, int day) {
		int convertDay;
		if (dayInfo == 0) {
			if (day == 1)
				return convertDay = 0;
			else if ((day >= 5) && (day <= 8))
				return convertDay = day;
			else if ((day >= 12) && (day <= 15))
				return convertDay = day + 1;
			else if ((day >= 19) && (day <= 22))
				return convertDay = day + 2;
			else if ((day >= 26) && (day <= 29))
				return convertDay = day + 5;
			else {
				switch (day) {
				case 2:
					return convertDay = 1;
				case 3:
					return convertDay = 3;
				case 9:
					return convertDay = 9;
				case 10:
					return convertDay = 11;
				case 16:
					return convertDay = 17;
				case 17:
					return convertDay = 19;
				case 23:
					return convertDay = 25;
				case 24:
					return convertDay = 27;
				case 25:
					return convertDay = 29;
				case 30:
					return convertDay = 35;
				case 31:
					return convertDay = 37;
				default:
					return 0;
				}
			}
		} else if (dayInfo == 1) {
			switch (day) {
			case 2:
				return convertDay = 2;
			case 3:
				return convertDay = 4;
			case 9:
				return convertDay = 10;
			case 10:
				return convertDay = 12;
			case 16:
				return convertDay = 18;
			case 17:
				return convertDay = 20;
			case 23:
				return convertDay = 26;
			case 24:
				return convertDay = 28;
			case 25:
				return convertDay = 30;
			case 30:
				return convertDay = 36;
			case 31:
				return convertDay = 38;
			default:
				return 0;
			}
		}
		else return 0;
	}

	public static int get_Day(int convertDay) {
		if (convertDay == 0)
			return 1;
		else if ((convertDay >= 5) && (convertDay <= 8))
			return convertDay;
		else if ((convertDay >= 13) && (convertDay <= 16))
			return convertDay - 1;
		else if ((convertDay >= 21) && (convertDay <= 24))
			return convertDay - 2;
		else if ((convertDay >= 31) && (convertDay <= 34))
			return convertDay - 5;
		else {
			switch (convertDay) {
			case 1:
				return 2;
			case 3:
				return 3;
			case 9:
				return 9;
			case 11:
				return 10;
			case 17:
				return 16;
			case 19:
				return 17;
			case 25:
				return 23;
			case 27:
				return 24;
			case 29:
				return 25;
			case 35:
				return 30;
			case 37:
				return 31;

			case 2:
				return 2;
			case 4:
				return 3;
			case 10:
				return 9;
			case 12:
				return 10;
			case 18:
				return 16;
			case 20:
				return 17;
			case 26:
				return 23;
			case 28:
				return 24;
			case 30:
				return 25;
			case 36:
				return 30;
			case 38:
				return 31;
			default:
				return 0;
			}
		}
	}
	public static String getTime(int ConvertDay) {
		switch (ConvertDay) {
		case 1:
			return "1-3 PM";
		case 3:
			return "1-3 PM";
		case 9:
			return "1-3 PM";
		case 11:
			return "1-3 PM";
		case 17:
			return "1-3 PM";
		case 19:
			return "1-3 PM";
		case 25:
			return "1-3 PM";
		case 27:
			return "1-3 PM";
		case 29:
			return "1-3 PM";
		case 35:
			return "1-3 PM";
		case 37:
			return "1-3 PM";

		case 2:
			return "4-6 PM";
		case 4:
			return "4-6 PM";
		case 10:
			return "4-6 PM";
		case 12:
			return "4-6 PM";
		case 18:
			return "4-6 PM";
		case 20:
			return "4-6 PM";
		case 26:
			return "4-6 PM";
		case 28:
			return "4-6 PM";
		case 30:
			return "4-6 PM";
		case 36:
			return "4-6 PM";
		case 38:
			return "4-6 PM";
		default:
			return "1-3 PM";
		}
	}
}
