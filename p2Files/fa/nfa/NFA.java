/*
 * @author: Jordan Casper
 * @author: Anton Leslie
 * CS 361
 * Date: 24 March 2023
 * Description: Project 2: NFA Class
 *
 */
package fa.nfa;

import fa.State;

import java.util.Set;

public class NFA implements NFAInterface {
    /**
     * Adds a a state to the FA instance
     *
     * @param name is the label of the state
     * @return true if a new state created successfully and false if there is already state with such name
     */
    public boolean addState(String name) {
        return false;
    }

    /**
     * Marks an existing state as an accepting state
     *
     * @param name is the label of the state
     * @return true if successful and false if no state with such name exists
     */
    public boolean setFinal(String name) {
        return false;
    }

    /**
     * Adds the initial state to the FA instance
     *
     * @param name is the label of the start state
     * @return true if successful and false if no state with such name exists
     */
    public boolean setStart(String name) {
        return false;
    }

    /**
     * Adds a symbol to Sigma
     *
     * @param symbol to add to the alphabet set
     */
    public void addSigma(char symbol) {

    }

    /**
     * Simulates a FA on input s to determine
     * whether the FA accepts s.
     *
     * @param s - the input string
     * @return true if s in the language of the FA and false otherwise
     */
    public boolean accepts(String s) {
        return false;
    }

    /**
     * Getter for Sigma
     *
     * @return the alphabet of FA
     */
    public Set<Character> getSigma() {
        return null;
    }

    /**
     * Returns state with the given name, or null if none exists
     *
     * @param name of a state
     * @return state object or null
     */
    public State getState(String name) {
        return null;
    }

    /**
     * Determines if a state with a given name is final
     *
     * @param name the name of the state
     * @return true if a state with that name exists and it is final
     */
    public boolean isFinal(String name) {
        return false;
    }

    /**
     * Determines if a state with name is final
     *
     * @param name the name of the state
     * @return true if a state with that name exists and it is the start state
     */
    public boolean isStart(String name) {
        return false;
    }

    /**
     * Return delta entries
     *
     * @param from   - the source state
     * @param onSymb - the label of the transition
     * @return a set of sink states
     */
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return null;
    }

    /**
     * Traverses all epsilon transitions and determine
     * what states can be reached from s through e
     *
     * @param s
     * @return set of states that can be reached from s on epsilon trans.
     */
    public Set<NFAState> eClosure(NFAState s) {
        return null;
    }

    /**
     * Determines the maximum number of NFA copies
     * created when processing string s
     *
     * @param s - the input string
     * @return - the maximum number of NFA copies created.
     */
    public int maxCopies(String s) {
        return 0;
    }

    /**
     * Adds the transition to the NFA's delta data structure
     *
     * @param fromState is the label of the state where the transition starts
     * @param toStates
     * @param onSymb    is the symbol from the NFA's alphabet.
     * @return true if successful and false if one of the states don't exist or the symbol in not in the alphabet
     */
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        return false;
    }

    /**
     * Determines if NFA is an instance of a DFA
     *
     * @return - true if NFA's transition function has DFA's properties.
     */
    public boolean isDFA() {
        return false;
    }
}
