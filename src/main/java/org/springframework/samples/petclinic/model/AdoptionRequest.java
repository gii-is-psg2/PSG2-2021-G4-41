package org.springframework.samples.petclinic.model;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "requests")
public class AdoptionRequest extends BaseEntity {

    /* ATTRIBUTES */

    @Column(name = "timestamp")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @CreationTimestamp
    private Date timestamp;

    /* RELATIONS */
    /**
     * Holds value of property pet.
     */
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "applications")
    private Collection<AdoptionApplication> applications;

    /* DERIVATIVE */
    @Transient
    public Boolean isClosed() {
        return this.applications.stream().anyMatch(ap -> ap.getApproved());
    }
}
