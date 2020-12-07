package com.luthfihariz.almasgocore.controller.v1.dashboard;


import com.luthfihariz.almasgocore.controller.v1.dto.mapper.EngineMapper;
import com.luthfihariz.almasgocore.controller.v1.dto.request.EngineRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.EngineResponseDto;
import com.luthfihariz.almasgocore.middleware.v1.EngineMiddleware;
import com.luthfihariz.almasgocore.model.Engine;
import com.luthfihariz.almasgocore.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard/v1/engine")
public class DashboardEngineController {

    @Autowired
    EngineMapper engineMapper;

    @Autowired
    EngineMiddleware engineMiddleware;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @PostMapping
    public EngineResponseDto addEngine(@RequestBody EngineRequestDto engineRequestDto) {
        Engine engine = engineMapper.fromRequestDto(engineRequestDto);
        Engine addedEngine = engineMiddleware.addEngine(engine, authenticationFacade.getAuthentication().getName());
        return engineMapper.toResponseDto(addedEngine);
    }

    @GetMapping("/{id}")
    public EngineResponseDto getEngine(@PathVariable("id") Long id) {
        var engine = engineMiddleware.getEngine(id);
        return engineMapper.toResponseDto(engine);
    }

    @GetMapping
    public List<EngineResponseDto> getPaginatedEngines(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "20") Integer size) {
        List<Engine> engines = engineMiddleware.getPaginatedEngineByUserId(
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
        engineMiddleware.removeEngine(engineId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
