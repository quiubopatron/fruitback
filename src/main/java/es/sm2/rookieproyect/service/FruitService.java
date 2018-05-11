package es.sm2.rookieproyect.service;

import es.sm2.rookieproyect.exception.RookieProjectException;
import es.sm2.rookieproyect.model.FruitDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FruitService {

    FruitDto insert (FruitDto fruit);

    FruitDto update (FruitDto fruit) throws RookieProjectException;

    void delete (Long fruitId);

    FruitDto getFruit (Long id) throws RookieProjectException;

    List<FruitDto> list();
}
