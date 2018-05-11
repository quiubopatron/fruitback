package es.sm2.rookieproyect.controller;

import es.sm2.rookieproyect.dao.FruitRepository;
import es.sm2.rookieproyect.exception.RookieProjectException;
import es.sm2.rookieproyect.model.Fruit;
import es.sm2.rookieproyect.model.FruitDto;
import es.sm2.rookieproyect.model.WordsTransformRequest;
import es.sm2.rookieproyect.service.FruitService;
import es.sm2.rookieproyect.service.WordsTransformService;
import es.sm2.rookieproyect.service.converter.FruitConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins="*")
@RestController
public class RookieController {

    private final WordsTransformService wordsTransformService;
    private final FruitService fruitService;
    private final FruitRepository fruitRepository;
    private final FruitConverter fruitConverter;


    @Autowired
    public RookieController(WordsTransformService wordsTransformService, FruitService fruitService, FruitRepository fruitRepository, FruitConverter fruitConverter) {
        this.wordsTransformService = wordsTransformService;
        this.fruitService = fruitService;
        this.fruitRepository = fruitRepository;
        this.fruitConverter = fruitConverter;
    }

    @GetMapping(value = "/wordstransform")
    @ResponseStatus(HttpStatus.OK)
    public String wordTransform(WordsTransformRequest wordsTransformRequest) {

        try {
            return wordsTransformService.wordsTransform(wordsTransformRequest);

        } catch (RookieProjectException e) {

            System.out.println(e.getMessage());
            return "Ha fallado la petición";
        }
    }

    @GetMapping (value = "/fruits", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FruitDto> listAllFruits(){
        List<FruitDto> fruitDtoList = fruitService.list();
        return fruitDtoList;
    }

    @GetMapping("/fruits/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FruitDto getOne(@PathVariable Long id) throws RookieProjectException {
        FruitDto fruitDto = fruitService.getFruit(id);
        return fruitDto;
    }

    @DeleteMapping ("/fruits/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFruit(@PathVariable Long id){
        fruitService.delete(id);
    }

    @PostMapping ("/fruits")
    @ResponseStatus(HttpStatus.CREATED)
    public FruitDto createFruit(@RequestBody FruitDto fruit){
        FruitDto savedFruit = fruitService.insert(fruit);
        return savedFruit;
    }

    @PutMapping("/fruits/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FruitDto updateFruit(@RequestBody FruitDto fruit, @PathVariable Long id) throws RookieProjectException {
        fruit.setIdFruit(id);
        FruitDto updatedFruit = fruitService.update(fruit);

        return updatedFruit;
    }
}
