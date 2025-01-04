package Notes;

// The Note class represents a banknote with a value and a count
public class Note implements Cloneable {
    private String note;  // The value of the note (e.g., "2000", "500")
    private long count;   // The number of such notes

    // Set the note value
    public void setNote(String note) {
        this.note = note;
    }

    // Get the note value
    public String getNote() {
        return note;
    }

    // Set the count of notes
    public void setCount(long count) {
        this.count = count;
    }

    // Get the count of notes
    public long getCount() {
        return count;
    }

    // Clone the Note object to store in New address 
    //Used for DeepCopy
    @Override
    public Note clone() throws CloneNotSupportedException {
        return (Note) super.clone(); // Create and return a copy of the object
    }

}
