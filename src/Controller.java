public class Controller {
    private ToyList toyList;
    private ToyBox toyBox;

    public Controller() throws InterruptedException {
        toyBox = new ToyBox();
        toyList = new ToyList();
        mainMenuLoop();
    }

    private void mainMenuLoop() throws InterruptedException {
        Menu menu = new Menu();
        menu.add("1", "Посмотреть игрушки в призовой коробке");
        menu.add("2", "Посмотреть список игрушек на выдачу");
        menu.add("3", "Добавить игрушки в коробку");
        menu.add("4", "Сформировать список на выдачу");
        menu.add("5", "Выдать игрушку");
        menu.add("6", "Опустошить призовую коробку");
        menu.add("7", "Записать список подарков в файл");
        menu.add("0", "Выход");
        while (true) {
            switch (menu.run()) {
                case "1":
                    LogicLevel.viewBox(this.toyBox);
                    break;
                case "2":
                    LogicLevel.viewList(this.toyList,this.toyBox);
                    break;
                case "3":
                    LogicLevel.addToysToBox(this.toyBox);
                    break;
                case "4":
                    this.toyList.clean();
                    LogicLevel.makeListToys(this.toyList,this.toyBox);
                    break;
                case "5":
                    LogicLevel.getToyToBayer(this.toyList,this.toyBox);
                    break;
                case "6":
                    LogicLevel.cleanBox(this.toyBox,this.toyList);
                    break;
                case "7":
                    LogicLevel.saveListToysToFile(this.toyList,this.toyBox);
                    break;
                case "0":
                    return;
            }
            System.out.println();
        }
    }
}
