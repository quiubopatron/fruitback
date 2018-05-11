package es.sm2.rookieproyect.service;

import es.sm2.rookieproyect.exception.RookieProjectException;
import es.sm2.rookieproyect.model.FruitDto;
import es.sm2.rookieproyect.model.WordsTransformRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WordsTransformServiceImpl implements WordsTransformService {

    private final FruitServiceImpl fruitService;

    @Value("${custom.word}")
    private String configuredWord;


    @Autowired
    public WordsTransformServiceImpl(FruitServiceImpl fruitService) {
        this.fruitService = fruitService;
    }

    @Override
    public String wordsTransform(WordsTransformRequest wordsTransformRequest) throws RookieProjectException {

        FruitDto randomFruit = getOneRandomFruit();

        if (randomFruit == null) {
            throw new RookieProjectException("No hay frutas en la base de datos.");
        }

        if (wordsTransformRequest.getWord1() == null || wordsTransformRequest.getWord2() == null) {
            throw new RookieProjectException("Uno de los parámetros es null");
        }

        return wordsTransformRequest.getWord1().toUpperCase()
                + wordsTransformRequest.getWord2().toUpperCase()
                + configuredWord.toUpperCase()
                + randomFruit.getName().toUpperCase();
    }

    private FruitDto getOneRandomFruit() {
        FruitDto randomFruit = null;
        List<FruitDto> fruitList = fruitService.list();

        if (fruitList != null && !fruitList.isEmpty()) {
            Collections.shuffle(fruitList);
            randomFruit = fruitList.get(0);
        }
        return randomFruit;
    }
}
