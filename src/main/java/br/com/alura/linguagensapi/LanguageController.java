package br.com.alura.linguagensapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LanguageController {

    @Autowired
    private LanguageRepository repository;

    @GetMapping("/linguagens")
    public List<Language> getLanguagesList() {
        List<Language> languages = repository.findAll();
        return languages;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Language> registerLanguage(@RequestBody Language language) {
        Language saveLanguage = repository.save(language);
        return new ResponseEntity<>(saveLanguage, HttpStatus.CREATED);
    }

    @GetMapping("/linguagens/{id}")
    public Object getLanguageById(@PathVariable String id) {
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/linguagens/{id}")
    public Language updateLanguage(@PathVariable String id, @RequestBody Language language) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        language.setId(id);
        Language saveLanguage = repository.save(language);
        return saveLanguage;
    }

    @DeleteMapping("/linguagens/{id}")
    public void deleteLanguage(@PathVariable String id) {
        repository.deleteById(id);
    }

}
