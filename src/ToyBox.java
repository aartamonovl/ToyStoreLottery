import java.util.*;

public class ToyBox {
    ArrayList<Toy> toyList;
    HashMap<String, Integer> count = new HashMap<>();
    HashMap<String, Integer> cost = new HashMap<>();

    public ToyBox() {
        this.clear();
    }

    public void clear() {
        toyList = new ArrayList<>();
        count = new HashMap<>();
        cost = new HashMap<>();
    }

    public Toy getToyById(UUID id) {
        for (Toy toy : this.toyList) {
            if (toy.getId().equals(id)) {
                return toy;
            }
        }
        return null;
    }

    public void add(Toy toy) {
        toyList.add(toy);
        Collections.shuffle(toyList);
        count.merge(toy.getNamed(), 1, Integer::sum);
        cost.put(toy.getNamed(), toy.getPossibility());

    }

    public int size() {
        return this.toyList.size();
    }

    public HashMap<String, Integer> view_count() {
        return (HashMap<String, Integer>) count;
    }



    public UUID getIdByPossibility(int marker,ToyList toysList) {
        for (Toy toy: toyList){
            int cost = toy.getPossibility();
            if (cost<marker) continue;
            UUID id = toy.getId();
            if (!toysList.contains(id)) return id;
        }
        return null;
    }

    public void delById(UUID id){
        Iterator<Toy> iterator = this.toyList.iterator();
        while (iterator.hasNext()){
            Toy toy = iterator.next();
            if (toy.getId().equals(id)){
                this.count.put(toy.getNamed(),this.count.get(toy.getNamed())-1);
                if (this.count.get(toy.getNamed())==0){
                    this.count.remove(toy.getNamed());
                }
                iterator.remove();
                return;
            }
        }
    }
}
