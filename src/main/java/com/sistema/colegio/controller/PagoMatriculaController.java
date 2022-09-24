package com.sistema.colegio.controller;

import com.sistema.colegio.model.PagoMatricula;
import com.sistema.colegio.service.PagoMatriculaService;
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
@RequestMapping("/PagoMatricula")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PagoMatriculaController {

    private final PagoMatriculaService pagoMatriculaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<PagoMatricula> pagoMatriculas = this.pagoMatriculaService.listarPagoMatricula();


        return ResponseEntity.ok(pagoMatriculas);
		/*Collection<PagoMatricula> pagoMatricula = pagoMatriculaService.findAll();
		return new ResponseEntity<>(pagoMatricula, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long pagoMatriculaId) {
        PagoMatricula pagoMatricula = pagoMatriculaService.findById(pagoMatriculaId);
        if (pagoMatricula == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro PagoMatricula con ese id");
        }
        return new ResponseEntity<>(pagoMatricula, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody PagoMatricula pagoMatricula) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            pagoMatriculaService.insert(pagoMatricula);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente la nueva PagoMatricula con codigo: " + pagoMatricula.getIdPago());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El PagoMatricula ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody PagoMatricula pagoMatricula) {



        pagoMatriculaService.actualizarPagoMatricula(pagoMatricula);

        Map<String,String>respuesta = new HashMap<>();


        try {
            pagoMatriculaService.actualizarPagoMatricula(pagoMatricula);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el PagoMatricula con codigo: " + pagoMatricula.getIdPagoMatricula());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra PagoMatriculado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idPagoMatricula,
                                    @RequestBody PagoMatricula pagoMatricula) {
        PagoMatricula pagoMatriculaActual = pagoMatriculaService.findById(idPagoMatricula);
//        if (pagoMatriculaActual != null) {
//            pagoMatriculaActual.setDescripcion(pagoMatricula.getDescripcion());
//            pagoMatriculaActual.setFechaInicio(pagoMatricula.getFechaInicio());
//            pagoMatriculaActual.setFechaFin(pagoMatricula.getFechaFin());
//            pagoMatriculaActual.setEstado(pagoMatricula.getEstado());
//            pagoMatriculaActual.setFechaRegistro(pagoMatricula.getFechaRegistro());
//            pagoMatriculaService.update(pagoMatriculaActual);
//            return new ResponseEntity<>(pagoMatriculaActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro pagoMatricula con ese id");

        //PagoMatricula pagoMatriculaActual = pagoMatriculaService.findById(idPagoMatricula);
        if (pagoMatriculaActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar PagoMatricula");
        this.pagoMatriculaService.update(pagoMatricula);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos  PagoMatricula con codigo: "+ String.valueOf(idPagoMatricula));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        PagoMatricula matriculaActual = pagoMatriculaService.findById(id);
        if (matriculaActual != null) {
            pagoMatriculaService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
