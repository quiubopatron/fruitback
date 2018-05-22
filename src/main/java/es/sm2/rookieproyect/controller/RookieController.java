package es.sm2.rookieproyect.controller;

import es.sm2.rookieproyect.exception.RookieProjectException;
import es.sm2.rookieproyect.model.FruitDto;
import es.sm2.rookieproyect.model.WordsTransformRequest;
import es.sm2.rookieproyect.service.FruitService;
import es.sm2.rookieproyect.service.WordsTransformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin(origins="*")
@RestController
public class RookieController {

    private final WordsTransformService wordsTransformService;
    private final FruitService fruitService;


    @Autowired
    public RookieController(WordsTransformService wordsTransformService, FruitService fruitService) {

        this.wordsTransformService = wordsTransformService;
        this.fruitService = fruitService;
    }

    @GetMapping(value = "/wordstransform")
    @ResponseStatus(HttpStatus.OK)
    public String wordTransform(WordsTransformRequest wordsTransformRequest) {

        try {
            return wordsTransformService.wordsTransform(wordsTransformRequest);

        } catch (RookieProjectException e) {

            Logger.getLogger(e.getMessage());
            return "Ha fallado la petición";
        }
    }

    @GetMapping (value = "/fruits", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<FruitDto> listAllFruits(){
        return  fruitService.list();

    }

    @GetMapping("/fruits/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FruitDto getOne(@PathVariable Long id) throws RookieProjectException {
        return fruitService.getFruit(id);

    }

    @DeleteMapping ("/fruits/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFruit(@PathVariable Long id){
        fruitService.delete(id);
    }

    @PostMapping ("/fruits")
    @ResponseStatus(HttpStatus.CREATED)
    public FruitDto createFruit(@RequestBody FruitDto fruit){
        return fruitService.insert(fruit);

    }

    @PutMapping("/fruits/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FruitDto updateFruit(@RequestBody FruitDto fruit, @PathVariable Long id) throws RookieProjectException {
        fruit.setIdFruit(id);
        return fruitService.update(fruit);


    }
}
