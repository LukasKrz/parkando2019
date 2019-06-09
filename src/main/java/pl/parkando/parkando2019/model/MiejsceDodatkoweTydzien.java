package pl.parkando.parkando2019.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "MIEJSCEDODATKOWETYDZIEN")
public class MiejsceDodatkoweTydzien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "PARKID")
    String parkId;

    @Column(name = "PARKPLACEID")
    Long parkPlaceId;

    @Column(name = "STUDENTCARDIDPONIEDZIALEK")
    Long studentCardIdPoniedzialek;

    @Column(name = "RESERVATIONDATEPONIEDZIALEK")
    LocalDate reservationDatePoniedzialek;

    @Column(name = "STUDENTCARDIDWTOREK")
    Long studentCardIdWtorek;

    @Column(name = "RESERVATIONDATEWTOREK")
    LocalDate reservationDateWtorek;

    @Column(name = "STUDENTCARDIDSRODA")
    Long studentCardIdSroda;

    @Column(name = "RESERVATIONDATESRODA")
    LocalDate reservationDateSroda;

    @Column(name = "STUDENTCARDIDCZWARTEK")
    Long studentCardIdCzwartek;

    @Column(name = "RESERVATIONDATECZWARTEK")
    LocalDate reservationDateCzwartek;

    @Column(name = "STUDENTCARDIDPIATEK")
    Long studentCardIdPiatek;

    @Column(name = "RESERVATIONDATEPIATEK")
    LocalDate reservationDatePiatek;

    public MiejsceDodatkoweTydzien() {
    }

    public MiejsceDodatkoweTydzien(String parkId, Long parkPlaceId, Long studentCardIdPoniedzialek, LocalDate reservationDatePoniedzialek,
                                   Long studentCardIdWtorek, LocalDate reservationDateWtorek, Long studentCardIdSroda, LocalDate reservationDateSroda,
                                   Long studentCardIdCzwartek, LocalDate reservationDateCzwartek, Long studentCardIdPiatek, LocalDate reservationDatePiatek) {
        this.parkId = parkId;
        this.parkPlaceId = parkPlaceId;
        this.studentCardIdPoniedzialek = studentCardIdPoniedzialek;
        this.reservationDatePoniedzialek = reservationDatePoniedzialek;
        this.studentCardIdWtorek = studentCardIdWtorek;
        this.reservationDateWtorek = reservationDateWtorek;
        this.studentCardIdSroda = studentCardIdSroda;
        this.reservationDateSroda = reservationDateSroda;
        this.studentCardIdCzwartek = studentCardIdCzwartek;
        this.reservationDateCzwartek = reservationDateCzwartek;
        this.studentCardIdPiatek = studentCardIdPiatek;
        this.reservationDatePiatek = reservationDatePiatek;
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

    public Long getStudentCardIdPoniedzialek() {
        return studentCardIdPoniedzialek;
    }

    public void setStudentCardIdPoniedzialek(Long studentCardIdPoniedzialek) {
        this.studentCardIdPoniedzialek = studentCardIdPoniedzialek;
    }

    public LocalDate getReservationDatePoniedzialek() {
        return reservationDatePoniedzialek;
    }

    public void setReservationDatePoniedzialek(LocalDate reservationDatePoniedzialek) {
        this.reservationDatePoniedzialek = reservationDatePoniedzialek;
    }

    public Long getStudentCardIdWtorek() {
        return studentCardIdWtorek;
    }

    public void setStudentCardIdWtorek(Long studentCardIdWtorek) {
        this.studentCardIdWtorek = studentCardIdWtorek;
    }

    public LocalDate getReservationDateWtorek() {
        return reservationDateWtorek;
    }

    public void setReservationDateWtorek(LocalDate reservationDateWtorek) {
        this.reservationDateWtorek = reservationDateWtorek;
    }

    public Long getStudentCardIdSroda() {
        return studentCardIdSroda;
    }

    public void setStudentCardIdSroda(Long studentCardIdSroda) {
        this.studentCardIdSroda = studentCardIdSroda;
    }

    public LocalDate getReservationDateSroda() {
        return reservationDateSroda;
    }

    public void setReservationDateSroda(LocalDate reservationDateSroda) {
        this.reservationDateSroda = reservationDateSroda;
    }

    public Long getStudentCardIdCzwartek() {
        return studentCardIdCzwartek;
    }

    public void setStudentCardIdCzwartek(Long studentCardIdCzwartek) {
        this.studentCardIdCzwartek = studentCardIdCzwartek;
    }

    public LocalDate getReservationDateCzwartek() {
        return reservationDateCzwartek;
    }

    public void setReservationDateCzwartek(LocalDate reservationDateCzwartek) {
        this.reservationDateCzwartek = reservationDateCzwartek;
    }

    public Long getStudentCardIdPiatek() {
        return studentCardIdPiatek;
    }

    public void setStudentCardIdPiatek(Long studentCardIdPiatek) {
        this.studentCardIdPiatek = studentCardIdPiatek;
    }

    public LocalDate getReservationDatePiatek() {
        return reservationDatePiatek;
    }

    public void setReservationDatePiatek(LocalDate reservationDatePiatek) {
        this.reservationDatePiatek = reservationDatePiatek;
    }
}