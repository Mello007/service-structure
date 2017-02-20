package ru.structure.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.structure.service.entity.Record;
import ru.structure.service.entity.Structure;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by artem on 20.02.17.
 */
public class RecordMapper implements RowMapper<Record> {

    @Override
    public Record mapRow(ResultSet resultSet, int i) throws SQLException {
        Record record = new Record();
        record.setId(resultSet.getString("id"));
        record.setData(resultSet.getString("data"));
        record.setStructureId(resultSet.getString("structure_id"));
        return record;
    }
}
