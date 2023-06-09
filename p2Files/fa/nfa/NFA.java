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
            return true;
        } else {
            return false;
        }
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
        if (finalS != null) {
            finalS.setstateFinal(true);
            F.add(finalS);
            return true;
        }
        else{
            return false;
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

        if (state != null) {
            this.q0 = state;
            state.setstateStart(true);
            return true;
        } else {
            return false;

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
        // Keep track of all active states in the NFA
        Set<NFAState> activeStates = new HashSet<>();
        activeStates.add(q0);

        // Compute eClosure of initial state and add all resulting states to active states
        Set<NFAState> eClosure = eClosure(q0);
        activeStates.addAll(eClosure);

        // Process each input symbol and update active states accordingly
        for (char c : s.toCharArray()) {
            Set<NFAState> newActiveStates = new HashSet<>();
            for (NFAState state : activeStates) {
                // Get all the states reachable from the current state on symbol c
                Set<NFAState> reachableStates = getToState(state, c);

                // Compute eClosure for each reachable state and add all resulting states to newActiveStates
                if (reachableStates != null) {
                    for (NFAState reachableState : reachableStates) {
                        Set<NFAState> reachableEClosure = eClosure(reachableState);
                        newActiveStates.addAll(reachableEClosure);
                    }
                }
            }
            activeStates = newActiveStates;
        }

        // Check if any of the active states are accepting states
        for (NFAState state : activeStates) {
            if (state.getFinal()) {
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
        for (NFAState state : Q) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }


    /**
     * Determines if a state with a given name is final
     *
     * @param name the name of the state
     * @return true if a state with that name exists and it is final
     */
    @Override
    public boolean isFinal(String name) {
        NFAState temp = stateExists(name);
        if(temp != null) {
            return temp.getFinal();
        } else {
            return false;
        }
    }

    /**
     * Determines if a state with name is final
     *
     * @param name the name of the state
     * @return true if a state with that name exists and it is the start state
     */
    @Override
    public boolean isStart(String name) {
        NFAState temp = stateExists(name);
        if(temp != null) {
            return temp.getStart();
        } else {
            return false;
        }

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
            if (epsilonTransitions != null) {
                for (NFAState epsilonState : epsilonTransitions) {
                    if (!eClosure.contains(epsilonState)) {
                        stack.push(epsilonState);
                        eClosure.add(epsilonState);
                    }
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
    public int maxCopies(String s) {
        Set<NFAState> currStates = new HashSet<>();
        currStates.add(q0);
        currStates.addAll(eClosure(q0));

        int maxCopies = currStates.size();

        for (char symbol : s.toCharArray()) {
            Set<NFAState> nextStates = new HashSet<>();
            for (NFAState state : currStates) {
                Set<NFAState> transitions = getToState(state, symbol);
                if(transitions != null) {
                    nextStates.addAll(transitions);
                    for (NFAState transition : transitions) {
                        nextStates.addAll(eClosure(transition));
                    }
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
     * @param toStates  is the set of labels of the states where the transition goes
     * @param onSymb    is the symbol from the NFA's alphabet
     * @return true if successful and false if one of the states don't exist or the symbol is not in the alphabet
     */
    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {

        boolean added = false;
        if (!sigma.contains(onSymb) && onSymb != 'e') {

            return false;
        }
        NFAState from = getState(fromState);
        // If state from exists, add transition(s) to it
        if (from != null) {
            for (String toState : toStates) {
                NFAState to = getState(toState);
                // If state to exists, add the transition
                if (to != null) {
                    from.addstateTransition(onSymb, to);
                    added = true;
                }
            }
        }
        return added;
    }




    /**
     * Determines if NFA is an instance of a DFA
     *
     * @return - true if NFA's transition function has DFA's properties.
     */
    @Override
    public boolean isDFA() {
        // Check if every state has exactly one transition for each symbol in sigma
        for (NFAState state : Q) {
            HashMap<Character, HashSet<NFAState>> transitions = state.getstateTransitions();
            if (transitions.size() != sigma.size()) {
                return false;
            }
            for (char symbol : sigma) {
                if (!transitions.containsKey(symbol) || transitions.get(symbol).size() != 1) {
                    return false;
                }
            }
        }

        // Check if there are no epsilon transitions
        for (NFAState state : Q) {
            if (state.getstateTransitions().containsKey('e')) {
                return false;
            }
        }

        return true;
    }
}
