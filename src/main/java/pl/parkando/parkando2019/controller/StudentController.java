package pl.parkando.parkando2019.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.parkando.parkando2019.model.Student;
import pl.parkando.parkando2019.service.StudentDoWalidacjiDO;
import pl.parkando.parkando2019.service.StudentService;
import pl.parkando.parkando2019.service.StudentZwalidowanyDO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class StudentController {

    @RequestMapping(value = "studenci/walidacja/{card_id}", method = RequestMethod.GET)
    public StudentZwalidowanyDO validateStudent(@PathVariable Long card_id) {
        return studentService.validateStudent(card_id);
    }

    @RequestMapping(value = "studenci/walidacja", method = RequestMethod.POST)
    public StudentZwalidowanyDO validateStudentAndCheckMiejsce(@RequestBody StudentDoWalidacjiDO studentDoWalidacjiDO) {
        return studentService.validateStudentAndCheckMiejsce(studentDoWalidacjiDO);
    }

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "studenci", method = RequestMethod.GET)
    public List<Student> list() {
        return studentService.list();
    }

    @RequestMapping(value = "studenci", method = RequestMethod.POST)
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @RequestMapping(value = "studenci/{id}", method = RequestMethod.GET)
    public Optional<Student> get(@PathVariable Long id) {
        return studentService.get(id);
    }

    @RequestMapping(value = "studenci/{id}", method = RequestMethod.PUT)
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @RequestMapping(value = "studenci/{id}", method = RequestMethod.DELETE)
    public Student delete(@PathVariable Long id) {
        return studentService.delete(id);
    }
}
