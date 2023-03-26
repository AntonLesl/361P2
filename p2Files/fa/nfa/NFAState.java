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

//I will be cleaning up these comments later of the changes that I made but
//I am keeping them in for now before we submit the project so that
//you know why I made the changes that I made.
public class NFAState extends State {
    //set NFAState instance variables to private 3/24/2023 10:30 PM
    private String from;
    private char OnSymb;
    private boolean isFinal; //isFinal needs to be set through a public method 3/24/2023 10:30 PM
    public HashMap<String, NFAState> delta;
    // Map to model the state's transitions

    private HashMap<Character, HashSet<NFAState>> transitions; //Changed Map to Hashmap simply for consistency 3/25/2023 ~9:45 PM Commit
    //Changed Set<NFAState> to HashSet<NFAState> as a Hashset will check for duplicates automatically
    // as well as the Hashset having faster access time and therefor more efficient. ~10:00 PM 3/25/2023 Commit
    public NFAState(String name) {
        super(name);
        isFinal = false;
        transitions = new HashMap<Character, HashSet<NFAState>>();
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
     * @author Jordan Casper
     * Getter to check whether the state is a final state or not.
     * @return isFinal
     */
    public boolean getFinal() {
        return isFinal;
    }

    /**
     * @author Jordan Casper
     * Getter to retrieve the transition functions.
     * @return transitions HashMap
     */
    public HashMap<Character, HashSet<NFAState>> getTransitions() {
        return transitions;
    }

    /**
     * @author Anton Leslie
     * @author Jordan Casper
     * Adds a transition to the state
     * @param onSymb the symbol triggering the transition
     * @param toState the state to transition to
     */
    public void addTransition(char onSymb, NFAState toState) {
        //Changed the addTransition method to check if there is already
        // a HashSet that is associated with the onSymb and if there is not
        // it creates a new empty Hashset. Then it adds the destination state(toState)
        // to the HashSet and puts the updated HashSet back into the HashMap for
        // the input symbol OnSymb. ~10:00 PM 3/25/2023 Commit
        HashSet<NFAState> state = transitions.get(onSymb);
        if (state == null) {
            state = new HashSet<NFAState>();
        }
         state.add(toState);
         transitions.put(onSymb, state);
    }

    /**
     * @author Anton Leslie
     * @author Jordan Casper
     * Returns the set of states reachable from this state on the given symbol
     * @param onSymb the symbol triggering the transition
     * @return set of reachable states, or empty set if no transition on the symbol
     */
    public Set<NFAState> getToStates(char onSymb) {
        //Added error handling in case the return is null.
        //as  ~10:00 PM 3/25/2023 Commit
        HashSet<NFAState> temp = transitions.get(onSymb);
        if(temp == null) {
            System.err.println("Error: Returns null on " + onSymb + " from " + getName());
            System.exit(1);
        }
        //If the return is not null then returns the ToStates.
        return transitions.get(onSymb);
    }

}
