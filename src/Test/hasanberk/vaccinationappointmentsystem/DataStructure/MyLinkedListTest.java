package hasanberk.vaccinationappointmentsystem.DataStructure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {
    private MyLinkedList<String> strings;

    @BeforeEach
    void setUp() {
        strings = new MyLinkedList<>();
        strings.add("hello");
        strings.add("world");
        strings.add(":)");
    }

    @AfterEach
    void tearDown() {
        strings = null;
    }

    @Test
    void insertAtStart() {
        String a = "monde";
        String b = "bonjour";
        strings.insertAtStart(a);
        strings.insertAtStart(b);
        for(String s : strings){
            assert(s.equals(b));
            break; //runs once before breaking, hence only checking first element
        }
    }

    @Test
    void size() {
        assert (strings.size() == 3);
        strings.add("another string");
        assert (strings.size() == 4);
    }

    @Test
    void isEmpty() {
        assert(!strings.isEmpty());
        strings.removeAll(strings);
        assert(strings.isEmpty());
    }

    @Test
    void contains() {
        String a = "testingContains";
        strings.add(a);
        assert(strings.contains(a));
    }

    @Test
    void iterator() {
    }

    @Test
    void add() {
        assert (strings.size() == 3);
        strings.add("hasan");
        assert (strings.size() == 4);

    }

    @Test
    void remove() {
        assert (strings.size() == 3);
        strings.remove("hello");
        assert (strings.size() == 2);
    }

    @Test
    void removeAll() {
        strings.removeAll(strings);
        assert(strings.isEmpty());
    }

    @Test
    void clear() {
        strings.clear();
        assert(strings.size() == 0);
    }

    /* Useless
    @Test
    void containsAll() {
    }

    @Test
    void addAll() {
    }


    @Test
    void retainAll() {
    } */

}