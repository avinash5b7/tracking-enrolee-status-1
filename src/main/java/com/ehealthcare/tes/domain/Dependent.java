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

@Entity
@Table(name = "dependent")
@EntityListeners(AuditingEntityListener.class)
public class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 100)
    @NotBlank
    private String name;
    @Column(name = "date_of_birth")
    @Basic
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull
    private Date dateOfBirth;
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
    @ManyToOne
    @JoinColumn(name = "enrollee_id", nullable = false)
    @JsonIgnore
    private Enrollee enrollee;

    public Dependent() {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependent dependent = (Dependent) o;
        return Objects.equals(id, dependent.id) &&
                Objects.equals(name, dependent.name) &&
                Objects.equals(dateOfBirth, dependent.dateOfBirth) &&
                Objects.equals(createdBy, dependent.createdBy) &&
                Objects.equals(createdDate, dependent.createdDate) &&
                Objects.equals(updatedBy, dependent.updatedBy) &&
                Objects.equals(updatedDate, dependent.updatedDate) &&
                Objects.equals(enrollee, dependent.enrollee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, createdBy, createdDate, updatedBy, updatedDate, enrollee);
    }

    @Override
    public String toString() {
        return "Dependent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                ", enrollee=" + enrollee +
                '}';
    }

}
