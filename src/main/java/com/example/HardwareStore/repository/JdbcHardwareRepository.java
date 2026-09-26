package com.example.HardwareStore.repository;

import com.example.HardwareStore.domain.Hardware;
import com.example.HardwareStore.domain.ItemType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class JdbcHardwareRepository implements HardwareRepository {

     private JdbcTemplate jdbcTemplate;


    @Override
    public List<Hardware> findAll() {
        return jdbcTemplate.query("SELECT * FROM HARDWARE", new ItemMapper());
    }


    @Override
    public Optional<Hardware> findByCode(String code) {
        Map<String, Object>  parameters = new HashMap<>();
        parameters.put("code", code);
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM HARDWARE WHERE CODE = :code",
                new ItemMapper(), parameters));
    }

    @Override
    public List<Hardware> getAllHardware() {
        return jdbcTemplate.query("SELECT * FROM HARDWARE", new ItemMapper());
    }

    @Override
    public List<Hardware> getHardwareByCode(String hardwareCode) {
        return jdbcTemplate.query("SELECT * FROM HARDWARE WHERE CODE LIKE ?",
                new ItemMapper(), "%" + hardwareCode + "%");
    }



    @Override
    public Hardware saveNewHardware(Hardware hardware) {
        final String SQL =
                "SELECT ID FROM FINAL TABLE (INSERT INTO HARDWARE (code, name, price, typeId, amount) VALUES (?, ?, ?, ?, ?)) HARDWARE";
        Integer generatedId = jdbcTemplate.queryForObject(SQL, Integer.class,
                hardware.getCode(), hardware.getName(), hardware.getPrice(),
                hardware.getType().getId(), hardware.getAmount());
        hardware.setId(generatedId);
        return hardware;
    }


    @Override
    public Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id) {
        if(hardwareByIdExists(id)) {
            final String SQL =
                    "UPDATE HARDWARE SET code = ?, name = ?, price = ?, typeId = ?, amount = ? WHERE ID = ?";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL);
                ps.setString(1, hardwareToUpdate.getCode());
                ps.setString(2, hardwareToUpdate.getName());
                ps.setDouble(3, hardwareToUpdate.getPrice());
                ps.setInt(4, hardwareToUpdate.getType().getId());
                ps.setInt(5, hardwareToUpdate.getAmount());
                ps.setInt(6, id);
                return ps;
            });
            hardwareToUpdate.setId(id);
            return Optional.of(hardwareToUpdate);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT (*) FROM HARDWARE WHERE ID = ?", Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        if(hardwareByIdExists(id)) {
            jdbcTemplate.update(
                    "DELETE FROM HARDWARE WHERE ID = ?", id);
            return true;
        }
        else {
            return false;
        }
    }


    private static class ItemMapper implements RowMapper<Hardware> {

        public Hardware mapRow(ResultSet rs, int i) throws SQLException {

            Hardware newItem = new Hardware();
            newItem.setId(rs.getInt("ID"));
            newItem.setCode(rs.getString("CODE"));
            newItem.setName(rs.getString("NAME"));
            newItem.setPrice(rs.getDouble("PRICE"));

            Integer typeId = rs.getInt("TYPEID");

            if(ItemType.CPU.getId().equals(typeId)) {
                newItem.setType(ItemType.CPU);
            }
            else if(ItemType.GPU.getId().equals(typeId)) {
                newItem.setType(ItemType.GPU);
            }
            else if(ItemType.MBO.getId().equals(typeId)) {
                newItem.setType(ItemType.MBO);
            }
            else if(ItemType.RAM.getId().equals(typeId)) {
                newItem.setType(ItemType.RAM);
            }
            else if(ItemType.STORAGE.getId().equals(typeId)) {
                newItem.setType(ItemType.STORAGE);
            }
            else {
                newItem.setType(ItemType.OTHER);
            }

            newItem.setAmount(rs.getInt("AMOUNT"));
            return newItem;
        }
    }


}
