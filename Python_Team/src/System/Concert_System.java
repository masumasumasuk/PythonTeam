package System;

import java.time.LocalDateTime;
import java.util.*;

class Concert_System extends Cheak_reservation {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Save saves = new Save(39, 120, 5);
		saves.initialize();

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

				boolean f = true;
				while (f) {
					System.out.println("예매 하실 날짜를 선택해 주세요 (0~38) : ");
					int date = scanner.nextInt(); // 달력에서 0(12월 1일) ~ 38(12월 31일 오후) 매핑해서 클릭으로 입력 받기
					try {
						invalidDate(date);
						f = false;

						int n = 0;
						boolean[] c = new boolean[2];
						boolean b = false;
						boolean d = true;
						while (d) {
							try {
								System.out.println("예매 매수를 입력해주세요 (1~2) : ");
								n = scanner.nextInt();
								c[0] = false;
								c[1] = true;
								b = c[n - 1];
								d = false;
							} catch (ArrayIndexOutOfBoundsException e) {
								System.out.println("예매 매수는 최대 2장 입니다. 다시 시도해 주세요");
							}
						}

						int sn = 0;
						String i = null;
						String name = null;
						String phoneNum = null;
						String g = null;
						Boolean a = true;

						while (a) {
							System.out.println("예매 하실 좌석 등급을 선택해 주세요 (V, S, A, B) : ");
							g = scanner.next();

							try {
								if (n == 1) {
									invalidInput(g);
									single_reservation(date, g);
								} else if (n == 2) {
									invalidInput(g);
									double_reservation(date, g);
								}

								a = false;
							} catch (IllegalArgumentException e) {
								System.out.println("잘못된 입력입니다. 다시 시도해 주세요.");
								System.out.println(" ");
							}
						}

					} catch (IllegalArgumentException e) {
						System.out.println("월요일은 예약이 불가능 합니다. 다시 시도해 주세요");
					}
				}

			} else if (input.equals("조회")) {
				System.out.println("예매 조회 기능입니다.");
				System.out.println("좌석별 예매 현황을 조회하시려면 1, ");
				System.out.println("개인별 예매 현황을 조회하시려면 2를 입력해주세요 : ");

				int n = scanner.nextInt();
				if (n == 1) {
					System.out.println("조회 하실 날짜를 선택해 주세요 (0~38) : ");
					int date = scanner.nextInt();
					System.out.println("V, S, A, B 중 조회 할 좌석 등급을 입력해주세요 : ");
					String g = scanner.next();
					if (g.equals("V")) {
						System.out.println("************ V석 좌석 현황입니다. ************");
						for (int i = 0; i < 30; i++) {
							System.out.println((i + 1) + "번 좌석의 예매자 이름 : " + saves.get_save(date, i)[1] + ", 예약 번호는 : "
									+ saves.get_save(date, i)[2]);
						}
					} else if (g.equals("S")) {
						System.out.println("************ S석 좌석 현황입니다. ************");
						for (int i = 30; i < 60; i++) {
							System.out.println((i - 29) + "번 좌석의 예매자 이름 : " + saves.get_save(date, i)[1] + ", 예약 번호는 : "
									+ saves.get_save(date, i)[2]);
						}
					} else if (g.equals("A")) {
						System.out.println("************ A석 좌석 현황입니다. ************");
						for (int i = 60; i < 90; i++) {
							System.out.println((i - 59) + "번 좌석의 예매자 이름 : " + saves.get_save(date, i)[1] + ", 예약 번호는 : "
									+ saves.get_save(date, i)[2]);
						}
					} else if (g.equals("B")) {
						System.out.println("************ B석 좌석 현황입니다. ************");
						for (int i = 90; i < 120; i++) {
							System.out.println((i - 89) + "번 좌석의 예매자 이름 : " + saves.get_save(date, i)[1] + ", 예약 번호는 : "
									+ saves.get_save(date, i)[2]);
						}
					}
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
					System.out.println("************ S석 좌석 현황입니다. ************");
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
					int n = scanner.nextInt();
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
				} catch (IllegalArgumentException e) {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}

			} else if (input.equals("종료")) {
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

	private static void invalidDate(int in) {
		if (in > 38) {
			throw new IllegalArgumentException();
		}
	}

	private static void single_reservation(int k, String input) {
		Scanner scanner = new Scanner(System.in);
		Reservation_system s = new Reservation_system();
		Save saves = new Save();

		System.out.println("예매자의 성명을 입력하세요");
		String name = scanner.next();

		System.out.println("예매자의 전화번호를 입력하세요");
		String phoneNum = scanner.next();

		System.out.println(input + "석에 예매하실 좌석 번호를 입력하세요 (1~30) : ");
		int sn = scanner.nextInt();
		invalidInput(sn);
		
		System.out.println("예매 조회/취소/변경시 필요한 비밀번호 4자리를 입력해 주세요 : ");
		String pw = scanner.next();

		String i = String.valueOf(sn);

		switch (input) {
		case "S":
			sn += 30;
		case "A":
			sn += 60;
		case "B":
			sn += 90;
		default:
			sn = sn;
		}

		s.set_TopNum(input, i);

		s.set_BottomNum();

		sn -= 1;
		i = d_grade(sn);

		String[] info = { i, name, s.get_Num(), phoneNum, pw };

		saves.add_save(k, sn, info);

		System.out.println("예매가 완료 되었습니다.");
		System.out.println(name + "님의 예매번호는 : " + s.get_Num() + "입니다.");
	}

	private static void double_reservation(int k, String input) {
		Scanner scanner = new Scanner(System.in);
		Reservation_system s = new Reservation_system();

		Save saves = new Save();

		System.out.println("대표자의 성명을 입력하세요");
		String name = scanner.next();

		System.out.println("대표자의 전화번호를 입력하세요");
		String phoneNum = scanner.next();

		System.out.println(input + "석에 예매하실 첫번째 좌석 번호를 입력하세요 (1~30) : ");
		int sn = scanner.nextInt();
		invalidInput(sn);
		System.out.println(input + "석에 예매하실 두번째 좌석 번호를 입력하세요 (1~30) : ");
		int sn2 = scanner.nextInt();
		invalidInput(sn2);
		
		System.out.println("예매 조회/취소/변경시 필요한 비밀번호 4자리를 입력해 주세요 : ");
		String pw = scanner.next();

		String i = String.valueOf(sn);
		String j = String.valueOf(sn2);

		switch (input) {
		case "S":
			sn += 30;
			sn2 += 30;
			break;
		case "A":
			sn += 60;
			sn2 += 60;
			break;
		case "B":
			sn += 90;
			sn2 += 90;
			break;
		default:
			break;
		}

		s.set_TopNum(input, i);
		s.set_BottomNum();
		String num_1 = s.get_Num();

		s.set_TopNum(input, j);
		s.set_BottomNum();
		String num_2 = s.get_Num();

		sn -= 1;
		sn2 -= 1;
		i = d_grade(sn);
		j = d_grade(sn2);

		String[] info = { i, name, num_1, phoneNum, pw };
		String[] info2 = { j, name, num_2, phoneNum, pw };

		saves.add_save(k, sn, info);
		saves.add_save(k, sn2, info2);

		System.out.println("예매가 완료 되었습니다.");
		System.out.println(name + "님의 예매번호는 : " + num_1 + " / " + num_2 + "입니다.");
	}

}