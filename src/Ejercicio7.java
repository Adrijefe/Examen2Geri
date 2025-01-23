import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Ejercicio7 {
    record Producto(int id, String nombre, AtomicInteger stock){

        void aumentarStock(int cantidad){
            stock.addAndGet(cantidad);
        }


        void disminuirStock(int cantidad){
            stock.addAndGet(-cantidad);
        }



    }

    public static void main(String[] args) {
        List<Producto> inventario = new ArrayList<>();
        inventario.add(new Producto(1,"pc1",new AtomicInteger(100)));
        inventario.add(new Producto(2,"pc2",new AtomicInteger(100)));
        inventario.add(new Producto(3,"pc3",new AtomicInteger(100)));
        inventario.add(new Producto(4,"pc4",new AtomicInteger(100)));
        inventario.add(new Producto(5,"pc5",new AtomicInteger(100)));
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){

            executor.submit(()->{
                while (true){
                    System.out.println(inventario);
                }
            });

            for (int i = 0; i < 100000; i++) {
                executor.submit(()->{
                    int itemRandom = ThreadLocalRandom.current(). nextInt();
                    inventario.get(itemRandom).aumentarStock(30);

                });
                executor.submit(()->{
                    int itemRandom = ThreadLocalRandom.current(). nextInt();
                    inventario.get(itemRandom).disminuirStock(4);

                });

            }



        }
    }

}
