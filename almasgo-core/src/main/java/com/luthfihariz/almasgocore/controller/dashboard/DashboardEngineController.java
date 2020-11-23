package com.luthfihariz.almasgocore.controller.dashboard;


import com.luthfihariz.almasgocore.controller.dto.mapper.EngineMapper;
import com.luthfihariz.almasgocore.controller.dto.request.EngineRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.EngineResponseDto;
import com.luthfihariz.almasgocore.model.Engine;
import com.luthfihariz.almasgocore.security.AuthenticationFacade;
import com.luthfihariz.almasgocore.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard/engine")
public class DashboardEngineController {

    @Autowired
    EngineMapper engineMapper;

    @Autowired
    EngineService engineService;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @PostMapping
    public EngineResponseDto addEngine(@RequestBody EngineRequestDto engineRequestDto) {
        Engine engine = engineMapper.fromRequestDto(engineRequestDto);
        Engine addedEngine = engineService.addEngine(engine, authenticationFacade.getAuthentication().getName());
        return engineMapper.toResponseDto(addedEngine);
    }

    @GetMapping("/{id}")
    public EngineResponseDto getEngine(@PathVariable("id") Long id) {
        return engineMapper.toResponseDto(engineService.getEngine(id));
    }

    @GetMapping
    public List<EngineResponseDto> getPaginatedEngines(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "20") Integer size) {
        List<Engine> engines = engineService.getPaginatedEngineByUserId(
                authenticationFacade.getAuthentication().getName(),
                page,
                size);

        if (engines.isEmpty()) {
            return Collections.emptyList();
        }

        return engines.stream().map(engine -> engineMapper.toResponseDto(engine)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeEngine(@PathVariable("id") Long engineId) {
        engineService.removeEngine(engineId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
