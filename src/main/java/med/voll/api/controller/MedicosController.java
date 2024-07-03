package med.voll.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.models.DatosMedico;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
    @PostMapping()
    public void registrarMedigo(@RequestBody DatosMedico json){
        System.out.println(json);
    }
}
