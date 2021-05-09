package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roombookings")
public class RoomBooking extends BaseEntity {
    /* ATTRIBUTES */
    /**
     * Start date of the booking
     */
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    private LocalDate checkIn;
    /**
     * Finish date of the booking
     */
    @Column(name = "finish_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    private LocalDate checkOut;

    /* RELATIONS */
    /**
     * Holds value of property pet.
     */
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
