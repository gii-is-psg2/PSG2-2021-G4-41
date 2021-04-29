package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "applications")
public class AdoptionApplication extends BaseEntity {
    /* ATTRIBUTES */
    /**
     * is approved or not
     */
    @Column(name = "approved")
    private Boolean approved;
    /**
     * description
     */
    @Column(name = "description")
    private String description;

    @Column(name = "timestamp")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Timestamp
    private Date timestamp;

    /* RELATIONS */

    @ManyToOne
    @JoinColumn(name = "request_id")
    private AdoptionRequest request;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner futureOwner;

}
