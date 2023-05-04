import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import static check.checkdouble.read_double;
import static check.checkint.read_int;

public class Main {
    public static void main(String[] args) throws ParseException {
//        HotelRoom[] hotelRooms;
//                new HotelRoom("Просторий двомісний номер", 2, 1400, true, LocalDate.of(2023,05,03), 5),
//                new HotelRoom("Зручний одномісний номер", 1, 800, true, LocalDate.of(2023,04,28), 5),
//                new HotelRoom("Номер сімейного типу", 4, 2100, false, LocalDate.of(2023,04,25), 3),
//                new HotelRoom("Апартаменти", 5, 3200, true, LocalDate.of(2023,05,01), 2),
//                new HotelRoom("Двомісний номер", 2, 1350, false, LocalDate.of(2023,05,13), 5),
//                new HotelRoom("Номер сімейного типу", 4, 2000, true, LocalDate.of(2023,05,23), 2),
//                new HotelRoom("Покращений люкс", 4, 1850, true, LocalDate.of(2023,05,01), 7)
//        };

        Hotel myHotel;
        Scanner input = new Scanner(System.in);
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\KV-User\\IdeaProjects\\Lab_5\\src\\data.txt"));
            ArrayList<HotelRoom> rooms = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                String description = tokens[0];
                int numberOfSeats = Integer.parseInt(tokens[1]);
                double price = Double.parseDouble(tokens[2]);
                boolean isFree = Boolean.parseBoolean(tokens[3]);
                LocalDate checkInDate = LocalDate.parse(tokens[4]);
                int durationOfStay = Integer.parseInt(tokens[5]);
                HotelRoom room = new HotelRoom(description, numberOfSeats, price, isFree, checkInDate, durationOfStay);
                rooms.add(room);
            }

            HotelRoom[] hotelRooms = rooms.toArray(new HotelRoom[rooms.size()]);
            myHotel = new Hotel(hotelRooms);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            formatter = formatter.withLocale(Locale.getDefault());
            int code = read_int(input, "1 - переглянути вільні/зайняті номери\n" +
                    "2 - пошук номера\n" +
                    "3 - додати запис у список номерів готелю\n" +
                    "4 - сортувати за кількістю місць\n" +
                    "0 - завершити\n");
            while (code != 0) {
                if (code == 3) {
                    System.out.print("Введіть опис: ");
                    String description = input.nextLine();
                    int numberofseat = read_int(input, "Введіть кількість місць: ");
                    double price = read_double(input, "Введіть ціну: ");
                    boolean isFree = true;
                    LocalDate checkInDate = null;
                    boolean isValid = false;
                    System.out.print("Введіть дату останнього бронювання: ");
                    while (!isValid) {
                        try {
                            String inp = input.nextLine().trim();
                            checkInDate = LocalDate.parse(inp, formatter);
                            isValid = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Неправильний формат дати або ця дата вже минула. Введіть ще раз!");
                        }
                    }
                    int durationOfStay = read_int(input, "Введіть тривалість проживання: ");

                    HotelRoom newRoom = new HotelRoom(description, numberofseat, price, isFree, checkInDate, durationOfStay);

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\KV-User\\IdeaProjects\\Lab_5\\src\\data.txt", true))) {
                        writer.write("\n\"" + newRoom.getDescription() + "\"," + newRoom.getNumberOfSeats() + "," + newRoom.getPrice() + "," + newRoom.isFree() + "," + newRoom.getCheckInDate() + "," + newRoom.getDurationOfStay() + "\n");
                        System.out.println("Новий номер готелю успішно додано!");
                    } catch (IOException e) {
                        System.out.println("Помилка при записі файлу: " + e.getMessage());
                    }
                    code = read_int(input, "1 - переглянути вільні/зайняті номери\n" +
                            "2 - пошук номера\n" +
                            "3 - додати запис у список номерів готелю\n" +
                            "4 - сортувати за кількістю місць\n" +
                            "0 - завершити\n");
                } else {
                    if (code == 2) {
                        System.out.println("Пошук номера:\n");
                        int numberOfSeats = read_int(input, "Ведіть кількість осіб: ");
                        double maxPrice = read_double(input, "Введіть допустиму вартість проживання: ");

                        LocalDate date = null;
                        boolean isDateValid = false;
                        boolean corectDate = false;
                        System.out.println("Введіть дату у форматі dd/MM/yyyy: ");

                        while (!isDateValid) {
                            try {
                                while (!corectDate) {
                                    String inp = input.nextLine().trim();
                                    date = LocalDate.parse(inp, formatter);
                                    isDateValid = true;
                                    if (date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now())) {
                                        corectDate = true;
                                    } else {
                                        System.out.println("Ця дата вже минула. Введіть ще раз!");
                                        corectDate = false;
                                    }
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Неправильний формат дати або ця дата вже минула. Введіть ще раз!");
                            }
                        }

                        int duration = read_int(input, "Введіть тривалість проживання: ");

                        String findHotelRooms = myHotel.findBySleepsAndPrice(numberOfSeats, maxPrice, date, duration);
                        System.out.println(findHotelRooms);
                        code = read_int(input, "1 - переглянути вільні/зайняті номери\n" +
                                "2 - пошук номера\n" +
                                "3 - додати запис у список номерів готелю\n" +
                                "4 - сортувати за кількістю місць\n" +
                                "0 - завершити\n");
                    } else {
                        if (code == 1) {
                            System.out.println("ВІЛЬНІ НОМЕРИ");
                            myHotel.printFreeRooms();
                            System.out.println("ЗАЙНЯТІ НОМЕРИ");
                            myHotel.printOccupierRooms();
                            code = read_int(input, "1 - переглянути вільні/зайняті номери\n" +
                                    "2 - пошук номера\n" +
                                    "3 - додати запис у список номерів готелю\n" +
                                    "4 - сортувати за кількістю місць\n" +
                                    "0 - завершити\n");

                        } else {
                            if (code == 4) {
                                System.out.println("\nВІДСОРТОВАНИЙ СПИСОК\n");
                                Arrays.sort(hotelRooms, new HotelRoom.NumberOfSeatsComparator());
                                myHotel.printList();
                                code = read_int(input, "1 - переглянути вільні/зайняті номери\n" +
                                        "2 - пошук номера\n" +
                                        "3 - додати запис у список номерів готелю\n" +
                                        "4 - сортувати за кількістю місць\n" +
                                        "0 - завершити\n");
                            }
                        }
                    }
                }
            }
            input.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}