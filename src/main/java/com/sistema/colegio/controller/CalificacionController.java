package com.sistema.colegio.controller;

import com.sistema.colegio.model.Calificacion;
import com.sistema.colegio.service.CalificacionService;
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
@RequestMapping("/Calificacion")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CalificacionController {

    private final CalificacionService calificacionService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Calificacion> calificacions = this.calificacionService.listarCalificacion();


        return ResponseEntity.ok(calificacions);
		/*Collection<Calificacion> calificacion = calificacionService.findAll();
		return new ResponseEntity<>(calificacion, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long calificacionId) {
        Calificacion calificacion = calificacionService.findById(calificacionId);
        if (calificacion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Calificacion con ese id");
        }
        return new ResponseEntity<>(calificacion, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Calificacion calificacion) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            calificacionService.insert(calificacion);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Calificacion con codigo: " + calificacion.getIdCalificacion());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Calificacion ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Calificacion calificacion) {



        calificacionService.actualizarCalificacion(calificacion);

        Map<String,String>respuesta = new HashMap<>();


        try {
            calificacionService.actualizarCalificacion(calificacion);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Calificacion con codigo: " + calificacion.getIdCalificacion());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Calificaciondo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idCalificacion,
                                    @RequestBody Calificacion calificacion) {
        Calificacion calificacionActual = calificacionService.findById(idCalificacion);
//        if (calificacionActual != null) {
//            calificacionActual.setDescripcion(calificacion.getDescripcion());
//            calificacionActual.setFechaInicio(calificacion.getFechaInicio());
//            calificacionActual.setFechaFin(calificacion.getFechaFin());
//            calificacionActual.setEstado(calificacion.getEstado());
//            calificacionActual.setFechaRegistro(calificacion.getFechaRegistro());
//            calificacionService.update(calificacionActual);
//            return new ResponseEntity<>(calificacionActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Calificacion con ese id");

        //Calificacion calificacionActual = calificacionService.findById(idCalificacion);
        if (calificacionActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Calificacion");
        this.calificacionService.update(calificacion);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Calificacion con codigo: "+ String.valueOf(idCalificacion));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Calificacion calificacionActual = calificacionService.findById(id);
        if (calificacionActual != null) {
            calificacionService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
