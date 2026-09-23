package com.example.HardwareStore.controller;

import com.example.HardwareStore.dto.HardwareDTO;
import com.example.HardwareStore.service.HardwareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;


import java.util.Collections;
import java.util.List;



@RestController
@RequestMapping("hardware")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAll() {
        return hardwareService.findAll();
    }


    @GetMapping("/{hardwareCode}")
    public ResponseEntity<List<HardwareDTO>> filterHardwareByCode(@PathVariable String hardwareCode) {
        return ResponseEntity.ok(Collections.singletonList(hardwareService.findByCode(hardwareCode)));
    }

    @PostMapping("/new")
    public ResponseEntity<Void> saveNewHardware(@Valid @RequestBody HardwareDTO hardwareDTO) {
        hardwareService.saveNewHardware(hardwareDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public ResponseEntity<?> deleteArticle(@PathVariable Integer hardwareId) {
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

