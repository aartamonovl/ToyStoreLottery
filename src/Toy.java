import java.util.UUID;

public class Toy {
    private final UUID id;
    private String named;
    private int possibility;

    public UUID getId() {
        return id;
    }

    public String getNamed() {
        return named;
    }

    public void setNamed(String named) {
        this.named = named;
    }

    public int getPossibility() {
        return possibility;
    }

    public void setPossibility(int possibility) {
        this.possibility = possibility;
    }

    public Toy(String named, int possibility) {
        this.id = UUID.randomUUID();
        this.named = named;
        this.possibility = possibility;
    }
}
