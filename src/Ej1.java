import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class Ej1 {
    public static void main(String[] args) {
        for (int ve = 0; ve < 10000000; ve++) {
            Map<String, Integer> map = new ConcurrentHashMap<>();
            String[] palabras = {
                    "manzana", "pera", "naranja", "uva", "manzana", "naranja", "naranja", "uva",
                    "naranja", "manzana", "naranja", "uva", "manzana", "naranja", "naranja", "uva",
                    "manzana", "naranja", "naranja", "uva", "naranja", "manzana", "naranja", "uva",
                    "pera", "naranja", "pera", "naranja", "uva", "naranja", "naranja", "manzana",
                    "pera", "pera", "naranja", "manzana", "naranja", "naranja", "pera", "manzana",
                    "uva", "naranja", "manzana", "pera", "pera", "naranja", "naranja", "manzana",
                    "pera", "naranja", "uva", "pera", "manzana", "naranja", "naranja", "naranja",
                    "pera", "uva", "manzana", "naranja", "pera", "uva", "naranja", "manzana",
                    "naranja", "manzana", "uva", "pera", "pera", "naranja", "uva", "manzana",
                    "naranja", "uva", "manzana", "naranja", "pera", "naranja", "manzana", "pera",
                    "naranja", "uva", "manzana", "pera", "manzana", "naranja", "manzana", "pera",
                    "naranja", "manzana", "manzana", "naranja", "uva", "naranja", "manzana", "naranja"
            };
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                executor.submit(() -> {
                    for (int i = 0; i < palabras.length / 2; i++) {
                        map.compute(palabras[i], (_, v) -> v == null ? 1 : v + 1);
                    }
                });
                executor.submit(() -> {
                    for (int i = palabras.length / 2; i < palabras.length; i++) {
                        map.compute(palabras[i], (_, v) -> v == null ? 1 : v + 1);


                    }
                });
            }
            if (map.get("manzana") != 24
                    || map.get("pera") != 18
                    || map.get("uva") != 16
                    || map.get("naranja") != 38
            ) {
                System.out.println("Erroooooooooorrrrrr");
                map.forEach((k, v) -> System.out.println(k + " : " + v));
            }
        }
    }
}
