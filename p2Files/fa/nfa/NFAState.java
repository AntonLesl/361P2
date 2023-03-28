/**
 * @author Jordan Casper
 * @author Anton Leslie
 * CS 361
 * Date: 24 March 2023
 * Description: Project 2: NFAState Class
 *
 */
package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import fa.State;


public class NFAState extends State {
    private boolean isFinal;
    private boolean isStart;
    private HashMap<Character,HashSet<NFAState>> delta;


    public NFAState(String name) {
        super(name);
        isFinal = false;
        isStart = false;
        delta = new HashMap<Character,HashSet<NFAState>> ();
    }

    /**
     * @aythor Jordan Casper
     * Public setter for the NFAState isFinal variable.
     * @param b - True or False
     */
    public void setstateFinal(boolean b) {
        this.isFinal = b;
    }

    /**
     * @aythor Jordan Casper
     * Public setter for the NFAState isStart variable.
     * @param b - True or False
     */
    public void setstateStart(boolean b) {
        this.isStart = b;
    }

    /**
     * @author Jordan Casper
     * Getter to check whether the state is a final state or not.
     * @return isFinal
     */
    public boolean getFinal() {
        return isFinal;
    }

    /**
     * @author Jordan Casper
     * Getter to check whether the state is a final state or not.
     * @return isFinal
     */
    public boolean getStart() {
        return isStart;
    }

    /**
     * @author Jordan Casper
     * Getter to retrieve the transition functions.
     * @return transitions HashMap
     */
    public HashMap<Character, HashSet<NFAState>> getstateTransitions() {

        return delta;
    }

    /**
     * @author Anton Leslie
     * @author Jordan Casper
     * Adds a transition to the state
     * @param onSymb the symbol triggering the transition
     * @param toState the state to transition to
     */
    public void addstateTransition(char onSymb, NFAState toState) {
        HashSet<NFAState> state = delta.get(onSymb);
        if (state == null) {
            state = new HashSet<NFAState>();
        }
         state.add(toState);
         delta.put(onSymb, state);
    }

    /**
     * @author Anton Leslie
     * @author Jordan Casper
     * Returns the set of states reachable from this state on the given symbol
     * @param onSymb the symbol triggering the transition
     * @return set of reachable states, or empty set if no transition on the symbol
     */
    public Set<NFAState> toStates(char onSymb) {

        HashSet<NFAState> temp = delta.get(onSymb);
        return delta.get(onSymb);
    }


}
