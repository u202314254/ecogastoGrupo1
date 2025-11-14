package pe.edu.upc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.RecursoDTO;
import pe.edu.upc.entities.Recurso;
import pe.edu.upc.serviceinterfaces.IRecursoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/recursos")
public class RecursoController {
    @Autowired
    private IRecursoService rS;
    @GetMapping
    public List<RecursoDTO> listar(){
        return rS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,RecursoDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody RecursoDTO dto){
        ModelMapper m = new ModelMapper();
        Recurso r = m.map(dto, Recurso.class);
        rS.insert(r);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Recurso r = rS.listId(id);
        if (r == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        RecursoDTO dto = m.map(r, RecursoDTO.class);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Recurso r = rS.listId(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RecursoDTO dto) {
        ModelMapper m = new ModelMapper();
        Recurso r = m.map(dto, Recurso.class);

        Recurso existente = rS.listId(r.getIdRecurso());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + r.getIdRecurso());
        }

        rS.update(r);
        return ResponseEntity.ok("Registro con ID " + r.getIdRecurso() + " modificado correctamente.");
    }

    //@GetMapping("/consumidos")
    //public ResponseEntity<?> obtenerTotaldeUsuariosxRecurso(){
    //    List<RecursoxConsumoDTO> listaDTO = new ArrayList<>();
    //    List<String[]> fila = rS.listarRecursos();
    //    if (fila.isEmpty()){
    //        return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                .body("No hay consumos por recurso.");
    //    }
    //    for(String[] g:fila){
    //        RecursoxConsumoDTO dto = new RecursoxConsumoDTO();
    //        dto.setIdRecurso(Integer.parseInt(g[0]));
    //        dto.setNombreRecurso(g[1]);
    //        dto.setTotalUsuarios(Integer.parseInt(g[2]));
    //        listaDTO.add(dto);
    //    }
    //    return ResponseEntity.ok(listaDTO);
    //}
}
