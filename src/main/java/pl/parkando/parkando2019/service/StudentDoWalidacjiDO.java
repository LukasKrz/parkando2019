package pl.parkando.parkando2019.service;

public class StudentDoWalidacjiDO {

    private Long card_id;

    private String name;

    private String surname;

    public StudentDoWalidacjiDO() {
    }

    public StudentDoWalidacjiDO(Long card_id, String name, String surname) {
        this.card_id = card_id;
        this.name = name;
        this.surname = surname;
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
}
