package com.immanuelspring.lakesiderhotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column (name = "Check_In")
    private LocalDate checkInDate;

    @Column (name = "Check_Out")
    private LocalDate checkOutDate;

    @Column (name = "Guest_FullName")
    private String guestFullName;

    @Column (name = "Guest_Email")
    private String guestEmail;

    @Column (name = "Adults")
    private int numberOfAdults;

    @Column (name = "Children")
    private int numberOfChildren;

    @Column (name = "Total_Guest")
    private int totalGuestNumber;

    @Column (name = "Confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "Room_Id")
    private Room room;

    public void calculateTotalNumberOfGuests(){
        this.totalGuestNumber = this.numberOfAdults + this.numberOfChildren;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
        calculateTotalNumberOfGuests();
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
        calculateTotalNumberOfGuests();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
