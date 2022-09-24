package com.sistema.colegio.controller;

import com.sistema.colegio.model.Periodo;
import com.sistema.colegio.service.PeriodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/periodo")
//@CrossOrigin(origins = "*", allowedHeaders = e"*")
public class PeriodoController {

    private final PeriodoService periodoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Periodo> periodos = this.periodoService.listarPeriodo();


        return ResponseEntity.ok(periodos);
		/*Collection<Periodo> periodo = periodoService.findAll();
		return new ResponseEntity<>(periodo, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long periodoId) {
        Periodo periodo = periodoService.findById(periodoId);
        if (periodo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro periodo con ese id");
        }
        return new ResponseEntity<>(periodo, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Periodo periodo) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            periodoService.insert(periodo);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Periodo con codigo: " + periodo.getIdPeriodo());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El periodo ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



    @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Periodo periodo) {



        periodoService.actualizarPeriodo(periodo);

        Map<String,String>respuesta = new HashMap<>();


        try {
            periodoService.actualizarPeriodo(periodo);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Periodo con codigo: " + periodo.getIdPeriodo());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Periododo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }





 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idPeriodo,
                                    @RequestBody Periodo periodo) {
        Periodo periodoActual = periodoService.findById(idPeriodo);
//        if (periodoActual != null) {
//            periodoActual.setDescripcion(periodo.getDescripcion());
//            periodoActual.setFechaInicio(periodo.getFechaInicio());
//            periodoActual.setFechaFin(periodo.getFechaFin());
//            periodoActual.setEstado(periodo.getEstado());
//            periodoActual.setFechaRegistro(periodo.getFechaRegistro());
//            periodoService.update(periodoActual);
//            return new ResponseEntity<>(periodoActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro periodo con ese id");

        //Periodo periodoActual = periodoService.findById(idPeriodo);
     Map<String, String> respuesta = new HashMap<>();
        if (periodoActual != null) {
            this.periodoService.update(periodo);

            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta", "Se Actualizo satisfactoriamente los datos del Periodo con codigo: " + String.valueOf(idPeriodo));
            return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Periodo");

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Periodo matriculaActual = periodoService.findById(id);
        if (matriculaActual != null) {
            periodoService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
