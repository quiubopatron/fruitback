package es.sm2.rookieproyect.service;

import es.sm2.rookieproyect.dao.FruitRepository;
import es.sm2.rookieproyect.exception.RookieProjectException;
import es.sm2.rookieproyect.model.Fruit;
import es.sm2.rookieproyect.model.FruitDto;
import es.sm2.rookieproyect.service.converter.FruitConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceImpl implements FruitService{

    private static final String FRUITS_CACHE = "FRUITS_CACHE";

    private final FruitRepository fruitRepository;
    private final FruitConverter fruitConverter;

    @Autowired
    public FruitServiceImpl(FruitRepository fruitRepository, FruitConverter fruitConverter) {
        this.fruitRepository = fruitRepository;
        this.fruitConverter = fruitConverter;
    }

    @Override
    @CacheEvict(value = FRUITS_CACHE, allEntries = true)
    public FruitDto insert(FruitDto fruitDto) {
        Fruit fruit = fruitConverter.toModelDomain(fruitDto);
        fruit.setDateCreated(LocalDateTime.now());
        fruit = fruitRepository.save(fruit);
        return fruitConverter.toModelDto(fruit);
    }

    @Override
    @CacheEvict(value = FRUITS_CACHE, allEntries = true)
    public FruitDto update(FruitDto fruitDto)  throws RookieProjectException {
        Fruit fruit = fruitRepository.findById(fruitDto.getIdFruit())
            .orElseThrow(() -> new RookieProjectException("No se encuentra la fruta con id " + fruitDto.getIdFruit()));

        fruit.setName(fruitDto.getName());
        fruit.setDescription(fruitDto.getDescription());
        fruit.setPricePerKg(fruitDto.getPricePerKg());

        fruit = fruitRepository.save(fruit);

        return fruitConverter.toModelDto(fruit);
    }

    @Override
    @CacheEvict(value = FRUITS_CACHE, allEntries = true)
    public void delete(Long fruitId) {
        fruitRepository.deleteById(fruitId);
    }

    @Override
    public FruitDto getFruit(Long id) throws RookieProjectException {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new RookieProjectException("No existe ninguna fruta con id " + id));

        return fruitConverter.toModelDto(fruit);
    }

    @Override
    @Cacheable (FRUITS_CACHE)
    public List<FruitDto> list() {
        List<Fruit> fruitList = fruitRepository.findAll();

        return fruitList.stream().map(
                fruitConverter::toModelDto)
                .collect(Collectors.toList());
    }
}
