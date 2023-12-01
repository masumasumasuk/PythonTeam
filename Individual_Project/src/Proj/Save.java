package Proj;

public class Save {
	public static String[][] saves;
	
	public Save() {
		
	}
	
	public Save(int i, int j) {
		saves = new String[i][j];
	}
	
	public void add_save(int i, String[] save) {
		saves[i] = save;
	}
	
	public String[] get_save(int i) {
		return saves[i];
	}
	
	public static void initialize() {
        for (int i = 0; i < saves.length; i++) {
                saves[i][0] = "0";
                saves[i][1] = "빈자리";
                saves[i][2] = "공란";
                saves[i][3] = "공란";
        }
    }
}
