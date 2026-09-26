package com.example.HardwareStore.controller;

import com.example.HardwareStore.dto.HardwareDTO;
import com.example.HardwareStore.service.HardwareService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;



@RestController
@RequestMapping("hardware")
@AllArgsConstructor
public class HardwareController {



    private HardwareService hardwareService;

    @GetMapping
    public ResponseEntity<List<HardwareDTO>> findAll() {
        return ResponseEntity.ok(hardwareService.findAll().stream().toList());
    }

    @GetMapping("/{hardwareCode}")
    public ResponseEntity<List<HardwareDTO>> filterHardwareByCode(@PathVariable String hardwareCode) {
        return ResponseEntity.ok(hardwareService.getHardwareByCode(hardwareCode));
    }


    @PostMapping("/new")
    public ResponseEntity<?> saveNewHardware(@Valid @RequestBody HardwareDTO hardwareDTO) {
           HardwareDTO savedHardwareDTO = hardwareService.saveNewHardware(hardwareDTO);
           return ResponseEntity.ok(savedHardwareDTO);
    }


    @PutMapping("/{hardwareId}")
    public ResponseEntity<HardwareDTO> updateItem(@Valid @RequestBody HardwareDTO hardwareDTO, @PathVariable Integer hardwareId) {

        if (hardwareService.hardwareByIdExists(hardwareId)) {
            hardwareService.updateHardware(hardwareDTO, hardwareId);
            return ResponseEntity.ok(hardwareDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{hardwareId}")
    public ResponseEntity<?> deleteHardware(@PathVariable Integer hardwareId) {
        if (hardwareService.hardwareByIdExists(hardwareId)) {
            boolean result = hardwareService.deleteHardwareById(hardwareId);
            if (result) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

