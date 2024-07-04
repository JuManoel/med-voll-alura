package med.voll.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import med.voll.api.DTO.DatosActualizarMedicoDTO;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    @Column(unique = true)
    private String documento;
    private String email;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion dirrecion;

    public Medico(DatosMedico datosMedico) {
        this.nombre = datosMedico.nombre();
        this.documento = datosMedico.documento();
        this.email = datosMedico.email();
        this.telefono = datosMedico.telefono();
        this.especialidad = datosMedico.especialidad();
        this.dirrecion = new Direccion(datosMedico.dirrecion());
    }

    public void actualizarDatos(@Valid DatosActualizarMedicoDTO json) {
        if(json.nombre() != null) {
            this.nombre = json.nombre();
        }
        if(json.documento() != null) {
            this.documento = json.documento();
        }
        if(json.direccion()!= null) {
            this.dirrecion.actualizarDireccion(json.direccion());
        }
    }

}
