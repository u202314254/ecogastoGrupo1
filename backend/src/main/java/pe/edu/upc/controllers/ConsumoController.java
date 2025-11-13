package pe.edu.upc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.ConsumoDTO;
import pe.edu.upc.dtos.GastoxRecursoDTO;
import pe.edu.upc.entities.Consumo;
import pe.edu.upc.serviceinterfaces.IConsumoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumos")
public class    ConsumoController {
    @Autowired
    private IConsumoService cS;
    @GetMapping
    public List<ConsumoDTO> listar(){
        return cS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, ConsumoDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody ConsumoDTO dto){
        ModelMapper m = new ModelMapper();
        Consumo c = m.map(dto,Consumo.class);
        cS.insert(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id){
        Consumo c = cS.listId(id);
        if (c == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ConsumoDTO dto = m.map(c, ConsumoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Consumo c = cS.listId(id);
        if (c == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        }
        cS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + "eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ConsumoDTO dto){
        ModelMapper m = new ModelMapper();
        Consumo c = m.map(dto,Consumo.class);

        Consumo existente = cS.listId(c.getIdConsumo());
        if (existente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + c.getIdConsumo());
        }
        cS.update(c);
        return ResponseEntity.ok("Registro con ID " + c.getIdConsumo() + "modificado correctamente.");
    }

    @GetMapping("/gasto")
    public ResponseEntity<?> obtenerGastosPorRecurso(){
        List<GastoxRecursoDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = cS.obtenerGastosPorRecurso();
        if (fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay consumos disponibles.");
        }
        for(String[] g:fila){
            GastoxRecursoDTO dto = new GastoxRecursoDTO();
            dto.setRecurso(String.valueOf(g[0]));
            dto.setTotal(Double.parseDouble(g[1]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }

}
