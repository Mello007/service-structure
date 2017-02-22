package ru.structure.service;

import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.structure.service.entity.Record;
import ru.structure.service.entity.Structure;
import ru.structure.service.mapper.RecordMapper;
import ru.structure.service.mapper.StructureMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service

public class StructureService {

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<Structure> getStructures() {
        String SQL = "select * from structures";
        List <Structure> structures = jdbcTemplateObject.query(SQL, new StructureMapper());
        return structures;
    }

    public void create(String json) throws IOException, SQLException {
        PGobject jsonObject = createPgObject(json);
        String sql = "insert into structures (data) values (?)";
        jdbcTemplateObject.update(sql, jsonObject);
    }

    public Structure getStructure(Long id) {
        String SQL = "select * from structures where id = ?";
        Structure structure = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new StructureMapper());
        char [] arr = structure.getData().replaceAll("\"\"", "").toCharArray();
        char [] newchar = new char[arr.length+2];
        newchar[0] = '[';
        System.arraycopy(arr, 0, newchar, 1, arr.length);
        newchar[newchar.length-1] = ']';
        structure.setData(String.copyValueOf(newchar));
        return structure;
    }

    public void delete(Integer id){
        String SQL = "delete from structures where id = ?";
        jdbcTemplateObject.update(SQL, id);
    }

    public void update(Long id, String json) throws SQLException {
        PGobject jsonObject = createPgObject(json);
        String SQL = "update structures set data = ? where id = ?";
        jdbcTemplateObject.update(SQL, jsonObject, id);
    }


    public List<Record> getRecordsByStructureId(Long id){
        String SQL = "SELECT * FROM records INNER JOIN structures ON records.structure_id = structures.id WHERE structure_id = ?";
        List <Record> records = jdbcTemplateObject.query(SQL, new Object[]{id}, new RecordMapper());
        return records;
    }

    public void createRecordByStructure(Long id, String json) throws SQLException {
        PGobject jsonObject = createPgObject(json);
        String sql = "insert into records (id, data) values (?, ?)";
        jdbcTemplateObject.update(sql, new Object[]{id}, jsonObject);
    }

    public void updateRecordByStructureId(Long id, String json) throws SQLException {
        PGobject jsonObject = createPgObject(json);
        String SQL = "update records set data = ? WHERE id= ?";
        jdbcTemplateObject.update(SQL, jsonObject, id);
    }

    public void deleteRecordByStructureId(Long recordId){
        String SQL = "delete from records where id = ?";
        jdbcTemplateObject.update(SQL, recordId);
    }

    public Record getRecordById(Long id) {
        String SQL = "select * from records where id = ?";
        Record record = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new RecordMapper());
        char [] arr = record.getData().replaceAll("\"\"", "").toCharArray();
        char [] newchar = new char[arr.length+2];
        newchar[0] = '[';
        System.arraycopy(arr, 0, newchar, 1, arr.length);
        newchar[newchar.length-1] = ']';
        record.setData(String.copyValueOf(newchar));
        return record;
    }


    private PGobject createPgObject(String json) throws SQLException {
        PGobject jsonObject = new PGobject();
        jsonObject.setType("json");
        jsonObject.setValue(json);
        return jsonObject;
    }

}
