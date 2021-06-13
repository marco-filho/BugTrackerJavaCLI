package Application.Model;

public class BaseModel implements Comparable<BaseModel> {
    protected int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(BaseModel o) {
        return id < o.id ? -1 : o.id < id ? 1 : 0;
    }
}
