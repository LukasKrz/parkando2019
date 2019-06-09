//package pl.parkando.parkando2019.controller;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import pl.parkando.parkando2019.model.MiejsceDodatkoweTydzien;
//import pl.parkando.parkando2019.repository.MiejsceDodatkoweTydzienRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/")
//public class MiejscaDodatkoweController {
//
//    @Autowired
//    private MiejsceDodatkoweTydzienRepository miejsceDodatkoweTydzienRepository;
//
//    @RequestMapping(value = "miejsca/dodatkowe", method = RequestMethod.GET)
//    public List<MiejsceDodatkoweTydzien> list() {
//        return miejsceDodatkoweTydzienRepository.findAll();
//    }
//
//    @RequestMapping(value = "miejsca/dodatkowe", method = RequestMethod.POST)
//    public MiejsceDodatkoweTydzien create(@RequestBody MiejsceDodatkoweTydzien miejsce) {
//        return miejsceDodatkoweTydzienRepository.saveAndFlush(miejsce);
//    }
//
//    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.GET)
//    public Optional<MiejsceDodatkoweTydzien> get(@PathVariable Long id) {
//        return miejsceDodatkoweTydzienRepository.findById(id);
//    }
//
//    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.PUT)
//    public MiejsceDodatkoweTydzien update(@PathVariable Long id, @RequestBody MiejsceDodatkoweTydzien miejsce) {
//        Optional<MiejsceDodatkoweTydzien> existingMiejsce = miejsceDodatkoweTydzienRepository.findById(id);
//        BeanUtils.copyProperties(miejsce, existingMiejsce);
//        return miejsceDodatkoweTydzienRepository.saveAndFlush(existingMiejsce.get());
//    }
//
//    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.DELETE)
//    public MiejsceDodatkoweTydzien delete(@PathVariable Long id) {
//        Optional<MiejsceDodatkoweTydzien> existingMiejsce = miejsceDodatkoweTydzienRepository.findById(id);
//        miejsceDodatkoweTydzienRepository.delete(existingMiejsce.get());
//        return existingMiejsce.get();
//    }
//}