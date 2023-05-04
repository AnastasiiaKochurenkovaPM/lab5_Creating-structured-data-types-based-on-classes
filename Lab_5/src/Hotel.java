import java.time.LocalDate;
public class Hotel {
    private HotelRoom[] hotelRooms;
    public Hotel(HotelRoom[] list) {
        hotelRooms = list;
    }

    //друк всіх елементів масиву
    public void printList() {
        for (HotelRoom hotelRoom : hotelRooms) {
            System.out.println(hotelRoom);
        }
    }

    //друк зайнятих/вільних номерів
    public void printFreeRooms() {
        for (HotelRoom hotelRoom : hotelRooms) {
            LocalDate checkInDate = hotelRoom.getCheckInDate();
            LocalDate checkOutDate = checkInDate.plusDays(hotelRoom.getDurationOfStay());
            if (LocalDate.now().isAfter(checkOutDate) || LocalDate.now().isBefore(checkInDate)) System.out.println(hotelRoom);
        }
    }
    public void printOccupierRooms() {
        for (HotelRoom hotelRoom : hotelRooms) {
            LocalDate checkInDate = hotelRoom.getCheckInDate();
            LocalDate checkOutDate = checkInDate.plusDays(hotelRoom.getDurationOfStay());
            if (LocalDate.now().isAfter(checkInDate) && LocalDate.now().isBefore(checkOutDate) || LocalDate.now().isEqual(checkOutDate) || LocalDate.now().isEqual(checkInDate)) System.out.println(hotelRoom);
        }
    }

    //пошук вільних номерів за кількістю місць та вартістю проживання
    public String findBySleepsAndPrice(int numberOfSeats, double price, LocalDate checkInDate, int durationOfStay) {
        String result = "";
        LocalDate checkOutDate = checkInDate.plusDays(durationOfStay);
        int count = 0;
        for (HotelRoom hotelRoom : hotelRooms) {
            LocalDate bookingCheckInDate = hotelRoom.getCheckInDate();
            LocalDate bookingCheckOutDate = bookingCheckInDate.plusDays(hotelRoom.getDurationOfStay());
            if (hotelRoom.getNumberOfSeats() == numberOfSeats &&
                    hotelRoom.getPrice() <= price &&
                    ((checkInDate.isBefore(bookingCheckInDate) && checkOutDate.isBefore(bookingCheckInDate))
                    || checkInDate.isAfter(bookingCheckOutDate))){
                result += hotelRoom.toStringOpptions() + "\n";
                count++;
            }
        }
        return result.isEmpty() ? "\nНічого не знайдено!" : "\n\nВАРІАНТИ ЗА ВАШИМ ЗАПИТОМ: \nЗнайдено " + count + ". \n" + result;
    }
}