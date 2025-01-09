import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

record Mensaje (String text, LocalTime date){
    public Mensaje (String text){
        this(text, LocalTime.now());
    }
}

class MessageSystem {
    List<Mensaje> mensajeList = new CopyOnWriteArrayList<>();

    void store(Mensaje text) {
        mensajeList.add(text);
    }

    void removeold() {
        mensajeList.removeIf(m ->
                    m.date().minusSeconds(3).isAfter(LocalTime.now())

        );

    }

    public class Ejercicio5 {
        public static void main(String[] args) {
            MessageSystem messageSystem = new MessageSystem();
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                for (int i = 0; i < 30; i++) {
                    executor.submit(() -> {
                        while (true) {
                            messageSystem.store(new Mensaje("aaaa si"));

                            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 4000));
                        }
                    });
                }
                executor.submit(() -> {
                    messageSystem.removeold();
                    System.out.println(messageSystem.mensajeList.size());
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException exception){
                        throw new RuntimeException();
                    }
                });


            }

        }

    }
}
