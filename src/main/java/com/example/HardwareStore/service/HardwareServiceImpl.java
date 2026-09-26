package com.example.HardwareStore.service;

import com.example.HardwareStore.domain.Hardware;
import com.example.HardwareStore.domain.ItemType;
import com.example.HardwareStore.dto.HardwareDTO;
import com.example.HardwareStore.repository.HardwareRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class HardwareServiceImpl implements HardwareService {

    private HardwareRepository hardwareRepository;


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
        return convertHardwareToDTO(hardwareRepository.saveNewHardware(convertHardwareDtoToHardware(hardware)));
    }



    @Override
    public Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id) {
        Optional<Hardware> updatedHardwareOptional =
                hardwareRepository.updateHardware(convertHardwareDtoToHardware(hardwareDTO), id);
        if(updatedHardwareOptional.isPresent()) {
            return Optional.of(convertHardwareToDTO(updatedHardwareOptional.get()));
        }
        return Optional.empty();
    }



    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareRepository.hardwareByIdExists(id);
    }



    @Override
    public boolean deleteHardwareById(Integer hardwareId) {
        return hardwareRepository.deleteHardwareById(hardwareId);
    }

    @Override
    public List<HardwareDTO> getHardwareByCode(String hardwareCode) {
        return hardwareRepository.getHardwareByCode(hardwareCode).stream()
                .map(this::convertHardwareToDTO)
                .toList();
    }


    private Hardware convertHardwareDtoToHardware(HardwareDTO hardwareDTO) {
        Integer latestId =
                hardwareRepository.getAllHardware().stream()
                        .max((a1, a2) -> a1.getId().compareTo(a2.getId()))
                        .get().getId();

        return new Hardware(latestId + 1, hardwareDTO.getCode(), hardwareDTO.getName(), hardwareDTO.getPrice(),
                ItemType.valueOf(hardwareDTO.getType()), hardwareDTO.getAmount());
    }



    private HardwareDTO convertHardwareToDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getCode(), hardware.getName(),
                hardware.getPrice(), hardware.getType().toString(),
                hardware.getAmount());
    }

}