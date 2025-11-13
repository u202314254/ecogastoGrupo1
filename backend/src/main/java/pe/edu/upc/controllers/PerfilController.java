package pe.edu.upc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.PerfilDTO;
import pe.edu.upc.entities.Perfil;
import pe.edu.upc.serviceinterfaces.IPerfilService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {
    @Autowired
    private IPerfilService pS;
    @GetMapping
    public List<PerfilDTO> listar(){
        return pS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, PerfilDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody PerfilDTO dto){
        ModelMapper m = new ModelMapper();
        Perfil p = m.map(dto,Perfil.class);
        pS.insert(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id){
        Perfil p = pS.listId(id);
        if (p == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        PerfilDTO dto = m.map(p, PerfilDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Perfil p = pS.listId(id);
        if (p == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        pS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + "eliminado correctamente. ");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PerfilDTO dto){
        ModelMapper m = new ModelMapper();
        Perfil p = m.map(dto,Perfil.class);

        Perfil existente = pS.listId(p.getIdPerfil());
        if (existente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + p.getIdPerfil());
        }

        pS.update(p);
        return ResponseEntity.ok("Registro con ID " + p.getIdPerfil() + " modificado correctamente. ");
    }

}
