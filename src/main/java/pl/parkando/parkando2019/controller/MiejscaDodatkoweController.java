package pl.parkando.parkando2019.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.parkando.parkando2019.model.MiejsceDodatkowe;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MiejscaDodatkoweController {

    @Autowired
    private MiejsceDodatkoweRepository miejsceDodatkoweRepository;

    @RequestMapping(value = "miejsca/dodatkowe", method = RequestMethod.GET)
    public List<MiejsceDodatkowe> list() {
        return miejsceDodatkoweRepository.findAll();
    }

    @RequestMapping(value = "miejsca/dodatkowe", method = RequestMethod.POST)
    public MiejsceDodatkowe create(@RequestBody MiejsceDodatkowe miejsce) {
        return miejsceDodatkoweRepository.saveAndFlush(miejsce);
    }

    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.GET)
    public Optional<MiejsceDodatkowe> get(@PathVariable Long id) {
        return miejsceDodatkoweRepository.findById(id);
    }

    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.PUT)
    public MiejsceDodatkowe update(@PathVariable Long id, @RequestBody MiejsceDodatkowe miejsce) {
        Optional<MiejsceDodatkowe> existingMiejsce = miejsceDodatkoweRepository.findById(id);
        BeanUtils.copyProperties(miejsce, existingMiejsce);
        return miejsceDodatkoweRepository.saveAndFlush(existingMiejsce.get());
    }

    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.DELETE)
    public MiejsceDodatkowe delete(@PathVariable Long id) {
        Optional<MiejsceDodatkowe> existingMiejsce = miejsceDodatkoweRepository.findById(id);
        miejsceDodatkoweRepository.delete(existingMiejsce.get());
        return existingMiejsce.get();
    }
}