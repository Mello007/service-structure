package ru.structure.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.structure.service.entity.Structure;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StructureMapper implements RowMapper<Structure> {
    @Override
    public Structure mapRow(ResultSet rs, int i) throws SQLException {
        Structure structure = new Structure();
        structure.setId(rs.getString("id"));
        structure.setData(rs.getString("data"));
        return structure;
    }
}
