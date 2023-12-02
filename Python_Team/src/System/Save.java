package System;

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
