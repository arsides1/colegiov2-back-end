package com.sistema.colegio.controller;

import com.sistema.colegio.model.Docente;
import com.sistema.colegio.service.DocenteService;
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
@RequestMapping("/Docente")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DocenteController {

    private final DocenteService docenteService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Docente> docentes = this.docenteService.listarDocente();


        return ResponseEntity.ok(docentes);
		/*Collection<Docente> docente = docenteService.findAll();
		return new ResponseEntity<>(docente, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long docenteId) {
        Docente docente = docenteService.findById(docenteId);
        if (docente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Docente con ese id");
        }
        return new ResponseEntity<>(docente, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Docente docente) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            docenteService.insert(docente);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Docente con codigo: " + docente.getIdDocente());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Docente ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Docente docente) {



        docenteService.actualizarDocente(docente);

        Map<String,String>respuesta = new HashMap<>();


        try {
            docenteService.actualizarDocente(docente);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Docente con codigo: " + docente.getIdDocente());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Docentedo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idDocente,
                                    @RequestBody Docente docente) {
        Docente docenteActual = docenteService.findById(idDocente);
//        if (docenteActual != null) {
//            docenteActual.setDescripcion(docente.getDescripcion());
//            docenteActual.setFechaInicio(docente.getFechaInicio());
//            docenteActual.setFechaFin(docente.getFechaFin());
//            docenteActual.setEstado(docente.getEstado());
//            docenteActual.setFechaRegistro(docente.getFechaRegistro());
//            docenteService.update(docenteActual);
//            return new ResponseEntity<>(docenteActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Docente con ese id");

        //Docente docenteActual = docenteService.findById(idDocente);
        if (docenteActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Docente");
        this.docenteService.update(docente);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Docente con codigo: "+ String.valueOf(idDocente));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Docente docenteActual = docenteService.findById(id);
        if (docenteActual != null) {
            docenteService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
