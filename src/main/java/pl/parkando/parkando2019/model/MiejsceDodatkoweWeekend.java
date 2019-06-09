package pl.parkando.parkando2019.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "MIEJSCEDODATKOWEWEEKEND")
public class MiejsceDodatkoweWeekend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "PARKID")
    String parkId;

    @Column(name = "PARKPLACEID")
    Long parkPlaceId;

    @Column(name = "STUDENTCARDIDSOBOTA")
    Long studentCardIdSobota;

    @Column(name = "RESERVATIONDATESOBOTA")
    LocalDate reservationDateSobota;

    @Column(name = "STUDENTCARDIDNIEDZIELA")
    Long studentCardIdNiedziela;

    @Column(name = "RESERVATIONDATENIEDZIELA")
    LocalDate reservationDateNiedziela;

    public MiejsceDodatkoweWeekend() {
    }

    public MiejsceDodatkoweWeekend(String parkId, Long parkPlaceId, Long studentCardIdSobota, LocalDate reservationDateSobota, Long studentCardIdNiedziela, LocalDate reservationDateNiedziela) {
        this.parkId = parkId;
        this.parkPlaceId = parkPlaceId;
        this.studentCardIdSobota = studentCardIdSobota;
        this.reservationDateSobota = reservationDateSobota;
        this.studentCardIdNiedziela = studentCardIdNiedziela;
        this.reservationDateNiedziela = reservationDateNiedziela;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public Long getParkPlaceId() {
        return parkPlaceId;
    }

    public void setParkPlaceId(Long parkPlaceId) {
        this.parkPlaceId = parkPlaceId;
    }

    public Long getStudentCardIdSobota() {
        return studentCardIdSobota;
    }

    public void setStudentCardIdSobota(Long studentCardIdSobota) {
        this.studentCardIdSobota = studentCardIdSobota;
    }

    public LocalDate getReservationDateSobota() {
        return reservationDateSobota;
    }

    public void setReservationDateSobota(LocalDate reservationDateSobota) {
        this.reservationDateSobota = reservationDateSobota;
    }

    public Long getStudentCardIdNiedziela() {
        return studentCardIdNiedziela;
    }

    public void setStudentCardIdNiedziela(Long studentCardIdNiedziela) {
        this.studentCardIdNiedziela = studentCardIdNiedziela;
    }

    public LocalDate getReservationDateNiedziela() {
        return reservationDateNiedziela;
    }

    public void setReservationDateNiedziela(LocalDate reservationDateNiedziela) {
        this.reservationDateNiedziela = reservationDateNiedziela;
    }
}