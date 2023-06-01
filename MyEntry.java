public class MyEntry <T> implements Cloneable {

    // speichert den Wert
    T o;
    //Referenz auf den nächsten Eintrag in der Liste.
    MyEntry<T> next;

    MyEntry() {
        this(null, null);
    }

    MyEntry(T o) {
        this(o, null);
    }

    MyEntry(T o, MyEntry<T> e) {
        this.o = o;
        this.next = e;
    }

    //Methode erstellt eine tiefe Kopie des MyEntry-Objekts
    @Override
    public MyEntry<T> clone() {
        MyEntry<T> clone;
        try {
            clone = (MyEntry<T>) super.clone();
            if (next != null) {
                clone.next = next.clone();
            }
            // Wenn der next-Zeiger nicht null ist, wird auch eine Kopie des nächsten MyEntry-Objekts rekursiv erstellt.
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    //Methode vergleicht zwei MyEntry-Objekte auf Gleichheit.
    @Override
    public boolean equals(Object obj) {
        //überprüfen, ob die Objekte dieselbe Referenz haben, null sind oder nicht von der gleichen Klasse sind
        if (this == obj)
            return true;
        if (this == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyEntry other = (MyEntry) obj;
        if (next == null) {
            if (other.next != null)
                return false;
        } else if (!next.equals(other.next)) {
            return false;
        }
        return false;
    }

    // Methode berechnet den Hashcode des MyEntry-Objekts.
    @Override
    public int hashCode() {
        //Primzahlkonstante (prime) verwendet, um die Hashcodes zu kombinieren.
        final int prime = 10;
        int result = 1;
        result = prime * result + ((next == null) ? 0 : next.hashCode());
        result = prime * result + ((o == null) ? 0 : o.hashCode());
        return result;
    }
}