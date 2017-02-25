package ru.structure.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.structure.service.entity.Record;
import ru.structure.service.entity.Structure;
import ru.structure.service.mapper.RecordMapper;
import ru.structure.service.mapper.StructureMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        List<Structure> structures = jdbcTemplateObject.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                return connection.prepareStatement(SQL);
            }
        }, new StructureMapper());
        return structures;
    }

    public void create(String json) throws IOException, SQLException {
        String sql = "insert into structures (data) values (?::JSONB)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, json);
                return ps;
            }
        }, keyHolder);
    }

    public Structure getStructure(Long id) {
        String SQL = "select * from structures where id = ?";
        Structure structure = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new StructureMapper());
        return structure;
    }

    public void delete(Integer id) {
        String sql = "delete from structures where id = ?";
        jdbcTemplateObject.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setObject(1, id);
                return ps;
            }
        }, new StructureMapper());
    }


    public void updateStructure(Long id, String json) throws SQLException {
        String sql = "update structures set data = ?::JSONB where id = ?";
//        jdbcTemplateObject.update(sql, jsonObject, id);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String c = null;
                try {
                    c = new ObjectMapper().writeValueAsString(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, json);
                ps.setObject(2, id);
                return ps;
            }
        }, keyHolder);
    }

    public List<Record> getRecordsByStructureId(Long id) {
        String sql = "SELECT * FROM records INNER JOIN structures ON records.structure_id = structures.id WHERE structure_id = ?";
        // List <Record> records = jdbcTemplateObject.query(sql, new Object[]{id}, new RecordMapper());
        List<Record> records = jdbcTemplateObject.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setObject(1, id);
                return ps;
            }
        }, new RecordMapper());
        return records;
    }

    public void createRecordByStructure(Long id, String json) throws SQLException {
        String sql = "insert into records (structure_id, data) values (?, ?::JSONB)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String c = null;
                try {
                    c = new ObjectMapper().writeValueAsString(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setObject(1, id);
                ps.setString(2, json);
                return ps;
            }
        }, keyHolder);
    }

    public void updateRecordByStructureId(Long id, String json) throws SQLException {
        String sql = "update records set data = ?::JSONB WHERE id= ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String c = null;
                try {
                    c = new ObjectMapper().writeValueAsString(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, json);
                ps.setObject(2, id);
                return ps;
            }
        }, keyHolder);
    }

    public void deleteRecordByStructureId(Long recordId) {
        String sql = "delete from records where id = ?";
        jdbcTemplateObject.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setObject(1, recordId);
                return ps;
            }
        }, new RecordMapper());
    }

    public Record getRecordById(Long id) {
        String SQL = "select * from records where id = ?";
        Record record = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new RecordMapper());
        return record;
    }
}
