import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LogicLevel {

    public static void addToysToBox(ToyBox box) {
        System.out.println("Добавляем игрушки в коробку");
        System.out.println("для отмены введите пустую строку в любое поле");
        String name = null;
        int possibility = 0;
        int count = 0;
        try {
            name = TextScanner.getText("Название игрушки: ");
            if (name.isEmpty() || name.isBlank()) {
                throw new Exception();
            }

            possibility = Integer.parseInt(TextScanner.getText("вес в выдаче %: "));
            if (possibility > 100 || possibility <= 0) {
                throw new NumberFormatException();
            }
            count = Integer.parseInt(TextScanner.getText("количество: "));
            if (count <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Отмена.");
        } catch (Exception e) {
            System.out.println("Отмена");
            return;
        }
        while (count > 0) {
            box.add(new Toy(name, possibility));
            count--;
        }
        System.out.println("Игрушки добавлены");
        System.out.printf("Всего в коробке: %d игрушек\n\n", box.size());

    }

    public static void viewBox(ToyBox toysBox) {
        System.out.printf("Всего в коробке: %d игрушек:\n", toysBox.size());
        if (toysBox.size() < 1) {
            return;
        }
        HashMap<String, Integer> output = toysBox.view_count();
        for (Map.Entry<String, Integer> entry : output.entrySet()) {
            String name = entry.getKey();
            Integer quantity = entry.getValue();
            Integer cost = toysBox.cost.get(name);
            System.out.printf(" - %s, %d шт., вес: %d %%;\n", name, quantity, cost);
        }
    }

    public static ToyList viewList(ToyList toyList, ToyBox toysBox) {
        System.out.printf("В списке на выдачу %d позиций:\n", toyList.size());
        int n = 1;
        for (UUID id : toyList.getList()) {
            Toy toy = toysBox.getToyById(id);
            if (toy != null) {
                System.out.printf(" %d - %s;\n", n++, toy.getNamed());
            }
        }
        return toyList;
    }

    public static ToyList makeListToys(ToyList toysList, ToyBox toysBox) throws InterruptedException {
        int quant;
        while (true) {
            System.out.printf("В коробке %d игрушек.\n", toysBox.size());
            try {
                quant = Integer.parseInt(TextScanner.getText("сколько возьмем на подарки?: "));
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Отмена");
                return new ToyList();
            }
            if (quant == 0) {
                System.out.println("Отмена");
            } else if (quant > toysBox.size()) {
                System.out.println("У нас нет столько игрушек!");
                Thread.sleep(3000);
                continue;
            }
            break;
        }
        Random rnd = new Random();
        for (int i = 0; i < quant; i++) {
            boolean fl = true;
            while (fl) {
                int marker = rnd.nextInt(99) + 1;
                UUID id = toysBox.getIdByPossibility(marker, toysList);
                if (id != null) {
                    toysList.add(id);
                    fl = false;
                }
            }
        }
        return toysList;
    }


    public static void getToyToBayer(ToyList toyList, ToyBox toysBox) {
        if (toyList.size() == 0) {
            System.out.println("Нет списка для выдачи подарков");
            return;
        }
        UUID id = toyList.getList()[0];
        Toy toy = toysBox.getToyById(id);
        System.out.println("Выдача подарка");
        System.out.printf("%s \n", toy.getNamed());
        if (toyList.del(id)) {
            toysBox.delById(id);
        }
        LogicLevel.viewBox(toysBox);
        LogicLevel.viewList(toyList, toysBox);
    }

    public static void cleanBox(ToyBox toysBox, ToyList toysList) {
        toysBox.clear();
        toysList.clean();
    }

    public static void saveListToysToFile(ToyList toysList, ToyBox toysBox) {
        if (toysList.size()==0){
            System.out.println("Сначала выполните формирование списка на выдачу.");
            return;
        }
        String filename = TextScanner.getText("Имя файла для записи(пусто для отмены): ");
        if (filename == null || filename.isBlank() || filename.isEmpty()) {
            System.out.println("Отмена записи");
        }
        StringBuilder text = new StringBuilder("Товары для выдачи:\n");
        int n=1;
        for (UUID id : toysList.getList()) {
            text.append(String.format("%d - %s\n",n++,toysBox.getToyById(id).getNamed()));
        }
        try (FileWriter writer = new FileWriter(filename, false)) {

            writer.write(text.toString());
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
