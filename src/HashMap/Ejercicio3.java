package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

public class Ejercicio3 {
   static class GroupManager{

        ConcurrentHashMap<String, List<String>> grupos = new ConcurrentHashMap<>();
        boolean addUserToGroup (String user, String group){
                grupos.putIfAbsent(group, new ArrayList<>());
                grupos.compute(group, (grupo, usuarios) -> {
                    usuarios.removeIf(u-> u.equals(user));
                    usuarios.add(user);
                    return usuarios;

                });
            return true;

        }

        boolean removeUserFromGroup (String user, String group){
            for (var u : grupos.get(group)){
                if (u.equals(user)){
                    grupos.get(group).remove(user);
                    return true;
                }

            }
            return false;


        }

        boolean deleteGroup (String group){
            if (grupos.containsKey(group)){
                grupos.remove(group);
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {

        var users = List.of("usera", "userb", "userc", "userd", "usere", "userf", "userg", "userf");
        var groups = List.of("group1", "group2", "group3", "group4");

        for (int j = 0; j < 1000; j++) {


            GroupManager groupManager = new GroupManager();

            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

                executor.submit(() -> {
                    for (int i = 0; i < 100; i++) {
                        groupManager.addUserToGroup("alvaro", "dam2");
                    }

                });

                executor.submit(() -> {
                    for (int i = 0; i < 100; i++) {
                        groupManager.addUserToGroup("alvarita", "dam2");
                    }
                });

            }
            if (groupManager.grupos.get("dam2").size() != 2){
                System.out.println("MIERDAAAA");

            }
        }

        }


}
