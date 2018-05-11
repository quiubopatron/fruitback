package es.sm2.rookieproyect.dao.jdbc;

import es.sm2.rookieproyect.model.FruitDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FruitRowMapper implements RowMapper<FruitDto> {


    @Override
    public FruitDto mapRow(ResultSet resultSet, int i) throws SQLException {
        FruitDto fruitDto = new FruitDto();
        fruitDto.setIdFruit(resultSet.getLong("id_fruit"));
        fruitDto.setName(resultSet.getString("name"));
        fruitDto.setDescription(resultSet.getString("description"));
        fruitDto.setPricePerKg(resultSet.getInt("price_per_kg"));
        //fruitDto.setDateCreated(resultSet.getTimestamp("date_created"));

        return fruitDto;
    }
}
