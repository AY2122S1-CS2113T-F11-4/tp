package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.exception.EmptyListException;
import seedu.duke.model.ShelfList;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ListCommandTest {
    private Shelf testList;
    private Command testCommand;
    //private final PrintStream standardOut = System.out;
    //private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws Exception {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
        testCommand = new ListCommand("test");
        //System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_itemsAlreadyInList_listsNormally() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        testList.addItem(new Item("LOTR", "10.2", "15.7"));
        assertTrue(testList.contains("HarryPotter"));
        assertTrue(testList.contains("LOTR"));
        String expected = "Here is the list of items:\n"
                + "1. HarryPotter (Cost: 16.1, Price: 25.12)\n"
                + "2. LOTR (Cost: 10.2, Price: 15.7)\n";
        assertEquals(expected, testCommand.execute());
    }

    @Test
    public void execute_noItemsInList_throwsEmptyListException() {
        assertThrows(EmptyListException.class, () -> testCommand.execute());
    }
}
