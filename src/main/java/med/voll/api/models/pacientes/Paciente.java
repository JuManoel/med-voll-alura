package med.voll.api.models.pacientes;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.DTO.Pacientes.DatosActualizarPacienteDTO;
import med.voll.api.models.direccion.Direccion;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    @Column(unique = true)
    private String documento;
    @Embedded
    private Direccion direccion;
    private boolean activo;

    public Paciente(DatosPaciente paciente) {
        this.nombre = paciente.nombre();
        this.email = paciente.email();
        this.telefono = paciente.telefono();
        this.documento = paciente.documentoIdentidad();
        this.direccion = new Direccion(paciente.direccion());
        this.activo = true;
    }

    public void actualizarDatos(@Valid DatosActualizarPacienteDTO json) {
        if(json.nombre() != null) {
            this.nombre = json.nombre();
        }
        if(json.telefono() != null) {
            this.telefono = json.telefono();
        }
        if(json.direccion()!= null) {
            this.direccion.actualizarDireccion(json.direccion());
        }
    }

    public void desactivar() {
        this.activo = false;
    }
}
