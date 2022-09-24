package com.sistema.colegio.controller;

import com.sistema.colegio.model.Nivel;
import com.sistema.colegio.service.NivelService;
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
@RequestMapping("/Nivel")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NivelController {

    private final NivelService nivelService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Nivel> nivels = this.nivelService.listarNivel();


        return ResponseEntity.ok(nivels);
		/*Collection<Nivel> nivel = nivelService.findAll();
		return new ResponseEntity<>(nivel, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long nivelId) {
        Nivel nivel = nivelService.findById(nivelId);
        if (nivel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Nivel con ese id");
        }
        return new ResponseEntity<>(nivel, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Nivel nivel) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            nivelService.insert(nivel);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente el Nivel con codigo: " + nivel.getIdNivel());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Nivel ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



    @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Nivel nivel) {



        nivelService.actualizarNivel(nivel);

        Map<String,String>respuesta = new HashMap<>();


        try {
            nivelService.actualizarNivel(nivel);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Nivel con codigo: " + nivel.getIdNivel());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Niveldo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }





 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idNivel,
                                    @RequestBody Nivel nivel) {
        Nivel nivelActual = nivelService.findById(idNivel);
//        if (nivelActual != null) {
//            nivelActual.setDescripcion(nivel.getDescripcion());
//            nivelActual.setFechaInicio(nivel.getFechaInicio());
//            nivelActual.setFechaFin(nivel.getFechaFin());
//            nivelActual.setEstado(nivel.getEstado());
//            nivelActual.setFechaRegistro(nivel.getFechaRegistro());
//            nivelService.update(nivelActual);
//            return new ResponseEntity<>(nivelActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Nivel con ese id");

        //Nivel nivelActual = nivelService.findById(idNivel);
        if (nivelActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Nivel");
        this.nivelService.update(nivel);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Nivel con codigo: "+ String.valueOf(idNivel));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Nivel nivelActual = nivelService.findById(id);
        if (nivelActual != null) {
            nivelService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
