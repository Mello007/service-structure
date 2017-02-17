package ru.structure.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.structure.service.entity.Structure;
import ru.structure.service.mapper.StructureMapper;

import javax.sql.DataSource;
import java.util.List;

@Service
public class StructureService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }


    public List<Structure> getStructures() {
        String SQL = "select * from structures";
        List <Structure> structures = jdbcTemplateObject.query(SQL, new StructureMapper());
        return structures;
    }

    public void create(String name, Integer age) {
        String sql = "insert into structures (name, age) values (?, ?)";
        jdbcTemplateObject.update(sql, name, age);
    }

    public Structure getStructure(Integer id) {
        String SQL = "select * from structures where id = ?";
        Structure structure = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new StructureMapper());
        return structure;
    }

    public void delete(Integer id){
        String SQL = "delete from structures where id = ?";
        jdbcTemplateObject.update(SQL, id);
    }

    public void update(Integer id, Integer age){
        String SQL = "update structures set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, age, id);
    }

}
