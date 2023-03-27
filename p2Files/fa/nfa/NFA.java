/**
 * @author Jordan Casper
 * @author Anton Leslie
 * CS 361
 * Date: 24 March 2023
 * Description: Project 2: NFA Class
 *
 */
package fa.nfa;

import fa.State;

import java.util.*;

public class NFA implements NFAInterface {

    /* Variables */
    private HashSet<NFAState> Q;
    private NFAState q0;
    private Set<NFAState> F;
    private HashSet<Character> sigma;




    /* Constructor */
    public NFA() {
        Q = new HashSet<NFAState>();
        sigma = new HashSet<Character>();
        F = new HashSet<NFAState>();
    }

    /**
     * Adds a a state to the FA instance
     *
     * @param name is the label of the state
     * @return true if a new state created successfully and false if there is already state with such name
     */
    @Override
    public boolean addState(String name) {
        NFAState state = stateExists(name);
        if (state == null) {
            Q.add(new NFAState(name));
        } else {
            boolean b = false;
            return b;
        }
        boolean b = true;
        return b;
    }

    /**
     * Simple check to see if a state with the given name exists or not.
     * @param name
     * @return State with the given name or null if the state does not exist.
     */

    private NFAState stateExists(String name) {
        NFAState temp = null;
        for (NFAState s : Q) {
            if (s.getName().equals(name)) {
                temp = s;
                break;
            }
        }
        return temp;
    }

    /**
     * Marks an existing state as an accepting state
     *
     * @param name is the label of the state
     * @return true if successful and false if no state with such name exists
     */
    @Override
    public boolean setFinal(String name) {
        NFAState finalS = stateExists(name);
        if (finalS == null) {
            finalS = new NFAState(name);
            finalS.setstateFinal(true);
            F.add(finalS);
            Q.add(finalS);
            boolean b = true;
            return b;
        }
        else{
            boolean b = false;
            return b;
        }
    }

    /**
     * Adds the initial state to the FA instance
     *
     * @param name is the label of the start state
     * @return true if successful and false if no state with such name exists
     */
    @Override
    public boolean setStart(String name) {
        NFAState state = stateExists(name);

        if (state == null) {
            state = new NFAState(name);
            this.q0 = state;
            Q.add(state);
            state.setstateStart(true);
            boolean b = true;
            return b;
        } else {
            boolean b = false;
            return b;
        }
    }

    /**
     * Adds a symbol to Sigma
     *
     * @param symbol to add to the alphabet set
     */
    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    /**
     * Simulates a FA on input s to determine
     * whether the FA accepts s.
     *
     * @param s - the input string
     * @return true if s in the language of the FA and false otherwise
     */
    @Override
    public boolean accepts(String s) {
        Set<NFAState> currStates = new HashSet<>();
        currStates.add(q0);
        currStates.addAll(eClosure(q0));

        for (char symbol : s.toCharArray()) {
            Set<NFAState> nextStates = new HashSet<>();
            for (NFAState state : currStates) {
                Set<NFAState> transitions = getToState(state, symbol);
                nextStates.addAll(transitions);
                for (NFAState transition : transitions) {
                    nextStates.addAll(eClosure(transition));
                }
            }
            currStates = nextStates;
        }

        for (NFAState state : currStates) {
            if (F.contains(state)) {
                return true;
            }
        }
        return false;
    }





    /**
     * Getter for Sigma
     *
     * @return the alphabet of FA
     */
    @Override
    public Set<Character> getSigma() {

        return sigma;
    }



    /**
     * Returns state with the given name, or null if none exists
     *
     * @param name of a state
     * @return state object or null
     */
    @Override
    public NFAState getState(String name) {
        NFAState temp = null;
        if (Q.contains(name)) {
            for (NFAState element : Q) {
                if (element.getName().equals(name)) {
                    temp = element;
                    return temp;
                }
            }
        }
        return temp;

    }

    /**
     * Determines if a state with a given name is final
     *
     * @param name the name of the state
     * @return true if a state with that name exists and it is final
     */
    @Override
    public boolean isFinal(String name) {
        NFAState temp = (NFAState) getState(name);

        return false;
    }

    /**
     * Determines if a state with name is final
     *
     * @param name the name of the state
     * @return true if a state with that name exists and it is the start state
     */
    @Override
    public boolean isStart(String name) {

        return false;
    }

    /**
     * Return delta entries
     *
     * @param from   - the source state
     * @param onSymb - the label of the transition
     * @return a set of sink Q
     */
    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.toStates(onSymb);
    }

    /**
     * Traverses all epsilon transitions and determine
     * what Q can be reached from s through e
     *
     * @param s
     * @return set of Q that can be reached from s on epsilon trans.
     */
    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> eClosure = new HashSet<>();
        Stack<NFAState> stack = new Stack<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            NFAState currState = stack.pop();
            eClosure.add(currState);

            Set<NFAState> epsilonTransitions = getToState(currState, 'e');
            for (NFAState epsilonState : epsilonTransitions) {
                if (!eClosure.contains(epsilonState)) {
                    stack.push(epsilonState);
                    eClosure.add(epsilonState);
                }
            }
        }

        return eClosure;
    }


    /**
     * Determines the maximum number of NFA copies
     * created when processing string s
     *
     * @param s - the input string
     * @return - the maximum number of NFA copies created.
     */
    @Override
    public int maxCopies(String input) {
        Set<NFAState> currStates = new HashSet<>();
        currStates.add(q0);
        currStates.addAll(eClosure(q0));

        int maxCopies = currStates.size();

        for (char symbol : input.toCharArray()) {
            Set<NFAState> nextStates = new HashSet<>();
            for (NFAState state : currStates) {
                Set<NFAState> transitions = getToState(state, symbol);
                nextStates.addAll(transitions);
                for (NFAState transition : transitions) {
                    nextStates.addAll(eClosure(transition));
                }
            }
            currStates = nextStates;
            maxCopies = Math.max(maxCopies, currStates.size());
        }

        return maxCopies;
    }


    /**
     * Adds the transition to the NFA's delta data structure
     *
     * @param fromState is the label of the state where the transition starts
     * @param toStates
     * @param onSymb    is the symbol from the NFA's alphabet.
     * @return true if successful and false if one of the Q don't exist or the symbol in not in the alphabet
     */
    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        // Check that the fromState exists in Q
        if (!Q.contains(fromState)) {
            return false;
        }

        // Check that all the toStates exist in Q
        for (String state : toStates) {
            if (!Q.contains(state)) {
                return false;
            }
        }

        // Check that the symbol is in the alphabet
        if (!sigma.contains(onSymb)) {
            return false;
        }



        return true;
    }


    /**
     * Determines if NFA is an instance of a DFA
     *
     * @return - true if NFA's transition function has DFA's properties.
     */
    @Override
    public boolean isDFA() {

        return false;
    }
}
