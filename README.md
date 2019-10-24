# SortingProject 3
The goal of the sorting project is to completely redesign and modularise my existing sorter program (SP 2, which I'll 
keep private out of shame), and to expand it with more features.

## How to use 
The input handling is still somewhat janky, and the program isn't very user friendly yet, so it just assumes that it 
should parse and sort the input.

To input, paste the RAW input into the console then press ENTER a couple of times until the program moves on (will be 
improved in the future).

## Feature List
The features aren't very fancy yet, but all of the features from SP 2 is here, with a few improvements.

### Improvements from SP2
- **Modularity:** SP 3 has a modular structure that should make it significantly easier to modify for personal usage. 
Should also make it easier to expand the project.
- **Actual documentation:** Most of the features should be explained decently well this time around.
- **More powerful parsing & input handling:** Input no longer requires requires inputting the number of items given, 
and the parser works much better now, and is much simpler to modify.
- **Actual testing:** SP 2 didn't have any form of tests, that has been improved upon (still needs more work though).

## Planned Features
- **Improved Parsing**

  Parser will be able to interpret certain sub-strings as scores or *Modifiers* or *Overrides*.
  
- **Modifiers**

  Modifiers are planned to be certain keywords in the notes that will modify the score positively or negatively.

- **Overrides**

  Overrides are planned to be certain item names/id that override given score of the item.
  
- **Interactive interface**

  I want to make the program interactive to allow easily modifying items or implementing overrides or modifiers without having to edit files directly.
  
  The user should also be given more power on how the items are output.
  
  An interactive interface could also allow the user to use different parsers/IO handlers and similar to fit their needs.
  
- **Safe Storage**

  I plan to include secure storage of files related to modifiers, overrides and similar in the future.
  
- **More to come**

  There will likely be more planned features added later, and I'm always open to input.