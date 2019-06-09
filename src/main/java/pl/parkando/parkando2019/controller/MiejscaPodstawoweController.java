//package pl.parkando.parkando2019.controller;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import pl.parkando.parkando2019.model.MiejscePodstawowe;
//import pl.parkando.parkando2019.repository.MiejscePodstawoweRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/")
//public class MiejscaPodstawoweController {
//
//    @Autowired
//    private MiejscePodstawoweRepository miejscePodstawoweRepository;
//
//    @RequestMapping(value = "miejsca/podstawowe", method = RequestMethod.GET)
//    public List<MiejscePodstawowe> list() {
//        return miejscePodstawoweRepository.findAll();
//    }
//
//    @RequestMapping(value = "miejsca/podstawowe", method = RequestMethod.POST)
//    public MiejscePodstawowe create(@RequestBody MiejscePodstawowe miejscePodstawowe) {
//        return miejscePodstawoweRepository.saveAndFlush(miejscePodstawowe);
//    }
//
//    @RequestMapping(value = "miejsca/podstawowe/{id}", method = RequestMethod.GET)
//    public Optional<MiejscePodstawowe> get(@PathVariable Long id) {
//        return miejscePodstawoweRepository.findById(id);
//    }
//
//    @RequestMapping(value = "miejsca/podstawowe/{id}", method = RequestMethod.PUT)
//    public MiejscePodstawowe update(@PathVariable Long id, @RequestBody MiejscePodstawowe miejscePodstawowe) {
//        Optional<MiejscePodstawowe> existingMiejsce = miejscePodstawoweRepository.findById(id);
//        BeanUtils.copyProperties(miejscePodstawowe, existingMiejsce);
//        return miejscePodstawoweRepository.saveAndFlush(existingMiejsce.get());
//    }
//
//    @RequestMapping(value = "miejsca/podstawowe/{id}", method = RequestMethod.DELETE)
//    public MiejscePodstawowe delete(@PathVariable Long id) {
//        Optional<MiejscePodstawowe> existingMiejsce = miejscePodstawoweRepository.findById(id);
//        miejscePodstawoweRepository.delete(existingMiejsce.get());
//        return existingMiejsce.get();
//    }
//}