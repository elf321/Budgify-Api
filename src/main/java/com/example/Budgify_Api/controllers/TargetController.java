package com.example.Budgify_Api.controllers;

import com.example.Budgify_Api.entities.Target;
import com.example.Budgify_Api.services.target.TargetService;
import com.example.Budgify_Api.services.target.dtos.TargetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/targets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TargetController {

    private final TargetService targetService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TargetDTO.StatusView>> getTargetStatusList(@PathVariable Long userId) {
        List<TargetDTO.StatusView> targetStatusList = targetService.getTargetStatusList(userId);
        return ResponseEntity.ok(targetStatusList);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Target> createTarget(
            @PathVariable Long userId,
            @RequestBody TargetDTO.Create createDTO) {

        Target createdTarget = targetService.createTarget(userId, createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTarget);
    }
}