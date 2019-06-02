package pl.parkando.parkando2019.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.parkando.parkando2019.model.CardIdDO;
import pl.parkando.parkando2019.model.MiejsceDodatkoweDzienne;
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
    private MiejsceDodatkoweDzienneRepository miejsceDodatkoweDzienneRepository;

    @Autowired
    private MiejsceDodatkoweZaoczneRepository miejsceDodatkoweZaoczneRepository;

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

    public List<MiejsceDodatkoweDzienne> listDostepneDodatkoweD() {
        return miejsceDodatkoweDzienneRepository.findAll().stream()
                .filter(miejscePodstawowe -> miejscePodstawowe.getReservationDatePoniedzialek().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
    }

    public List<MiejsceDodatkoweDzienne> listDostepneDodatkoweZ() {
        return miejsceDodatkoweDzienneRepository.findAll().stream()
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

    public MiejsceDodatkoweDzienne bookDodatkoweD(Long id, CardIdDO cardIdDO) {
        Optional<MiejsceDodatkoweDzienne> existingMiejsce = miejsceDodatkoweDzienneRepository.findById(id);
        existingMiejsce.get().setStudentCardIdPoniedzialek(cardIdDO.getCard_id());
        existingMiejsce.get().setReservationDatePoniedzialek(LocalDate.now());
        return miejsceDodatkoweDzienneRepository.saveAndFlush(existingMiejsce.get());
    }

    public MiejsceDodatkoweDzienne bookDodatkoweZ(Long id, CardIdDO cardIdDO) {
        Optional<MiejsceDodatkoweDzienne> existingMiejsce = miejsceDodatkoweDzienneRepository.findById(id);
        existingMiejsce.get().setStudentCardIdWtorek(cardIdDO.getCard_id());
        existingMiejsce.get().setReservationDateWtorek(LocalDate.now());
        return miejsceDodatkoweDzienneRepository.saveAndFlush(existingMiejsce.get());
    }

    public List<MiejscePodstawowe> listPodstawowe() {
        return miejscePodstawoweRepository.findAll();
    }

    public List<MiejsceDodatkoweDzienne> listDodatkowe() {
        return miejsceDodatkoweDzienneRepository.findAll();
    }
}
