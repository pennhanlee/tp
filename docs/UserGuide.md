---
layout: page
title: User Guide
---

bookmark is a **desktop app for tracking reading progress and book loans, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, bookmark can get your reading and book management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start [coming soon]

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all books.

   * **`add`**`n/Fifty Shades of Gray total/350 bookmark/200` : Adds a book titled `Fifty Shades of Grey` to the Storage Library.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>


### Adding a person: `add`


Adds a book to the Storage Library.

Format: `add n/BOOK_TITLE tp/TOTAL_PAGES b/PAGE_NUMBER`

Examples:
* `add n/Fifty Shades of Gray tp/350 b/200`
* `add n/Harry Potter and the Chamber of Secrets tp/1500 b/25`


### Listing all persons : `list`

Shows a list of all books in the Storage Library.

Format: `list`

### Locating persons by name: `find` [coming soon]

Find books whose names contain any of the given keywords.


### Deleting a book : `delete`

Deletes the specified book from the book list.

Format: `delete INDEX`

* Deletes the book at the specified `INDEX`.
* The index refers to the index number shown in the displayed book list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd book in the book list.
* `find MyBook` followed by `delete 1` deletes the 1st book in the results of the `find` command.

### Editing a person : `edit`

Edits an existing book in the list.

Format: `edit INDEX b/PAGE_NUMBER​`

* Edits the book at the specified `INDEX`. The index refers to the index number shown in the displayed book list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 b/101` Edits the bookmark of the 1st book to be `101`.
* `find MyBook` followed by `edit 1 b/101` edits the 1st book in the results of the `find` command and sets the bookmark page to `101`.

### Clearing all entries : `clear` [coming soon]

Clears all entries from the Storage Library.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

bookmark data will be saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous bookmark home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**List** | `list`
**Edit** | `edit INDEX b/PAGE_NUMBER`
