# Project 2: Nondeterministic Finite Automata

* Author: Jordan Casper
* Author: Anton Leslie
* Class: CS361 Section 2
* Semester: Spring 2023

## Overview

This is a programming project where we implemented two classes that model an instance of a nondeterministic finite 
automaton (NFA) and it's behavior. This project utilizes a breadth first search algorithm to determine whether 
the NFA is accepted or not and a depth first search algorithm to traverse through all the Epsilon transitions.


## Reflection

In this project, we were able to successfully transfer the skills we learned from the previous project to this one,
which worked well for us. However, we faced a significant struggle with the test class, 
which caused errors and prevented it from running. 
We adopted a debugging approach to identify the issues in the test class and resolve them. 
This helped us understand the challenges we were facing and enabled us to progress with the project.

One of the big issues that we ran across was getting the tests to function properly. We understood that
we were unable to modify any of the tests or the given method parameters. In the beginning we were getting an error
due to the getState method returning the type State instead of NFAState which caused the tests to not be able to run. 
This error was caused by us using the IDE to add the unimplemented methods and not taking into account the extends
and implements functionality in Java. This issue with the tests was easily solved by modifying the return type 
of getState to NFAState instead of State and for it to Override the method declared in it's supertype. This fixed
the issues we were having with the inability to test properly.

During the project, we found the concepts of max copies and implementing the bfs method unclear. 
To tackle this, we used condition statements in the debug methods to identify the areas we were not covering in our code, 
and this helped us to locate our mistakes. Our approach was to focus on the most crucial concepts and dedicate more time 
to them, such as dfs and bfs methods, which helped us complete the project with fewer issues.

If we were to go back in time, we would advise ourselves to focus more on the dfs and bfs methods 
to get them finished early and build on this to complete the project faster. 
We would also recommend a more structured approach to the project, 
identifying what was essential and what would take longer to solve, and starting from there.

## Compiling and Using

javac *.java 
cd test.nfa 
javac NFATest.java
java NFATest

## Sources used

The sources used were Oracle documentation specifically, Collections which was extremely helpful when
writing the methods to use Breadth First Search and Depth First Search.

----------
This README template is using Markdown. To preview your README output,
you can copy your file contents to a Markdown editor/previewer such
as [https://stackedit.io/editor](https://stackedit.io/editor).
