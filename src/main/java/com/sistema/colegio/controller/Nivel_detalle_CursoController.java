package com.sistema.colegio.controller;

import com.sistema.colegio.model.Nivel_detalle_Curso;
import com.sistema.colegio.service.Nivel_detalle_CursoService;
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
@RequestMapping("/Nivel_detalle_Curso")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Nivel_detalle_CursoController {

    private final Nivel_detalle_CursoService nivel_detalle_CursoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Nivel_detalle_Curso> nivel_detalle_Cursos = this.nivel_detalle_CursoService.listarNivel_detalle_Curso();


        return ResponseEntity.ok(nivel_detalle_Cursos);
		/*Collection<Nivel_detalle_Curso> nivel_detalle_Curso = nivel_detalle_CursoService.findAll();
		return new ResponseEntity<>(nivel_detalle_Curso, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long nivel_detalle_CursoId) {
        Nivel_detalle_Curso nivel_detalle_Curso = nivel_detalle_CursoService.findById(nivel_detalle_CursoId);
        if (nivel_detalle_Curso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro nivel_detalle_Curso con ese id");
        }
        return new ResponseEntity<>(nivel_detalle_Curso, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Nivel_detalle_Curso nivel_detalle_Curso) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            nivel_detalle_CursoService.insert(nivel_detalle_Curso);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente Nivel_detalle_Curso con codigo: " + nivel_detalle_Curso.getIdNivelDetalleCurso());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Nivel_detalle_Curso ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Nivel_detalle_Curso nivel_detalle_Curso) {



        nivel_detalle_CursoService.actualizar(nivel_detalle_Curso);

        Map<String,String>respuesta = new HashMap<>();


        try {
            nivel_detalle_CursoService.actualizarNivel_detalle_Curso(nivel_detalle_Curso);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Nivel_detalle_Curso con codigo: " + nivel_detalle_Curso.getIdNivelDetalleCurso());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Nivel_detalle_Cursodo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
*/




 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idNivel_detalle_Curso,
                                    @RequestBody Nivel_detalle_Curso nivel_detalle_Curso) {
        Nivel_detalle_Curso nivel_detalle_CursoActual = nivel_detalle_CursoService.findById(idNivel_detalle_Curso);
//        if (nivel_detalle_CursoActual != null) {
//            nivel_detalle_CursoActual.setDescripcion(nivel_detalle_Curso.getDescripcion());
//            nivel_detalle_CursoActual.setFechaInicio(nivel_detalle_Curso.getFechaInicio());
//            nivel_detalle_CursoActual.setFechaFin(nivel_detalle_Curso.getFechaFin());
//            nivel_detalle_CursoActual.setEstado(nivel_detalle_Curso.getEstado());
//            nivel_detalle_CursoActual.setFechaRegistro(nivel_detalle_Curso.getFechaRegistro());
//            nivel_detalle_CursoService.update(nivel_detalle_CursoActual);
//            return new ResponseEntity<>(nivel_detalle_CursoActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro nivel_detalle_Curso con ese id");

        //Nivel_detalle_Curso nivel_detalle_CursoActual = nivel_detalle_CursoService.findById(idNivel_detalle_Curso);
     Map<String, String> respuesta = new HashMap<>();
        if (nivel_detalle_CursoActual != null) {
            this.nivel_detalle_CursoService.update(nivel_detalle_Curso);

            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta", "Se Actualizo satisfactoriamente los datos del Nivel_detalle_Curso con codigo: " + String.valueOf(idNivel_detalle_Curso));
            return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Nivel_detalle_Curso");

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Nivel_detalle_Curso matriculaActual = nivel_detalle_CursoService.findById(id);
        if (matriculaActual != null) {
            nivel_detalle_CursoService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
