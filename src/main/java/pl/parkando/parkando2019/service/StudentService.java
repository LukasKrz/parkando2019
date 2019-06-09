package pl.parkando.parkando2019.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.parkando.parkando2019.model.MiejsceDodatkoweDzienne;
import pl.parkando.parkando2019.model.MiejsceDodatkoweZaoczne;
import pl.parkando.parkando2019.model.MiejscePodstawowe;
import pl.parkando.parkando2019.model.Student;
import pl.parkando.parkando2019.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MiejsceService miejsceService;

//    public StudentZwalidowanyDO validateStudent(Long card_id) {
//        List<Student> studentList = studentRepository.findAll();
//        Student studentFromDb = studentList.stream().filter(student -> student.getCardId().equals(card_id)).findFirst().get();
//        StudentDoWalidacjiDO studentDoWalidacjiDO = createStudentDOWalidacji(studentFromDb);
//
//        List<MiejscePodstawowe> miejscePodstawowe = miejsceService.listPodstawowe();
//        Long numerMiejsca = miejscePodstawowe.stream().filter(miejsce -> miejsce.getIdStudentDzienny().equals(card_id)).findFirst().get().getParkPlaceId();
//        return createStudentZwalidowany(studentDoWalidacjiDO, numerMiejsca);
//    }

    public StudentZwalidowanyDO validateStudentAndCheckMiejsce(StudentDoWalidacjiDO studentDoWalidacjiDO) {
        if (studentIsNotInDb(studentDoWalidacjiDO)){
            return createInvalidStudent(studentDoWalidacjiDO);
        } return createValidStudentAndCheckMiejsce(studentDoWalidacjiDO);
    }

    private boolean studentIsNotInDb(StudentDoWalidacjiDO studentDoWalidacjiDO) {
        return !studentRepository.findAll().stream().anyMatch(student -> student.getCardId().equals(studentDoWalidacjiDO.getCard_id())
                && student.getName().equals(studentDoWalidacjiDO.getName())
                && student.getSurname().equals(studentDoWalidacjiDO.getSurname()));
    }

    private StudentZwalidowanyDO createInvalidStudent(StudentDoWalidacjiDO studentDoWalidacjiDO) {
        StudentZwalidowanyDO studentZwalidowanyDO = new StudentZwalidowanyDO();
        studentZwalidowanyDO.setName(studentDoWalidacjiDO.getName());
        studentZwalidowanyDO.setSurname(studentDoWalidacjiDO.getSurname());
        return studentZwalidowanyDO;
    }

    private StudentZwalidowanyDO createValidStudentAndCheckMiejsce(StudentDoWalidacjiDO studentDoWalidacjiDO) {
        Student studentFromDb = retrieveStudentFromDb(studentDoWalidacjiDO);
        StudentZwalidowanyDO studentZwalidowanyDO = new StudentZwalidowanyDO();
        studentZwalidowanyDO.setUser_id(studentFromDb.getId());
        studentZwalidowanyDO.setCard_id(studentDoWalidacjiDO.getCard_id());
        studentZwalidowanyDO.setName(studentDoWalidacjiDO.getName());
        studentZwalidowanyDO.setSurname(studentDoWalidacjiDO.getSurname());
        studentZwalidowanyDO.setUser_type(studentFromDb.getUserType());
        checkAndFillMiejscePodstawowe(studentDoWalidacjiDO, studentZwalidowanyDO);
        checkAndFillMiejsceDodatkowe(studentDoWalidacjiDO, studentZwalidowanyDO);
        return studentZwalidowanyDO;
    }

    private void checkAndFillMiejscePodstawowe(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO) {
        List<MiejscePodstawowe> miejscaPodstawowe = miejsceService.listPodstawowe();
        if (studentZwalidowanyDO.getUser_type().equals("dzienne")) {
            if (hasStudentDziennyMiejscePodstawowe(studentDoWalidacjiDO, miejscaPodstawowe)) {
                studentZwalidowanyDO.setPark_id("A");
                studentZwalidowanyDO.setPark_place_id(miejscaPodstawowe.stream()
                        .filter(miejscePodstawowe -> miejscePodstawowe.getIdStudentDzienny().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
                studentZwalidowanyDO.setReservationDatePodstawowe(miejscaPodstawowe.stream()
                        .filter(miejscePodstawowe -> miejscePodstawowe.getIdStudentDzienny().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getDataRezerwacjiDzienny());
            }
        }
        if (studentZwalidowanyDO.getUser_type().equals("zaoczne")) {
            if (hasStudentZaocznyMiejscePodstawowe(studentDoWalidacjiDO, miejscaPodstawowe)) {
                studentZwalidowanyDO.setPark_id("A");
                studentZwalidowanyDO.setPark_place_id(miejscaPodstawowe.stream()
                        .filter(miejscePodstawowe -> miejscePodstawowe.getIdStudentZaoczny().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
                studentZwalidowanyDO.setReservationDatePodstawowe(miejscaPodstawowe.stream()
                        .filter(miejscePodstawowe -> miejscePodstawowe.getIdStudentZaoczny().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getDataRezerwacjiZaoczny());
            }
        }
    }

    // TODO LuKr here we set date of reservation, not date of chosen day !!! - to be discussed
    private void checkAndFillMiejsceDodatkowe(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO) {
        List<MiejsceDodatkoweZaoczne> miejscaDodatkoweWeekendDlaDziennych = miejsceService.listDodatkoweWeekendDlaDziennych();
        List<MiejsceDodatkoweDzienne> miejscaDodatkoweTydzienDlaZaocznych = miejsceService.listDodatkoweTydzienDlaZaocznych();
        if (studentZwalidowanyDO.getUser_type().equals("dzienne")){
            if (hasStudentDziennyMiejsceDodatkoweOnSobota(studentDoWalidacjiDO, miejscaDodatkoweWeekendDlaDziennych)) {
                fillMiejsceDodatkoweSobota(studentDoWalidacjiDO, studentZwalidowanyDO, miejscaDodatkoweWeekendDlaDziennych);
            }
            if (hasStudentDziennyMiejsceDodatkoweOnNiedziela(studentDoWalidacjiDO, miejscaDodatkoweWeekendDlaDziennych)) {
                fillMiejsceDodatkoweNiedziela(studentDoWalidacjiDO, studentZwalidowanyDO, miejscaDodatkoweWeekendDlaDziennych);
            }
        }
        if (studentZwalidowanyDO.getUser_type().equals("zaoczne")) {
            if (hasStudentZaocznyMiejsceDodatkoweOnPoniedzialek(studentDoWalidacjiDO, miejscaDodatkoweTydzienDlaZaocznych)) {
                fillMiejsceDodatkowePoniedzialek(studentDoWalidacjiDO, studentZwalidowanyDO, miejscaDodatkoweTydzienDlaZaocznych);
            }
            if (hasStudentZaocznyMiejsceDodatkoweOnWtorek(studentDoWalidacjiDO, miejscaDodatkoweTydzienDlaZaocznych)) {
                fillMiejsceDodatkoweWtorek(studentDoWalidacjiDO, studentZwalidowanyDO, miejscaDodatkoweTydzienDlaZaocznych);
            }
            if (hasStudentZaocznyMiejsceDodatkoweOnSroda(studentDoWalidacjiDO, miejscaDodatkoweTydzienDlaZaocznych)) {
                fillMiejsceDodatkoweSroda(studentDoWalidacjiDO, studentZwalidowanyDO, miejscaDodatkoweTydzienDlaZaocznych);
            }
            if (hasStudentZaocznyMiejsceDodatkoweOnCzwartek(studentDoWalidacjiDO, miejscaDodatkoweTydzienDlaZaocznych)) {
                fillMiejsceDodatkoweCzwartek(studentDoWalidacjiDO, studentZwalidowanyDO, miejscaDodatkoweTydzienDlaZaocznych);
            }
            if (hasStudentZaocznyMiejsceDodatkoweOnPiatek(studentDoWalidacjiDO, miejscaDodatkoweTydzienDlaZaocznych)) {
                fillMiejsceDodatkowePiatek(studentDoWalidacjiDO, studentZwalidowanyDO, miejscaDodatkoweTydzienDlaZaocznych);
            }
        }
    }

    private Student retrieveStudentFromDb(StudentDoWalidacjiDO studentDoWalidacjiDO) {
        return studentRepository.findAll().stream().filter(student -> student.getCardId().equals(studentDoWalidacjiDO.getCard_id())
                && student.getName().equals(studentDoWalidacjiDO.getName())
                && student.getSurname().equals(studentDoWalidacjiDO.getSurname())).collect(Collectors.toList()).get(0);
    }

    private boolean hasStudentDziennyMiejscePodstawowe(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejscePodstawowe> miejscaPodstawowe) {
        return miejscaPodstawowe.stream().anyMatch(miejscePodstawowe -> miejscePodstawowe.getIdStudentDzienny().equals(studentDoWalidacjiDO.getCard_id())
                && miejscePodstawowe.getDataRezerwacjiDzienny().isAfter(LocalDate.now().minusDays(7L)));
    }

    private boolean hasStudentZaocznyMiejscePodstawowe(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejscePodstawowe> miejscaPodstawowe) {
        return miejscaPodstawowe.stream().anyMatch(miejscePodstawowe -> miejscePodstawowe.getIdStudentZaoczny().equals(studentDoWalidacjiDO.getCard_id())
                && miejscePodstawowe.getDataRezerwacjiZaoczny().isAfter(LocalDate.now().minusDays(7L)));
    }

    private boolean hasStudentDziennyMiejsceDodatkoweOnSobota(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejsceDodatkoweZaoczne> miejscaDodatkoweZaoczneDlaDziennych) {
        return miejscaDodatkoweZaoczneDlaDziennych.stream().anyMatch(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdSobota().equals(studentDoWalidacjiDO.getCard_id())
                && miejsceDodatkowe.getReservationDateSobota().isAfter(LocalDate.now().minusDays(7L)));
    }

    private boolean hasStudentDziennyMiejsceDodatkoweOnNiedziela(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejsceDodatkoweZaoczne> miejscaDodatkoweZaoczneDlaDziennych) {
        return miejscaDodatkoweZaoczneDlaDziennych.stream().anyMatch(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdNiedziela().equals(studentDoWalidacjiDO.getCard_id())
                && miejsceDodatkowe.getReservationDateNiedziela().isAfter(LocalDate.now().minusDays(7L)));
    }

    private boolean hasStudentZaocznyMiejsceDodatkoweOnPoniedzialek(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        return miejscaDodatkoweDzienneDlaZaocznych.stream().anyMatch(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdPoniedzialek().equals(studentDoWalidacjiDO.getCard_id())
                && miejsceDodatkowe.getReservationDatePoniedzialek().isAfter(LocalDate.now().minusDays(7L)));
    }

    private boolean hasStudentZaocznyMiejsceDodatkoweOnWtorek(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        return miejscaDodatkoweDzienneDlaZaocznych.stream().anyMatch(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdWtorek().equals(studentDoWalidacjiDO.getCard_id())
                && miejsceDodatkowe.getReservationDateWtorek().isAfter(LocalDate.now().minusDays(7L)));
    }

    private boolean hasStudentZaocznyMiejsceDodatkoweOnSroda(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        return miejscaDodatkoweDzienneDlaZaocznych.stream().anyMatch(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdSroda().equals(studentDoWalidacjiDO.getCard_id())
                && miejsceDodatkowe.getReservationDateSroda().isAfter(LocalDate.now().minusDays(7L)));
    }

    private boolean hasStudentZaocznyMiejsceDodatkoweOnCzwartek(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        return miejscaDodatkoweDzienneDlaZaocznych.stream().anyMatch(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdCzwartek().equals(studentDoWalidacjiDO.getCard_id())
                && miejsceDodatkowe.getReservationDateCzwartek().isAfter(LocalDate.now().minusDays(7L)));
    }

    private boolean hasStudentZaocznyMiejsceDodatkoweOnPiatek(StudentDoWalidacjiDO studentDoWalidacjiDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        return miejscaDodatkoweDzienneDlaZaocznych.stream().anyMatch(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdPiatek().equals(studentDoWalidacjiDO.getCard_id())
                && miejsceDodatkowe.getReservationDatePiatek().isAfter(LocalDate.now().minusDays(7L)));
    }

    private void fillMiejsceDodatkoweSobota(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO, List<MiejsceDodatkoweZaoczne> miejscaDodatkoweZaoczneDlaDziennych) {
        studentZwalidowanyDO.setParkPlaceIdDodatkowe(miejscaDodatkoweZaoczneDlaDziennych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdSobota().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
        studentZwalidowanyDO.setReservationDateDodatkowe(miejscaDodatkoweZaoczneDlaDziennych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdSobota().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getReservationDateSobota());
        studentZwalidowanyDO.setDayOfWeek("sobota");
    }

    private void fillMiejsceDodatkoweNiedziela(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO, List<MiejsceDodatkoweZaoczne> miejscaDodatkoweZaoczneDlaDziennych) {
        studentZwalidowanyDO.setParkPlaceIdDodatkowe(miejscaDodatkoweZaoczneDlaDziennych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdNiedziela().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
        studentZwalidowanyDO.setReservationDateDodatkowe(miejscaDodatkoweZaoczneDlaDziennych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdNiedziela().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getReservationDateNiedziela());
        studentZwalidowanyDO.setDayOfWeek("niedziela");
    }

    private void fillMiejsceDodatkowePoniedzialek(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        studentZwalidowanyDO.setParkPlaceIdDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdPoniedzialek().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
        studentZwalidowanyDO.setReservationDateDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdPoniedzialek().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getReservationDatePoniedzialek());
        studentZwalidowanyDO.setDayOfWeek("poniedzialek");
    }

    private void fillMiejsceDodatkoweWtorek(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        studentZwalidowanyDO.setParkPlaceIdDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdWtorek().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
        studentZwalidowanyDO.setReservationDateDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdWtorek().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getReservationDateWtorek());
        studentZwalidowanyDO.setDayOfWeek("wtorek");
    }

    private void fillMiejsceDodatkoweSroda(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        studentZwalidowanyDO.setParkPlaceIdDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdSroda().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
        studentZwalidowanyDO.setReservationDateDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdSroda().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getReservationDateSroda());
        studentZwalidowanyDO.setDayOfWeek("sroda");
    }

    private void fillMiejsceDodatkoweCzwartek(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        studentZwalidowanyDO.setParkPlaceIdDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdCzwartek().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
        studentZwalidowanyDO.setReservationDateDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdCzwartek().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getReservationDateCzwartek());
        studentZwalidowanyDO.setDayOfWeek("czwartek");
    }

    private void fillMiejsceDodatkowePiatek(StudentDoWalidacjiDO studentDoWalidacjiDO, StudentZwalidowanyDO studentZwalidowanyDO, List<MiejsceDodatkoweDzienne> miejscaDodatkoweDzienneDlaZaocznych) {
        studentZwalidowanyDO.setParkPlaceIdDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdPiatek().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getParkPlaceId());
        studentZwalidowanyDO.setReservationDateDodatkowe(miejscaDodatkoweDzienneDlaZaocznych.stream()
                .filter(miejsceDodatkowe -> miejsceDodatkowe.getStudentCardIdPiatek().equals(studentDoWalidacjiDO.getCard_id())).findFirst().get().getReservationDatePiatek());
        studentZwalidowanyDO.setDayOfWeek("piatek");
    }

//    private StudentDoWalidacjiDO createStudentDOWalidacji(Student student) {
//        return new StudentDoWalidacjiDO(student.getCardId(), student.getName(), student.getSurname());
//    }
//
//    private StudentZwalidowanyDO createStudentZwalidowany(StudentDoWalidacjiDO studentDoWalidacjiDO, Long numerMiejsca) {
//        StudentZwalidowanyDO studentZwalidowanyDO = new StudentZwalidowanyDO();
//        studentZwalidowanyDO.setUser_id(1L);
//        studentZwalidowanyDO.setCard_id(studentDoWalidacjiDO.getCard_id());
//        studentZwalidowanyDO.setName(studentDoWalidacjiDO.getName());
//        studentZwalidowanyDO.setSurname(studentDoWalidacjiDO.getSurname());
//        studentZwalidowanyDO.setUser_type("dzienne");
//        studentZwalidowanyDO.setPark_id("A");
//        studentZwalidowanyDO.setPark_place_id(1L);
//        return studentZwalidowanyDO;
//    }

    public List<Student> list() {
        return studentRepository.findAll();
    }

    public Student create(@RequestBody Student student) {
        return studentRepository.saveAndFlush(student);
    }

    public Optional<Student> get(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    public Student update(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        existingStudent.get().setName(student.getName());
//        BeanUtils.copyProperties(student, existingStudent);
        return studentRepository.saveAndFlush(existingStudent.get());
    }

    public Student delete(@PathVariable Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        studentRepository.delete(existingStudent.get());
        return existingStudent.get();
    }
}