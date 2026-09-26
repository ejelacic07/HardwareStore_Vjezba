package com.example.HardwareStore.service;
import com.example.HardwareStore.domain.Hardware;
import com.example.HardwareStore.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    HardwareDTO findByCode(String code);

    HardwareDTO saveNewHardware(HardwareDTO hardware);

    Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id);

    boolean hardwareByIdExists(Integer id);

    boolean deleteHardwareById(Integer hardwareId);


    List<HardwareDTO> getHardwareByCode(String hardwareCode);
}
