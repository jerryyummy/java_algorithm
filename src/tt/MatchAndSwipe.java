package tt;

import java.util.Stack;

public class MatchAndSwipe {
    public static int findLoser(String videoSequence, int totalPlayers) {
        Stack<Character> matchStack = new Stack<>();
        int matchCount = 0;

        for (char player : videoSequence.toCharArray()) {
            if (!matchStack.isEmpty() && matchStack.peek() == player) {
                matchStack.pop();
                matchCount++;
            } else {
                matchStack.push(player);
            }
        }

        return (matchCount % totalPlayers) + 1;
    }
}
