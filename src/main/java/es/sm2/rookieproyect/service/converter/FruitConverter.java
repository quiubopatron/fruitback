package es.sm2.rookieproyect.service.converter;

import es.sm2.rookieproyect.model.Fruit;
import es.sm2.rookieproyect.model.FruitDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FruitConverter {

    public Fruit toModelDomain(FruitDto fruitDto) {

        Fruit fruit = new Fruit();

        BeanUtils.copyProperties(fruitDto, fruit);

        return fruit;
    }


    public FruitDto toModelDto(Fruit fruit) {
        FruitDto fruitDto = new FruitDto();

        BeanUtils.copyProperties(fruit, fruitDto);

        return fruitDto;
    }
}
