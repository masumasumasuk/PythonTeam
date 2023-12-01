package pj1;

import java.util.Scanner;

class ChangeType {
	
	static int getTypeCode(String type) {
		switch (type) {
			case "V":
				return  1;
			case "S":
				return  2;
			case "A":
				return  3;
			case "B":
				return  4;
			default:
				return 0;
		}
    }	
}

public class Reservation extends ChangeType {
	
		private static String[] VSeats = new String[30];
	    private static String[] SSeats = new String[30];
	    private static String[] ASeats = new String[30];
	    private static String[] BSeats = new String[30];
	    static int counter = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(" *** 서울과기대 학생들을 위한 NewJeans 단독 콘서트 예약 시스템입니다. *** ");
		System.out.println();
		System.out.println("     본 콘서트는 2023년 11월 15일 토요일 18:00 1회 개최됩니다. ");
    	System.out.println("     행사가 끝난 후 안전을 위해 질서를 지키며 차례대로 퇴장해주시길 바랍니다.");
    	System.out.println("     공연장에 쓰레기를 버리지 말아주세요. 공연 에티켓을 지키는 과기인이 됩시다. :) ");
    	System.out.println();
		System.out.println("   ※ 좌석은 V,S,A,B 석이 있으며, 각각 30개의 좌석이 있습니다. ");
		System.out.println("   ※ 각 좌석의 가격은 V석 30,000원, S석 25,000원, A,B석 20,000원 입니다.");
		System.out.println("   ※ 같은 좌석 타입 내에서 최대 1인 2매까지 예약 가능합니다. ");
		System.out.println("   ※ 다른 좌석 타입의 1인 동시예매는 불가능합니다. ");
		System.out.println();
		Scanner scanner = new Scanner(System.in);

