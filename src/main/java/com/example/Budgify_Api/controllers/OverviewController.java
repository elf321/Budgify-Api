package com.example.Budgify_Api.controllers;

import com.example.Budgify_Api.services.overview.OverviewService;
import com.example.Budgify_Api.services.overview.dtos.OverviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/overview")
@RequiredArgsConstructor
public class OverviewController {

    private final OverviewService overviewService;
    @GetMapping("/user/{userId}")
    public ResponseEntity<OverviewDTO.Overview> getOverview(@PathVariable Long userId) {
        return ResponseEntity.ok(overviewService.getOverview(userId));
    }
}