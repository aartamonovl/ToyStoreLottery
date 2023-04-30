import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Menu {
    HashMap<String, String> menuList;

    public Menu(){
        this.clean();
    }

    public void clean() {
        menuList = new HashMap<>();
    }

    public void add(String a, String description) {
        menuList.put(a, description);
    }

    public void print() {
        ArrayList<String> menu_items = new ArrayList<>(menuList.keySet());
        Collections.sort(menu_items);
        System.out.println("Выберите действие: ");
        for (String a : menu_items) {
            System.out.printf("%4s - %s\n", a, menuList.get(a));
        }
    }

    public String run() {
        String key;
        while (true) {
            this.print();
            System.out.print(" >>> ");
            key = TextScanner.getText();
            System.out.println();
            if (menuList.get(key) != null) {
                // сработал пункт меню
                return key;
            }
            else {}
            System.out.println("Такого пункта в меню нет");
        }
    }
    }
