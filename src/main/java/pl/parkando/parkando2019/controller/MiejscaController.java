package pl.parkando.parkando2019.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.parkando.parkando2019.model.CardIdDO;
import pl.parkando.parkando2019.model.MiejsceDodatkoweDzienne;
import pl.parkando.parkando2019.model.MiejscePodstawowe;
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
    public List<MiejsceDodatkoweDzienne> listDostepneDodatkoweD() {
        return miejsceService.listDostepneDodatkoweD();
    }

    @RequestMapping(value = "miejsca/dostepnedodatkowe/zaoczne", method = RequestMethod.GET)
    public List<MiejsceDodatkoweDzienne> listDostepneDodatkoweZ() {
        return miejsceService.listDostepneDodatkoweZ();
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
    public MiejsceDodatkoweDzienne bookDodatkoweD(@PathVariable Long id, @RequestBody CardIdDO cardIdDO) {
        return miejsceService.bookDodatkoweTydzienDlaZaocznych(id, cardIdDO);
    }

    @RequestMapping(value = "miejsca/rezerwacjadodatkowe/zaoczne/{id}", method = RequestMethod.PUT)
    public MiejsceDodatkoweDzienne bookDodatkoweZ(@PathVariable Long id, @RequestBody CardIdDO cardIdDO) {
        return miejsceService.bookDodatkoweWeekendDlaDziennych(id, cardIdDO);
    }

    @RequestMapping(value = "miejsca/podstawoweall", method = RequestMethod.GET)
    public List<MiejscePodstawowe> listPodstawowe() {
        return miejsceService.listPodstawowe();
    }

    @RequestMapping(value = "miejsca/dodatkoweall", method = RequestMethod.GET)
    public List<MiejsceDodatkoweDzienne> listDodatkowe() {
        return miejsceService.listDodatkoweTydzienDlaZaocznych();
    }
}