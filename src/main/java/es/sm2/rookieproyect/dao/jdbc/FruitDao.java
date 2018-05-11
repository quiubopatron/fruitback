package es.sm2.rookieproyect.dao.jdbc;

import es.sm2.rookieproyect.model.FruitDto;

import java.util.List;

public interface FruitDao {

    void insert (FruitDto fruit);

    void update (FruitDto fruit);

    void delete (FruitDto fruit);

    FruitDto getFruit (long id);

    List<FruitDto> list();

}
