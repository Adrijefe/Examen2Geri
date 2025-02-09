import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


record Item(String description, AtomicReference<State> state) {
        enum State {
            CHECKED, UNCHECKED
        }

        public Item(String description) {
            this(description, new AtomicReference<>(Item.State.UNCHECKED));
        }

        void setState(State newState) {
            state.set(newState);
        }

        @Override
        public String toString() {
            return (state.get() == State.CHECKED ? "[V]" : "[ ]") + description + "/n";
        }
    }
public class Ejercicio6 {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        List<Item> items = new CopyOnWriteArrayList<>();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()){
            //Un thread debe ir añadiendo items, hasta un máximo de 10. El nombre debe ser random
            executor.submit(()->{
                while (true){
                    if (items.size()<10){
                        items.add(new Item("Item " + ThreadLocalRandom.current().nextInt()));

                    }

                }
            });
            //Un thread debe ir cambiando el estado de un item random de la lista
            executor.submit(()->{

                    while (true){
                            lock.lock();
                            if (items.size()>0) {
                                items.get(ThreadLocalRandom.current().nextInt(items.size())).setState(Item.State.CHECKED);
                            }
                        lock.unlock();




                    }
            });
            //Un thread debe ir eliminando items random
            executor.submit(()->{
                while (true){
                    lock.lock();
                    if (items.size()>0) {
                        items.remove(ThreadLocalRandom.current().nextInt(items.size()));

                    }
                    lock.unlock();

                }
            });
            //Un thread debe mostar la lista de items
            executor.submit(()->{
                while (true){
                    System.out.println(items.toString());
                    Thread.sleep(500);
                }


            });
        }

    }


}
