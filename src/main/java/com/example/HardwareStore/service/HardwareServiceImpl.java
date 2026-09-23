package com.example.HardwareStore.service;

import com.example.HardwareStore.domain.Hardware;
import com.example.HardwareStore.domain.Type;
import com.example.HardwareStore.dto.HardwareDTO;
import com.example.HardwareStore.repository.HardwareRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(name -> new HardwareDTO(name)).collect(Collectors.toList());
    }

    @Override
    public HardwareDTO findByCode(String code) {
        return hardwareRepository.findByCode(code).map(name -> new HardwareDTO(name)).orElse(null);
    }

    @Override
    public Integer saveNewHardware(HardwareDTO hardware) {
        return hardwareRepository.saveNewHardware(convertHardwareDtoToHardware(hardware));
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
        return  hardwareRepository.deleteHardwareById(hardwareId);
    }

    private Hardware convertHardwareDtoToHardware(HardwareDTO hardwareDTO) {
        Integer latestId =
                hardwareRepository.getAllHardware().stream()
                        .max((a1, a2) -> a1.getId().compareTo(a2.getId()))
                        .get().getId();

        return new Hardware(latestId + 1, hardwareDTO.getCode(), hardwareDTO.getName(), hardwareDTO.getPrice(),
                Type.valueOf(hardwareDTO.getType()) , hardwareDTO.getAmount());
    }



    private HardwareDTO convertHardwareToDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getCode(), hardware.getName(),
                hardware.getPrice(), hardware.getType().toString(),
                hardware.getAmount());
    }

}