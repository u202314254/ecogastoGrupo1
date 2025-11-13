package pe.edu.upc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.NotificacionDTO;
import pe.edu.upc.dtos.NotificacionNoLeidaDTO;
import pe.edu.upc.dtos.NotificacionesxUsuarioDTO;
import pe.edu.upc.entities.Notificacion;
import pe.edu.upc.serviceinterfaces.INotificacionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private INotificacionService service;

    @GetMapping
    public List<NotificacionDTO> listar(){
        return service.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, NotificacionDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody NotificacionDTO dto){
        ModelMapper m = new ModelMapper();
        Notificacion n = m.map(dto,Notificacion.class);
        service.insert(n);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id){
        Notificacion n = service.listId(id);
        if (n == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        NotificacionDTO dto = m.map(n, NotificacionDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Notificacion n = service.listId(id);
        if (n == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + "eliminado correctamente. ");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody NotificacionDTO dto){
        ModelMapper m = new ModelMapper();
        Notificacion noti = m.map(dto,Notificacion.class);

        Notificacion existente = service.listId(noti.getIdNotificacion());
        if (existente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + noti.getIdNotificacion());
        }

        service.update(noti);
        return ResponseEntity.ok("Registro con ID " + noti.getIdNotificacion() + " modificado correctamente. ");
    }

    @GetMapping("/notificacionleida")
    public ResponseEntity<?> Notificacionleida(){
        List<String[]> fila = service.NotificacionesNoLeido();
        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay notificaciones disponibles");
        }

        List<NotificacionNoLeidaDTO> listaDTO = new ArrayList<>();
        for (String[] s : fila) {
            NotificacionNoLeidaDTO dto = new NotificacionNoLeidaDTO();
            dto.setTotal(Integer.parseInt(s[0]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/reporte-usuarios")
    public ResponseEntity<?> obtenerNotificacionesPorUsuario() {
        List<NotificacionesxUsuarioDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = service.obtenerNotificacionesPorUsuario();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay notificaciones disponibles.");
        }

        for (String[] g : fila) {
            NotificacionesxUsuarioDTO dto = new NotificacionesxUsuarioDTO();
            dto.setUsuario(String.valueOf(g[0]));
            dto.setTotal(Integer.parseInt(g[1]));
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }
}