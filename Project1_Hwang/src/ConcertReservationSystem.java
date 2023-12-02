import java.util.*;

// Define the Seat class which represents a seat in the concert hall.
class Seat {
    private final String type; // Type of the seat (V, S, A, B)
    private final int number; // Seat number
    private boolean reserved; // Whether the seat is reserved
    private String bookerName; // Name of the person who reserved the seat
    private String bookerPhone; // Phone number of the person who reserved the seat

    // Constructor for Seat that initializes its type and number.
    public Seat(String type, int number) {
        this.type = type;
        this.number = number;
        this.reserved = false; // Initially, the seat is not reserved
    }

    // Getter methods for the seat's properties
    public String getType() { return type; }
    public int getNumber() { return number; }
    public boolean isReserved() { return reserved; }

    // Method to reserve the seat, marking it as reserved and storing the booker's details.
    public void reserve(String bookerName, String bookerPhone) {
        this.reserved = true;
        this.bookerName = bookerName;
        this.bookerPhone = bookerPhone;
    }

    // Method to cancel the reservation of the seat.
    public void cancel() {
        this.reserved = false;
        this.bookerName = null;
        this.bookerPhone = null;
    }
}

// Define the ConcertHall class which manages the seats and reservations.
class ConcertHall {
    private final Map<String, List<Seat>> seats = new HashMap<>(); // Stores all seats, categorized by type
    private final Map<String, Reservation> reservations = new HashMap<>(); // Stores all reservations by their unique ID

    // Constructor for ConcertHall that initializes the seats.
    public ConcertHall() {
        // Initialize seats of each type (V, S, A, B) with 30 seats each
        for (String type : Arrays.asList("V", "S", "A", "B")) {
            List<Seat> seatList = new ArrayList<>();
            for (int i = 1; i <= 30; i++) {
                seatList.add(new Seat(type, i));
            }
            seats.put(type, seatList);
        }
    }

    // Method to reserve a list of seats.
    public String reserveSeats(String type, List<Integer> seatNumbers, String bookerName, String bookerPhone) {
        List<Seat> reservedSeats = new ArrayList<>();
        for (int number : seatNumbers) {
            Seat seat = getSeat(type, number);
            if (seat.isReserved()) {
                throw new IllegalArgumentException("Seat " + number + " is already reserved.");
            }
            seat.reserve(bookerName, bookerPhone);
            reservedSeats.add(seat);
        }
        String reservationNumber = generateReservationNumber();
        reservations.put(reservationNumber, new Reservation(bookerName, bookerPhone, reservedSeats));
        return reservationNumber;
    }

    // Method to cancel a reservation.
    public void cancelReservation(String reservationNumber) {
        Reservation reservation = reservations.remove(reservationNumber);
        if (reservation != null) {
            for (Seat seat : reservation.getReservedSeats()) {
                seat.cancel();
            }
        } else {
            throw new IllegalArgumentException("Invalid reservation number.");
        }
    }

    // Method to print all current reservations.
    public void printReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations have been made.");
        } else {
            reservations.forEach((reservationNumber, reservation) -> {
                reservation.getReservedSeats().forEach(seat -> {
                    System.out.println("Reservation Number: " + reservationNumber +
                                       ", Seat Type: " + seat.getType() +
                                       ", Seat Number: " + seat.getNumber() +
                                       ", Booker Name: " + reservation.getBookerName());
                });
            });
        }
    }

    // Method to print the availability of all seats.
    public void printSeatAvailability() {
        seats.forEach((type, seatList) -> {
            System.out.println(type + " Seats:");
            seatList.forEach(seat -> {
                String status = seat.isReserved() ? "Reserved" : "Available";
                System.out.println("Seat Number: " + seat.getNumber() + " - " + status);
            });
        });
    }

    // Helper method to generate a unique reservation number.
    private String generateReservationNumber() {
        return String.valueOf(new Random().nextInt(90000000) + 10000000);
    }

    // Helper method to retrieve a specific seat by type and number.
    private Seat getSeat(String type, int number) {
        List<Seat> seatList = seats.get(type);
        if (seatList != null && number >= 1 && number <= seatList.size()) {
            return seatList.get(number - 1);
        }
        throw new IllegalArgumentException("Invalid seat type or number.");
    }

    // Method to check if a seat is valid and not reserved.
    public boolean isSeatValid(String type, int number) {
        List<Seat> seatList = seats.get(type);
        return seatList != null && number >= 1 && number <= seatList.size() && !seatList.get(number - 1).isReserved();
    }
    
    // Method to check if a specific seat is already reserved.
    public boolean isSeatReserved(String type, int number) {
        List<Seat> seatList = seats.get(type);
        if (seatList != null && number >= 1 && number <= seatList.size()) {
            return seatList.get(number - 1).isReserved();
        }
        throw new IllegalArgumentException("Invalid seat type or number.");
    }
}

// Define the Reservation class that encapsulates details of a reservation.
class Reservation {
    private final String bookerName; // Booker's name
    private final String bookerPhone; // Booker's phone number
    private final List<Seat> reservedSeats; // List of reserved seats

    // Constructor for Reservation that initializes the booker's details and reserved seats.
    public Reservation(String bookerName, String bookerPhone, List<Seat> reservedSeats) {
        this.bookerName = bookerName;
        this.bookerPhone = bookerPhone;
        this.reservedSeats = reservedSeats;
    }

    // Getter methods for the reservation's properties
    public List<Seat> getReservedSeats() { return reservedSeats; }
    public String getBookerName() { return bookerName; }
}

// Main class that contains the entry point of the program.
public class ConcertReservationSystem {
    private final ConcertHall concertHall = new ConcertHall(); // Instance of ConcertHall to manage reservations
    private final Scanner scanner = new Scanner(System.in); // Scanner to read user input

