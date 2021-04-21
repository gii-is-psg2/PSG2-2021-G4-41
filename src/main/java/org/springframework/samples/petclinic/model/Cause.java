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

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "cause")
public class Cause extends BaseEntity {

	@NotEmpty
	@Column(name = "name")
	private String name;
	
	@NotEmpty
	@Column(name = "description")
	private String description;
	
	@NotEmpty
	@Column(name = "target")
	private Integer target;
	
	@NotEmpty
	@Column(name = "organization")
	private String organization;
	
	@NotEmpty
	@Column(name = "is_open")
	private Boolean isOpen;
	
	//	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause")
	private Set<Donation> donations;
	//	
	
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
	
	
	public Integer getTarget() {
		return this.target;
	}
	public void setTarget(Integer target) {
		this.target = target;
	}
	
	
	public String getOrganization() {
		return this.organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public Boolean getIsOpen() {
		return this.isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	//	
	
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

}