	        while (true) {
	        	try {
	        	System.out.println("원하시는 메뉴의 번호를 입력하세요.");
	            System.out.println("1. 예약하기   2. 좌석조회   3. 예약취소  4. 종료");
	            System.out.print("선택: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1 :
	                	try {
	                	System.out.print("예매할 좌석의 수를 입력하세요. (최대 2석) :  ");
	                    int n = scanner.nextInt(); 
	                
	                    if (n==1) { SingleReservation(); n=0; }
	                    else if (n==2) { DuoReservation(); n=0; }
	                    else { 
	                    	System.out.println("올바르지 않은 입력입니다. 다시 입력해주세요. ");
	                    	System.out.println();
	                    	n=0; 
	                    	}
	                	}
	                	catch(Exception e){
	                		System.out.println("올바르지 않은 입력입니다. 다시 입력해주세요. ");
	                		System.out.println();
	                		scanner.nextLine();
	                	}
	                    break;
	                case 2:
	                	System.out.println("현재 예약된 좌석들을 출력합니다. 현재 예약 인원수는 "+ counter + " 명 입니다.");
	                	System.out.println();
	                	printReservationStatus();
	                    
	                    break;
	                case 3:
	                	System.out.println();
	                	CancelReservation();
	                    
	                    break;
	                case 4:
	                    System.out.println("프로그램을 종료합니다.");
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("올바르지 않은 입력입니다. 다시 선택해주세요.");
	                    System.out.println();
	            }       
	        	} catch(Exception e){
	                	System.out.println("올바르지 않은 입력입니다. 다시 입력해주세요. ");
	                	System.out.println();
	                	scanner.nextLine();
	                	}   
	        }
	   }
	
	//1인 예약
	private static void SingleReservation() {
		Scanner seat = new Scanner(System.in);
	
		System.out.print("예약할 좌석타입과 번호를 입력해주세요.(ex:V 1) : ");
		String type = seat.next();
		int seatnumber = seat.nextInt();
		
		if (!(type.equals("V") || type.equals("S") || type.equals("A") || type.equals("B")) ){
            System.out.println("잘못된 좌석 타입입니다.");
            System.out.println();
            return;      
        }
		
		if (!(seatnumber >= 1 && seatnumber <= 30)) {
			System.out.println("잘못된 좌석 번호입니다.");
			System.out.println();
			return;
		}
		
		String[] seatArray = getSeatArray(type);
        int index = seatnumber - 1;
        
		if(!(seatArray[index] == null)) {
			 System.out.println("이미 예약된 좌석입니다.");
	         System.out.println();
	         return;
		}
		
		System.out.print("예약자 이름을 입력하세요 : ");
        String name = seat.next();
        
        int OverRes = 0;
        
        for (String[] seats : new String[][]{VSeats, SSeats, ASeats, BSeats}) {
            for (int i = 0; i < seats.length; i++) {
                if (seats[i] != null && seats[i].contains("예약자: " + name)) {
                	OverRes++;
                }
            }
        }
        if(OverRes > 1) {
        	System.out.println("티켓은 1인 최대 2석 까지만 예약가능합니다. "+ name +" 님께서는 이미 2석을 예약하셨습니다.");
        	System.out.println();
        	return;
        }

        System.out.print("예약자 전화번호를 띄어쓰기 없이 입력하세요(ex:01011112222) : ");
        String phoneNumber = seat.next();
        
        System.out.print("예약 취소시 사용할 4자리 비밀번호를 입력하세요 : ");
        String password = seat.next();
       
        counter++;
        int typecode = getTypeCode(type);
        String resnumber = typecode*1000 + counter + phoneNumber.substring(phoneNumber.length() - 4) ;
		
        if (seatArray[index] == null) {
            seatArray[index] = "예약번호: " + resnumber + ", 예약자: " + name +  password;
            System.out.println("예약이 성공하였습니다. 예약번호: " + resnumber);
            System.out.println();
        } else {
            System.out.println("예약이 실패하였습니다.");
            System.out.println();
            counter--;
        }
         
	}
	
	//2인 예약
	private static void DuoReservation() {
		Scanner seat = new Scanner(System.in);
		
		System.out.print("예약할 좌석타입과 두 번호를 공백을 넣어서 입력해주세요.(ex:V 1 2) :  ");
		String type = seat.next();
		int seatnumber1 = seat.nextInt();
		int seatnumber2 = seat.nextInt();
		
		if (!(type.equals("V") || type.equals("S") || type.equals("A") || type.equals("B")) ){
            System.out.println("잘못된 좌석 타입입니다.");
            System.out.println();
            return;
        }
		
		if (!((seatnumber1 >= 1 && seatnumber1 <= 30)) && ((seatnumber2 >= 1 && seatnumber2 <= 30))) {
			System.out.println("잘못된 좌석 번호입니다.");
			System.out.println();
			return;
		}
		
		String[] seatArray = getSeatArray(type);
        int index1 = seatnumber1 - 1;
        int index2 = seatnumber2 - 1;
        
		if(!(seatArray[index1] == null && seatArray[index2] == null)) {
			 System.out.println("이미 예약된 좌석입니다.");
	         System.out.println();
	         return;
		}
		
		
		System.out.print("대표 예약자 이름을 입력하세요: ");
        String name = seat.next();
        
        int OverRes = 0;
        
        for (String[] seats : new String[][]{VSeats, SSeats, ASeats, BSeats}) {
            for (int i = 0; i < seats.length; i++) {
                if (seats[i] != null && seats[i].contains("예약자: " + name)) {
                	OverRes++;
                }
            }
        }
        if(OverRes == 1) {
        	System.out.println("티켓은 1인 최대 2석 까지만 예약가능합니다. "+ name +" 님께서는 이미 1석을 예약하셨습니다.");
        	System.out.println("다시 처음메뉴로 돌아가 1석을 추가로 예매하거나, 다른 메뉴를 선택해주세요. ");
        	System.out.println();
        	return;
        } else if(OverRes > 1) {
        	System.out.println("티켓은 1인 최대 2석 까지만 예약가능합니다. "+ name +" 님께서는 이미 2석을 예약하셨습니다.");
        	System.out.println();
        	return;
        }

        System.out.print("대표 예약자 전화번호를 띄어쓰기 없이 입력하세요(ex:01011112222) :  ");
        String phoneNumber = seat.next();
        
        System.out.print("예약 취소시 사용할 4자리 비밀번호를 입력하세요 : ");
        String password = seat.next();
        
        counter = counter + 2;
        int typecode = getTypeCode(type);
        String resnumber = typecode*1000 + counter + phoneNumber.substring(phoneNumber.length() - 4) ;
 

        if (seatArray[index1] == null && seatArray[index2] == null) {
            seatArray[index1] = "예약번호: " + resnumber + " 예약자: " + name +  password;
            seatArray[index2] = "예약번호: " + resnumber + " 예약자: " + name +  password;
            System.out.println("예약이 완료되었습니다. 예약번호: " + resnumber);
            System.out.println();
          
        } else {
            System.out.println("예약이 실패하였습니다.");
            System.out.println();
            counter = counter -2;
            
        }

	}
	
	private static String[] getSeatArray(String seatType) {
		switch (seatType) {
			case "V":
				return VSeats;
	        case "S":
	            return SSeats;
	        case "A":
	            return ASeats;
	        case "B":
	            return BSeats;
	        default:
	        	return null;
	        }
	    }
		
	//좌석출력
	private static void printReservationStatus() {
	        
		System.out.println("-V석-");
		for (int i = 0; i < 30; i++) {
			if (VSeats[i] != null) {
				System.out.println((i + 1) + "번 " + VSeats[i].substring(0, VSeats[i].length() - 4));
			}
		} System.out.println();
	        
		System.out.println("-S석-");
		for (int i = 0; i < 30; i++) {
			if (SSeats[i] != null) {
				System.out.println((i + 1) + "번 " + SSeats[i].substring(0, SSeats[i].length() - 4));
			}
		} System.out.println();
	     
		System.out.println("-A석-");
		for (int i = 0; i < 30; i++) {
			if (ASeats[i] != null) {
				System.out.println((i + 1) + "번 " + ASeats[i].substring(0, ASeats[i].length() - 4));
			}
		} System.out.println();
	        
		System.out.println("-B석-");
		for (int i = 0; i < 30; i++) {
			if (BSeats[i] != null) {
				System.out.println((i + 1) + "번 " + BSeats[i].substring(0, BSeats[i].length() - 4));
			}
		} System.out.println();
	}
	
	//예약취소
	private static void CancelReservation() {
        Scanner cancel = new Scanner(System.in);

        System.out.print("취소할 예약번호를 입력하세요: ");
        String resnumber = cancel.next();
        
        String [] seat = CancelSeat(resnumber);
        int count = 0;
        int a = 0, b = 0;
        
        for(int i = 0; i < 30; i++) {
        	 if (seat[i] != null && seat[i].contains("예약번호: " + resnumber)) {
        		if(a==0) {
        			a = i;
        		}else if (b == 0) {
        			b = i;
        		}count++;          
        	 }
        }
        
        if(count != 0) {
        	System.out.println();
        	System.out.println("예약이 확인 되었습니다. ");
        	System.out.println("정말로 취소하시려면 입력하신 취소 비밀번호 4자리를 입력하세요. ");
        	System.out.println("입력하신 비밀번호가 일치하지 않는 경우 예약취소가 중지됩니다. ");
        	System.out.print("비밀번호 : ");
        	String check = cancel.next();
        	
        	String tempt = seat[a].substring(seat[a].length() - 4);
        	
        	if(check.equals(tempt)) {
        		if(b != 0 ) {
        			System.out.println("예약이 취소되었습니다. ");
        			seat[a]=null;
        			seat[b]=null;
        		} else {
        			System.out.println("예약이 취소되었습니다. ");
        			seat[a]=null;
        		}
        	} else {
        		System.out.println("비밀번호가 불일치합니다. 예약취소가 중지됩니다.");
        	}
        	count = 0;
        	a = 0;
        	b = 0;
        } else {
        	System.out.println("예약번호를 찾을 수 없습니다.");
        	a = 0;
        	b = 0;
        }
        System.out.println();
    }
	
	private static String[] CancelSeat(String resnumber) {
		
		String seat = resnumber.substring(0,1);
		
		switch (seat) {
			case "1":
				return VSeats;
			case "2":
				return SSeats;
			case "3":
				return ASeats;
			case "4":
				return BSeats;
			default:
				return null;
			}
    	}
}
	





