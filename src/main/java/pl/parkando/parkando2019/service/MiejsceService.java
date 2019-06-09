package pl.parkando.parkando2019.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.parkando.parkando2019.model.CardIdDO;
import pl.parkando.parkando2019.model.MiejscaDodatkoweTydzienDO;
import pl.parkando.parkando2019.model.MiejscaDodatkoweWeekendDO;
import pl.parkando.parkando2019.model.MiejsceDodatkoweTydzien;
import pl.parkando.parkando2019.model.MiejsceDodatkoweWeekend;
import pl.parkando.parkando2019.model.MiejscePodstawowe;
import pl.parkando.parkando2019.model.WyborDodatkoweDO;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweTydzienRepository;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweWeekendRepository;
import pl.parkando.parkando2019.repository.MiejscePodstawoweRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MiejsceService {

    @Autowired
    private MiejsceDodatkoweTydzienRepository miejsceDodatkoweTydzienDlaZaocznych;

    @Autowired
    private MiejsceDodatkoweWeekendRepository miejsceDodatkoweWeekendDlaDziennych;

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

    public MiejscaDodatkoweWeekendDO listDostepneDodatkoweWeekendDlaDziennych() {
        List<Long> sobota = retrieveParkPlaceIdDostepneSobota();
        List<Long> niedziela = retrieveParkPlaceIdDostepneNiedziela();
        return new MiejscaDodatkoweWeekendDO(sobota, niedziela);
    }

    public MiejscaDodatkoweTydzienDO listDostepneDodatkoweTydzienDlaZaocznych() {
        List<Long> poniedzialek = retrieveParkPlaceIdDostepnePoniedzialek();
        List<Long> wtorek = retrieveParkPlaceIdDostepneWtorek();
        List<Long> sroda = retrieveParkPlaceIdDostepneSroda();
        List<Long> czwartek = retrieveParkPlaceIdDostepneCzwartek();
        List<Long> piatek = retrieveParkPlaceIdDostepnePiatek();
        return new MiejscaDodatkoweTydzienDO(poniedzialek, wtorek, sroda, czwartek, piatek);
    }

    private List<Long> retrieveParkPlaceIdDostepneSobota() {
        List<MiejsceDodatkoweWeekend> dostepneMiejscaDodatkoweSobota = miejsceDodatkoweWeekendDlaDziennych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getReservationDateSobota().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
        return dostepneMiejscaDodatkoweSobota.stream().map(MiejsceDodatkoweWeekend::getParkPlaceId).collect(Collectors.toList());
    }

    private List<Long> retrieveParkPlaceIdDostepneNiedziela() {
        List<MiejsceDodatkoweWeekend> dostepneMiejscaDodatkoweNiedziela = miejsceDodatkoweWeekendDlaDziennych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getReservationDateNiedziela().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
        return dostepneMiejscaDodatkoweNiedziela.stream().map(MiejsceDodatkoweWeekend::getParkPlaceId).collect(Collectors.toList());
    }

    private List<Long> retrieveParkPlaceIdDostepnePoniedzialek() {
        List<MiejsceDodatkoweTydzien> dostepneMiejscaDodatkowePoniedzialek = miejsceDodatkoweTydzienDlaZaocznych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getReservationDatePoniedzialek().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
        return dostepneMiejscaDodatkowePoniedzialek.stream().map(MiejsceDodatkoweTydzien::getParkPlaceId).collect(Collectors.toList());
    }

    private List<Long> retrieveParkPlaceIdDostepneWtorek() {
        List<MiejsceDodatkoweTydzien> dostepneMiejscaDodatkoweWtorek = miejsceDodatkoweTydzienDlaZaocznych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getReservationDateWtorek().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
        return dostepneMiejscaDodatkoweWtorek.stream().map(MiejsceDodatkoweTydzien::getParkPlaceId).collect(Collectors.toList());
    }

    private List<Long> retrieveParkPlaceIdDostepneSroda() {
        List<MiejsceDodatkoweTydzien> dostepneMiejscaDodatkoweSroda = miejsceDodatkoweTydzienDlaZaocznych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getReservationDateSroda().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
        return dostepneMiejscaDodatkoweSroda.stream().map(MiejsceDodatkoweTydzien::getParkPlaceId).collect(Collectors.toList());
    }

    private List<Long> retrieveParkPlaceIdDostepneCzwartek() {
        List<MiejsceDodatkoweTydzien> dostepneMiejscaDodatkoweCzwartek = miejsceDodatkoweTydzienDlaZaocznych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getReservationDateCzwartek().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
        return dostepneMiejscaDodatkoweCzwartek.stream().map(MiejsceDodatkoweTydzien::getParkPlaceId).collect(Collectors.toList());
    }

    private List<Long> retrieveParkPlaceIdDostepnePiatek() {
        List<MiejsceDodatkoweTydzien> dostepneMiejscaDodatkowePiatek = miejsceDodatkoweTydzienDlaZaocznych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getReservationDatePiatek().isBefore(LocalDate.now().minusDays(7L))).collect(Collectors.toList());
        return dostepneMiejscaDodatkowePiatek.stream().map(MiejsceDodatkoweTydzien::getParkPlaceId).collect(Collectors.toList());
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

    public MiejsceDodatkoweWeekend bookDodatkoweWeekendDlaDziennych(Long id, WyborDodatkoweDO wyborDodatkoweDO) {
        MiejsceDodatkoweWeekend existingMiejsce = miejsceDodatkoweWeekendDlaDziennych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getParkPlaceId().equals(id)).findFirst().get();
        if (wyborDodatkoweDO.getDayOfWeek().equals("sobota")) {
            existingMiejsce.setStudentCardIdSobota(wyborDodatkoweDO.getCard_id());
            existingMiejsce.setReservationDateSobota(LocalDate.now());
        }
        if (wyborDodatkoweDO.getDayOfWeek().equals("niedziela")) {
            existingMiejsce.setStudentCardIdNiedziela(wyborDodatkoweDO.getCard_id());
            existingMiejsce.setReservationDateNiedziela(LocalDate.now());
        }
        return miejsceDodatkoweWeekendDlaDziennych.saveAndFlush(existingMiejsce);
    }

    public MiejsceDodatkoweTydzien bookDodatkoweTydzienDlaZaocznych(Long id, WyborDodatkoweDO wyborDodatkoweDO) {
        MiejsceDodatkoweTydzien existingMiejsce = miejsceDodatkoweTydzienDlaZaocznych.findAll().stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getParkPlaceId().equals(id)).findFirst().get();
        if (wyborDodatkoweDO.getDayOfWeek().equals("poniedzialek")) {
            existingMiejsce.setStudentCardIdPoniedzialek(wyborDodatkoweDO.getCard_id());
            existingMiejsce.setReservationDatePoniedzialek(LocalDate.now());
        }
        if (wyborDodatkoweDO.getDayOfWeek().equals("wtorek")) {
            existingMiejsce.setStudentCardIdWtorek(wyborDodatkoweDO.getCard_id());
            existingMiejsce.setReservationDateWtorek(LocalDate.now());
        }
        if (wyborDodatkoweDO.getDayOfWeek().equals("sroda")) {
            existingMiejsce.setStudentCardIdSroda(wyborDodatkoweDO.getCard_id());
            existingMiejsce.setReservationDateSroda(LocalDate.now());
        }
        if (wyborDodatkoweDO.getDayOfWeek().equals("czwartek")) {
            existingMiejsce.setStudentCardIdCzwartek(wyborDodatkoweDO.getCard_id());
            existingMiejsce.setReservationDateCzwartek(LocalDate.now());
        }
        if (wyborDodatkoweDO.getDayOfWeek().equals("piatek")) {
            existingMiejsce.setStudentCardIdPiatek(wyborDodatkoweDO.getCard_id());
            existingMiejsce.setReservationDatePiatek(LocalDate.now());
        }
        return miejsceDodatkoweTydzienDlaZaocznych.saveAndFlush(existingMiejsce);
    }

    public List<MiejscePodstawowe> listPodstawowe() {
        return miejscePodstawoweRepository.findAll();
    }

    public List<MiejsceDodatkoweTydzien> listDodatkoweTydzienDlaZaocznych() {
        return miejsceDodatkoweTydzienDlaZaocznych.findAll();
    }

    public List<MiejsceDodatkoweWeekend> listDodatkoweWeekendDlaDziennych() {
        return miejsceDodatkoweWeekendDlaDziennych.findAll();
    }
}
