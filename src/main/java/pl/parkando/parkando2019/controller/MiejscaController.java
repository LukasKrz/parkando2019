package pl.parkando.parkando2019.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.parkando.parkando2019.model.CardIdDO;
import pl.parkando.parkando2019.model.MiejscaDodatkoweTydzienDO;
import pl.parkando.parkando2019.model.MiejscaDodatkoweWeekendDO;
import pl.parkando.parkando2019.model.MiejsceDodatkoweTydzien;
import pl.parkando.parkando2019.model.MiejsceDodatkoweWeekend;
import pl.parkando.parkando2019.model.MiejscePodstawowe;
import pl.parkando.parkando2019.model.WyborDodatkoweDO;
import pl.parkando.parkando2019.service.MiejsceService;

import java.util.List;

@RestController
@RequestMapping("/")
public class MiejscaController {

    @Autowired
    private MiejsceService miejsceService;

    @RequestMapping(value = "miejsca/dostepnepodstawowe/dzienne", method = RequestMethod.GET)
    public List<MiejscePodstawowe> listDostepnePodstawoweD() {
        return miejsceService.listDostepnePodstawoweD();
    }

    @RequestMapping(value = "miejsca/dostepnepodstawowe/zaoczne", method = RequestMethod.GET)
    public List<MiejscePodstawowe> listDostepnePodstawoweZ() {
        return miejsceService.listDostepnePodstawoweZ();
    }

    @RequestMapping(value = "miejsca/dostepnedodatkowe/dzienne", method = RequestMethod.GET)
    public MiejscaDodatkoweWeekendDO listDostepneDodatkoweWeekendDlaDziennych() {
        return miejsceService.listDostepneDodatkoweWeekendDlaDziennych();
    }

    @RequestMapping(value = "miejsca/dostepnedodatkowe/zaoczne", method = RequestMethod.GET)
    public MiejscaDodatkoweTydzienDO listDostepneDodatkoweTydzienDlaZaocznych() {
        return miejsceService.listDostepneDodatkoweTydzienDlaZaocznych();
    }

    @RequestMapping(value = "miejsca/rezerwacjapodstawowe/dzienne/{id}", method = RequestMethod.PUT)
    public MiejscePodstawowe bookPodstawoweD(@PathVariable Long id, @RequestBody CardIdDO cardIdDO) {
        return miejsceService.bookPodstawoweD(id, cardIdDO);
    }

    @RequestMapping(value = "miejsca/rezerwacjapodstawowe/zaoczne/{id}", method = RequestMethod.PUT)
    public MiejscePodstawowe bookPodstawoweZ(@PathVariable Long id, @RequestBody CardIdDO cardIdDO) {
        return miejsceService.bookPodstawoweZ(id, cardIdDO);
    }

    @RequestMapping(value = "miejsca/rezerwacjadodatkowe/dzienne/{id}", method = RequestMethod.PUT)
    public MiejsceDodatkoweWeekend bookDodatkoweWeekendDlaDziennych(@PathVariable Long id, @RequestBody WyborDodatkoweDO wyborDodatkoweDO) {
        return miejsceService.bookDodatkoweWeekendDlaDziennych(id, wyborDodatkoweDO);
    }

    @RequestMapping(value = "miejsca/rezerwacjadodatkowe/zaoczne/{id}", method = RequestMethod.PUT)
    public MiejsceDodatkoweTydzien bookDodatkoweTydzienDlaZaocznych(@PathVariable Long id, @RequestBody WyborDodatkoweDO wyborDodatkoweDO) {
        return miejsceService.bookDodatkoweTydzienDlaZaocznych(id, wyborDodatkoweDO);
    }

    @RequestMapping(value = "miejsca/podstawoweall", method = RequestMethod.GET)
    public List<MiejscePodstawowe> listPodstawowe() {
        return miejsceService.listPodstawowe();
    }

    @RequestMapping(value = "miejsca/dodatkoweall/dzienne", method = RequestMethod.GET)
    public List<MiejsceDodatkoweTydzien> listDodatkoweDzienne() {
        return miejsceService.listDodatkoweTydzienDlaZaocznych();
    }

    @RequestMapping(value = "miejsca/dodatkoweall/zaoczne", method = RequestMethod.GET)
    public List<MiejsceDodatkoweWeekend> listDodatkoweZaoczne() {
        return miejsceService.listDodatkoweWeekendDlaDziennych();
    }
}