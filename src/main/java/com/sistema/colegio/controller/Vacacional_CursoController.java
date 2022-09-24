package com.sistema.colegio.controller;

import com.sistema.colegio.model.Vacacional_Curso;
import com.sistema.colegio.service.Vacacional_CursoService;
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
@RequestMapping("/Vacacional_Curso")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Vacacional_CursoController {

    private final Vacacional_CursoService vacacional_CursoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Vacacional_Curso> vacacional_Cursos = this.vacacional_CursoService.listarVacacional_Curso();


        return ResponseEntity.ok(vacacional_Cursos);
		/*Collection<Vacacional_Curso> vacacional_Curso = vacacional_CursoService.findAll();
		return new ResponseEntity<>(vacacional_Curso, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long vacacional_CursoId) {
        Vacacional_Curso vacacional_Curso = vacacional_CursoService.findById(vacacional_CursoId);
        if (vacacional_Curso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Vacacional_Curso con ese id");
        }
        return new ResponseEntity<>(vacacional_Curso, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Vacacional_Curso vacacional_Curso) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            vacacional_CursoService.insert(vacacional_Curso);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente  Vacacional_Curso con codigo: " + vacacional_Curso.getIdVacionalCurso());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Vacacional_Curso ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Vacacional_Curso vacacional_Curso) {



        vacacional_CursoService.actualizarVacacional_Curso(vacacional_Curso);

        Map<String,String>respuesta = new HashMap<>();


        try {
            vacacional_CursoService.actualizarVacacional_Curso(vacacional_Curso);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Vacacional_Curso con codigo: " + vacacional_Curso.getIdVacacional_Curso());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Vacacional_Cursodo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idVacacional_Curso,
                                    @RequestBody Vacacional_Curso vacacional_Curso) {
        Vacacional_Curso vacacional_CursoActual = vacacional_CursoService.findById(idVacacional_Curso);
//        if (vacacional_CursoActual != null) {
//            vacacional_CursoActual.setDescripcion(vacacional_Curso.getDescripcion());
//            vacacional_CursoActual.setFechaInicio(vacacional_Curso.getFechaInicio());
//            vacacional_CursoActual.setFechaFin(vacacional_Curso.getFechaFin());
//            vacacional_CursoActual.setEstado(vacacional_Curso.getEstado());
//            vacacional_CursoActual.setFechaRegistro(vacacional_Curso.getFechaRegistro());
//            vacacional_CursoService.update(vacacional_CursoActual);
//            return new ResponseEntity<>(vacacional_CursoActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Vacacional_Curso con ese id");

        //Vacacional_Curso vacacional_CursoActual = vacacional_CursoService.findById(idVacacional_Curso);
        if (vacacional_CursoActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Vacacional_Curso");
        this.vacacional_CursoService.update(vacacional_Curso);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Vacacional_Curso con codigo: "+ String.valueOf(idVacacional_Curso));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Vacacional_Curso matriculaActual = vacacional_CursoService.findById(id);
        if (matriculaActual != null) {
            vacacional_CursoService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
