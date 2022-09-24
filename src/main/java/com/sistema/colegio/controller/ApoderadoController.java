package com.sistema.colegio.controller;

import com.sistema.colegio.model.Apoderado;
import com.sistema.colegio.service.ApoderadoService;
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
@RequestMapping("/Apoderado")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApoderadoController{

    private final ApoderadoService apoderadoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Apoderado> apoderados = this.apoderadoService.listarApoderado();


        return ResponseEntity.ok(apoderados);
		/*Collection<Apoderado> apoderado = apoderadoService.findAll();
		return new ResponseEntity<>(apoderado, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long apoderadoId) {
        Apoderado apoderado = apoderadoService.findById(apoderadoId);
        if (apoderado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Apoderado con ese id");
        }
        return new ResponseEntity<>(apoderado, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Apoderado apoderado) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            apoderadoService.insert(apoderado);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva Apoderado con codigo: " + apoderado.getIdApoderado());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Apoderado ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Apoderado apoderado) {



        apoderadoService.actualizarApoderado(apoderado);

        Map<String,String>respuesta = new HashMap<>();


        try {
            apoderadoService.actualizarApoderado(apoderado);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Apoderado con codigo: " + apoderado.getIdApoderado());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Apoderadodo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idApoderado,
                                    @RequestBody Apoderado apoderado) {
        Apoderado apoderadoActual = apoderadoService.findById(idApoderado);
//        if (apoderadoActual != null) {
//            apoderadoActual.setDescripcion(apoderado.getDescripcion());
//            apoderadoActual.setFechaInicio(apoderado.getFechaInicio());
//            apoderadoActual.setFechaFin(apoderado.getFechaFin());
//            apoderadoActual.setEstado(apoderado.getEstado());
//            apoderadoActual.setFechaRegistro(apoderado.getFechaRegistro());
//            apoderadoService.update(apoderadoActual);
//            return new ResponseEntity<>(apoderadoActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Apoderado con ese id");

        //Apoderado apoderadoActual = apoderadoService.findById(idApoderado);
        if (apoderadoActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Apoderado");
        this.apoderadoService.update(apoderado);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Apoderado con codigo: "+ String.valueOf(idApoderado));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Apoderado apoderadoActual = apoderadoService.findById(id);
        if (apoderadoActual != null) {
            apoderadoService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
