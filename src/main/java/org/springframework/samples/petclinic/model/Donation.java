package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "donation")
public class Donation extends BaseEntity {

	@NotEmpty
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	
	//
	
	public Integer getAmount() {
		return this.amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
