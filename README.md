# Project 2: Nondeterministic Finite Automata

* Author: Jordan Casper
* Author: Anton Leslie
* Class: CS361 Section 2
* Semester: Spring 2023

## Overview

This is a programming project where we implemented two classes that model an instance of a nondeterministic finite automaton (NFA) and its behavior. 
This project requires you to implement the NFA class, which should implement the NFAInterface interface and extend the State class, both located in the fa.nfa package. 
The project also requires you to implement the graph search algorithms: Breadth First Search (BFS) and Depth First Search (DFS). 
You will be provided with several test cases, and you will have to create additional test cases to further test your implementation. 
The NFA class have methods to add states, set start and final states, add transitions, compute eClosures, and check if a string is accepted by the NFA. 
The accepts method should simulate traversing the NFA graph using BFS to keep track of all the NFA's copies created while following symbols in input. 
The method returns false when after reading all the symbols, none of the copies are in any accepting state. 
The maxCopies method should return the maximum number of copies a trace can generate.

## Reflection

In this project, we were able to successfully transfer the skills we learned from the previous project to this one,
which worked well for us. However, we faced a significant struggle with the test class, 
which caused errors and prevented it from running. 
We adopted a debugging approach to identify the issues in the test class and resolve them. 
This helped us understand the challenges we were facing and enabled us to progress with the project.

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

If you used any sources outside of the lecture notes, class lab files,
or text book you need to list them here. If you looked something up on
stackoverflow.com and fail to cite it in this section it will be
considered plagiarism and be dealt with accordingly. So be safe CITE!

----------
This README template is using Markdown. To preview your README output,
you can copy your file contents to a Markdown editor/previewer such
as [https://stackedit.io/editor](https://stackedit.io/editor).
