//idea
//use dfs to search longest word and use tries to store all character inside word
//dfs is performed using stack. iterate over stack till its empty and push root first and then push child of popped items .
//compare the length of each word with max length.

//time complexity o(n) n is size of words innput array
//space complexity o(n)

//run on leet code :yes
//did not understand solution fully

import java.util.HashMap;
import java.util.Stack;

public class LongestWordinDictionary {
	
	//brute force 
   /* public String longestWord(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        for (String word: words) {
            if (word.length() > ans.length() ||
                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) ans = word;
            }    
        }
        return ans;
    }
	*/
	class Solution {
	    public String longestWord(String[] words) {
	        Trie trie = new Trie();
	        int index = 0;
	        for (String word: words) {
	            trie.insert(word, ++index); //indexed by 1
	        }
	        trie.words = words;
	        return trie.dfs();
	    }
	}
	class Node {
	    char c;
	    HashMap<Character, Node> children = new HashMap();
	    int end;
	    public Node(char c){
	        this.c = c;
	    }
	}

	class Trie {
	    Node root;
	    String[] words;
	    public Trie() {
	        root = new Node('0');
	    }

	    public void insert(String word, int index) {
	        Node cur = root;
	        for (char c: word.toCharArray()) {
	            cur.children.putIfAbsent(c, new Node(c));
	            cur = cur.children.get(c);
	        }
	        cur.end = index;
	    }

	    public String dfs() {
	        String ans = "";
	        Stack<Node> stack = new Stack();
	        stack.push(root);
	        while (!stack.empty()) {
	            Node node = stack.pop();
	            if (node.end > 0 || node == root) {
	                if (node != root) {
	                    String word = words[node.end - 1];
	                    if (word.length() > ans.length() ||
	                            word.length() == ans.length() && word.compareTo(ans) < 0) {
	                        ans = word;
	                    }
	                }
	                for (Node nei: node.children.values()) {
	                    stack.push(nei);
	                }
	            }
	        }
	        return ans;
	    }
	}
}
