package com.immanuelspring.lakesiderhotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "Room_Type")
    private String roomType;
    @Column (name = "Room_Price")
    private BigDecimal roomPrice;
    @Column (name = "Is_Booked")
    private boolean isBooked = false;
    @Lob
    @Column (name = "Room_Photo")
    private Blob roomPhoto;
    @Column (name = "Bookings")
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookedRoom> bookings;

    //The initial state of a new Room is null/empty to avoid errors
    public Room() {
        this.bookings = new ArrayList<>();
    }

    public void addBooking(BookedRoom booking){
        if(bookings == null){
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
        booking.setRoom(this);
        isBooked = true;
        String bookingCode = RandomStringUtils.randomNumeric(15);
        booking.setBookingConfirmationCode(bookingCode);
    }
}
