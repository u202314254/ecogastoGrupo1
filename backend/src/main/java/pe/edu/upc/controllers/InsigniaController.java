package pe.edu.upc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.CantidadInsigniaUsuarioDTO;
import pe.edu.upc.dtos.InsigniByMetaDTO;
import pe.edu.upc.dtos.InsigniaDTO;
import pe.edu.upc.dtos.InsigniasxUsuarioDTO;
import pe.edu.upc.entities.Insignia;
import pe.edu.upc.serviceinterfaces.IInsigniaService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/insignias")
public class InsigniaController {
    @Autowired
    private IInsigniaService service;
    @GetMapping
    public List<InsigniaDTO> listar(){
        return service.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, InsigniaDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody InsigniaDTO dto){
        ModelMapper m = new ModelMapper();
        Insignia i = m.map(dto,Insignia.class);
        service.insert(i);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id){
        Insignia i = service.listId(id);
        if (i == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        InsigniaDTO dto = m.map(i, InsigniaDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Insignia i = service.listId(id);
        if (i == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + "eliminado correctamente. ");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody InsigniaDTO dto){
        ModelMapper m = new ModelMapper();
        Insignia insig = m.map(dto,Insignia.class);

        Insignia existente = service.listId(insig.getIdInsignia());
        if (existente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + insig.getIdInsignia());
        }

        service.update(insig);
        return ResponseEntity.ok("Registro con ID " + insig.getIdInsignia() + " modificado correctamente. ");
    }

    @GetMapping("/usuario")
    public ResponseEntity<?> obtenerInsigniaPorUsuario(){
        List<InsigniasxUsuarioDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = service.obtenerInsigniasPorUsuario();
        if(fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario no tiene insignias obtenidas");
        }
        for(String[] i:fila){
            InsigniasxUsuarioDTO dto = new InsigniasxUsuarioDTO();
            dto.setTipo(String.valueOf(i[0]));
            dto.setPuntos(Integer.parseInt(i[1]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> obtenerCantidadInsigniasPorUsuario(){
        List<CantidadInsigniaUsuarioDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = service.contarInsigniasPorUsuario();
        if(fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario no tiene insignias obtenidas");
        }
        for(String[] i:fila){
            CantidadInsigniaUsuarioDTO dto = new CantidadInsigniaUsuarioDTO();
            dto.setCantidadInsignias(Integer.parseInt(i[0]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/bymeta")
    public ResponseEntity<?> obtenerInsigniaByMeta(){
        List<InsigniByMetaDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = service.findInsigniasByMeta();
        if(fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron insignias para esta meta.");
        }
        for(String[] i:fila){
            InsigniByMetaDTO dto = new InsigniByMetaDTO();
            dto.setTipo(String.valueOf(i[0]));
            dto.setNombreLogro(String.valueOf(i[1]));
            dto.setPuntos(Integer.parseInt(i[2]));
            dto.setMetaDescripcion(String.valueOf(i[3]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }
}