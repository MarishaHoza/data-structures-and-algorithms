package linkedList;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedListTest {

    LinkedList<String> testStringList;
    LinkedList<Integer> testIntegerList;

    @Before
    public void makeTestList() {
        testStringList = new LinkedList<>();
        testStringList.insert("Fish");
        testStringList.insert("Cat");
        testStringList.insert("Emu");
        testStringList.insert("Beaver");
        // the order of this list is Beaver > Emu > Cat > Fish > null

        testIntegerList = new LinkedList<>();
        testIntegerList.insert(12);
        testIntegerList.insert(24);
        testIntegerList.insert(13);
        testIntegerList.insert(8);
        // the order of this list is 8 > 13 > 24 > 12 > null
    }


    @Test
    public void testInsert_strings() {
        // tests if insert works properly on a LinkedList of strings
        testStringList.insert("Moose");
        assertTrue("Insert should work on Strings", testStringList.toString().contains("Moose"));
    }

    @Test
    public void testInsert_integers() {
        // tests if insert works properly on a LinkedList of integers
        testIntegerList.insert(1);
        assertTrue("Insert should work on Integers", testIntegerList.toString().contains("1"));
    }

    @Test
    public void testIncludes_middle() {
        // tests if includes returns true for an item that is included in the middle of the list
        assertTrue("Includes should return true if the value is present",
                testStringList.includes("Emu"));
    }

    @Test
    public void testIncludes_start() {
        // tests if includes returns true for an item that is included in the start of the list
        assertTrue("Includes should return true if the value is present at the head of the list",
                testStringList.includes("Beaver"));
    }

    @Test
    public void testIncludes_end() {
        // tests if includes returns true for an item that is included in the end of the list
        assertTrue("Includes should return true if the value is present at the tail/end of the list",
                testStringList.includes("Fish"));
    }


    @Test
    public void testIncludes_false() {
        // tests if includes returns false for an item not included in the list
        assertFalse("Includes should return false if the value is not present",
                testStringList.includes("Bumblebee"));
    }

    @Test
    public void testToString_empty() {
        // tests whether an empty linked list can be created and that the toString method works regardless

        LinkedList<String> emptyLinkedList = new LinkedList<>();
        assertNotNull("Should return a message", emptyLinkedList.toString());
    }

    @Test
    public void testToString_severalItems() {
        // tests whether the entire list is printed when toString is called
        assertTrue("The toString method should include all items in order",
                testStringList.toString().equals("List: Beaver, Emu, Cat, Fish"));
    }

    // ************************* Insertions Tests *************************

    @Test
    public void testAppend_string() {
        testStringList.append("Zebra");

        assertTrue("The list should include the new item at the end",
                testStringList.toString().endsWith("Zebra"));
    }

    @Test
    public void testAppend_manyIntegers() {
        testIntegerList.append(50);
        testIntegerList.append(4);
        testIntegerList.append(33);

        assertTrue("The list should include all the new items",
                testIntegerList.toString().endsWith("50, 4, 33"));
    }

    @Test
    public void testInsertBefore_middle() {
        testIntegerList.insertBefore(13, 72);
        assertTrue("The new item should be included in the list immediately before the target",
                testIntegerList.toString().equals("List: 8, 72, 13, 24, 12"));
    }

    @Test
    public void testInsertBefore_start() {
        testStringList.insertBefore("Beaver", "Giraffe");
        assertTrue("The new item should be included in the list immediately before the target",
                testStringList.toString().equals("List: Giraffe, Beaver, Emu, Cat, Fish"));
    }

    @Test
    public void testInsertBefore_end() {
        testStringList.insertBefore("Fish", "Giraffe");
        assertTrue("The new item should be included in the list immediately before the target",
                testStringList.toString().equals("List: Beaver, Emu, Cat, Giraffe, Fish"));
    }

    @Test
    public void testInsertAfter_middle() {
        testIntegerList.insertAfter(13, 72);
        assertTrue("The new item should be included in the list immediately after the target",
                testIntegerList.toString().equals("List: 8, 13, 72, 24, 12"));
    }

    @Test
    public void testInsertAfter_start() {
        testStringList.insertAfter("Beaver", "Giraffe");
        assertTrue("The new item should be included in the list immediately after the target",
                testStringList.toString().equals("List: Beaver, Giraffe, Emu, Cat, Fish"));
    }

    @Test
    public void testInsertAfter_end() {
        testStringList.insertAfter("Fish", "Giraffe");
        assertTrue("The new item should be included in the list immediately after the target",
                testStringList.toString().equals("List: Beaver, Emu, Cat, Fish, Giraffe"));
    }

    //https://www.baeldung.com/junit-assert-exception
    @Test (expected = NoSuchElementException.class)
    public void testInsertAfter_failure() {
        // trying to insert after a specific value which does not exist should throw the NoSuchElementException
        testStringList.insertAfter("Mouse", "Giraffe");
    }

    @Test
    public void testRemove_included() {
        String deletedNode = testStringList.remove("Cat");
        assertTrue("The deleted item should be returned",
                deletedNode.equals("Cat"));
    }

    @Test
    public void testRemove_notIncluded() {
        assertNull("If the value to delete is not included in the list, the return value should be null",
                testIntegerList.remove(999));
    }

    @Test
    public void testRemove_firstItem() {
        testIntegerList.remove(8);
        assertEquals("The remove should work on the first item and not break the list",
                "List: 13, 24, 12", testIntegerList.toString());
    }

    // ************************* kth From End Tests *************************

    // Where k is greater than the length of the linked list
    @Test (expected = NoSuchElementException.class)
    public void testKthFromEnd_kOutOfBounds() {
        testIntegerList.kthFromEnd(7);
    }

    // Where k and the length of the list are the same
    @Test (expected = NoSuchElementException.class)
    public void testKthFromEnd_kEqualsLength() {
        testIntegerList.kthFromEnd(4);
    }

    // Where k is not a positive integer
    @Test (expected = NoSuchElementException.class)
    public void testKthFromEnd_negativeK() {
        testStringList.kthFromEnd(-2);
    }

    // Where the linked list is of a size 1
    @Test
    public void testKthFromEnd_oneItem() {
        LinkedList<String> oneItemList = new LinkedList<>();
        oneItemList.insert("Surprisingly, it's just me!");

        assertEquals("kthFromEnd should still work on a list of one item if k = 0",
                "Surprisingly, it's just me!", oneItemList.kthFromEnd(0));
    }

    // “Happy Path” where k is not at the end, but somewhere in the middle of the linked list
    @Test
    public void testKthFromEnd_kInMiddle() {
        assertTrue("kthFromEnd should return the value k places from the end of the list",
                13 == testIntegerList.kthFromEnd(2));
        assertTrue("kthFromEnd should return the value k places from the end of the list",
                24 == testIntegerList.kthFromEnd(1));
    }

    // where k is one less than the list length, and the value returned should be the first item of the list
    @Test
    public void testKthFromEnd_kAtStart() {
        assertTrue("kthFromEnd should return the value at the start of the list if k is one less than the list length",
                testStringList.kthFromEnd(3).equals("Beaver"));
    }

    // ************************* merge lists tests *************************
    @Test
    public void testMergeLists_sameLength(){
        // List 1: head -> [1] -> [3] -> [2] -> X
        // List 2: head -> [5] -> [9] -> [4] -> X
        // Expected: head -> [1] -> [5] -> [3] -> [9] -> [2] -> [4] -> X

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.insert(1);
        list1.append(3);
        list1.append(2);

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.insert(5);
        list2.append(9);
        list2.append(4);

        assertEquals("The merge should work on two lists of the same length",
                "List: 1, 5, 3, 9, 2, 4", LinkedList.mergeLists(list1, list2).toString());

    }

    @Test
    public void testMergeLists_firstShorter(){
        // List 1: head -> [1] -> [3] -> X
        // List 2: head -> [5] -> [9] -> [4] -> X
        // Expected: head -> [1] -> [5] -> [3] -> [9] -> [4] -> X

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.insert(1);
        list1.append(3);

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.insert(5);
        list2.append(9);
        list2.append(4);

        assertEquals("The merge should work when the first list is shorter",
                "List: 1, 5, 3, 9, 4", LinkedList.mergeLists(list1, list2).toString());
    }

    @Test
    public void testMergeLists_secondShorter(){
        // List 1: head -> [1] -> [3] -> [2] -> X
        // List 2: head -> [5] -> [9] -> X
        // Expected: head -> [1] -> [5] -> [3] -> [9] -> [2] -> X

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.insert(1);
        list1.append(3);
        list1.append(2);

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.insert(5);
        list2.append(9);

        assertEquals("The merge should work when the second list is shorter",
                "List: 1, 5, 3, 9, 2", LinkedList.mergeLists(list1, list2).toString());
    }

}