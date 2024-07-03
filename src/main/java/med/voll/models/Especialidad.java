package med.voll.models;

public enum Especialidad {
    ORTOPEDIA("ortopedia"),
    CARDIOLOGIA("cardiologia"),
    PEDIATRIA("pediatria"),
    GINECOLOGIA("ginecologia");

    private String type;

    Especialidad(String type) {
        this.type = type;
    }

    public Especialidad fromString(String type) {
        for (Especialidad especialidad : Especialidad.values()) {
            if(especialidad.type.contains(type.toLowerCase())){
                return especialidad;
            }
        }
        throw new IllegalArgumentException("No se encontro especialidad con el nombre: " + type);
    }
}
