package pl.edu.agh.mwo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.mwo.Screening.FORMATTER;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== MULTIPLEKS - SIEĆ KIN ===\n");

        // Sieć kin
        CinemaNetwork network = new CinemaNetwork("Sieć Kin Przestrzeń Marzeń", new ArrayList<>());

        // Tworzymy kino i dodajemy do sieci
        Cinema cinema1 = new Cinema("Nocny Lot", "ul. Filmowa 8");
        Cinema cinema2 = new Cinema("Złote Klisze", "ul. Ciemna 15");

        network.addCinema(cinema1);
        network.addCinema(cinema2);

        // Dodanie sali z miejscami
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat("A", 34, Seat.SeatType.STANDARD));
        seats.add(new Seat("A", 35, Seat.SeatType.STANDARD));
        seats.add(new Seat("A", 36, Seat.SeatType.STANDARD));
        seats.add(new Seat("A", 37, Seat.SeatType.VIP));

        Hall hall1 = new Hall(1, seats, true);
        hall1.setSupports3D(true);
        cinema1.addHall(hall1);

        // Dodajemy filmy
        Movie lewandowskiNieznany = new Movie("Lewandowski – Nieznany", 120, false);
        Movie kingRichard = new Movie("King Richard 3D", 150, true);

        // Dodajemy seanse
        Screening screeningLewandowskiNieznany = new Screening(lewandowskiNieznany, hall1, LocalDateTime.now().plusDays(1));
        Screening screeningKingRichard = new Screening(kingRichard, hall1, LocalDateTime.now().plusDays(2));

        cinema1.addScreening(screeningLewandowskiNieznany);
        cinema1.addScreening(screeningKingRichard);


        // Dodajemy oddzielną salę dla drugiego kina
        List<Seat> seats2 = new ArrayList<>();
        seats2.add(new Seat("B", 1, Seat.SeatType.STANDARD));
        seats2.add(new Seat("B", 2, Seat.SeatType.STANDARD));
        seats2.add(new Seat("B", 3, Seat.SeatType.VIP));

        Hall hall2 = new Hall(1, seats2, true);
        cinema2.addHall(hall2);

        // Dodajemy seans (ten sam film, ale inna sala i godzina)
        Screening screeningKingRichardCinema2 =
                new Screening(kingRichard, hall2, LocalDateTime.now().plusDays(3));

        cinema2.addScreening(screeningKingRichardCinema2);


        // Wyświetlenie repertuaru kina
        cinema1.printProgramme();

        System.out.println("\n * Repertuar kina " + cinema2.getName());

        int index = 1;
        for (Screening s : cinema2.getScreeningList()) {
            System.out.println(index++ + ". " +
                    s.getMovie().getTitle() +
                    " | " + s.getDateTime().format(FORMATTER) +
                    " | Sala " + s.getHall().getNumber() +
                    (s.getMovie().is3D() ? " | 3D" : " | 2D")
            );
        }

        System.out.println("\n * Repertuar tygodniowy sieci kin Przestrzeń Marzeń: ");

        for (Cinema cinema : network.getCinemas()) {
            System.out.println("\nKino: " + cinema.getName());
            List<Screening> weekly = cinema.getWeeklyProgramme();

            if (weekly.isEmpty()) {
                System.out.println("Brak seansów w najbliższym tygodniu.");
                continue;
            }

            int idx = 1;
            for (Screening s : weekly) {
                System.out.println(idx++ + ". " +
                        s.getMovie().getTitle() +
                        " | " + s.getDateTime().format(FORMATTER) +
                        " | Sala " + s.getHall().getNumber() +
                        (s.getMovie().is3D() ? " | 3D" : " | 2D")
                );
            }
        }

        // Wyszukiwanie filmu
        System.out.println("\n * Wyszukanie filmu 'King Richard 3D': ");
        List<Screening> found = cinema1.findMovie("King Richard 3D");
        System.out.println("Znaleziono seansów: " + found.size());

        // Rezerwacja miejsc bez konta
        System.out.println("\n * Rezerwuję miejsca A34 i A35 na Lewandowski - Nieznany");
        screeningLewandowskiNieznany.reservePlaces("A34", "A35");

        // Rezerwacja po miejscu
        Seat vipSeat = hall1.findSeat("A37");
        System.out.println(" * Rezerwuję miejsce VIP A37");
        screeningLewandowskiNieznany.reservePlaces(vipSeat.getCode());

        // Próba rezerwacji zajętego miejsca
        System.out.println("\n * Próba rezerwacji zajętego miejsca A34:");
        try {
            screeningLewandowskiNieznany.reservePlaces("A34");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }

        // Zakup biletów bez konta
        System.out.println("\n * Kupuję bilet na King Richard 3D (A36) bez konta");
        List<Ticket> ticketsNoAccount = screeningKingRichard.buyTickets("A36");
        System.out.println("Kupiono bilet za: " + ticketsNoAccount.getFirst().getPrice() + " zł");

        // Zakup biletów z kontem
        Customer customer = new Customer("Rozalia", "rozalia@gmail.com");
        System.out.println("\n * Rozalia kupuje bilet na King Richard 3D (A35)");

        List<Ticket> ticketsCustomer = customer.buy(screeningKingRichard, "A35");
        System.out.println("Kupiono bilet. Rozalia posiada biletów: " + customer.getTickets().size());

        // Zwrot biletu
        System.out.println("\n * Zwrot biletu A36 (kupionego bez konta)");
        screeningKingRichard.refund(ticketsNoAccount.getFirst());
        System.out.println("Zwrócono bilet.");

        // Wyświetlamy wolne miejsca
        System.out.println("\n * Wolne miejsca na: Lewandowski - Nieznany");
        for (Seat s : screeningLewandowskiNieznany.getAvailableSeats()) {
            System.out.println(s.getCode());
        }
    }

}
