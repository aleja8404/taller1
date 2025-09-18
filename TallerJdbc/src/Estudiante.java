

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private int edad;
    private EstadoCivil estadoCivil;

    public Estudiante(int id, String nombre, String apellido, String correo, int edad, EstadoCivil estadoCivil) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
    }

    public Estudiante(String nombre, String apellido, String correo, int edad, EstadoCivil estadoCivil) {
        this(0, nombre, apellido, correo, edad, estadoCivil);
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public EstadoCivil getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }

    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Apellido: %s, Correo: %s, Edad: %d, Estado Civil: %s",
                id, nombre, apellido, correo, edad, estadoCivil);
    }
}
