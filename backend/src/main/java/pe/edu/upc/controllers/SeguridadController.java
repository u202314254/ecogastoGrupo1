package pe.edu.upc.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.AccesosSeguridadDTO;
import pe.edu.upc.dtos.SeguridadDTO;
import pe.edu.upc.entities.Seguridad;
import pe.edu.upc.serviceinterfaces.ISeguridadService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seguridades")
public class SeguridadController {
    @Autowired
    private ISeguridadService sS;

    @GetMapping
    public List<SeguridadDTO> listar() {
        return sS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, SeguridadDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody SeguridadDTO dto) {
        ModelMapper m = new ModelMapper();
        Seguridad s = m.map(dto, Seguridad.class);
        sS.insert(s);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Seguridad s = sS.listId(id);
        if (s == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        SeguridadDTO dto = m.map(s, SeguridadDTO.class);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Seguridad s = sS.listId(id);
        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        sS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody SeguridadDTO dto) {
        ModelMapper m = new ModelMapper();
        Seguridad s = m.map(dto, Seguridad.class);

        Seguridad existente = sS.listId(s.getIdSeguridad());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + s.getIdSeguridad());
        }

        sS.update(s);
        return ResponseEntity.ok("Registro con ID " + s.getIdSeguridad() + " modificado correctamente.");
    }

    @GetMapping("/accesos")
    public ResponseEntity<?> verAccesosSospechosos(){
        List<AccesosSeguridadDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = sS.listarAccesos();
        if (fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay accesos sospechosos.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        for(String[] g:fila){
            AccesosSeguridadDTO dto = new AccesosSeguridadDTO();
            dto.setId_usuario(Integer.parseInt(g[0]));
            dto.setCorreo(g[1]);
            dto.setUltimo_login(LocalDateTime.parse(g[2], formatter));
            dto.setIp(g[3]);
            dto.setIntentos_fallidos(Integer.parseInt(g[4]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }
}