import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class Reservas2 {
    record Reserva (String n, int i, int f){}

    static class GestorReservas{
        ConcurrentHashMap<Integer,List<Reserva>> reservas = new ConcurrentHashMap<>();

        boolean reservar (int hab, int ini,int fin , String user) {
            if (reservas.get(hab) == null) {
                reservas.put(hab, new ArrayList<>());

                boolean estaLibre = false;
                for (var r : reservas.get(hab)) {
                    if (r.i() >= fin || ini >= r.f()) {
                        estaLibre = true;
                        break;
                    }
                }
                if (!estaLibre) {
                    reservas.get(hab).add(new Reserva(user, ini, fin));

                }
                return estaLibre;
            }
            return true;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorReservas gestorReservas = new GestorReservas();

        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            for (int i = 0 ; i<1000; i++){
                executor.submit(()->{
//                    gestorReservas.reservar (ThreadLocalRandom.current().nextInt(100),
//                            ini,
//                            fin,
//                            user);
                });
            }
        }
        while (true) {

            int hab = scanner.nextInt();
            scanner.nextLine();
            String user = scanner.nextLine();
            int ini = scanner.nextInt();
            int fin = scanner.nextInt();

            gestorReservas.reservar(hab,ini,fin,user);






        }
    }
}
