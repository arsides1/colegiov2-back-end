package com.sistema.colegio.controller;

import com.sistema.colegio.model.Asistencia;
import com.sistema.colegio.service.AsistenciaService;
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
@RequestMapping("/Asistencia")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AsitenciaController {

    private final AsistenciaService asistenciaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Asistencia> asistencias = this.asistenciaService.listarAsistencia();


        return ResponseEntity.ok(asistencias);
		/*Collection<Asistencia> asistencia = asistenciaService.findAll();
		return new ResponseEntity<>(asistencia, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long asistenciaId) {
        Asistencia asistencia = asistenciaService.findById(asistenciaId);
        if (asistencia == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Asistencia con ese id");
        }
        return new ResponseEntity<>(asistencia, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Asistencia asistencia) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            asistenciaService.insert(asistencia);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Asistencia con codigo: " + asistencia.getIdAsistencia());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Asistencia ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Asistencia asistencia) {



        asistenciaService.actualizarAsistencia(Asistencia);

        Map<String,String>respuesta = new HashMap<>();


        try {
            asistenciaService.actualizarAsistencia(asistencia);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Asistencia con codigo: " + asistencia.getIdAsistencia());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Asistenciado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idAsistencia,
                                    @RequestBody Asistencia asistencia) {
        Asistencia asistenciaActual = asistenciaService.findById(idAsistencia);
//        if (asistenciaActual != null) {
//            asistenciaActual.setDescripcion(asistencia.getDescripcion());
//            asistenciaActual.setFechaInicio(asistencia.getFechaInicio());
//            asistenciaActual.setFechaFin(asistencia.getFechaFin());
//            asistenciaActual.setEstado(asistencia.getEstado());
//            asistenciaActual.setFechaRegistro(asistencia.getFechaRegistro());
//            asistenciaService.update(asistenciaActual);
//            return new ResponseEntity<>(asistenciaActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Asistencia con ese id");

        //Asistencia asistenciaActual = asistenciaService.findById(idAsistencia);
        if (asistenciaActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Asistencia");
        this.asistenciaService.update(asistencia);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Asistencia con codigo: "+ String.valueOf(idAsistencia));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Asistencia asistenciaActual = asistenciaService.findById(id);
        if (asistenciaActual != null) {
            asistenciaService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
