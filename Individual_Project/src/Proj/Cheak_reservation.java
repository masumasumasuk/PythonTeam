package Proj;

import java.util.*;

public class Cheak_reservation extends Save {
	
	public Cheak_reservation () {
		
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
	
	private void reCheak_num() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("잘못된 예매 번호 입니다. ");
        System.out.println("예약 번호를 다시 입력하세요 : ");
        String r_num = scanner.next();
        
        Cheak(r_num);
    }
	
	private void reCheak_name() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("잘못된 입력 입니다. ");
        System.out.println("이름을 다시 입력하세요 : ");
        String name = scanner.next();
        System.out.println("전화번호를 다시 입력하세요 : ");
        String p_num = scanner.next();
        
        Cheak(name, p_num);
    }
	
	public static int get_i(String r_num) {
		Save saves = new Save();
		for(int i = 0; i < 120; i ++) {
			if(saves.get_save(i)[2].equals(r_num)) {
				return i;
			}
		}
		return 0;
	}
	
	void Cheak(String name, String p_num) {
		Save saves = new Save();
		Boolean a = false;
		
		for(int i = 0; i < 120; i ++) {
			if(saves.get_save(i)[1].equals(name) && saves.get_save(i)[3].equals(p_num)) {
				System.out.println(name + " 고객님의 예매 좌석은 : " + d_grade(i) + "(" + d_num(d_grade(i), i) + " 번) 좌석 입니다.");
				a = true;
			}
		}
		if(a==false) {
			reCheak_name();
		}
	}
	
	void Cheak(String r_num) {
		Save saves = new Save();
		Boolean a = false;
			
		for(int i = 0; i < 120; i ++) {
			if(saves.get_save(i)[2].equals(r_num)) {
				System.out.println(saves.get_save(i)[1] + " 고객님의 예약 좌석은 : " + d_grade(i) + "(" + d_num(d_grade(i), i) + " 번) 좌석 입니다.");
				a = true;
			}
		}
		if(a==false) {
			reCheak_num();
		}
	}
	
	public static void initialize(int i, String g) {
		saves[i][0] = g;
        saves[i][1] = "빈자리";
        saves[i][2] = "공란";
        saves[i][3] = "공란";
	}
}
