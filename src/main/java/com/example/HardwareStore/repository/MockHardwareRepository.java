package com.example.HardwareStore.repository;

import com.example.HardwareStore.domain.Hardware;
import com.example.HardwareStore.domain.Type;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MockHardwareRepository implements HardwareRepository {

    private static List<Hardware> hardwareList;

    static {

        hardwareList = new ArrayList<>();

        Hardware firstHardware = new Hardware(1, "3437932", "AMD RYZEN 7", 400, Type.CPU, 5);
        Hardware secondHardware = new Hardware(2, "4534325",  "Intel Core Ultra 7", 250, Type.CPU, 4);
        Hardware thirdHardware = new Hardware(3, "4592351",  " G.Skill Trident Z5 RGB DDR5-6000", 600, Type.RAM, 7);
        Hardware fourthHardware = new Hardware(4, "6789026",  "GeForce RTX 5090", 1900, Type.GPU, 12);

        hardwareList.add(firstHardware);
        hardwareList.add(secondHardware);
        hardwareList.add(thirdHardware);
        hardwareList.add(fourthHardware);
    }


    @Override
    public List<Hardware> getAllHardware() {
        return hardwareList;
    }


    @Override
    public List<Hardware> getHardwareByCode(String hardwareCode) {
        return hardwareList.stream()
                .filter(a -> a.getCode().toLowerCase().contains(hardwareCode.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Override
    public Integer saveNewHardware(Hardware hardware) {
      Integer generatedId = hardwareList.size() + 1;
      hardware.setId(generatedId);
      hardwareList.add(hardware);
      return generatedId;
    }


    @Override
    public List<Hardware> findAll() {
        return hardwareList;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return hardwareList.stream().filter(hardware -> Objects.equals(hardware.getCode(), code)).findAny();
    }


    @Override
    public Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id) {
        Optional<Hardware> storedHardwareOptional = hardwareList.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (storedHardwareOptional.isPresent()) {
            Hardware storedHardware = storedHardwareOptional.get();

            storedHardware.setCode(hardwareToUpdate.getCode());
            storedHardware.setName(hardwareToUpdate.getName());
            storedHardware.setPrice(hardwareToUpdate.getPrice());
            storedHardware.setType(hardwareToUpdate.getType());
            storedHardware.setAmount(hardwareToUpdate.getAmount());

            return Optional.of(storedHardware);
        }

        return Optional.empty();
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareList.stream().filter(a -> a.getId().equals(id)).findFirst().isPresent();
    }


    @Override
    public boolean deleteHardwareById(Integer id) {
        return hardwareList.removeIf(hardware -> hardware.getId().equals(id));
    }





}