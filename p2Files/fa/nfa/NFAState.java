package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import fa.State;

public class NFAState extends State {

    // Map to model the state's transitions
    private Map<Character, Set<NFAState>> transitions;

    public NFAState(String name) {
        super(name);
        transitions = new HashMap<Character, Set<NFAState>>();
    }

    /**
     * Adds a transition to the state
     * @param onSymb the symbol triggering the transition
     * @param toState the state to transition to
     */
    public void addTransition(char onSymb, NFAState toState) {
        Set<NFAState> states = transitions.getOrDefault(onSymb, new HashSet<NFAState>());
        states.add(toState);
        transitions.put(onSymb, states);
    }

    /**
     * Returns the set of states reachable from this state on the given symbol
     * @param onSymb the symbol triggering the transition
     * @return set of reachable states, or empty set if no transition on the symbol
     */
    public Set<NFAState> getToStates(char onSymb) {
        return transitions.getOrDefault(onSymb, new HashSet<NFAState>());
    }

}
