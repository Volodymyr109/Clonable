import java.util.NoSuchElementException;

public class MyList <T> implements Cloneable{
    /**
     * Reference auf den ersten Entry der List
     */
    private MyEntry<T> begin;
    /**
     * References vor dem aktuelle Entry der List
     */
    private MyEntry<T> pos;

    /**
     * Erstellt eine leere Liste
     */
    public MyList() {
        pos = begin = new MyEntry<T>();
    }

    /**
     * Bestimmt, ob die List leer ist oder nicht.
     *
     * @return <code>true</code>, falls keine Elemente in der List sind
     */
    public boolean empty() {
        return begin.next == null;
    }

    /**
     * Bestimmt, ob in der List {@link #advance()} moeglich ist. Returnt
     * <code>true</code> , falls die letzte Position in der List erreicht wurde. An
     * {@link #empty()} gibt die List immer <code>true</code> aus
     *
     * @return <code>true</code> Falls der letzte Entry schon erreicht wurde
     */
    public boolean endpos() { // true, wenn am Ende
        return pos.next == null;
    }

    /**
     * Springt zum Beginn der List zurueck
     */
    public void reset() {
        pos = begin;
    }

    /**
     * Bewegt sich eine Position weiter in der List
     *
     * @throws RuntimeExcpetion
     *            falls die letzte Stelle bereits erreicht wurde
     */
    public void advance() {
        if (endpos()) {
            throw new RuntimeException("Schon Ende der List erreicht");
        }
        pos = pos.next;
    }

    /**
     * Returnt das aktuelle Element dieser List.
     *
     * @return das aktuelle Element
     *
     * @throws RuntimeException
     *            Falls der letzte Entry der List schon erreicht wurde
     */
    public T elem() {
        if (endpos()) {
            throw new RuntimeException("Schon Ende der List erreicht");
        }
        return pos.next.o;
    }

    /**
     * Fuegt <code>o</code> in die List ein. Es wird vor dem aktuelle Element eingefuegt
     * Nach dem Eingefuegen, wird das eingefuegte Element das aktuelle Element
     *
     * @param x
     *           Das einzufuegende Element
     */
    public void add(T x) {
        MyEntry<T> newone = new MyEntry<T>(x, pos.next);

        pos.next = newone;
    }

    /**
     * Loescht das aktuelle Element der List.
     * Das Element danach wird das neue aktuelle Element.
     *
     * @throws RuntimeExcpetion
     *            Falls das Ende der List schon erreicht wurde
     */
    public void delete() {
        if (endpos()) {
            throw new RuntimeException("Schon Ende der List erreicht");
        }
        pos.next = pos.next.next;
    }
    /**
     * Die Liste wird geklont
     * @throws CloneNotSupportedException
     * 				wird auftretende Exception an die Superklasse weitergegeben
     */

    //!!!! WARUM Vererbung: Wiederverwendung, Erweiterbarkeit, Abhängigkeiten des teiles des codes, Verständlichkeit und Wartbarkeit
    /*
    Durch das Überschreiben dieser Methoden kann die Verwendung von MyList-Objekten in verschiedenen Szenarien erleichtert werden,
    einschließlich Klonen, Hashing und Vergleich.
     */

    // Invoking Object's clone method on an instance that does not implement the Cloneable interface results in the exception CloneNotSupportedException being thrown.

    // Instanz die überschreibt die Methode clone() der Klasse Object und implementiert das Cloneable-Interface.
    public MyList<T> clone() {
        // try mögliche Ausnahmen während des Klonvorgangs abzufangen.
        try {
            // rufe superClone! flache Kopie des aktuellen Objekts zu erstellen
            // damit clone überschreibt wird
            MyList<T> clone = (MyList<T>) super.clone();
            clone.begin = this.begin.clone();
            clone.pos = clone.begin;

            return clone;
            // Liste geklont, indem begin und pos kopiert werden. Am Ende wird MyList-Objekt zurückgegeben
        } catch (CloneNotSupportedException e) { //Invoking Object's clone method on an instance that does not implement the Cloneable interface results in the exception CloneNotSupportedException being thrown.
            throw new InternalError();
        }
    }
    //berechnet den Hashcode der MyList-Instanz und Primzahlkonstante (prime) wird für die Verteilung der Hashcodes verwendet.
    @Override
    public int hashCode() {
        //def prime
        final int prime = 10;
        // res der berechnung
        int result = 1;
        // if else null
        result = prime * result + ((begin == null) ? 0 : begin.hashCode());
        return result;
    }

    //vergleicht die Gleichheit der MyList-Instanz mit einem anderen Objekt
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        // MyList-Instanzen verglichen, indem die begin-Referenzen beider Objekte miteinander verglichen werden.
        MyList other = (MyList) obj;
        if (!begin.equals(other.begin))
            return false;
        return true;
    }
}

