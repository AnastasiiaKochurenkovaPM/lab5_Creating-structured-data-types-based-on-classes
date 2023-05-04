import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;

public class HotelRoom {
    private String description;
    private int numberOfSeats;
    private double price;
    private boolean isFree;
    private LocalDate checkInDate;
    private int durationOfStay;

    public HotelRoom(String description, int numberOfSeats, double price, boolean isFree, LocalDate checkInDate, int durationOfStay) {
        this.description = description;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.isFree = true;
        this.checkInDate = checkInDate;
        this.durationOfStay = durationOfStay;
    }

    // Методи для отримання та встановлення значень полів
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(int durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public void addNewHotelRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть опис: ");
        String description = scanner.nextLine();
        System.out.print("Введіть кількість місць: ");
        int numberOfSeats = scanner.nextInt();
        System.out.print("Введіть ціну: ");
        double price = scanner.nextDouble();
        boolean isFree = true;
        System.out.print("Введіть дату останнього бронювання: ");
        LocalDate checkInDate = LocalDate.parse(scanner.next());
        System.out.print("Введіть тривалість проживання: ");
        int durationOfStay = scanner.nextInt();

        HotelRoom newRoom = new HotelRoom(description, numberOfSeats, price, isFree, checkInDate, durationOfStay);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hotelRooms.txt", true))) {
            writer.write(newRoom.getDescription() + "," + newRoom.getNumberOfSeats() + "," + newRoom.getPrice() + "," + newRoom.isFree() + "," + newRoom.getCheckInDate() + "," + newRoom.getDurationOfStay() + "\n");
            System.out.println("Новий номер готелю успошно додано!");
        } catch (IOException e) {
            System.out.println("Помилка при записі файлу: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        //первизначений метод toString дозволяє зручно виводити інформацію
        return "Опис: " + description + "\nКількість місць: " + numberOfSeats + "\nВартість проживання: " +
                price + "\nДата останнього бронювання: " + checkInDate + "\nТермін проживання: " + durationOfStay + "\n\n";
    }

    public String toStringOpptions() {
        //первизначений метод toString дозволяє зручно виводити інформацію
        return "Опис: " + description + "\nКількість місць: " + numberOfSeats + "\nВартість проживання: " + price + "\n\n";
    }

    /*метод порівняння за кількістю місць*/
    public int compareTo(HotelRoom otherRoom) {
        if (this.numberOfSeats < otherRoom.getNumberOfSeats()) {
            return -1;
        } else if (this.numberOfSeats > otherRoom.getNumberOfSeats()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static class NumberOfSeatsComparator implements Comparator<HotelRoom> {
        public int compare(HotelRoom o1, HotelRoom o2) {
            return o1.getNumberOfSeats() - o2.getNumberOfSeats();
        }
    }

}

