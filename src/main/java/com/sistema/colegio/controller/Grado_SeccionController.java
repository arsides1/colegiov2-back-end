package com.sistema.colegio.controller;

import com.sistema.colegio.model.Grado_Seccion;
import com.sistema.colegio.service.Grado_SeccionService;
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
@RequestMapping("/Grado_Seccion")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Grado_SeccionController {

    private final Grado_SeccionService grado_SeccionService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Grado_Seccion> grado_Seccions = this.grado_SeccionService.listarGrado_Seccion();


        return ResponseEntity.ok(grado_Seccions);
		/*Collection<Grado_Seccion> grado_Seccion = grado_SeccionService.findAll();
		return new ResponseEntity<>(Grado_Seccion, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long grado_SeccionId) {
        Grado_Seccion grado_Seccion = grado_SeccionService.findById(grado_SeccionId);
        if (grado_Seccion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Grado_Seccion con ese id");
        }
        return new ResponseEntity<>(grado_Seccion, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Grado_Seccion grado_Seccion) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            grado_SeccionService.insert(grado_Seccion);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Grado_Seccion con codigo: " + grado_Seccion.getIdGradoSeccion());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Grado_Seccion ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



    @PutMapping("/actualizar")
   @RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Grado_Seccion grado_Seccion) {



        grado_SeccionService.actualizarGrado_Seccion(grado_Seccion);

        Map<String,String>respuesta = new HashMap<>();


        try {
            grado_SeccionService.actualizarGrado_Seccion(grado_Seccion);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Grado_Seccion con codigo: " + grado_Seccion.getIdGradoSeccion());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Grado_Secciondo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }





 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idGrado_Seccion,
                                    @RequestBody Grado_Seccion grado_Seccion) {
        Grado_Seccion grado_SeccionActual = grado_SeccionService.findById(idGrado_Seccion);
//        if (grado_SeccionActual != null) {
//            grado_SeccionActual.setDescripcion(grado_Seccion.getDescripcion());
//            grado_SeccionActual.setFechaInicio(grado_Seccion.getFechaInicio());
//            grado_SeccionActual.setFechaFin(grado_Seccion.getFechaFin());
//            grado_SeccionActual.setEstado(grado_Seccion.getEstado());
//            grado_SeccionActual.setFechaRegistro(grado_Seccion.getFechaRegistro());
//            grado_SeccionService.update(grado_SeccionActual);
//            return new ResponseEntity<>(grado_SeccionActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Grado_Seccion con ese id");

        //Grado_Seccion grado_SeccionActual = grado_SeccionService.findById(idGrado_Seccion);
        if (grado_SeccionActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Grado_Seccion");
        this.grado_SeccionService.update(grado_Seccion);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Grado_Seccion con codigo: "+ String.valueOf(idGrado_Seccion));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Grado_Seccion grado_SeccionActual = grado_SeccionService.findById(id);
        if (grado_SeccionActual != null) {
            grado_SeccionService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
