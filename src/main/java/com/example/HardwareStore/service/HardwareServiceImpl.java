package com.example.HardwareStore.service;

import com.example.HardwareStore.domain.Hardware;
import com.example.HardwareStore.domain.ItemType;
import com.example.HardwareStore.dto.HardwareDTO;
import com.example.HardwareStore.repository.SpringDataHardwareRepository;
import com.example.HardwareStore.repository.SpringDataTypeRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class HardwareServiceImpl implements HardwareService {

    private SpringDataHardwareRepository hardwareRepository;
    private SpringDataTypeRepository typeRepository;


    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().
                map(this::convertHardwareToDTO).
                toList();
    }

    @Override
    public HardwareDTO findByCode(String code) {
        return null;
    }


    @Override
    public HardwareDTO saveNewHardware(HardwareDTO hardware) {
        return convertHardwareToDTO(hardwareRepository.save(convertHardwareDtoToHardware(hardware)));
    }



    @Override
    public Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id) {
        Optional<Hardware> hardwareToUpdate = hardwareRepository.findById(id);
        if (hardwareToUpdate.isPresent()) {
            Hardware hardware = hardwareToUpdate.get();

            ItemType type = typeRepository.findByName(hardwareDTO.getTypeName());
            if (type == null) {
                return Optional.empty();
            }
            hardware.setType(type);

            hardware.setAmount(hardwareDTO.getAmount());
            hardware.setPrice(hardwareDTO.getPrice());
            hardware.setName(hardwareDTO.getName());
            hardware.setCode(hardwareDTO.getCode());
            Hardware updatedHardware = hardwareRepository.save(hardware);
            return Optional.of(convertHardwareToDTO(updatedHardware));
        }
        return Optional.empty();
    }


    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareRepository.findById(id).isPresent();
    }


    @Override
    public boolean deleteHardwareById(Integer hardwareId) {
        if(hardwareByIdExists(hardwareId)) {
            hardwareRepository.deleteById(hardwareId);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<HardwareDTO> getHardwareByCode(String hardwareCode) {
        return hardwareRepository.getHardwareByCode(hardwareCode).stream()
                .map(this::convertHardwareToDTO)
                .toList();
    }


    private Hardware convertHardwareDtoToHardware(HardwareDTO dto) {
        ItemType type = typeRepository.findByName(dto.getTypeName());
        return new Hardware(null, dto.getCode(), dto.getName(), dto.getPrice(),
                type, dto.getAmount());
    }


    private HardwareDTO convertHardwareToDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getCode(), hardware.getName(),
                hardware.getPrice(), hardware.getType().getName(),
                hardware.getAmount());
    }

}