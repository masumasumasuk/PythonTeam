package System;

import java.time.LocalDateTime;
import java.util.*;

import javax.swing.SwingUtilities;

class Concert_System extends Cheak_reservation {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Save saves = new Save(39, 120, 5);
		saves.set_save_from_file();

		System.out.println("*** 예매 안내 ***");
		System.out.println("1. 본 화면은 League Of Legends Champions Korea 예매화면 입니다.");
		System.out.println("2. 매주 월요일은 경기가 없으며 주말 및 공휴일은 일 2회 경기가 있습니다.");
		System.out.println("3. 좌석 등급은 V, S, A, B 총 4종류 입니다.");
		System.out.println("4. 1인당 최대 2매 예매 가능합니다.");

		while (true) {
			System.out.println(" ");
			System.out.println("기능을 선택하세요(예매,조회,취소,변경,종료) : ");
			String input = scanner.next();
			if (input.equals("예매")) {
					SwingUtilities.invokeLater(() -> {
			            CalendarGUI frame = new CalendarGUI();
			            frame.setVisible(true);
			        });
				saves.Make_catalog();

			} else if (input.equals("조회")) {
				System.out.println("예매 조회 기능입니다.");
				System.out.println("좌석별 예매 현황을 조회하시려면 1, ");
				System.out.println("개인별 예매 현황을 조회하시려면 2를 입력해주세요 : ");

				int n = scanner.nextInt();
				if (n == 1) {
					Cheak_reservation c = new Cheak_reservation();
					SwingUtilities.invokeLater(() -> {
						Reservation_InquiryGUI frame = new Reservation_InquiryGUI();
			            frame.setVisible(true);
			        });
					
					int day = CalendarGUI.getDay();
					int dayInfo = TheaterReservationGUI.getDayInfo();
					int date = c.getConvertDay(dayInfo, day);
					String g = TheaterReservationGUI_1.getGradeInfo();
					
				} else if (n == 2) {
					Cheak_reservation cheak = new Cheak_reservation();
					System.out.println("이름, 전화번호로 조회 or 예매번호로 조회 (1 or 2) : ");
					int c = scanner.nextInt();

					if (c == 1) {
						System.out.println("이름을 입력하세요 : ");
						String name = scanner.next();
						System.out.println("전화번호를 입력하세요 : ");
						String p_num = scanner.next();
						System.out.println("비밀번호를 입력하세요 : ");
						String pw = scanner.next();
						cheak.Cheak(name, p_num, pw);
					}

					if (c == 2) {
						System.out.println("예매번호를 입력하세요 : ");
						String r_num = scanner.next();
						System.out.println("비밀번호를 입력하세요 : ");
						String pw = scanner.next();
						cheak.Cheak(r_num, pw);
					}
				}
			} else if (input.equals("취소")) {
				Cheak_reservation cheak = new Cheak_reservation();
				System.out.println("취소할 예매번호를 입력해주세요 : ");
				String r_num = scanner.next();
				System.out.println("비밀번호를 입력하세요 : ");
				String pw = scanner.next();
				cheak.Cheak(r_num, pw);

				System.out.println("정말 취소하시려면 1을 입력해주세요 :");
				int n = scanner.nextInt();
				if (n == 1) {
					initialize(cheak.k, get_i(r_num), d_grade(get_i(r_num)));
				}
				System.out.println("취소 되었습니다.");
				saves.Make_catalog();
			} else if (input.equals("변경")) {
				Cheak_reservation cheak = new Cheak_reservation();
				
				System.out.println("예매 변경은 같은 날짜/등급의 좌석으로만 가능합니다. ");
				System.out.println("다른 등급의 좌석은 예매 취소 후 재예매 부탁드립니다.");
				System.out.println("변경할 예매번호를 입력해주세요 : ");
				String r_num = scanner.next();
				System.out.println("비밀번호를 입력하세요 : ");
				String pw = scanner.next();
				cheak.Cheak(r_num, pw);
				
				System.out.println(d_grade(get_i(r_num)) + "등급 좌석 현황 입니다.");
				if (d_grade(get_i(r_num)).equals("V")) {
					System.out.println("************ V석 좌석 현황입니다. ************");
					for (int i = 0; i < 30; i++) {
						System.out.println((i + 1) + "번 좌석의 예매자 이름 : " + saves.get_save(cheak.k, i)[1] + ", 예약 번호는 : "
								+ saves.get_save(cheak.k,i)[2]);
					}
				} else if (d_grade(get_i(r_num)).equals("S")) {
					System.out.println("************ S석 좌석 현황입니다. ************");
					for (int i = 30; i < 60; i++) {
						System.out.println((i - 29) + "번 좌석의 예매자 이름 : " + saves.get_save(cheak.k, i)[1] + ", 예약 번호는 : "
								+ saves.get_save(cheak.k, i)[2]);
					}
				} else if (d_grade(get_i(r_num)).equals("A")) {
					System.out.println("************ A석 좌석 현황입니다. ************");
					for (int i = 60; i < 90; i++) {
						System.out.println((i - 59) + "번 좌석의 예매자 이름 : " + saves.get_save(cheak.k, i)[1] + ", 예약 번호는 : "
								+ saves.get_save(cheak.k, i)[2]);
					}
				} else if (d_grade(get_i(r_num)).equals("B")) {
					System.out.println("************ B석 좌석 현황입니다. ************");
					for (int i = 90; i < 120; i++) {
						System.out.println((i - 89) + "번 좌석의 예매자 이름 : " + saves.get_save(cheak.k, i)[1] + ", 예약 번호는 : "
								+ saves.get_save(cheak.k, i)[2]);
					}
				}
				System.out.println("변경할 좌석 번호를 말씀해주세요 :");
				try {
					Cheak_reservation c = new Cheak_reservation();
					int n = scanner.nextInt();
					if(c.reservation_Cheak(c.get_k(r_num), d_grade(get_i(r_num)), n)) {
						
					
					invalidInput(n);

					String i = String.valueOf(n);
					Reservation_system s = new Reservation_system();

					s.set_TopNum(d_grade(get_i(r_num)), i);

					s.set_BottomNum();

					if (d_grade(get_i(r_num)).equals("V")) {
						n = n;
					} else if (d_grade(get_i(r_num)).equals("S")) {
						n = n + 30;
					} else if (d_grade(get_i(r_num)).equals("A")) {
						n = n + 60;
					} else if (d_grade(get_i(r_num)).equals("B")) {
						n = n + 90;
					}
					n -= 1;
					i = d_grade(n);

					String[] info = { i, saves.get_save(cheak.k, get_i(r_num))[1], s.get_Num(),
							saves.get_save(cheak.k, get_i(r_num))[3], saves.get_save(cheak.k, get_i(r_num))[4]};
					saves.add_save(cheak.k, n, info);

					initialize(cheak.k, get_i(r_num), d_grade(get_i(r_num)));

					System.out.println("변경되었습니다.");
					System.out.println("변경된 예매 번호는 : " + s.get_Num() + " 입니다.");
					}else {
						System.out.println("이미 예약된 좌석입니다.");
					}
				} catch (IllegalArgumentException e) {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}

				saves.Make_catalog();
				
			} else if (input.equals("종료")) {
				saves.Make_catalog();
				break;
			} else {
				System.out.println(" 잘못 입력하셨습니다. 다시 시도해주세요");
			}
		}
	}

	private static void invalidInput(String in) {
		if (!(in.equals("V") || in.equals("S") || in.equals("A") || in.equals("B"))) {
			throw new IllegalArgumentException();
		}
	}

	private static void invalidInput(int in) {
		if (in < 1 || in > 30) {
			throw new IllegalArgumentException();
		}
	}
	

}





