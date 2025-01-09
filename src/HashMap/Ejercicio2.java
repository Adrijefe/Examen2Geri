package HashMap;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Reserva {
    private String nombre;
    private int horaEntrada;
    private int horaSalida;

    public Reserva(String nombre, int horaEntrada, int horaSalida) {
        this.nombre = nombre;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "nombre='" + nombre + '\'' +
                ", horaEntrada='" + horaEntrada + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                '}';
    }
}



public class Ejercicio2 {
            public static void main(String[] args) {
                ArrayList<Reserva> reservasH = new ArrayList<>();
                reservasH.add(new Reserva("Alvarito",2,5));
                reservasH.add(new Reserva("Josevi", 10,12));
                reservasH.add(new Reserva("Antonio",1,2));

                Map<Integer, List<Reserva>> reservas = new ConcurrentHashMap<>();
                reservas.put(103,reservasH);




            reservas.forEach((k,v) -> System.out.println(k + " = " + v));



        }

}
