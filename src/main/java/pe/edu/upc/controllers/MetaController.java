package pe.edu.upc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dtos.CantidadMetaActivaDTO;
import pe.edu.upc.dtos.MetaDTO;
import pe.edu.upc.dtos.MetasxUsuarioDTO;
import pe.edu.upc.entities.Meta;
import pe.edu.upc.serviceinterfaces.IMetaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metas")
public class MetaController {
    @Autowired
    private IMetaService mS;
    @GetMapping
    public List<MetaDTO> listar(){
        return mS.list().stream().map(y->{
            ModelMapper m= new ModelMapper();
            return m.map(y, MetaDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody MetaDTO dto){
        ModelMapper m= new ModelMapper();
        Meta me = m.map(dto, Meta.class);
        mS.insert(me);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable ("id") Integer id){
        Meta met = mS.listId(id);
        if (met == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        }
        ModelMapper m= new ModelMapper();
        MetaDTO dto = m.map(met, MetaDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable ("id") Integer id){
        Meta met = mS.listId(id);
        if ( met == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro con el ID: " + id);
        }
        mS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody MetaDTO dto){
        ModelMapper m= new ModelMapper();
        Meta met = m.map(dto, Meta.class);

        Meta existente = mS.listId(met.getIdMeta());
        if (existente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede modificar. No existe un registro con el ID: " + met.getIdMeta());
        }
        mS.update(met);
        return ResponseEntity.ok("Registro con ID " + met.getIdMeta() + " modificado");
    }

    @GetMapping("/incompleta")
    public ResponseEntity<?> obtenerCantidadMetasCompletadas(){
        List<CantidadMetaActivaDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = mS.findMetasActivasByUsuario();
        if(fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron metas activas para este usuario..");
        }
        for(String[] m:fila){
            CantidadMetaActivaDTO dto = new CantidadMetaActivaDTO();
            dto.setIdMeta(Integer.parseInt(m[0]));
            dto.setDescripcion(String.valueOf(m[1]));
            dto.setFechaInicio(LocalDate.parse(m[2]));
            dto.setFechaFin(LocalDate.parse(m[3]));
            dto.setEstado(String.valueOf(m[4]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/reporte-metas")
    public ResponseEntity<?> obtenerMetasPorUsuario() {
        List<MetasxUsuarioDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = mS.obtenerMetasPorUsuario();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay metas disponibles.");
        }

        for (String[] g : fila) {
            MetasxUsuarioDTO dto = new MetasxUsuarioDTO();
            dto.setUsuario(String.valueOf(g[0]));
            dto.setTotal(Integer.parseInt(g[1]));
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }
}