    // Variable to control the option to go back in the menu.
    private boolean allowBack = false;

    // Method to start the reservation system interface.
    public void start() {
        while (true) {
            // Display menu options to the user.
            if (!allowBack) {
                System.out.println("1. Make a Reservation");
                System.out.println("2. Cancel a Reservation");
                System.out.println("3. Print Reservations");
                System.out.println("4. Print Seat Availability");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
            } else {
                System.out.println("0. Back");
                System.out.print("Enter your choice (0 to go back): ");
            }

            // Read the user's choice.
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline character.

            // Handle the user's choice.
            switch (choice) {
                case 0: // Option to go back in the menu.
                    allowBack = false;
                    break;
                case 1: // Handle reservation process.
                    handleReservation();
                    break;
                case 2: // Handle cancellation process.
                    handleCancellation();
                    break;
                case 3: // Print all current reservations.
                    concertHall.printReservations();
                    break;
                case 4: // Print seat availability.
                    concertHall.printSeatAvailability();
                    break;
                case 5: // Exit the program.
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default: // Invalid choice entered by the user.
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper method to handle the reservation process.
    private void handleReservation() {
        // Variables to store reservation details.
        String seatType = "";
        int numSeats = 0;
        Set<Integer> seatNumbers = new HashSet<>();
        String bookerName = "";
        String phoneNumber = "";

        // Loop to handle reservation inputs from the user.
        while (true) {
            // Input and validation for seat type.
            if (seatType.isEmpty()) {
                System.out.print("Enter seat type (V, S, A, B) or 'back' to return: ");
                String input = scanner.nextLine().trim();
                if ("back".equalsIgnoreCase(input)) return;
                if (!Arrays.asList("V", "S", "A", "B").contains(input)) {
                    System.out.println("Invalid seat type. Please try again.");
                    continue;
                }
                seatType = input;
            }

            // Input and validation for the number of seats.
            if (numSeats == 0) {
                System.out.print("Enter the number of seats (1 or 2) or 'back' to return: ");
                String input = scanner.nextLine().trim();
                if ("back".equalsIgnoreCase(input)) {
                    seatType = ""; // Reset seat type selection
                    continue;
                }
                try {
                    numSeats = Integer.parseInt(input);
                    if (numSeats < 1 || numSeats > 2) {
                        System.out.println("Invalid number of seats. Please enter 1 or 2.");
                        numSeats = 0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            // Input and validation for seat numbers.
            while (seatNumbers.size() < numSeats) {
                System.out.printf("Enter seat number #%d (1-30) or 'back' to return: ", seatNumbers.size() + 1);
                String input = scanner.nextLine().trim();
                if ("back".equalsIgnoreCase(input)) {
                    numSeats = 0; // Reset number of seats selection
                    seatNumbers.clear();
                    break;
                }
                try {
                    int seatNumber = Integer.parseInt(input);
                    // Check if the seat is valid and not already reserved.
                    if (seatNumber < 1 || seatNumber > 30 || seatNumbers.contains(seatNumber) || concertHall.isSeatReserved(seatType, seatNumber)) {
                        System.out.println("Invalid or duplicate seat number. Please try again.");
                        continue;
                    }
                    seatNumbers.add(seatNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            // Finalize the reservation if all inputs are valid.
            if (!seatNumbers.isEmpty() && seatNumbers.size() == numSeats) {
                // Input and validation for the booker's name.
                if (bookerName.isEmpty()) {
                    System.out.print("Enter booker name or 'back' to return: ");
                    String input = scanner.nextLine().trim();
                    if ("back".equalsIgnoreCase(input)) {
                        seatNumbers.clear(); // Reset seat number selection
                        continue;
                    }
                    if (input.isEmpty()) {
                        System.out.println("Booker name cannot be empty. Please enter the booker name.");
                        continue;
                    }
                    bookerName = input;
                }

                // Input and validation for the booker's phone number.
                if (phoneNumber.isEmpty()) {
                    System.out.print("Enter phone number or 'back' to return: ");
                    String input = scanner.nextLine().trim();
                    if ("back".equalsIgnoreCase(input)) {
                        bookerName = ""; // Reset booker name input
                        continue;
                    }
                    if (input.isEmpty()) {
                        System.out.println("Phone number cannot be empty. Please enter the phone number.");
                        continue;
                    }
                    phoneNumber = input;
                }

                try {
                    // Attempt to reserve the seats and provide a reservation number.
                    String reservationNumber = concertHall.reserveSeats(seatType, new ArrayList<>(seatNumbers), bookerName, phoneNumber);
                    System.out.println("Reservation successful. Your reservation number is: " + reservationNumber);
                    return; // Exit the reservation process after success
                } catch (IllegalArgumentException e) {
                    // Handle errors such as seat already reserved.
                    System.out.println(e.getMessage());
                    // Reset all inputs to allow re-entry.
                    seatType = "";
                    numSeats = 0;
                    seatNumbers.clear();
                    bookerName = "";
                    phoneNumber = "";
                }
            }
        }
    }

    // Helper method to handle the cancellation process.
    private void handleCancellation() {
        System.out.print("Enter your reservation number: ");
        String reservationNumber = scanner.nextLine();

        try {
            // Attempt to cancel the reservation using the provided number.
            concertHall.cancelReservation(reservationNumber);
            System.out.println("Cancellation successful.");
        } catch (IllegalArgumentException e) {
            // Handle errors such as invalid reservation number.
            System.out.println(e.getMessage());
        }
    }

    // Entry point of the program.
    public static void main(String[] args) {
        // Create an instance of the reservation system and start it.
        new ConcertReservationSystem().start();
    }
}

