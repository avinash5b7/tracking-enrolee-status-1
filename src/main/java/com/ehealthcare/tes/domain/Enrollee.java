package com.ehealthcare.tes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "enrollee")
@EntityListeners(AuditingEntityListener.class)
public class Enrollee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 100)
    @NotBlank
    private String name;
    @Column(name = "activation_status")
    @NotNull
    private Boolean activationStatus;
    @Column(name = "date_of_birth")
    @Basic
    @Temporal(TemporalType.DATE)
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @Column(name = "phone_number", length = 15)
    @NotBlank
    private String phoneNumber;
    @Column(name = "created_by", nullable = false, updatable = false)
    @CreatedBy
    private Integer createdBy;
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    @Column(name = "updated_by")
    @LastModifiedBy
    private Integer updatedBy;
    @Column(name = "updated_date")
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollee_id")
    @JsonIgnore
    private Set<Dependent> dependents;

    public Enrollee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(Set<Dependent> dependents) {
        this.dependents = dependents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollee enrollee = (Enrollee) o;
        return Objects.equals(id, enrollee.id) &&
                Objects.equals(name, enrollee.name) &&
                Objects.equals(activationStatus, enrollee.activationStatus) &&
                Objects.equals(dateOfBirth, enrollee.dateOfBirth) &&
                Objects.equals(phoneNumber, enrollee.phoneNumber) &&
                Objects.equals(createdBy, enrollee.createdBy) &&
                Objects.equals(createdDate, enrollee.createdDate) &&
                Objects.equals(updatedBy, enrollee.updatedBy) &&
                Objects.equals(updatedDate, enrollee.updatedDate) &&
                Objects.equals(dependents, enrollee.dependents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, activationStatus, dateOfBirth, phoneNumber, createdBy, createdDate, updatedBy, updatedDate, dependents);
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activationStatus=" + activationStatus +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                ", dependents=" + dependents +
                '}';
    }

}
