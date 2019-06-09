package pl.parkando.parkando2019.model;

import java.util.List;

public class MiejscaDodatkoweWeekendDO {

    List<Long> sobota;

    List<Long> niedziela;

    public MiejscaDodatkoweWeekendDO() {
    }

    public MiejscaDodatkoweWeekendDO(List<Long> sobota, List<Long> niedziela) {
        this.sobota = sobota;
        this.niedziela = niedziela;
    }

    public List<Long> getSobota() {
        return sobota;
    }

    public void setSobota(List<Long> sobota) {
        this.sobota = sobota;
    }

    public List<Long> getNiedziela() {
        return niedziela;
    }

    public void setNiedziela(List<Long> niedziela) {
        this.niedziela = niedziela;
    }
}
