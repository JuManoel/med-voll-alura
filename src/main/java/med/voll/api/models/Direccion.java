package med.voll.api.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String distrito;
    private String ciudad;
    private int numero;
    private String complemento;

    public Direccion(DatosDireccion dirrecion) {
        this.calle = dirrecion.calle();
        this.distrito = dirrecion.distrito();
        this.ciudad = dirrecion.ciudad();
        this.numero = dirrecion.numero();
        this.complemento = dirrecion.complemento();
    }

    public void actualizarDireccion(DatosDireccion direccion){
        this.calle = direccion.calle();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
    }

}
