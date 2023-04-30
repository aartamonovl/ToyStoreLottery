import java.util.UUID;
import java.util.HashSet;

public class ToyList {
//    private List<Toy> toyList = new ArrayList<>();
    private HashSet<UUID> toyList;

    public ToyList() {
        this.toyList = new HashSet<>();
    }

    public boolean add(UUID id) {
        if (this.toyList.contains(id)){
            return false;
        }
        this.toyList.add(id);
        return true;
    }

    public boolean del(UUID id){
        if (this.toyList.size() < 1){
            return false;
        }
        return toyList.remove(id);
    }

    public UUID[] getList(){
        int i = toyList.size() - 1;
        UUID[] result = new UUID[i+1];
        for (UUID id : toyList){
            result[i] = id;
            i--;
        }
        return result;
    }

    public boolean contains(UUID uuid) {
        return toyList.contains(uuid);
    }

    public void clean() {
        this.toyList = new HashSet<>();
    }

    public int size() {
        return toyList.size();
    }
}
