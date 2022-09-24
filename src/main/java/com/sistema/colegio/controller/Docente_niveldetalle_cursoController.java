package com.sistema.colegio.controller;

import com.sistema.colegio.model.Docente_niveldetalle_curso;
import com.sistema.colegio.service.Docente_niveldetalle_cursoService;
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
@RequestMapping("/Docente_niveldetalle_curso")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Docente_niveldetalle_cursoController {

    private final Docente_niveldetalle_cursoService docente_niveldetalle_cursoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Docente_niveldetalle_curso> docente_niveldetalle_cursos = this.docente_niveldetalle_cursoService.listarDocente_niveldetalle_curso();


        return ResponseEntity.ok(docente_niveldetalle_cursos);
		/*Collection<Docente_niveldetalle_curso> docente_niveldetalle_curso = docente_niveldetalle_cursoService.findAll();
		return new ResponseEntity<>(docente_niveldetalle_curso, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long docente_niveldetalle_cursoId) {
        Docente_niveldetalle_curso docente_niveldetalle_curso = docente_niveldetalle_cursoService.findById(docente_niveldetalle_cursoId);
        if (docente_niveldetalle_curso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Docente_niveldetalle_curso con ese id");
        }
        return new ResponseEntity<>(docente_niveldetalle_curso, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Docente_niveldetalle_curso docente_niveldetalle_curso) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            docente_niveldetalle_cursoService.insert(docente_niveldetalle_curso);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Docente_niveldetalle_curso con codigo: " + docente_niveldetalle_curso.getIdDocenteniveldetallecurso());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Docente_niveldetalle_curso ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Docente_niveldetalle_curso docente_niveldetalle_curso) {



        docente_niveldetalle_cursoService.actualizarDocente_niveldetalle_curso(docente_niveldetalle_curso);

        Map<String,String>respuesta = new HashMap<>();


        try {
            docente_niveldetalle_cursoService.actualizarDocente_niveldetalle_curso(docente_niveldetalle_curso);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Docente_niveldetalle_curso con codigo: " + docente_niveldetalle_curso.getIdDocente_niveldetalle_curso());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Docente_niveldetalle_cursodo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idDocente_niveldetalle_curso,
                                    @RequestBody Docente_niveldetalle_curso docente_niveldetalle_curso) {
        Docente_niveldetalle_curso docente_niveldetalle_cursoActual = docente_niveldetalle_cursoService.findById(idDocente_niveldetalle_curso);
//        if (docente_niveldetalle_cursoActual != null) {
//            docente_niveldetalle_cursoActual.setDescripcion(docente_niveldetalle_curso.getDescripcion());
//            docente_niveldetalle_cursoActual.setFechaInicio(docente_niveldetalle_curso.getFechaInicio());
//            docente_niveldetalle_cursoActual.setFechaFin(docente_niveldetalle_curso.getFechaFin());
//            docente_niveldetalle_cursoActual.setEstado(docente_niveldetalle_curso.getEstado());
//            docente_niveldetalle_cursoActual.setFechaRegistro(docente_niveldetalle_curso.getFechaRegistro());
//            docente_niveldetalle_cursoService.update(docente_niveldetalle_cursoActual);
//            return new ResponseEntity<>(docente_niveldetalle_cursoActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Docente_niveldetalle_curso con ese id");

        //Docente_niveldetalle_curso docente_niveldetalle_cursoActual = docente_niveldetalle_cursoService.findById(idDocente_niveldetalle_curso);
        if (docente_niveldetalle_cursoActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Docente_niveldetalle_curso");
        this.docente_niveldetalle_cursoService.update(docente_niveldetalle_curso);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Docente_niveldetalle_curso con codigo: "+ String.valueOf(idDocente_niveldetalle_curso));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Docente_niveldetalle_curso docente_niveldetalle_cursoActual = docente_niveldetalle_cursoService.findById(id);
        if (docente_niveldetalle_cursoActual != null) {
            docente_niveldetalle_cursoService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
