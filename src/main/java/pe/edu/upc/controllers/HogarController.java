package pe.edu.upc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.HogarDTO;
import pe.edu.upc.entities.Hogar;
import pe.edu.upc.serviceinterfaces.IHogarService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hogares")
public class HogarController {
    @Autowired
    private IHogarService hS;

    @GetMapping
    public List<HogarDTO> listar() {
        return hS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, HogarDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody HogarDTO dto) {
        ModelMapper m = new ModelMapper();
        Hogar h = m.map(dto, Hogar.class);
        hS.insert(h);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Hogar h = hS.listId(id);
        if (h == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        HogarDTO dto = m.map(h, HogarDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Hogar h = hS.listId(id);
        if (h == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        }
        hS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody HogarDTO dto) {
        ModelMapper m = new ModelMapper();
        Hogar h = m.map(dto,Hogar.class);

        Hogar existente = hS.listId(h.getIdHogar());
        if (existente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + h.getIdHogar());
        }
        hS.update(h);
        return ResponseEntity.ok("Registro con ID " + h.getIdHogar() + "modificado correctamente.");
    }
}
