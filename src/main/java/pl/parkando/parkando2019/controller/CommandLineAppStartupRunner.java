package pl.parkando.parkando2019.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.parkando.parkando2019.model.MiejsceDodatkoweDzienne;
import pl.parkando.parkando2019.model.MiejsceDodatkoweZaoczne;
import pl.parkando.parkando2019.model.MiejscePodstawowe;
import pl.parkando.parkando2019.model.Student;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweDzienneRepository;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweZaoczneRepository;
import pl.parkando.parkando2019.repository.MiejscePodstawoweRepository;
import pl.parkando.parkando2019.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private MiejscePodstawoweRepository miejscePodstawoweRepository;

    private MiejsceDodatkoweDzienneRepository miejsceDodatkoweDzienneRepository;

    private MiejsceDodatkoweZaoczneRepository miejsceDodatkoweZaoczneRepository;

    private StudentRepository studentRepository;

    @Autowired
    public CommandLineAppStartupRunner(MiejscePodstawoweRepository miejscePodstawoweRepository,
                                       MiejsceDodatkoweDzienneRepository miejsceDodatkoweDzienneRepository,
                                       MiejsceDodatkoweZaoczneRepository miejsceDodatkoweZaoczneRepository,
                                       StudentRepository studentRepository) {
        this.miejscePodstawoweRepository = miejscePodstawoweRepository;
        this.miejsceDodatkoweDzienneRepository = miejsceDodatkoweDzienneRepository;
        this.miejsceDodatkoweZaoczneRepository = miejsceDodatkoweZaoczneRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        List<MiejscePodstawowe> miejscaPodstawoweList = miejscePodstawoweRepository.findAll();
        fillMiejscaPodstawowe();
        fillMiejscaDodatkoweDzienne();
        fillMiejscaDodatkoweZaoczne();
        fillStudent();
    }

    private void fillMiejscaDodatkoweDzienne() {
        List<MiejsceDodatkoweDzienne> miejscaDodatkowe = createMiejscaDodatkoweDzienne();
        for (MiejsceDodatkoweDzienne miejsce : miejscaDodatkowe) {
            miejsceDodatkoweDzienneRepository.save(miejsce);
        }
    }

    private void fillMiejscaDodatkoweZaoczne() {
        List<MiejsceDodatkoweZaoczne> miejscaDodatkowe = createMiejscaDodatkoweZaoczne();
        for (MiejsceDodatkoweZaoczne miejsce : miejscaDodatkowe) {
            miejsceDodatkoweZaoczneRepository.save(miejsce);
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
        MiejscePodstawowe m2 = new MiejscePodstawowe("A", 2L, 105L, LocalDate.now(), 110L, LocalDate.now().minusDays(100L));
        MiejscePodstawowe m3 = new MiejscePodstawowe("A", 3L, 111L, LocalDate.now().minusDays(15L), 114L, LocalDate.now().minusDays(1L));
        MiejscePodstawowe m4 = new MiejscePodstawowe("A", 4L, 107L, LocalDate.now(), 102L, LocalDate.now().minusDays(5L));
        MiejscePodstawowe m5 = new MiejscePodstawowe("A", 5L, 111L, LocalDate.now().minusDays(30L), 108L, LocalDate.now().minusDays(4L));
        MiejscePodstawowe m6 = new MiejscePodstawowe("A", 6L, 113L, LocalDate.now().minusDays(2L), 108L, LocalDate.now().minusDays(60L));
        MiejscePodstawowe m7 = new MiejscePodstawowe("A", 7L, 109L, LocalDate.now(), 104L, LocalDate.now().minusDays(3L));
        List<MiejscePodstawowe> miejsca = new ArrayList<>();
        miejsca.addAll(Arrays.asList(m1, m2, m3, m4, m5, m6, m7));
        return miejsca;
    }

    private List<MiejsceDodatkoweDzienne> createMiejscaDodatkoweDzienne() {
        MiejsceDodatkoweDzienne m8 = new MiejsceDodatkoweDzienne("A", 8L, null, null, null, null, null, null, null, null, null, null);
        MiejsceDodatkoweDzienne m9 = new MiejsceDodatkoweDzienne("A", 9L, null, null, null , null, null, null, null, null,null, null);
        MiejsceDodatkoweDzienne m10 = new MiejsceDodatkoweDzienne("A", 10L, null, null, null, null, null, null, null, null, null, null);
        List<MiejsceDodatkoweDzienne> miejsca = new ArrayList<>();
        miejsca.addAll(Arrays.asList(m8, m9, m10));
        return miejsca;
    }

    private List<MiejsceDodatkoweZaoczne> createMiejscaDodatkoweZaoczne() {
        MiejsceDodatkoweZaoczne m8 = new MiejsceDodatkoweZaoczne("A", 8L, null, null, null, null);
        MiejsceDodatkoweZaoczne m9 = new MiejsceDodatkoweZaoczne("A", 9L, null, null, null , null);
        MiejsceDodatkoweZaoczne m10 = new MiejsceDodatkoweZaoczne("A", 10L, null, null, null, null);
        List<MiejsceDodatkoweZaoczne> miejsca = new ArrayList<>();
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
        Student s7 = new Student(107L, "Leopld", "Staff", "dzienne", "d.awid@interia.pl");
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