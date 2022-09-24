package com.sistema.colegio.controller;

import com.sistema.colegio.model.Horario;
import com.sistema.colegio.service.HorarioService;
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
@RequestMapping("/Horario")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HorarioController {

    private final HorarioService horarioService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar()  {
        List<Horario> horarios = this.horarioService.listarHorario();


        return ResponseEntity.ok(horarios);
		/*Collection<Horario> horario = horarioService.findAll();
		return new ResponseEntity<>(horario, HttpStatus.OK);*/
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable(name = "id") Long horarioId) {
        Horario horario = horarioService.findById(horarioId);
        if (horario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Horario con ese id");
        }
        return new ResponseEntity<>(horario, HttpStatus.OK);
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Horario horario) {
        Map<String,String>respuesta = new HashMap<>();

        try {
            horarioService.insert(horario);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se creo Satisfactoriamente el nuevo Horario con codigo: " + horario.getIdHorario());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","El Horario ya se encuentra registrado");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }



   /* @PutMapping("/actualizar")
   //@RequestMapping(value = "/actualizar", method = { RequestMethod.GET, RequestMethod.PUT })
    public ResponseEntity<?> actualizar(@RequestBody Horario horario) {



        horarioService.actualizarHorario(horario);

        Map<String,String>respuesta = new HashMap<>();


        try {
            horarioService.actualizarHorario(horario);
            respuesta.put("codigoRespuesta", "Ok");
            respuesta.put("msjRespuesta","Se Actualizo el Horario con codigo: " + horario.getIdHorario());
            respuesta.put("fechaCreacion", new Date(0).toString());
        }
        catch(Exception ex) {

            respuesta.put("CodigoRespuesta", "FAIL");
            respuesta.put("msjRespuesta","Ya se encuentra Horariodo");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }


*/


 @PutMapping("/editar/{id}")
/*@RequestMapping(value = "/editar/{id}",
        produces = "application/json",
        method={ RequestMethod.GET, RequestMethod.PUT })*/
    //@RequestMapping(value = "/editar/{id}", method = {RequestMethod.PUT })
    public ResponseEntity<?> editar(@PathVariable(name = "id") Long idHorario,
                                    @RequestBody Horario horario) {
        Horario horarioActual = horarioService.findById(idHorario);
//        if (horarioActual != null) {
//            horarioActual.setDescripcion(horario.getDescripcion());
//            horarioActual.setFechaInicio(horario.getFechaInicio());
//            horarioActual.setFechaFin(horario.getFechaFin());
//            horarioActual.setEstado(horario.getEstado());
//            horarioActual.setFechaRegistro(horario.getFechaRegistro());
//            horarioService.update(horarioActual);
//            return new ResponseEntity<>(horarioActual, HttpStatus.ACCEPTED);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Horario con ese id");

        //Horario horarioActual = horarioService.findById(idHorario);
        if (horarioActual == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se pudo actualizar el Horario");
        this.horarioService.update(horario);
        Map<String,String>respuesta = new HashMap<>();
        respuesta.put("codigoRespuesta", "Ok");
        respuesta.put("msjRespuesta","Se Actualizo satisfactoriamente los datos del Horario con codigo: "+ String.valueOf(idHorario));
        return new ResponseEntity<Object>(respuesta, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        Horario horarioActual = horarioService.findById(id);
        if (horarioActual != null) {
            horarioService.delete(id);
            return new ResponseEntity<>("Se borro correctamente", HttpStatus.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro matricula con ese id");

    }


}
