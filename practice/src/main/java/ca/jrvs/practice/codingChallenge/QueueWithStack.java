package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * Ticket: https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-a1bc91012c9c4c1aa1a6ef543b20d48b
 */
public class QueueWithStack {
    queueHeavyPush qHPush = new queueHeavyPush();
    queueHeavyPop qHPop = new queueHeavyPop();

    class queueHeavyPush {
        Stack<Integer> mainS = new Stack<>();
        Stack<Integer> tempS = new Stack<>();

        public queueHeavyPush() {}

        public void push(int x) {
            //push to the last of main stack
            //reverse main stack, push, reverse
            int increments = mainS.size();
            while (!mainS.empty()) {
                tempS.add(mainS.pop());
            }
            tempS.add(x);
            while (!tempS.empty()) {
                mainS.add(tempS.pop());
            }
        }

        public int pop() {
            //pop the last in main stack
            return mainS.pop();
        }

        public int peek() {
            return mainS.peek();
        }

        public boolean empty() {
            return mainS.empty();
        }
    }

    class queueHeavyPop{
        Stack<Integer> mainS = new Stack<>();
        Stack<Integer> tempS = new Stack<>();

        public queueHeavyPop() {}

        public void push(int x) {
            mainS.push(x);
        }

        public int pop() {
            while (!mainS.isEmpty()) {
                tempS.push(mainS.pop());
            }
            int ans = tempS.pop();
            while(!tempS.empty()){
                mainS.push(tempS.pop());
            }
            return ans;
        }

        public int peek() {
            while (!mainS.isEmpty()) {
                tempS.push(mainS.pop());
            }
            int ans = tempS.peek();
            while(!tempS.empty()){
                mainS.push(tempS.pop());
            }
            return ans;
        }

        public boolean empty() {
            return mainS.empty();
        }
    }
}
