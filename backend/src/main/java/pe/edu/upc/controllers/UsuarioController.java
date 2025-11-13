package pe.edu.upc.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.*;
import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterfaces.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public List<UsuarioDTOList> listar(){
        return uS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioDTOList.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody UsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        uS.insert(u);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Integer id) {
        Usuario u = uS.listId(id);
        if (u == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UsuarioDTOList dto = m.map(u, UsuarioDTOList.class);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario u = uS.listId(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        uS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        Usuario existente = uS.listId(u.getIdUsuario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + u.getIdUsuario());
        }

        uS.update(u);
        return ResponseEntity.ok("Registro con ID " + u.getIdUsuario() + " modificado correctamente.");
    }

    @GetMapping("/actividad")
    public ResponseEntity<?> verActividad(){
        List<UsuarioxPerfilDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = uS.listarUsuariosActivos();
        if (fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay usuarios activos.");
        }
        for(String[] g:fila){
            UsuarioxPerfilDTO dto = new UsuarioxPerfilDTO();
            dto.setIdUsuario(Integer.parseInt(g[0]));
            dto.setCorreo(g[1]);
            dto.setNombre(g[2]);
            dto.setEdad(Integer.parseInt(g[3]));
            dto.setDistrito(g[4]);
            dto.setNum_personas(Integer.parseInt(g[5]));
            dto.setTipo_hogar(g[6]);
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }

}
