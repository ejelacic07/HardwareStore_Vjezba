package com.example.HardwareStore.repository;

import com.example.HardwareStore.domain.Hardware;


import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    List<Hardware> getAllHardware();

    List<Hardware> getHardwareByCode(String hardwareCode);

    Hardware saveNewHardware(Hardware hardware);

    Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id);

    boolean hardwareByIdExists(Integer id);

    boolean deleteHardwareById(Integer id);

}
