package seedu.duke.model;

import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.model.exception.ShelfNotExistException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author yuejunfeng0909
/**
 * Represents a container that is able to store items.
 * e.g. A shelf
 */
public class Shelf {

    public static final String MESSAGE_INVALID_NAME_FORMAT = "Invalid item container name";
    public static final String MESSAGE_NULL_ITEM_ADDITION = "Null item cannot be added";

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final ArrayList<Item> items;
    private String name;
    private String remarks;

    /**
     * Constructor for the Shelf class.
     *
     * @param name new name for the Shelf
     *             consists of alphabet, number, space, underscore, round bracket and hyphen
     *
     * @throws IllegalModelArgumentException if the name contains other characters
     */
    public Shelf(String name) throws IllegalModelArgumentException, DuplicateShelfException {
        ShelfList shelfList = ShelfList.getShelfList();
        if (shelfList.existShelf(name)) {
            throw new DuplicateShelfException(name);
        }
        setName(name);
        items = new ArrayList<>();
        setRemark("");
        shelfList.addShelf(this);
        logger.log(Level.INFO, String.format("Shelf %s created", name));
    }

    public void deleteShelf() throws ShelfNotExistException {
        ShelfList.getShelfList().deleteShelf(this);
    }

    /**
     * Returns the name of the Shelf. e.g. Shelf-Scifi
     *
     * @return name of the Shelf
     */
    public String getName() {
        return name;
    }

    /**
     * Rename the Shelf to the new name.
     *
     * @param name New name for the Shelf
     *             consists of alphabet, number, space, underscore, round bracket and hyphen
     *
     * @throws IllegalModelArgumentException if the name contains other characters
     */
    public void setName(String name) throws IllegalModelArgumentException, DuplicateShelfException {
        String originalShelfName = (this.name == null) ? "new shelf" : this.name;
        String newName = name.trim();
        if (newName.matches("[a-zA-Z0-9 _()-]+") && !newName.isBlank()) {
            if (ShelfList.getShelfList().existShelf(newName)) {
                throw new DuplicateShelfException(newName);
            }
            this.name = newName;
            logger.log(Level.INFO, String.format("Successfully set Shelf %s's name as %s", originalShelfName, name));
        } else {
            logger.log(Level.WARNING, String.format(
                    "Trying to set Shelf %s's name as %s",
                    originalShelfName, newName));
            throw new IllegalModelArgumentException(MESSAGE_INVALID_NAME_FORMAT);
        }
    }

    /**
     * Adds the Item to the Shelf.
     *
     * @param item The item to be added
     *
     * @throws DuplicateItemException If the item already exists in the Shelf
     */
    public void addItem(Item item) throws DuplicateItemException {
        if (!contains(item)) {
            items.add(item);
            logger.log(Level.INFO, String.format("Successfully added Item %s into Shelf %s",
                    item.getName(), this.getName()));
        } else {
            logger.log(Level.WARNING, String.format("Item %s already exists in Shelf %s",
                    item.getName(), this.getName()));
            throw new DuplicateItemException(item.getName());
        }

        // sort items alphabetically
        sortItems();
    }

    /**
     * Remove the reference of the Item from the Shelf.
     *
     * @param item The Item to be removed from the Shelf
     *
     * @throws ItemNotExistException If the Item does not exist
     */
    public void deleteItem(Item item) throws ItemNotExistException {
        if (item == null) {
            logger.log(Level.WARNING, String.format("Trying to delete Null item from Shelf %s",
                    this.getName()));
            throw new NullPointerException();
        } else if (!contains(item)) {
            logger.log(Level.WARNING, String.format("Trying to delete Item %s which does not exist in Shelf %s",
                    item.getName(), this.getName()));
            throw new ItemNotExistException(item.getName());
        }
        items.remove(item);
    }

