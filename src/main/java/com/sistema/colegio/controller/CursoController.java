package com.sistema.colegio.controller;

import com.sistema.colegio.model.Curso;
import com.sistema.colegio.service.CursoService;
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
@RequestMapping("/Curso")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Curso> cursos = this.cursoService.listarCurso();


        return ResponseEntity.ok(cursos);
		/*Collection<Curso> curso = cursoService.findAll();
		return new ResponseEntity<>(curso, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long cursoId) {
        Curso curso = cursoService.findById(cursoId);
        if (curso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Curso con ese id");
        }
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Curso curso) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            cursoService.insert(curso);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Curso con codigo: " + curso.getIdCurso());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Curso ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Curso curso) {



        cursoService.actualizarCurso(curso);

        Map<String,String>respuesta = new HashMap<>();


        try {
            cursoService.actualizarCurso(curso);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Curso con codigo: " + curso.getIdCurso());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Cursodo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idCurso,
                                    @RequestBody Curso curso) {
        Curso cursoActual = cursoService.findById(idCurso);
//        if (cursoActual != null) {
//            cursoActual.setDescripcion(curso.getDescripcion());
//            cursoActual.setFechaInicio(curso.getFechaInicio());
//            cursoActual.setFechaFin(curso.getFechaFin());
//            cursoActual.setEstado(curso.getEstado());
//            cursoActual.setFechaRegistro(curso.getFechaRegistro());
//            cursoService.update(cursoActual);
//            return new ResponseEntity<>(cursoActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Curso con ese id");

        //Curso cursoActual = cursoService.findById(idCurso);
        if (cursoActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Curso");
        this.cursoService.update(curso);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Curso con codigo: "+ String.valueOf(idCurso));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Curso cursoActual = cursoService.findById(id);
        if (cursoActual != null) {
            cursoService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
