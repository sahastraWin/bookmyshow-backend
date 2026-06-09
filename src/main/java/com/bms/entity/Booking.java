package com.bms.entity;

import com.bms.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    @Builder.Default
    private List<Seat> seats = new ArrayList<>();

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    @Builder.Default
    private BookingStatus bookingStatus = BookingStatus.CONFIRMED;

    @Column(name = "booked_at", updatable = false)
    private LocalDateTime bookedAt;

    @PrePersist
    protected void onCreate() {
        this.bookedAt = LocalDateTime.now();
        if (this.bookingStatus == null) {
            this.bookingStatus = BookingStatus.CONFIRMED;
        }
    }
}
