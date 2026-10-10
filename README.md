# Java Simple Library Project

A simple console-based library management application written in Java.  
It stores books, members, and IDs in local text files.

## Project Structure

- `/home/runner/work/java_simple_lib_project-01./java_simple_lib_project-01./digital_libary/DigitalLibrary.java` — main source file
- `/home/runner/work/java_simple_lib_project-01./java_simple_lib_project-01./digital_libary/book.txt` — books data
- `/home/runner/work/java_simple_lib_project-01./java_simple_lib_project-01./digital_libary/member.txt` — members data
- `/home/runner/work/java_simple_lib_project-01./java_simple_lib_project-01./digital_libary/id_counter.txt` — next generated ID

## Features

- Add and remove books
- Add and remove members
- Borrow and return books
- Show available books
- Reload data from files
- Save data before exit

## Requirements

- Java 8+ (JDK)

## Compile and Run

From the project root:

```bash
cd /home/runner/work/java_simple_lib_project-01./java_simple_lib_project-01./digital_libary
javac -d . DigitalLibrary.java
java completed_working_code.DigitalLibrary
```

> Run the program from the `digital_libary` directory so it can read and write `book.txt`, `member.txt`, and `id_counter.txt`.

## Data Format

### `book.txt`
Each line:

```text
id|title|author|available
```

Example:

```text
15|quran|words of allah|true
```

### `member.txt`
Each line:

```text
id|name|borrowedState|borrowedBookId
```

If no borrowed book, `borrowedBookId` is `-1`.

### `id_counter.txt`
Contains one integer: the next ID to use.

## Notes

- This project is intentionally simple and keeps logic in one Java source file.
- File names and some class names follow the original project naming.
