package com.sistema.colegio.controller;

import com.sistema.colegio.model.Curricula;
import com.sistema.colegio.service.CurriculaService;
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
@RequestMapping("/Curricula")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CurriculaController {

    private final CurriculaService curriculaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Curricula> curriculas = this.curriculaService.listarCurricula();


        return ResponseEntity.ok(curriculas);
		/*Collection<Curricula> curricula = curriculaService.findAll();
		return new ResponseEntity<>(curricula, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long curriculaId) {
        Curricula curricula = curriculaService.findById(curriculaId);
        if (curricula == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Curricula con ese id");
        }
        return new ResponseEntity<>(curricula, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Curricula curricula) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            curriculaService.insert(curricula);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Curricula con codigo: " + curricula.getIdCurricula());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Curricula ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Curricula curricula) {



        curriculaService.actualizarCurricula(curricula);

        Map<String,String>respuesta = new HashMap<>();


        try {
            curriculaService.actualizarCurricula(curricula);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Curricula con codigo: " + curricula.getIdCurricula());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Curriculado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idCurricula,
                                    @RequestBody Curricula curricula) {
        Curricula curriculaActual = curriculaService.findById(idCurricula);
//        if (curriculaActual != null) {
//            curriculaActual.setDescripcion(curricula.getDescripcion());
//            curriculaActual.setFechaInicio(curricula.getFechaInicio());
//            curriculaActual.setFechaFin(curricula.getFechaFin());
//            curriculaActual.setEstado(curricula.getEstado());
//            curriculaActual.setFechaRegistro(curricula.getFechaRegistro());
//            curriculaService.update(curriculaActual);
//            return new ResponseEntity<>(curriculaActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Curricula con ese id");

        //Curricula curriculaActual = curriculaService.findById(idCurricula);
        if (curriculaActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Curricula");
        this.curriculaService.update(curricula);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Curricula con codigo: "+ String.valueOf(idCurricula));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Curricula curriculaActual = curriculaService.findById(id);
        if (curriculaActual != null) {
            curriculaService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
