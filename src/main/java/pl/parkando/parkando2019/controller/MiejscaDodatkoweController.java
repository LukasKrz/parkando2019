package pl.parkando.parkando2019.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.parkando.parkando2019.model.MiejsceDodatkoweDzienne;
import pl.parkando.parkando2019.repository.MiejsceDodatkoweDzienneRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MiejscaDodatkoweController {

    @Autowired
    private MiejsceDodatkoweDzienneRepository miejsceDodatkoweDzienneRepository;

    @RequestMapping(value = "miejsca/dodatkowe", method = RequestMethod.GET)
    public List<MiejsceDodatkoweDzienne> list() {
        return miejsceDodatkoweDzienneRepository.findAll();
    }

    @RequestMapping(value = "miejsca/dodatkowe", method = RequestMethod.POST)
    public MiejsceDodatkoweDzienne create(@RequestBody MiejsceDodatkoweDzienne miejsce) {
        return miejsceDodatkoweDzienneRepository.saveAndFlush(miejsce);
    }

    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.GET)
    public Optional<MiejsceDodatkoweDzienne> get(@PathVariable Long id) {
        return miejsceDodatkoweDzienneRepository.findById(id);
    }

    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.PUT)
    public MiejsceDodatkoweDzienne update(@PathVariable Long id, @RequestBody MiejsceDodatkoweDzienne miejsce) {
        Optional<MiejsceDodatkoweDzienne> existingMiejsce = miejsceDodatkoweDzienneRepository.findById(id);
        BeanUtils.copyProperties(miejsce, existingMiejsce);
        return miejsceDodatkoweDzienneRepository.saveAndFlush(existingMiejsce.get());
    }

    @RequestMapping(value = "miejsca/dodatkowe/{id}", method = RequestMethod.DELETE)
    public MiejsceDodatkoweDzienne delete(@PathVariable Long id) {
        Optional<MiejsceDodatkoweDzienne> existingMiejsce = miejsceDodatkoweDzienneRepository.findById(id);
        miejsceDodatkoweDzienneRepository.delete(existingMiejsce.get());
        return existingMiejsce.get();
    }
}