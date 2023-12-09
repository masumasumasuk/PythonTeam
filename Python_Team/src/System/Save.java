package System;

import java.io.*;

public class Save {
	public static String[][][] saves;
	
	public Save() {
		
	}
	
	public Save(int k, int i, int j) {
		saves = new String[k][i][j];
	}
	
	public void add_save(int k, int i, String[] save) {
		saves[k][i] = save;
	}
	
	public String[] get_save(int k, int i) {
		return saves[k][i];
	}
	
	public void set_save_from_file() {
		try {
			BufferedReader fr = new BufferedReader(new FileReader("C:\\Temp\\catalog.txt"));
			String line;
			for (int k = 0; k < saves.length; k++) {
				for (int i = 0; i < saves[k].length; i++) {
					for (int j = 0; j < saves[k][i].length; j++) {
						line = fr.readLine();
						saves[k][i][j] = line;
					}
				}
			}
			fr.close();
		} catch (IOException e) {
			System.out.println("예약 목록을 초기화합니다.");
			initialize(); // 카탈로그.txt 파일이 없으면 예약 목록 초기화
			
		}
	}
	
	protected void Make_catalog() {
		// 예약 정보를 저장하는 카탈로그.txt 파일 생성
		try {
			FileWriter catalog = new FileWriter("c:\\Temp\\catalog.txt");
			
			for (int k = 0; k < 39; k++) {

				for (int i = 0; i < 120; i++) {
					for (int j=0; j<5; j++) {
						String[] ssa = this.get_save(k, i);
						if (ssa[j] == null) {ssa[j] = "";}
						catalog.write(ssa[j]);
						catalog.write("\n");
					}
				}
			}
			catalog.close();
			
		} catch (IOException e) {
			System.out.println("저장할 수 없습니다." + e.getMessage());
			return;
		}
	}
	
	public static void initialize() {
		for (int k = 0; k < saves.length; k++) {
			for (int i = 0; i < saves[k].length; i++) {
				saves[k][i][0] = "0";
				saves[k][i][1] = "빈자리";
				saves[k][i][2] = "공란";
				saves[k][i][3] = "공란";
				saves[k][i][4] = " ";
			}
		}
	}
}
