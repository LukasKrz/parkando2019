package pl.parkando.parkando2019.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "MIEJSCEPODSTAWOWE")
public class MiejscePodstawowe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "PARKID")
    String parkId;

    @Column(name = "PARKPLACEID")
    Long parkPlaceId;

    @Column(name = "IDSTUDENTDZIENNY")   // TODO LuKr cardId instead of student ID
    Long idStudentDzienny;

    @Column(name = "DATAREZERWACJIDZIENNY")
    LocalDate dataRezerwacjiDzienny;

    @Column(name = "IDSTUDENTZAOCZNY")   // TODO LuKr cardId instead of student ID
    Long idStudentZaoczny;

    @Column(name = "DATAREZERWACJIZAOCZNY")
    LocalDate dataRezerwacjiZaoczny;

    public MiejscePodstawowe() {
    }

    public MiejscePodstawowe(String parkId, Long parkPlaceId, Long idStudentDzienny, LocalDate dataRezerwacjiDzienny, Long idStudentZaoczny, LocalDate dataRezerwacjiZaoczny) {
        this.parkId = parkId;
        this.parkPlaceId = parkPlaceId;
        this.idStudentDzienny = idStudentDzienny;
        this.dataRezerwacjiDzienny = dataRezerwacjiDzienny;
        this.idStudentZaoczny = idStudentZaoczny;
        this.dataRezerwacjiZaoczny = dataRezerwacjiZaoczny;
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

    public Long getIdStudentDzienny() {
        return idStudentDzienny;
    }

    public void setIdStudentDzienny(Long idStudentDzienny) {
        this.idStudentDzienny = idStudentDzienny;
    }

    public LocalDate getDataRezerwacjiDzienny() {
        return dataRezerwacjiDzienny;
    }

    public void setDataRezerwacjiDzienny(LocalDate dataRezerwacjiDzienny) {
        this.dataRezerwacjiDzienny = dataRezerwacjiDzienny;
    }

    public Long getIdStudentZaoczny() {
        return idStudentZaoczny;
    }

    public void setIdStudentZaoczny(Long idStudentZaoczny) {
        this.idStudentZaoczny = idStudentZaoczny;
    }

    public LocalDate getDataRezerwacjiZaoczny() {
        return dataRezerwacjiZaoczny;
    }

    public void setDataRezerwacjiZaoczny(LocalDate dataRezerwacjiZaoczny) {
        this.dataRezerwacjiZaoczny = dataRezerwacjiZaoczny;
    }
}