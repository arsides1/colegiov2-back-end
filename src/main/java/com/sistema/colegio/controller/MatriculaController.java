package com.sistema.colegio.controller;

import com.sistema.colegio.model.Matricula;
import com.sistema.colegio.service.MatriculaService;
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
@RequestMapping("/Matricula")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Matricula> matriculas = this.matriculaService.listarMatricula();


        return ResponseEntity.ok(matriculas);
		/*Collection<Matricula> matricula = matriculaService.findAll();
		return new ResponseEntity<>(matricula, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long matriculaId) {
        Matricula matricula = matriculaService.findById(matriculaId);
        if (matricula == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Matricula con ese id");
        }
        return new ResponseEntity<>(matricula, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Matricula matricula) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            matriculaService.insert(matricula);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Matricula con codigo: " + matricula.getIdMatricula());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Matricula ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Matricula matricula) {



        matriculaService.actualizarMatricula(matricula);

        Map<String,String>respuesta = new HashMap<>();


        try {
            matriculaService.actualizarMatricula(matricula);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Matricula con codigo: " + matricula.getIdMatricula());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Matriculado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idMatricula,
                                    @RequestBody Matricula matricula) {
        Matricula matriculaActual = matriculaService.findById(idMatricula);
//        if (matriculaActual != null) {
//            matriculaActual.setDescripcion(matricula.getDescripcion());
//            matriculaActual.setFechaInicio(matricula.getFechaInicio());
//            matriculaActual.setFechaFin(matricula.getFechaFin());
//            matriculaActual.setEstado(matricula.getEstado());
//            matriculaActual.setFechaRegistro(matricula.getFechaRegistro());
//            matriculaService.update(matriculaActual);
//            return new ResponseEntity<>(matriculaActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Matricula con ese id");

        //Matricula matriculaActual = matriculaService.findById(idMatricula);
        if (matriculaActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Matricula");
        this.matriculaService.update(matricula);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Matricula con codigo: "+ String.valueOf(idMatricula));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Matricula matriculaActual = matriculaService.findById(id);
        if (matriculaActual != null) {
            matriculaService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
