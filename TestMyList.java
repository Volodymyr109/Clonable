
public class TestMyList implements Cloneable{

    public static void main(String[] args) throws CloneNotSupportedException {

        TestMyList tester = new TestMyList();
        tester.testClone();

    }

    // ruft andere methoden
    public void testClone() {
        this.testNonAbsoluteRequirements();
        this.testIsAnythingCopied();
        this.testIndependenceOfClone();
    }

    //Es wird überprüft, ob das Klonen der Liste eine neue Instanz zurückgibt, die nicht dieselbe Referenz wie die Originalliste hat.
    //Es wird überprüft, ob der Klon denselben Klassentyp wie die Originalliste hat.
    //Es wird überprüft, ob der Klon und die Originalliste inhaltlich gleich sind.
    private void testNonAbsoluteRequirements() {
        MyList<String> l = new MyList<>();
        assert(l.clone() != l) : "Clone hat gleichen Zeiger so wie Original";
        assert(l.clone().getClass() == l.getClass()) : "Clone und Original nicht gleich";
        assert(l.clone().equals(l)) : "Clone und Original nicht gleich";

        System.out.println("Test Non-Absolute Requirements: Passed");
    }

    //Es wird überprüft, ob das einzige Element des Klons "A" ist.
    private void testIsAnythingCopied() {
        MyList<String> l = new MyList<>();
        l.add("A");
        MyList<String> clone = l.clone();
        // Test if inner structure is copied/cloned
        assert("A".equals(clone.elem())) : "Subelements nicht cloniert";

        System.out.println("Test Is Anything Copied: Passed");
    }

    //Es wird überprüft, ob der Klon an der Endposition ist, was bedeutet, dass er unabhängig von der Originalliste vorangeschritten ist.
    //Es werden weitere Elemente ("C" und "D") zum Klon hinzugefügt, während die Originalliste unverändert bleibt.
    //Es wird überprüft, ob die Originalliste immer noch an der Endposition ist.
    //Es wird die innere Struktur der Listen überprüft, um sicherzustellen, dass die
    private void testIndependenceOfClone() {
        MyList<String> l = new MyList<>();
        l.add("A");
        MyList<String> clone = l.clone();
        l.add("B");
        clone.advance();
        assert(clone.endpos()) : "testIndependenceOfClone Test 1 failed - assert(clone.endpos())";
        clone.add("C");
        clone.add("D");
        l.advance();
        l.advance();
        assert(l.endpos()) : "testIndependenceOfClone Test 2 failed - assert(l.endpos())";
        l.reset();
        l.delete();
        clone.reset();
        assert("A".equals(clone.elem())) : "testIndependenceOfClone Test 3 failed - assert(equals(clone.elem()))";

        System.out.println("Test Independence of Clone: Passed");
    }
}