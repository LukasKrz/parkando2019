package pl.parkando.parkando2019.model;

import java.util.List;

public class MiejscaDodatkoweTydzienDO {  // TODO LuKr make parameters private

    List<Long> poniedzialek;
    List<Long> wtorek;
    List<Long> sroda;
    List<Long> czwartek;
    List<Long> piatek;

    public MiejscaDodatkoweTydzienDO() {
    }

    public MiejscaDodatkoweTydzienDO(List<Long> poniedzialek, List<Long> wtorek, List<Long> sroda, List<Long> czwartek, List<Long> piatek) {
        this.poniedzialek = poniedzialek;
        this.wtorek = wtorek;
        this.sroda = sroda;
        this.czwartek = czwartek;
        this.piatek = piatek;
    }

    public List<Long> getPoniedzialek() {
        return poniedzialek;
    }

    public void setPoniedzialek(List<Long> poniedzialek) {
        this.poniedzialek = poniedzialek;
    }

    public List<Long> getWtorek() {
        return wtorek;
    }

    public void setWtorek(List<Long> wtorek) {
        this.wtorek = wtorek;
    }

    public List<Long> getSroda() {
        return sroda;
    }

    public void setSroda(List<Long> sroda) {
        this.sroda = sroda;
    }

    public List<Long> getCzwartek() {
        return czwartek;
    }

    public void setCzwartek(List<Long> czwartek) {
        this.czwartek = czwartek;
    }

    public List<Long> getPiatek() {
        return piatek;
    }

    public void setPiatek(List<Long> piatek) {
        this.piatek = piatek;
    }
}
