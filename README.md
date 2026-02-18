# Multipleks - System Obsługi Sieci Kin

* Opis projektu

Projekt przedstawia model biznesowy systemu wspierającego funkcjonowanie sieci multipleksów kinowych. 
* System pozwala na zarządzanie wieloma lokalizacjami kin, z których każda posiada wiele sal. 
* Koncentruje się na logice biznesowej, pomijając aspekty techniczne takie jak interfejs graficzny czy baza danych.


Główne funkcjonalności

* Zarządzanie siecią: Obsługa wielu kin w ramach jednej struktury CinemaNetwork.
* Repertuar: Możliwość sprawdzenia planowanych seansów na najbliższy tydzień oraz wyszukiwania konkretnych filmów.
* System rezerwacji: Rezerwowanie wybranych miejsc (poprzez kody lub obiekty) oraz zakup biletów.
* Elastyczność sprzedaży: Obsługa zakupu biletów dla klientów zarejestrowanych oraz anonimowych.
* System rozróżnia seanse 3D/zwykłe oraz typy miejsc (Standard/VIP), odpowiednio wyliczając cenę biletu.

Założenia

* Model Biznesowy: Brak GUI i integracji z bazą danych.
* Uproszczenia: 
* Pominięto moduły płatności online, systemy administracyjne oraz zaawansowane logowanie (poza obiektem klienta).
* System blokuje możliwość dodania seansu 3D do sali, która nie obsługuje tej technologii.

Diagram Klas

class CinemaNetwork {
- String name
- List~Cinema~ cinemas
+ addCinema(Cinema)
+ getProgramme() List~Screening~
}

class Cinema {
- String name
- String address
- List~Hall~ halls
- List~Screening~ screenings
+ addHall(Hall)
+ addScreening(Screening)
+ getWeeklyProgramme() List~Screening~
+ findMovie(String) List~Screening~
}

class Hall {
- int number
- List~Seat~ seats
- boolean supports3D
+ findSeat(String) Seat
}

class Screening {
- Movie movie
- Hall hall
- LocalDateTime dateTime
+ reservePlaces(Customer, String...) Reservation
+ buyTickets(String...) List~Ticket~
+ calculatePrice(Seat) double
}

class Movie {
- String title
- int duration
- boolean is3D
}

class Reservation {
- Customer customer
- List~Seat~ seats
- ReservationStatus status
+ pay() List~Ticket~
+ cancel()
}

class Ticket {
- Seat seat
- Screening screening
- double price
}

class Customer {
- String name
- String email
- List~Ticket~ tickets
+ buy(Screening, String...) List~Ticket~
}

class Seat {
- String row
- int number
- SeatType type
+ getCode() String
}

CinemaNetwork "1" *-- "*" Cinema
Cinema "1" *-- "*" Hall
Cinema "1" *-- "*" Screening
Hall "1" *-- "*" Seat
Screening "1" o-- "1" Movie
Screening "1" o-- "1" Hall
Screening "1" *-- "*" Reservation
Reservation "1" o-- "0..1" Customer
Reservation "1" *-- "*" Seat
Customer "1" *-- "*" Ticket
Ticket "1" --> "1" Seat
