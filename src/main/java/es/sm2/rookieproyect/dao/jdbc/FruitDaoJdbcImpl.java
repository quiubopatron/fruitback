package es.sm2.rookieproyect.dao.jdbc;

import es.sm2.rookieproyect.model.FruitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FruitDaoJdbcImpl implements FruitDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FruitDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void insert(FruitDto fruit) {
        String sql = "insert into fruits(id_fruit, name, description, price_per_kg, date_created) values (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, fruit);
    }

    public void update(FruitDto fruit) {
        String sql = "update fruits set name=?, description=?, price_per_kg=?, date_created=? where id_fruit=?;";
        jdbcTemplate.update(sql, fruit);
        //fruit.getName(), fruit.getDescription(), fruit.getPricePerKg(), fruit.getPricePerKg()
    }

    public void delete(FruitDto fruit) {
        String sql = "delete from fruits where id_fruit=?;";
        jdbcTemplate.update(sql, fruit);
    }

    public FruitDto getFruit(long id) {
//        String sql = "select * from fruits where id_fruit=" + id;
//        Map map = jdbcTemplate.queryForMap(sql, id);
//        FruitDto fruitDto = new FruitDto();
//        fruitDto.setIdFruit(Long.valueOf((Integer)map.get("id")));
//        fruitDto.setName((String)map.get("name"));
//        fruitDto.setDescription((String)map.get("description"));
//        fruitDto.setPricePerKg((Integer)map.get("price_per_kg"));
//        fruitDto.setDateCreated((String)map.get("date_created"));
//        return fruitDto;
        return jdbcTemplate.queryForObject("select * from fruits where id_fruit=?;",
                new Object[]{id}, new FruitRowMapper());
    }

    public List<FruitDto> list() {
        return jdbcTemplate.query("select * from fruits order by name;", new FruitRowMapper());
    }
}


