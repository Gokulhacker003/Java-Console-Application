package Notes;

public class Note implements Cloneable {
    private String note;
    private long count;

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
    @Override
    public Note clone() throws CloneNotSupportedException
    {
        return (Note) super.clone();
    }
}
