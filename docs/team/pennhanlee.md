---
layout: page
title: Penn Han's Project Portfolio Page
---

## Project: _bookmark_

_bookmark_ is a desktop book management application used for managing user added books to manage book records such as
last read page (bookmark), added goals and notes. A user will interact with bookmark through a GUI created with JavaFx and
invoke commands through a Command Line Interface style.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=F13&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=pennhanlee&tabRepo=AY2021S1-CS2103T-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added the ability to find Word Suggestions for typing errors during `Find` command.
  * What it does: When `Find` command is invoked, there might be spelling errors or inaccurate queries given by the user. The feature will help to search for
  words that are similar and return the most likely word as a suggestion to the user. However, if the given word to search has no similar words, there will not be
  any suggestions returned. 
  * Justification: This feature improves the product significantly because a user can make mistakes when finding books which will occur often as _bookmark_ uses the CLI to invoke commands. _bookmark_ will
  provide suggestions and improve the User Experience as users will be able to find their intended books more easily.
  * Highlights: This enhancement require the creation with a new data structure into model to store the individual words in each book's name, genre and tag. 
  * Credits: The suggestion feature is based off the damerau-levenstein algorithm. [Algorithm_link](https://github.com/KevinStern/software-and-algorithms/blob/master/src/main/java/blogspot/software_and_algorithms/stern_library/string/DamerauLevenshteinAlgorithm.java)

* **New Feature**: improved Help Window to display Command Summary for quick access.
  * What it does: when `help` command is activated, the Help Window will display a summarised version of the User Guide displaying the 
  commands available and the CLI format that it should follow.
  * Justification: This feature improves the User Experience of _bookmark_ significantly as the newly added commands adds to a long list of
  prefixes and CLI formats that a user will have to follow to navigate through the application. As opposed to providing a link to the User Guide, 
  experienced Users who have already visited the User Guide can look for what they need in the Help Window quickly.
  * Highlights: This enhancement resulted in the need for creation of different help page command classes. It will need to be maintained once new commands are added or existing commands are updated.
  * Credits: JavaFX Scenebuilder


* **Enhancements to existing features**:
  * Updated codebase for Add Command and other relevant classes to accept Books and its attributes.  (Pull requests [\#33](), [\#34]())
  * Updated constants in various TestUtil to be used in tests and updated tests to accommodate to 
  revamped Add Command(Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Add` and `Suggestion Feature` and `Help` [\#72]()
    * Edited Quickstart to installation and starting the application with Images
    * Standardise all comments to follow a format: Command Overview Examples, Important Detail and Warning to existing documentation: [\#74]()
    
  * Developer Guide:
    * Added implementation details of the `Add` feature.
    * Added implementation details of `Suggestion` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
