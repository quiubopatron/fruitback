package es.sm2.rookieproyect.service.converter;

import es.sm2.rookieproyect.dao.FruitRepository;
import es.sm2.rookieproyect.model.Fruit;
import es.sm2.rookieproyect.model.FruitDto;
import es.sm2.rookieproyect.service.converter.FruitConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)

public class FruitConverterTest {

    @TestConfiguration
    static class ContextConfiguration {

        @Bean
        public FruitConverter fruitConverter() {
            return new FruitConverter();
        }
    }

    @Autowired
    private FruitConverter fruitConverter;


    @Test
    public void toModelDomainShouldReturnFruitConvertedFromFruitDto() {

        //given
        FruitDto fruitDto = new FruitDto();
        fruitDto.setIdFruit(1L);
        fruitDto.setName("pera");
        fruitDto.setDescription("description");
        fruitDto.setPricePerKg(1);
        fruitDto.setDateCreated(LocalDateTime.now());

        //when
        Fruit result = fruitConverter.toModelDomain(fruitDto);

        //then
        Assert.assertEquals(result.getIdFruit(), fruitDto.getIdFruit());
        Assert.assertEquals(result.getName(), fruitDto.getName());
        Assert.assertEquals(result.getDescription(), fruitDto.getDescription());
        Assert.assertEquals(result.getPricePerKg(), fruitDto.getPricePerKg());
        Assert.assertEquals(result.getDateCreated(), fruitDto.getDateCreated());
    }

    @Test
    public void toModelDtoShouldReturnFruitDtoConvertedFromFruit() {

        //given
        Fruit fruit = new Fruit();
        fruit.setIdFruit(1L);
        fruit.setName("a");
        fruit.setDescription("aa");
        fruit.setPricePerKg(1);
        fruit.setDateCreated(LocalDateTime.now());

        //when
        FruitDto result = fruitConverter.toModelDto(fruit);

        //then
        Assert.assertEquals(result.getIdFruit(), fruit.getIdFruit());
        Assert.assertEquals(result.getName(), fruit.getName());
        Assert.assertEquals(result.getDescription(), fruit.getDescription());
        Assert.assertEquals(result.getPricePerKg(), fruit.getPricePerKg());
        Assert.assertEquals(result.getDateCreated(), fruit.getDateCreated());
    }
}




