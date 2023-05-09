package ProyectoProgramacion.Controlador;

import ProyectoProgramacion.Modelo.Cita;
import ProyectoProgramacion.Servicio.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class CitaControlador {

    @Autowired
    private CitaServicio citaServicio;

    @GetMapping("/citas")
    public String listarCitas(Model model){
        List<Cita>citas= citaServicio.listarCitas();
        model.addAttribute("citas", citas);
        return "listaCitas";
    }

    @GetMapping("/citas/anadir")
    public String anadirCita(Model model){
        model.addAttribute("cita", new Cita());
        return "formularioCitaSolicitar";
    }

    @PostMapping("/citas/guardar")
    public String guardarCita(@Validated Cita cita){
        citaServicio.guardarCita(cita);
        return "redirect:/citas";
    }

    @GetMapping("/citas/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model){
        Optional<Cita> cita = citaServicio.obtenerCita(id);
        model.addAttribute("cita", cita);
        return "formularioCitaModificar";
    }

    @GetMapping("/citas/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id){
        citaServicio.eliminarCita(id);
        return "redirect:/citas";
    }
}