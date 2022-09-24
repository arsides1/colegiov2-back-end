package com.sistema.colegio.controller;

import com.sistema.colegio.model.Nivel_Detalle;
import com.sistema.colegio.model.Periodo;
import com.sistema.colegio.service.Nivel_DetalleService;
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
@RequestMapping("/Nivel_Detalle")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Nivel_DetalleController {

    private final Nivel_DetalleService nivel_DetalleService;
    private final PeriodoService periodoService;
    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Nivel_Detalle> nivel_Detalles = this.nivel_DetalleService.listarNivel_Detalle();


        return ResponseEntity.ok(nivel_Detalles);
		/*Collection<Nivel_Detalle> nivel_Detalle = nivel_DetalleService.findAll();
		return new ResponseEntity<>(nivel_Detalle, HttpStatus.OK);*/
    }
    @GetMapping("/listar-Periodo")
    public ResponseEntity<?> listarPeriodo()  {

        List<Periodo> periodos = this.periodoService.listarPeriodo();


        return ResponseEntity.ok(periodos);
		/*Collection<Periodo> periodo = periodoService.findAll();
		return new ResponseEntity<>(periodo, HttpStatus.OK);*/
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long nivel_DetalleId) {
        Nivel_Detalle nivel_Detalle = nivel_DetalleService.findById(nivel_DetalleId);
        if (nivel_Detalle == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Nivel_Detalle con ese id");
        }
        return new ResponseEntity<>(nivel_Detalle, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Nivel_Detalle nivel_Detalle) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            nivel_DetalleService.insert(nivel_Detalle);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente  Nivel_Detalle con codigo: " + nivel_Detalle.getIdNivelDetalle());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Nivel_Detalle ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Nivel_Detalle nivel_Detalle) {



        Nivel_DetalleService.actualizarNivel_Detalle(nivel_Detalle);

        Map<String,String>respuesta = new HashMap<>();


        try {
            nivel_DetalleService.actualizarNivel_Detalle(nivel_Detalle);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Nivel_Detalle con codigo: " + nivel_Detalle.getIdNivel_Detalle());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Nivel_Detalledo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idNivel_Detalle,
                                    @RequestBody Nivel_Detalle nivel_Detalle) {
        Nivel_Detalle nivel_DetalleActual = nivel_DetalleService.findById(idNivel_Detalle);
//        if (nivel_DetalleActual != null) {
//            nivel_DetalleActual.setDescripcion(nivel_Detalle.getDescripcion());
//            nivel_DetalleActual.setFechaInicio(nivel_Detalle.getFechaInicio());
//            nivel_DetalleActual.setFechaFin(nivel_Detalle.getFechaFin());
//            nivel_DetalleActual.setEstado(nivel_Detalle.getEstado());
//            nivel_DetalleActual.setFechaRegistro(nivel_Detalle.getFechaRegistro());
//            nivel_DetalleService.update(nivel_DetalleActual);
//            return new ResponseEntity<>(nivel_DetalleActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Nivel_Detalle con ese id");

        //Nivel_Detalle nivel_DetalleActual = nivel_DetalleService.findById(idNivel_Detalle);
        if (nivel_DetalleActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Nivel_Detalle");
        this.nivel_DetalleService.update(nivel_Detalle);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Nivel_Detalle con codigo: "+ String.valueOf(idNivel_Detalle));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Nivel_Detalle nivel_DetalleActual = nivel_DetalleService.findById(id);
        if (nivel_DetalleActual != null) {
            nivel_DetalleService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
