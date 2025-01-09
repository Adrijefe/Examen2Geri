import java.util.ArrayList;
import java.util.concurrent.Executors;
public class Ejercicio4 {
    public static void main(String[] args) {
        var l = new ArrayList<String>();
        try(var e = Executors.newVirtualThreadPerTaskExecutor()){
            e.submit(()-> {l.add("Hola");});
            e.submit(()->{l.add("Adios");});
        }
        System.out.println(l);
    }
}
