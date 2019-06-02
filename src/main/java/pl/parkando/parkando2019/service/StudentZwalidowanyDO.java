package pl.parkando.parkando2019.service;

import java.time.LocalDate;

public class StudentZwalidowanyDO {

    private Long user_id;

    private Long card_id;

    private String name;

    private String surname;

    private String user_type;

    private String park_id;

    private Long park_place_id;

    private LocalDate reservationDatePodstawowe;

    private Long parkPlaceIdDodatkowe;

    private String dayOfWeek;

    private LocalDate reservationDateDodatkowe;

    public StudentZwalidowanyDO() {
    }

    public StudentZwalidowanyDO(Long user_id, Long card_id, String name, String surname, String user_type, String park_id, Long park_place_id,
                                LocalDate reservationDatePodstawowe, Long parkPlaceIdDodatkowe, String dayOfWeek, LocalDate reservationDateDodatkowe) {
        this.user_id = user_id;
        this.card_id = card_id;
        this.name = name;
        this.surname = surname;
        this.user_type = user_type;
        this.park_id = park_id;
        this.park_place_id = park_place_id;
        this.reservationDatePodstawowe = reservationDatePodstawowe;
        this.parkPlaceIdDodatkowe = parkPlaceIdDodatkowe;
        this.dayOfWeek = dayOfWeek;
        this.reservationDateDodatkowe = reservationDateDodatkowe;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getPark_id() {
        return park_id;
    }

    public void setPark_id(String park_id) {
        this.park_id = park_id;
    }

    public Long getPark_place_id() {
        return park_place_id;
    }

    public void setPark_place_id(Long park_place_id) {
        this.park_place_id = park_place_id;
    }

    public LocalDate getReservationDatePodstawowe() {
        return reservationDatePodstawowe;
    }

    public void setReservationDatePodstawowe(LocalDate reservationDatePodstawowe) {
        this.reservationDatePodstawowe = reservationDatePodstawowe;
    }

    public Long getParkPlaceIdDodatkowe() {
        return parkPlaceIdDodatkowe;
    }

    public void setParkPlaceIdDodatkowe(Long parkPlaceIdDodatkowe) {
        this.parkPlaceIdDodatkowe = parkPlaceIdDodatkowe;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDate getReservationDateDodatkowe() {
        return reservationDateDodatkowe;
    }

    public void setReservationDateDodatkowe(LocalDate reservationDateDodatkowe) {
        this.reservationDateDodatkowe = reservationDateDodatkowe;
    }
}
