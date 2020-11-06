---
layout: page
title: Le Yang's Project Portfolio Page
---

## Project: _bookmark_

_bookmark_ is a desktop book management application that allows user to manage their books and reading progress. It allows
users to track which page they stopped reading at. It also has features like adding goals and notes to help improve their reading
experience. A user will interact with _bookmark_ through a GUI created with JavaFx and
invoke commands through a Command Line Interface.

Given below are my contributions to the project.

### Summary of Contributions
* **Code contributed:** [RepoSense](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=CS2103T-F13-2&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=TanLeYang&tabRepo=AY2021S1-CS2103T-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Enhancement:** Added the ability to **undo previous commands** and **redo undone commands**.
  * What it does: Allows the user to undo previous commands or redo commands that have been undone.
  
  * Justification: This feature enhances the application by allowing the user to easily undo any mistakes made when
    entering a command. As an application that relies on a Command Line Interface, typing mistakes are inevitable, hence
    the undo command would be very useful in providing convenience to the user. The redo command also allows the user to
    move back and forth in history, allowing users to conveniently compare changes.
  
  * Highlights: This feature must work with all other commands as well as future commands to be added and not slow
    down the application. Hence proper brainstorming and planning of the implementation had to be done to ensure the 
    implementation can be easily extended to work with future commands as well as be as memory efficent as possible.
    The implementation was challenging as the application will display the book in different ways according to user entered
    commands, hence there was not only a need to keep track of the changes to the books stored,
    but also how the books were being displayed in order to allow the undo/redo command to bring the application back to
    exactly how it was.
    
  * Code contributed for this enhancement: [#115](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/115), 
    [#140](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/140)

* **Enhancement:** Added ability for Ui to switch between a summarised and detailed view of the books stored.

  * What it does: Allows the user to select and view a specific book in detail. The application by default shows a 
    summarised view of books but users can use the view command to select a specific book and view its complete details. 
  
  * Justification: This feature enhances the application by ensuring that users are not overwhelmed by too much information.
    Some information, such as the content of the notes added to the books, will only be shown when viewing a book in
    detailed view. This prevents the Ui from being overcrowded with too much information and allows the user to focus
    their attention on a specific book of interest, improving user experience.
    
  * Highlights: This feature must work fast as switching between views should be as smooth as possible to ensure a good
    user experience. Hence, special care was taken to minimize unecessary re-renderings. Furthermore, the Ui needed to be
    in sync with changes to the Model which was accomplished by following the Observer design pattern.
  
  * Code contributed for this enhancement: [#92](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/92),
    [#121](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/121), [#165](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/165)
   
* **Minor enhancement:** Designed the Ui of _bookmark_. Added a sidebar to the Ui to display meta-data to user.
  
  * Code contributed for this enhancement: [#92](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/92)

* **Contributions to team member's enhancements:**
  
  * Did the initial refactoring of the code inherited from the AB3 project. [#76](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/76)

* **Documentation:**<br>
  The exact sections I wrote have been reproduced in the [Contributions to the Developer Guide](#contributions-to-the-developer-guide)
  and [Contributions to the User Guide](#contributions-to-the-user-guide) sections.
  
  * User Guide:
    * Wrote documentation for the `undo` and `redo` features. [#115](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/103)
  
  * Developer Guide:
    * Wrote documentation and added UML diagrams for the `Ui view switching` and `Undo/redo` features. [#103](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/103),
      [#115](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/103)
    * Added more use cases into the Developer Guide. [#170](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/170)
    * Updated the manual testing section of the Developer Guide. [#170](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/170)

* **Project Management and Dev Ops:**
  * Managed releases v1.2 and v1.3 on GitHub
  * Set up the team GitHub repo
  * Managed the team repo's issue tracker
  
* **Community:**
  * PRs reviewed (with non-trivial review comments): [#107](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/107),
    [#97](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/97), [#168](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/168),
    [#119](https://github.com/AY2021S1-CS2103T-F13-2/tp/pull/119)
  
  * Contributed to forum discussions (e.g [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/170), [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/182))
  
  * Reported bugs and suggestions for other teams in the class (e.g [1](https://github.com/TanLeYang/ped/issues/4),
  [2](https://github.com/TanLeYang/ped/issues/3), [3](https://github.com/TanLeYang/ped/issues/1))
  
### Contributions to the Developer Guide

Given below are the sections I contributed to the Developer's Guide

### Contributions to the User Guide

Given below are the sections I contributed to the User Guide