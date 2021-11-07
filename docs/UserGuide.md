# User Guide

## Contents of User Guide
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Usage](#usage)
- [Features](#features)
  * [`help` - Show help](#display-help-message)
  * [`create` - Create a shelf](#create-a-shelf)
  * [`remove` - Remove a shelf](#remove-a-shelf)
  * [`add` - Add new items](#add-new-items)  
  * [`delete` - Delete item from list](#delete-an-item)
  * [`list` - List all items](#listing-all-items)
  * [`search` - Search for item](#search-for-item)
  * [`get` - Retrieve information of an item](#get-information-about-an-item)
  * [`edit` - Update an item](#edit-an-item)
  * [`sell` - Sell an item](#sell-an-item)
  * [`markup` - Markup price of an item](#markup-price-of-an-item)
  * [`report` - Generate sales report](#generate-sales-report)
  * [`bye` - Exit command](#exit-program)
  * [`[coming in v3.0]` - Add customer rating & review for each item](#add-customer-rating--review-for-each-item-coming-in-v30)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction

Clivershelf allows bookstore owners to create a management system of inventories and finances to better manage their
business, where they can view these information in a user-friendly manner.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download &#11015; the latest version of `CliverShelf` from [here](https://github.com/AY2122S1-CS2113T-F11-4/tp/releases).
3. Run `cd (path to the folder containing CliverShelf.jar)` and `java -jar CliverShelf.jar` to start using Clivershelf.
4. You should see the following:
```
                                                                  .............................................................
                                                                  : Hello from                                                :
                                                                  :   _____ _      _____              _____ _          _  __  :
                                                                  :  / ____| |    |_   _|            / ____| |        | |/ _| :
                                                                  : | |    | |      | |_   _____ _ _| (___ | |__   ___| | |_  :
                                                                  : | |    | |      | \ \ / / _ \ '__\___ \| '_ \ / _ \ |  _| :
                                                                  : | |____| |____ _| |\ V /  __/ |  ____) | | | |  __/ | |   :
                                                                  :  \_____|______|_____\_/ \___|_| |_____/|_| |_|\___|_|_|   :
                                                                  : What can I do for you?                                    :
                                                                  ................................................................
                                                                            ...................................................
                                                                            : Enter 'help' for the list of available commands :
                                                                            ......................................................
```
5. Type `help` to see all the commands available. You may try some example commands below:
   * `create shlv/book1` - creates a new shelf named `book1`
   * `add  n/Geronimo shlv/book1 p/15.90 s/23.99 q/10` - Adds a Book "Geronimo" to the shelf name "book1" 
   * `delete shlv/book2 i/3` - Deletes item of `index 3` from the shelf `book2`
   * `list shlv/book1` - list the items from shelf name "book1"
   * `get shlv/book1 i/2` - get information about an item of `index 2` in shelf `book1`
   * `bye` - exit the app
6. Refer to the [Features](#features) below for details of all available commands.

## Usage

Notes about the command format:

* Words in UPPER_CASE are the parameters to be supplied by the user. E.g. in delete `n/NAME`, `NAME` is the parameter
  which can be used as add n/Pilot Pen.
* Items in square brackets are optional and can only be specified **once** for that command. 
  E.g. `get n/NAME [p/PROPERTY]` can be used as `get n/Pencil p/cost` or as `get n/Pencil`.
* &#9888; Parameters with `NAME` & `SHELF_NAME` cannot contain special character or symbols
  such as "!@#$%^&*[]{}/|\+=`~<>?,."
* &#9888; Commands that do not take in extra unspecified parameters, including additional trailing spaces from 
  user guide will be flagged as **invalid** command formats.
* &#9888; Program input is **case-sensitive** & input sequence follows what has been mentioned in this user guide, any reordering 
  of parameters will be flagged as **invalid** command format
* &#9888; Program only takes in input typed in english character set, does not accept input of other languages such as 
  Chinese characters, Japanese characters.
* &#9888; Index of items always start from `1`.

## Features

### Display help message

Displays help message with list of performable actions.

Format: `help`

Expected outcome:
```
                            ...................................................................................................
                            : Here are the performable actions:                                                               :
                            : Words in UPPER_CASE are the parameters to be supplied by the user.                              :
                            : Items in square brackets [ ] are optional.                                                      :
                            : ____________________________________________________________________________________________    :
                            : Description: Command format                                                                     :
                            : ____________________________________________________________________________________________    :
                            : 1. Get help : help                                                                              :
                            : 2. Create new shelf: create shlv/SHELF_NAME                                                     :
                            : 3. Remove existing shelf: remove shlv/SHELF_NAME                                                :
                            : 4. Add item: add n/NAME shlv/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS] :
                            : 5. Delete item: delete shlv/SHELF_NAME i/INDEX                                                  :
                            : 6. List items: list [shlv/SHELF_NAME]                                                           :
                            : 7. Search item: search KEYWORD                                                                  :
                            : 8. Get information of item : get shlv/SHELF_NAME i/INDEX                                        :
                            : 9. Edit an item: edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE                                :
                            : 10. Sell an item: sell shlv/SHELF_NAME i/INDEX                                                  :
                            : 11. Markup price of item: markup shlv/SHELF_NAME i/INDEX [%/PERCENT_MARKUP]                     :
                            : 12. Generate sales report: report t/TYPE ym/START-YEAR-MONTH [ym/END-YEAR-MONTH]                :
                            : 13. Exit program: bye                                                                           :
                            ......................................................................................................
```
### Create a shelf

Creates a shelf to store items.

&#9888; **Note that shelf has to be first created before [item can be added](#add-new-item).**

&#9888; **Note `SHELF_NAME` should only consist of alphabets and integers (Eg: `book1`)**

Format: `create shlv/SHELF_NAME`

Example: **Create shelf named `book2`**
```
create shlv/book2
```

Expected outcome:
```
                                                                                            ...................................
                                                                                            : Shelf "book2" has been created. :
                                                                                            ......................................
```

### Remove a shelf

Removes a shelf.

&#9888; **Only can remove an empty shelf. Cannot remove a shelf with existing items.**


Format: `remove shlv/SHELF_NAME`

Example: **Remove shelf `book2`**
```
remove shlv/book2
```

Expected outcome:
```
                                                                                            ...................................
                                                                                            : Shelf "book2" has been removed. :
                                                                                            ......................................
```

### Add new items

Adds a new item to the inventory, specifying its name, shelf, purchase cost, selling price, quantity (and remarks if any).

**Important notes:**

&#9888; **[Shelf has to be first created](#create-a-shelf) before item can be added.**

&#9888; **Maximum quantity of items in a shelf is 999.**

&#9888; **After successfully adding the item, 8 alphanumeric characters will be printed. This is the unique ID corresponding to the item. This ID will be used to required for [selling an item](#sell-an-item).**

&#9888; As a bookstore owner, you are **strongly encouraged to use this ID to label your item** before placing it on the shelf. When the customer wishes to purchase an item, you will need the ID from the label of that item to sell. Alternatively, you can use [`get` function](#get-information-about-an-item) to retrieve the ID of an item.

Format: `add n/NAME shlv/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]`

Example: **Add 1 book titled "Harry Potter" with a purchase cost of $27 and selling price of $37 to shelf `book1`. Also added additional remarks**
```
add n/Harry Potter shlv/book1 p/27 s/37 q/1 r/50% discount
```

Expected outcome
```
                                                                   ............................................................
                                                                   : This item has been added to the list. Its unique ID is:  :
                                                                   : 6ddf90b0                                                 :
                                                                   ...............................................................
```

Example: **Add 99 Pilot P100 stationary with a purchase cost of $1 and selling price of $1.50 to shelf `stationary1`.**
```
add n/Pilot P100 shlv/stationary1 p/1 s/1.5 q/99
```

Expected outcome:
```
                                            ...................................................................................
                                            : 99 items have been added to the list. Use Get command to view their unique IDs. :
                                            ......................................................................................
```

### Delete an item

Deletes item from the inventory by specifying its shelf name and its index in the shelf.

&#9888; **For index, we accept both truncated and raw inputs (e.g. for index 1, both "1" and "001" are accepted.)**

Format: `delete shlv/SHELF_NAME i/INDEX`

Example: **Delete item of `index 1` in shelf `book2`**
```
delete shlv/book2 i/1
```
Expected outcome:
```
                                                                                  .............................................
                                                                                  : This item has been removed from the list. :
                                                                                  : Name: Geronimo                            :
                                                                                  : Cost: 17.90                               :
                                                                                  : Price: 20.90                              :
                                                                                  : Remarks:                                  :
                                                                                  ................................................
```

### Listing all items 

Shows a list of all items in the inventory list.
The attributes shown are: Item name, purchase cost, selling price, quantity, and if they have remarks.

Under remarks, `x` signifies that the item does not have any remarks, while `o` signifies that it has remarks.
Users can use the [`get` function](#get-information-about-an-item) to display the items' ID and remarks.

Format: `list [shlv/SHELF_NAME]`

Example: **Listing all items in all shelves**
```
list 
```
Expected outcome:
```
                      .........................................................................................................
                      : Here is the list of items:                                                                            :
                      : [book1]:                                                                                              :
                      :    No    |                        Item                        |   Cost    |   Price   | Qty  | Remark :
                      : ----------------------------------------------------------------------------------------------------- :
                      :  001     | Harry Potter                                       | 27.00     | 37.00     | 1    |   o    :
                      :  002-100 | Pilot P100                                         | 1.00      | 1.50      | 99   |   x    :
                      : [warehouse]:                                                                                          :
                      :    No    |                        Item                        |   Cost    |   Price   | Qty  | Remark :
                      : ----------------------------------------------------------------------------------------------------- :
                      :  001     | sample item                                        | 12.25     | 25.00     | 1    |   x    :
                      ............................................................................................................
```

Example: **Listing all items under `book1` shelf**
```
list shlv/book1
```
Expected outcome:
```
                      .........................................................................................................
                      : Here is the list of items:                                                                            :
                      :    No    |                        Item                        |   Cost    |   Price   | Qty  | Remark :
                      : ----------------------------------------------------------------------------------------------------- :
                      :  001     | Harry Potter                                       | 27.00     | 37.00     | 1    |   o    :
                      :  002-100 | Pilot P100                                         | 1.00      | 1.50      | 99   |   x    :
                      ............................................................................................................
```

### Search for item

Search for any item that has ID, name, remark or pricing that contains the specified keyword.
Shows a list of all items in the inventory list.
The attributes shown are: Item ID, Item name, purchase cost, selling price, quantity, and remark.

Users can use the [`get` function](#get-information-about-an-item) to display more information about the item.

Format: `search KEYWORD`

Example: **Search for "potter"**
```
search potter
```
Expected outcome:
```
                      .........................................................................................................
                      : Here are the items that has matching name                                                             :
                      : ----------------------------------------------------------------------------------------------------- :
                      :     ID   |                   Item                   |   Cost    |   Price   |          Remark         :
                      : ----------------------------------------------------------------------------------------------------- :
                      :  68bbe345| Harry Potter                             | 16.1      | 25.12     |                         :
                      :  1f36e637| Harry Potter                             | 16.1      | 25.12     | Cover is dirty          :
                      :  32709393| Harry Potter                             | 16.1      | 25.12     | Good condition          :
                      ............................................................................................................
```

Example: **Search for "dirty"**
```
search dirty
```
Expected outcome:
```
                      .........................................................................................................
                      : Here are the items that has matching remark                                                           :
                      : ----------------------------------------------------------------------------------------------------- :
                      :     ID   |                   Item                   |   Cost    |   Price   |          Remark         :
                      : ----------------------------------------------------------------------------------------------------- :
                      :  1f36e637| Harry Potter                             | 16.1      | 25.12     | Cover is dirty          :
                      ............................................................................................................
```

### Get information about an item

Retrieves information of an item. 

&#9888; **For index, we accept both truncated and raw inputs (e.g. for index 1, both "1" and "001" are accepted.)**

Format: `get shlv/SHELF_NAME i/INDEX`

Example: **Retrieves all the information of item `index 1` in shelf `book1`, including its unique and remarks**
```
get shlv/book1 i/1
```
Expected outcome:
```
list shlv/book1
                      .........................................................................................................
                      : Here is the list of items:                                                                            :
                      :    No    |                        Item                        |   Cost    |   Price   | Qty  | Remark :
                      : ----------------------------------------------------------------------------------------------------- :
                      :  001     | Harry Potter                                       | 27.00     | 37.00     | 1    |   o    :
                      :  002-100 | Pilot P100                                         | 1.00      | 1.50      | 99   |   x    :
                      ............................................................................................................

get shlv/book1 i/1                                                                                      
                                                                                      .........................................
                                                                                      : Here is the information of your item: :
                                                                                      : Name: Harry Potter                    :
                                                                                      : Cost: 27                              :
                                                                                      : Price: 37                             :
                                                                                      : ID: 6ddf90b0                          :
                                                                                      : Remarks: 50% discount                 :
                                                                                      ............................................
```

### Edit an item

Updates the properties of an item. You need to specify which item to edit using its shelf name and index number in that shelf, and you also need to specify which property you want to edit and what the new value will be.

&#9888; **For index, we accept both truncated and raw inputs (e.g. for index 1, both "1" and "001" are accepted.)**

Format: `edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE`

&#128221; Only 3 `PROPERTY` can be edited. Use `p/purchase cost` to select the cost of the item to edit, or use `p/selling price` to select the item price to edit, or use `p/remarks` to select the remarks of the item to edit. 

&#9888; **Maximum price and cost of any item is $9999.99 (Only allow values with 2 decimal points and below).**

Example: **Update selling price of item with `index 1` as $30**
```
edit shlv/book1 i/1 p/selling price v/30
```

Expected outcome:

```
                                                                                                ...............................
                                                                                                : This item has been updated. :
                                                                                                : Name: Harry Potter          :
                                                                                                : Cost: 20                    :
                                                                                                : Price: 30                   :
                                                                                                : Remarks: new book           :
                                                                                                ..................................

```
Example: **Update purchase cost of item with `index 2` as $23.50**
```
edit shlv/book1 i/2 p/purchase cost v/23.5 
```

Expected outcome:
```
                                                                                                ...............................
                                                                                                : This item has been updated. :
                                                                                                : Name: Harry Potter          :
                                                                                                : Cost: 23.5                  :
                                                                                                : Price: 30.90                :
                                                                                                : Remarks:                    :
                                                                                                ..................................
```

### Sell an item

Mark an item as sold. The item will be removed from the shelf and will be added to your sales report.

Format: `sell id/ITEM_ID
&#128221; the ID of an item is the 8 alphanumeric characthers printed out after you have added the item to a shelf.   

Example: **Sell the book "Harry Potter" which was previously added in "Add new items" section. Its ID is 76a3e297.**
```
sell id/76a3e297
```

Expected outcome:
```
                                                                                                   ............................
                                                                                                   : This item has been sold. :
                                                                                                   : Name: Harry Potter       :
                                                                                                   : Cost: 27                 :
                                                                                                   : Price: 37                :
                                                                                                   : Remarks: 50% discount    :
                                                                                                   ...............................
```


### Markup price of an item

Checks the current price markup of an item and calculates user estimated markup percent with the corresponding price change.
If no user markup percent is specified, Clivershelf will calculate the percent markup in multiples of 20. 

&#9888; **For index, we accept both truncated and raw inputs (e.g. for index 1, both "1" and "001" are accepted.)**

Format: `markup shlv/SHELF_NAME i/INDEX [%/PERCENT_MARKUP]`

&#9888; Maximum allowed percentage is 999.99% (Only up to 2 decimal points input is allowed)

Example: **Check the markup percent estimates of the item `index 2` in shelf `book1`**
```
markup shlv/book1 i/2
```

Expected outcome:
```
                                                                          .....................................................
                                                                          : Item: Pilot P100                                  :
                                                                          : Cost: 1, Price: 1.5                               :
                                                                          : Amount Difference: 0.5                            :
                                                                          : Current Mark Up: 50%                              :
                                                                          : markup: 0%, increase: $0.00, Final price: $1.00   :
                                                                          : markup: 20%, increase: $0.20, Final price: $1.20  :
                                                                          : markup: 40%, increase: $0.40, Final price: $1.40  :
                                                                          : markup: 60%, increase: $0.60, Final price: $1.60  :
                                                                          : markup: 80%, increase: $0.80, Final price: $1.80  :
                                                                          : markup: 100%, increase: $1.00, Final price: $2.00 :
                                                                          ........................................................
```

Example: **Check the 5% markup of the item `index 2` in shelf `book1`**
```
markup shlv/book1 i/2 %/5
```

Expected outcome:
```
markup shlv/book1 i/2 %/5
                                                                            ...................................................
                                                                            : Item: Pilot P100                                :
                                                                            : Cost: 1, Price: 1.5                             :
                                                                            : Amount Difference: 0.5                          :
                                                                            : Current Mark Up: 50%                            :
                                                                            : markup: 5%, increase: $0.05, Final price: $1.05 :
                                                                            ......................................................
```

### Generate sales report

Generates the sales report for given month.

Format: `report t/CONTENT_TYPE ym/START-YEAR-MONTH [ym/END-YEAR-MONTH]`

&#128221; Only 2 `CONTENT_TYPE` can be specified either `t/stats` to view statistics of sold items or `t/items` to view the list of all items

&#128221; `ym/START-YEAR-MONTH` & `ym/END-YEAR-MONTH` need to follow the format `ym/YYYY-MM`
<br> e.g. Jan 2020 is represented as `ym/2020-01` or Dec 2021 is `ym/2021-12`

&#128221; If only 1 `ym/START-YEAR-MONTH` parameter is specified, report will be generated for that particular month in the specified year.
If both `ym/START-YEAR-MONTH` & `ym/END-YEAR-MONTH` are specified, report in between the 2 date ranges will be generated

Example: **Generate sales report for the month of Nov 2021**
```
report t/stats ym/2021-11
```

Expected outcome:
```
                                                                                    ...........................................
                                                                                    : Total Purchase Cost: $ 28.00            :
                                                                                    : Total Selling Price: $ 31.50            :
                                                                                    : Total Profits: $ 3.50                   :
                                                                                    : Gross Profit Margin (in percent): 11.11 :
                                                                                    ..............................................
```
Example: **Generate sales report in between Oct 2021 and Nov 2021 inclusive**
```
report t/items ym/2021-10 ym/2021-11
```

Expected outcome:
```
                          .....................................................................................................
                          : No  |                    Item                 |  Cost   |  Price  |  Profit |      Sold Time      :
                          : ------------------------------------------------------------------------------------------------- :
                          : 1   | Harry Potter                            | 27      | 30      | 3       |2021-11-07 01:51:47  :
                          : 2   | Pilot P100                              | 1       | 1.5     | 0.5     |2021-11-07 01:51:22  :
                          ........................................................................................................
```

### Exit program

Exits from Clivershelf.

Format: `bye`

Expected outcome:
```
                                                                                                          .....................
                                                                                                          : See you next time :
                                                                                                          ........................
```

### Add customer rating & review for each item `[coming in v3.0]`

Users would be able to record the customer ratings and review of each item sold in the bookstore. 

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the save file `data/Data.txt` on your current computer into the other computer that you wish to use. 
Launch the program again and the saved data should load.

**Q**: What happens if my app crashes half-way?

**A**: Do not worry as your data will always be automatically saved in the text file whenever there are any changes made throughout the program. 
You can simply restart the program and your last updated data will be loaded.

**Q**: I have some suggestions/found some bugs for CLIver Shelf, what should I do?

**A**: Please do not hesitate to contact the [team](https://github.com/AY2122S1-CS2113T-F11-4/tp/blob/master/docs/AboutUs.md).

## Command Summary

|Commands    |Format, Examples                                                                                                                 |
| ----       | ----                                                                                                                            |
|**Help**    | `help`                                                                                                                          |
|**Create**  | `create shlv/SHELF_NAME` <br> eg: `create shlv/book1`                                                                                    |
|**Remove**  | `remove shlv/SHELF_NAME` <br> eg: `remove shlv/book4`                                                                                    |
|**Add**     | `add n/NAME shlv/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]` <br> eg: `add n/Harry Potter shlv/book1 p/27 s/37 q/5`|
|**Delete**  | `delete shlv/SHELF_NAME i/INDEX` <br> eg: `delete shlv/book2 i/1`                                                                         |
|**List**    | `list [shlv/SHELF_NAME]` <br> eg: `list shlv/stationary`                                                                                |
|**Search**  |
|**Get**     | `get shlv/SHELF_NAME i/INDEX` <br> eg: `get shlv/book1 i/1`                                                         |
|**Edit**    | `edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE` <br> eg: `edit shlv/book1 i/1 p/cost v/100`                      |
|**Sell**    | `sell shlv/SHELF_NAME i/INDEX` <br> eg: `sell shlv/book1 i/1`                                                                                    |
|**Markup**  | `markup shlv/SHELF_NAME i/INDEX [%/PERCENT_MARKUP]` <br> eg: `markup shlv/book1 i/1 %/5`                                                                                    |
|**Report**  | `report t/CONTENT_TYPE ym/YEAR-MONTH [ym/YEAR-MONTH]` <br> eg: `report c/stats ym/2021-10`                                                                                    |
|**Bye**     | `bye`                                                                                                                           |


