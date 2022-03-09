package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Ticket: https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-8956acb7f74144f8ac9d031551e00cb2
 */
public class StackWithQueue {
    StackWith2Queue sW2Q = new StackWith2Queue();
    StackWith1Queue sW1Q = new StackWith1Queue();

    class StackWith2Queue{
        Queue<Integer> mainQ;
        Queue<Integer> tempQ;
        public StackWith2Queue() {
            mainQ = new LinkedList<>();
            tempQ = new LinkedList<>();
        }

        public void push(int x) {
            tempQ.addAll(mainQ);
            mainQ.clear();
            mainQ.add(x);
            mainQ.addAll(tempQ);
            tempQ.clear();
        }

        public int pop() {
            return mainQ.remove();
        }

        public int top() {
            return mainQ.peek();
        }

        public boolean empty() {
            return mainQ.isEmpty();
        }
    }

    class StackWith1Queue{
        Queue<Integer> mainQ;
        public StackWith1Queue() {
            mainQ = new LinkedList<>();
        }

        public void push(int x) {
            mainQ.add(x);
        }

        public int pop() {
            //reverse the queue, then pop
            int size = mainQ.size();
            int[] temp = new int[size];
            for(int index = size-1; index >= 0; index--) {
                temp[index] = mainQ.remove();
            }
            int ans = temp[0];
            for(int index = 1; index < size-1; index++)
                mainQ.add(temp[index]);
            return ans;
        }

        public int top() {
            //reverse queue, return first (preserve queue)
            int size = mainQ.size();
            int[] temp = new int[size];
            for(int index = size-1; index >= 0; index--) {
                temp[index] = mainQ.remove();
            }
            int ans = temp[0];
            for(int index = 0; index < size; index++)
                mainQ.add(temp[index]);
            return ans;
        }

        public boolean empty() {
            return mainQ.isEmpty();
        }
    }

}
