package pl.parkando.parkando2019.model;

public class WyborDodatkoweDO {

    Long card_id;

    String dayOfWeek;

    public WyborDodatkoweDO() {
    }

    public WyborDodatkoweDO(Long card_id, String dayOfWeek) {
        this.card_id = card_id;
        this.dayOfWeek = dayOfWeek;
    }

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
