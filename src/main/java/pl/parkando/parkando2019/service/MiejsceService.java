package pl.parkando.parkando2019.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.parkando.parkando2019.model.CardIdDO;
import pl.parkando.parkando2019.model.MiejsceDodatkoweDzienne;
import pl.parkando.parkando2019.model.MiejsceDodatkoweZaoczne;
import pl.parkando.parkando2019.model.MiejscePodstawowe;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweDzienneRepository;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweZaoczneRepository;
import pl.parkando.parkando2019.repository.MiejscePodstawoweRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MiejsceService {

    @Autowired
    private MiejsceDodatkoweDzienneRepository miejsceDodatkoweTydzienDlaZaocznych;

    @Autowired
    private MiejsceDodatkoweZaoczneRepository miejsceDodatkoweWeekendDlaDziennych;

    @Autowired
    private MiejscePodstawoweRepository miejscePodstawoweRepository;

    public List<MiejscePodstawowe> listDostepnePodstawoweD() {
        return miejscePodstawoweRepository.findAll().stream()
                .filter(miejscePodstawowe -> miejscePodstawowe.getDataRezerwacjiDzienny().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
    }

    public List<MiejscePodstawowe> listDostepnePodstawoweZ() {
        return miejscePodstawoweRepository.findAll().stream()
                .filter(miejscePodstawowe -> miejscePodstawowe.getDataRezerwacjiZaoczny().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
    }

    public List<MiejsceDodatkoweDzienne> listDostepneDodatkoweD() {  // TODO LuKr
        return miejsceDodatkoweTydzienDlaZaocznych.findAll().stream()
                .filter(miejscePodstawowe -> miejscePodstawowe.getReservationDatePoniedzialek().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
    }

    public List<MiejsceDodatkoweDzienne> listDostepneDodatkoweZ() {  // TODO LuKr
        return miejsceDodatkoweTydzienDlaZaocznych.findAll().stream()
                .filter(miejscePodstawowe -> miejscePodstawowe.getReservationDateWtorek().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
    }

    public MiejscePodstawowe bookPodstawoweD(Long id, CardIdDO cardIdDO) {
        Optional<MiejscePodstawowe> existingMiejsce = miejscePodstawoweRepository.findById(id);
        existingMiejsce.get().setIdStudentDzienny(cardIdDO.getCard_id());
        existingMiejsce.get().setDataRezerwacjiDzienny(LocalDate.now());
        return miejscePodstawoweRepository.saveAndFlush(existingMiejsce.get());
    }

    public MiejscePodstawowe bookPodstawoweZ(Long id, CardIdDO cardIdDO) {
        Optional<MiejscePodstawowe> existingMiejsce = miejscePodstawoweRepository.findById(id);
        existingMiejsce.get().setIdStudentZaoczny(cardIdDO.getCard_id());
        existingMiejsce.get().setDataRezerwacjiZaoczny(LocalDate.now());
        return miejscePodstawoweRepository.saveAndFlush(existingMiejsce.get());
    }

    public MiejsceDodatkoweDzienne bookDodatkoweTydzienDlaZaocznych(Long id, CardIdDO cardIdDO) {  // TODO LuKr LOGIKA !!!
        Optional<MiejsceDodatkoweDzienne> existingMiejsce = miejsceDodatkoweTydzienDlaZaocznych.findById(id);
        existingMiejsce.get().setStudentCardIdPoniedzialek(cardIdDO.getCard_id());
        existingMiejsce.get().setReservationDatePoniedzialek(LocalDate.now());
        return miejsceDodatkoweTydzienDlaZaocznych.saveAndFlush(existingMiejsce.get());
    }

    public MiejsceDodatkoweDzienne bookDodatkoweWeekendDlaDziennych(Long id, CardIdDO cardIdDO) {  // TODO LuKr LOGIKA !!
        Optional<MiejsceDodatkoweDzienne> existingMiejsce = miejsceDodatkoweTydzienDlaZaocznych.findById(id);
        existingMiejsce.get().setStudentCardIdWtorek(cardIdDO.getCard_id());
        existingMiejsce.get().setReservationDateWtorek(LocalDate.now());
        return miejsceDodatkoweTydzienDlaZaocznych.saveAndFlush(existingMiejsce.get());
    }

    public List<MiejscePodstawowe> listPodstawowe() {
        return miejscePodstawoweRepository.findAll();
    }

    public List<MiejsceDodatkoweDzienne> listDodatkoweTydzienDlaZaocznych() {
        return miejsceDodatkoweTydzienDlaZaocznych.findAll();
    }

    public List<MiejsceDodatkoweZaoczne> listDodatkoweWeekendDlaDziennych() {
        return miejsceDodatkoweWeekendDlaDziennych.findAll();
    }
}
