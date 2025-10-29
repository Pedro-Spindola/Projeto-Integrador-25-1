package com.lpo.greenlog.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpo.greenlog.model.TipoResiduo;

@RestController
@RequestMapping("/tipos-residuo")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoResiduoController {

    @GetMapping
    public List<String> listarTipos() {
        return Arrays.stream(TipoResiduo.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