    /**
     * Replace an Item in the Shelf with another Item.
     *
     * @param originalItem The Item that is in the Shelf
     * @param updatedItem  The replacement Item
     *
     * @throws ItemNotExistException  If the originalItem does not exist in the Shelf
     * @throws DuplicateItemException if the updatedItem already exist in the Shelf
     */
    public void updateItem(Item originalItem, Item updatedItem)
            throws DuplicateItemException, ItemNotExistException {
        int index = items.indexOf(originalItem);
        if (originalItem == null) {
            logger.log(Level.WARNING, String.format("Trying to update Null item from Shelf %s",
                    this.getName()));
            throw new NullPointerException();
        } else if (contains(updatedItem)) {
            logger.log(Level.WARNING, String.format(
                    "Trying to replace Item %s with %s, which already exists in the Shelf %s",
                    originalItem.getName(), updatedItem.getName(), this.getName()));
            throw new DuplicateItemException(updatedItem.getName());
        } else if (index == -1) {
            logger.log(Level.WARNING, String.format(
                    "Trying to replace Item %s, which does not exist in Shelf %s",
                    originalItem.getName(), this.getName()));
            throw new ItemNotExistException(originalItem.getName());
        }
        items.set(index, updatedItem);

        // sort items alphabetically
        sortItems();

        assert items.get(index) == updatedItem : "Updated item should be at the index of the original item";
        logger.log(Level.INFO, String.format(
                "Successfully replace Item %s with %s in the Shelf %s",
                originalItem.getName(), updatedItem.getName(), this.getName()));
    }

    /**
     * Search through the Shelf and returns the first Item with the specified name.
     *
     * @param name The specified name of Item
     *
     * @return Item with the specified name
     *
     * @throws ItemNotExistException if no item has the name
     */
    public Item getItem(String name) throws ItemNotExistException {
        if (name == null) {
            logger.log(Level.WARNING, String.format("Trying to get Null item from Shelf %s",
                    this.getName()));
            throw new NullPointerException();
        }
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        logger.log(Level.WARNING, String.format("Item %s is not fond in the Shelf %s",
                name, this.getName()));
        throw new ItemNotExistException(name);
    }

    /**
     * Return the Item at the specified index.
     *
     * @param index The index of the Item
     *
     * @return The Item at the specified index
     *
     * @throws IndexOutOfBoundsException if index >= number of items in the item container
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * Search through the Shelf and returns the first Item with the specified name.
     *
     * @param itemID The specified name of Item
     * @return Item with the specified name
     * @throws ItemNotExistException if no item has the name
     */
    public Item getItemByID(String itemID) throws ItemNotExistException {
        if (name == null) {
            logger.log(Level.WARNING, String.format("Trying to get Null item from Shelf %s",
                    this.getName()));
            throw new NullPointerException();
        }
        for (Item item : items) {
            if (item.getID().equals(itemID)) {
                return item;
            }
        }
        logger.log(Level.WARNING, String.format("Item %s is not fond in the Shelf %s",
                name, this.getName()));
        throw new ItemNotExistException("with ID " + itemID);
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemark(String newRemarks) {
        if (newRemarks.isBlank()) {
            remarks = "";
        } else {
            remarks = newRemarks;
        }
    }

    /**
     * Returns true if there is an Item in the Shelf with the specified name.
     *
     * @param name Name of the item
     *
     * @return True if the item exists
     */
    public boolean contains(String name) {
        try {
            getItem(name);
        } catch (ItemNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if there is an Item in the Shelf with the specified name.
     *
     * @param item the specified item
     *
     * @return True if the item exists
     */
    public boolean contains(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        return items.contains(item);
    }

    /**
     * Returns true if there is an Item in the Shelf with the specified ID.
     *
     * @param itemID ID of the item
     * @return True if the item exists
     */
    public boolean containsGivenID(String itemID) {
        try {
            getItemByID(itemID);
        } catch (ItemNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the number if items in the item container.
     */
    public int getItemCount() {
        return items.size();
    }

    private void sortItems() {
        Comparator<Item> byName = (Item o1, Item o2) -> o1.getName().compareTo(o2.getName());
        items.sort(byName);
    }

    /**
     * Returns a string of names of the items in the Shelf separated by "\n".
     */
    @Override
    public String toString() {

        // sort items alphabetically
        sortItems();

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            output.append((i + 1) + ". " + items.get(i).getName() + "\n");
        }
        return output.toString().trim();
    }

    /**
     * Get the number of items in this container.
     *
     * @return the number of items in this container
     */
    public int getSize() {
        return items.size();
    }
}
