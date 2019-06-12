package pl.parkando.parkando2019.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.parkando.parkando2019.model.MiejsceDodatkoweTydzien;
import pl.parkando.parkando2019.model.MiejsceDodatkoweWeekend;
import pl.parkando.parkando2019.model.MiejscePodstawowe;
import pl.parkando.parkando2019.model.Student;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweTydzienRepository;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweWeekendRepository;
import pl.parkando.parkando2019.repository.MiejscePodstawoweRepository;
import pl.parkando.parkando2019.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private MiejscePodstawoweRepository miejscePodstawoweRepository;

    private MiejsceDodatkoweTydzienRepository miejsceDodatkoweTydzienDlaZaocznychRepository;

    private MiejsceDodatkoweWeekendRepository miejsceDodatkoweWeekendDlaDziennychRepository;

    private StudentRepository studentRepository;

    @Autowired
    public CommandLineAppStartupRunner(MiejscePodstawoweRepository miejscePodstawoweRepository,
                                       MiejsceDodatkoweTydzienRepository miejsceDodatkoweTydzienDlaZaocznychRepository,
                                       MiejsceDodatkoweWeekendRepository miejsceDodatkoweWeekendDlaDziennychRepository,
                                       StudentRepository studentRepository) {
        this.miejscePodstawoweRepository = miejscePodstawoweRepository;
        this.miejsceDodatkoweTydzienDlaZaocznychRepository = miejsceDodatkoweTydzienDlaZaocznychRepository;
        this.miejsceDodatkoweWeekendDlaDziennychRepository = miejsceDodatkoweWeekendDlaDziennychRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        fillMiejscaPodstawowe();
        fillMiejscaDodatkoweWeekendDlaDziennych();
        fillMiejscaDodatkoweTydzienDlaZaocznych();
        fillStudent();
    }

    private void fillMiejscaDodatkoweTydzienDlaZaocznych() {
        List<MiejsceDodatkoweTydzien> miejscaDodatkowe = createMiejscaDodatkoweTydzienDlaZaocznych();
        for (MiejsceDodatkoweTydzien miejsce : miejscaDodatkowe) {
            miejsceDodatkoweTydzienDlaZaocznychRepository.save(miejsce);
        }
    }

    private void fillMiejscaDodatkoweWeekendDlaDziennych() {
        List<MiejsceDodatkoweWeekend> miejscaDodatkowe = createMiejscaDodatkoweWeekendDlaDziennych();
        for (MiejsceDodatkoweWeekend miejsce : miejscaDodatkowe) {
            miejsceDodatkoweWeekendDlaDziennychRepository.save(miejsce);
        }
    }

    private void fillMiejscaPodstawowe() {
        List<MiejscePodstawowe> miejscaPodstawowe = createMiejscaPodstawowe();
        for (MiejscePodstawowe miejscePodstawowe : miejscaPodstawowe) {
            miejscePodstawoweRepository.save(miejscePodstawowe);
        }
    }


    private void fillStudent() {
        List<Student> studenci = createStudenci();
        for (Student student : studenci) {
            studentRepository.save(student);
        }
    }

    private List<MiejscePodstawowe> createMiejscaPodstawowe() {
        MiejscePodstawowe m1 = new MiejscePodstawowe("A", 1L, 101L, LocalDate.now(), 103L, LocalDate.now().minusDays(4L));
        MiejscePodstawowe m2 = new MiejscePodstawowe("A", 2L, 105L, LocalDate.now(), 110L, LocalDate.now().minusDays(1000L));
        MiejscePodstawowe m3 = new MiejscePodstawowe("A", 3L, 111L, LocalDate.now().minusDays(15L), 114L, LocalDate.now().minusDays(1L));
        MiejscePodstawowe m4 = new MiejscePodstawowe("A", 4L, 107L, LocalDate.now(), 102L, LocalDate.now().minusDays(5L));
        MiejscePodstawowe m5 = new MiejscePodstawowe("A", 5L, 111L, LocalDate.now().minusDays(30L), 108L, LocalDate.now().minusDays(4L));
        MiejscePodstawowe m6 = new MiejscePodstawowe("A", 6L, 113L, LocalDate.now().minusDays(2L), 108L, LocalDate.now().minusDays(60L));
        MiejscePodstawowe m7 = new MiejscePodstawowe("A", 7L, 109L, LocalDate.now(), 104L, LocalDate.now().minusDays(3L));
        List<MiejscePodstawowe> miejsca = new ArrayList<>();
        miejsca.addAll(Arrays.asList(m1, m2, m3, m4, m5, m6, m7));
        return miejsca;
    }

    private List<MiejsceDodatkoweWeekend> createMiejscaDodatkoweWeekendDlaDziennych() {
        MiejsceDodatkoweWeekend m8 = new MiejsceDodatkoweWeekend("A", 8L, 111L, LocalDate.now().minusDays(3L), 111L, LocalDate.now().minusDays(1111L));
        MiejsceDodatkoweWeekend m9 = new MiejsceDodatkoweWeekend("A", 9L, 111L, LocalDate.now().minusDays(1111L), 111L, LocalDate.now().minusDays(1111L));
        MiejsceDodatkoweWeekend m10 = new MiejsceDodatkoweWeekend("A", 10L, 111L, LocalDate.now().minusDays(1111L), 107L, LocalDate.now().minusDays(1L));
        List<MiejsceDodatkoweWeekend> miejsca = new ArrayList<>();
        miejsca.addAll(Arrays.asList(m8, m9, m10));
        return miejsca;
    }

    private List<MiejsceDodatkoweTydzien> createMiejscaDodatkoweTydzienDlaZaocznych() {
        MiejsceDodatkoweTydzien m8 = new MiejsceDodatkoweTydzien("A", 8L, 106L, LocalDate.now().minusDays(2L), 102L, LocalDate.now(), 106L, LocalDate.now().minusDays(1106L), 106L, LocalDate.now().minusDays(1106L), 106L, LocalDate.now().minusDays(1106L));
        MiejsceDodatkoweTydzien m9 = new MiejsceDodatkoweTydzien("A", 9L, 106L, LocalDate.now().minusDays(1106L), 106L , LocalDate.now().minusDays(1106L), 103L, LocalDate.now().minusDays(4L), 104L, LocalDate.now().minusDays(1L),106L, LocalDate.now().minusDays(1106L));
        MiejsceDodatkoweTydzien m10 = new MiejsceDodatkoweTydzien("A", 10L, 106L, LocalDate.now().minusDays(1106L), 106L, LocalDate.now().minusDays(1106L), 106L, LocalDate.now().minusDays(1106L), 106L, LocalDate.now().minusDays(1106L), 108L, LocalDate.now());
        List<MiejsceDodatkoweTydzien> miejsca = new ArrayList<>();
        miejsca.addAll(Arrays.asList(m8, m9, m10));
        return miejsca;
    }

    private List<Student> createStudenci() {
        Student s1 = new Student(101L, "Kunegunda", "Trupinska", "dzienne", "d.awid@interia.pl");
        Student s2 = new Student(102L, "Joanna", "Kosteczka", "zaoczne", "d.awid@interia.pl");
        Student s3 = new Student(103L, "Teodor", "Skostnialy", "zaoczne", "d.awid@interia.pl");
        Student s4 = new Student(104L, "Kornelia", "Zmrok", "zaoczne", "d.awid@interia.pl");
        Student s5 = new Student(105L, "Adam", "Mickiewicz", "dzienne", "d.awid@interia.pl");
        Student s6 = new Student(106L, "Juliusz", "Slowacki", "zaoczne", "d.awid@interia.pl");
        Student s7 = new Student(107L, "Leopold", "Staff", "dzienne", "d.awid@interia.pl");
        Student s8 = new Student(108L, "Wislawa", "Szymborska", "zaoczne", "d.awid@interia.pl");
        Student s9 = new Student(109L, "Halina", "Poswiatowska", "dzienne", "d.awid@interia.pl");
        Student s10 = new Student(110L, "Jan", "Brzechwa", "zaoczne", "d.awid@interia.pl");
        Student s11 = new Student(111L, "Zbigniew", "Herbert", "dzienne", "d.awid@interia.pl");
        Student s12 = new Student(112L, "Mikolaj", "Rej", "zaoczne", "d.awid@interia.pl");
        Student s13 = new Student(113L, "Ignacy", "Krasicki", "dzienne", "d.awid@interia.pl");
        Student s14 = new Student(114L, "Boleslaw", "Lesmian", "zaoczne", "d.awid@interia.pl");
        Student s15 = new Student(115L, "Witold", "Gombrowicz", "dzienne", "d.awid@interia.pl");
        Student s16 = new Student(116L, "Adam", "Asnyk", "dzienne", "d.awid@interia.pl");
        Student s17 = new Student(117L, "Czeslaw", "Milosz", "zaoczne", "d.awid@interia.pl");
        List<Student> studenci = new ArrayList<>();
        studenci.addAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17));
        return studenci;
    }
}