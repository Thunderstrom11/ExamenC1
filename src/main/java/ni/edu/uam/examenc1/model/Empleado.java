package ni.edu.uam.examenc1.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Empleado {
    private String nombres;
    private String apellidos;
    private String cargo;
    private double salario;

    //paso de datos entre ventanas. el registro agrega y el listado lee.
    public static final List<Empleado> registrados = new ArrayList<>(); //lista de objetos empleado

    //este es el empleado que el usuario selecciona en la tabla del listado
    public static Empleado seleccionado;

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
