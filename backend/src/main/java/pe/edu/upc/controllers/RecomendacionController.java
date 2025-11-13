package pe.edu.upc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.RecomendacionDTO;
import pe.edu.upc.dtos.RecomendacionPorRecursoDTO;
import pe.edu.upc.dtos.RecomendacionDTOList;
import pe.edu.upc.entities.Recomendacion;
import pe.edu.upc.serviceinterfaces.IRecomendacionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recomendaciones")
public class RecomendacionController {
    @Autowired
    private IRecomendacionService rS;
    @GetMapping
    public List<RecomendacionDTOList> lsitar(){
        return rS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,RecomendacionDTOList.class);
        }).collect((Collectors.toList()));
    }
    @PostMapping
    public void insertar(@RequestBody RecomendacionDTO dto){
        ModelMapper m = new ModelMapper();
        Recomendacion r = m.map(dto,Recomendacion.class);
        rS.insert(r);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id){
        Recomendacion r = rS.listId(id);
        if (r==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        RecomendacionDTO dto = m.map(r,RecomendacionDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Recomendacion r = rS.listId(id);
        if (r==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado con exito.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RecomendacionDTO dto){
        ModelMapper m = new ModelMapper();
        Recomendacion r = m.map(dto,Recomendacion.class);

        Recomendacion existente = rS.listId(r.getIdRecomendacion());
        if (existente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede modificar. No existe un registro con el ID: " + r.getIdRecomendacion());
        }
        rS.update(r);
        return ResponseEntity.ok("Registro con ID " + r.getIdRecomendacion() + " modificado con exito.");
    }

    @GetMapping("/recurso")
    public ResponseEntity<?> obtenerRecomendacionPorRecurso(){
        List<RecomendacionPorRecursoDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = rS.findRecomendacionesPorRecurso();
        if (fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una recomendacion de algún recurso.");
        }
        for(String[] r:fila){
            RecomendacionPorRecursoDTO dto = new RecomendacionPorRecursoDTO();
            dto.setDescripcion(String.valueOf(r[0]));
            dto.setFuente(String.valueOf(r[1]));
            dto.setNombreRecurso(String.valueOf(r[2]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/buscar")
    public List<RecomendacionDTOList> buscarPorCategoria(
            @RequestParam String categoria) {
        return rS.buscarPorCategoria( categoria);
    }
}
