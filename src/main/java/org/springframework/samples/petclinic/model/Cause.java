package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {

	@NotEmpty
	@Column(name = "name")
	private String name;

	@NotEmpty
	@Column(name = "description")
	private String description;

	@NotNull
	@Positive
	@Column(name = "target")
	@Range(min = 500, max = 5000)
	private Double target;

	@NotEmpty
	@Column(name = "organization")
	private String organization;

	private Boolean open;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause")
	private Set<Donation> donations;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTarget() {
		return this.target;
	}

	public void setTarget(Double target) {
		this.target = target;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	protected Set<Donation> getDonationsInternal() {
		if (this.donations == null) {
			this.donations = new HashSet<>();
		}
		return this.donations;
	}

	protected void setDonationsInternal(Set<Donation> donations) {
		this.donations = donations;
	}

	public List<Donation> getDonations() {
		List<Donation> sortedDonations = new ArrayList<>(getDonationsInternal());
		PropertyComparator.sort(sortedDonations, new MutableSortDefinition("date", true, true));
		return Collections.unmodifiableList(sortedDonations);
	}

	public void setDonations(List<Donation> donations) {
		setDonationsInternal(new HashSet<>(donations));
	}

	public Double getDonated() {
		Double amount = 0.;
		for (Donation d : getDonationsInternal()) {
			amount += d.getAmount();
		}
		return amount;
	}

	public Boolean getOpen() {
		return this.open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

}
