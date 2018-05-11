package es.sm2.rookieproyect.service;

import es.sm2.rookieproyect.dao.FruitRepository;
import es.sm2.rookieproyect.exception.RookieProjectException;
import es.sm2.rookieproyect.model.Fruit;
import es.sm2.rookieproyect.model.FruitDto;
import es.sm2.rookieproyect.service.FruitService;
import es.sm2.rookieproyect.service.FruitServiceImpl;
import es.sm2.rookieproyect.service.converter.FruitConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class FruitServiceImplTest {

    @TestConfiguration
    static class ContextConfiguration {

        @Bean
        public FruitService fruitService(FruitRepository fruitRepository, FruitConverter fruitConverter) {
            return new FruitServiceImpl(fruitRepository, fruitConverter);
        }
    }

    @MockBean
    private FruitRepository fruitRepository;

    @MockBean
    private FruitConverter fruitConverter;

    @Autowired
    private FruitService fruitService;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void insertFruitShouldInsertOneFruit() {

        //given
        Fruit fruit = buildFruit(1L, "name");
        fruit.setDateCreated(LocalDateTime.now());
        FruitDto fruitDto = buildFruitDto(1L, "name");

        Mockito.when(fruitRepository.save(fruit)).thenReturn(fruit);
        Mockito.when(fruitConverter.toModelDomain(fruitDto)).thenReturn(fruit);
        Mockito.when(fruitConverter.toModelDto(fruit)).thenReturn(fruitDto);

        //when
        FruitDto createdFruitDto = fruitService.insert(fruitDto);

        //then
        Mockito.verify(fruitRepository).save(fruitConverter.toModelDomain(createdFruitDto));
        Assert.assertEquals(createdFruitDto.getIdFruit(), fruitDto.getIdFruit());
        Assert.assertEquals(createdFruitDto.getName(), fruitDto.getName());
    }

    @Test
    public void updateFruitShouldUpdateOneFruit() throws RookieProjectException {

       //given
        Fruit fruit = buildFruit(1L, "name");
        FruitDto fruitDto = buildFruitDto(1L, "name");

        Mockito.when(fruitRepository.findById(1L)).thenReturn(Optional.of(fruit));
        Mockito.when(fruitRepository.save(fruit)).thenReturn(fruit);
        Mockito.when(fruitConverter.toModelDto(fruit)).thenReturn(fruitDto);
        Mockito.when(fruitConverter.toModelDomain(fruitDto)).thenReturn(fruit);

        //when
        FruitDto createdFruitDto = fruitService.update(fruitDto);

        //then
        Mockito.verify(fruitRepository).save(fruitConverter.toModelDomain(createdFruitDto));
        Assert.assertEquals(createdFruitDto.getName(), fruitDto.getName());
        Assert.assertEquals(createdFruitDto.getIdFruit(), fruitDto.getIdFruit());
    }

    @Test
    public void deleteFruitShouldDeleteOneFruit() {

        //given
        Mockito.doNothing().when(fruitRepository).deleteById(Mockito.anyLong());

       //when
        fruitService.delete(1L);

       //then
        Mockito.verify(fruitRepository).deleteById(1L);
    }

    @Test
    public void getFruitShouldReturnOneFruit() throws RookieProjectException {

        //given
        Fruit fruit = buildFruit(1L, "name");
        FruitDto convertedFruitDto = buildFruitDto(1L, "name");

        Mockito.when(fruitRepository.findById(1L)).thenReturn(Optional.of(fruit));
        Mockito.when(fruitConverter.toModelDto(fruit)).thenReturn(convertedFruitDto);

        //when
        FruitDto fruitDto = fruitService.getFruit(1L);

        //then
        Assert.assertNotNull(fruitDto);
        Assert.assertEquals(1L, fruitDto.getIdFruit().longValue());
        Assert.assertEquals("name", fruitDto.getName());
    }

    @Test
    public void listShouldReturnAllFruits() {

        //given
        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(buildFruit(1L, "test"));
        Mockito.when(fruitRepository.findAll()).thenReturn(fruitList);
        Mockito.when(fruitConverter.toModelDto(Mockito.any(Fruit.class))).thenReturn(new FruitDto());

        //when
        List<FruitDto> fruits = fruitService.list();

        //then
        Assert.assertNotNull(fruits);
        Assert.assertEquals(1, fruits.size());
    }

    private Fruit buildFruit(Long id, String name) {
        Fruit fruit = new Fruit();
        fruit.setIdFruit(id);
        fruit.setName(name);

        return fruit;
    }

    private FruitDto buildFruitDto(Long id, String name) {
        FruitDto fruitDto = new FruitDto();
        fruitDto.setIdFruit(id);
        fruitDto.setName(name);

        return fruitDto;
    }

}
