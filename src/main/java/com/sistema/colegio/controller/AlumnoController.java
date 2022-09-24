package com.sistema.colegio.controller;

import com.sistema.colegio.model.Alumno;
import com.sistema.colegio.service.AlumnoService;
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
@RequestMapping("/Alumno")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Alumno> alumnos = this.alumnoService.listarAlumno();


        return ResponseEntity.ok(alumnos);
		/*Collection<Alumno> alumno = alumnoService.findAll();
		return new ResponseEntity<>(alumno, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long alumnoId) {
        Alumno alumno = alumnoService.findById(alumnoId);
        if (alumno == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Alumno con ese id");
        }
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Alumno alumno) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            alumnoService.insert(alumno);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Alumno con codigo: " + alumno.getIdAlumno());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Alumno ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Alumno alumno) {



        alumnoService.actualizarAlumno(alumno);

        Map<String,String>respuesta = new HashMap<>();


        try {
            alumnoService.actualizarAlumno(alumno);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Alumno con codigo: " + alumno.getIdAlumno());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Alumnodo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idAlumno,
                                    @RequestBody Alumno alumno) {
        Alumno alumnoActual = alumnoService.findById(idAlumno);
//        if (alumnoActual != null) {
//            alumnoActual.setDescripcion(alumno.getDescripcion());
//            alumnoActual.setFechaInicio(alumno.getFechaInicio());
//            alumnoActual.setFechaFin(alumno.getFechaFin());
//            alumnoActual.setEstado(alumno.getEstado());
//            alumnoActual.setFechaRegistro(alumno.getFechaRegistro());
//            alumnoService.update(alumnoActual);
//            return new ResponseEntity<>(alumnoActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro alumno con ese id");

        //Alumno alumnoActual = alumnoService.findById(idAlumno);
        if (alumnoActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Alumno");
        this.alumnoService.update(alumno);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Alumno con codigo: "+ String.valueOf(idAlumno));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Alumno matriculaActual = alumnoService.findById(id);
        if (matriculaActual != null) {
            alumnoService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
