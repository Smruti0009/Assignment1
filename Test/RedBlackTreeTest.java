public class RedBlackTreeTest {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // Insert elements
        int[] keys = {50, 30, 20, 40, 70, 60, 80};

        for (int key : keys) {
            tree.insert(key);
        }

        System.out.println("Red-Black Tree created with elements: 50, 30, 20, 40, 70, 60, 80");

        // Test cases for search
        int[] testCases = {50, 30, 20, 40, 70, 60, 80, 25, 75, 100};
        boolean[] expectedResults = {true, true, true, true, true, true, true, false, false, false};

        boolean allTestsPassed = true;

        for (int i = 0; i < testCases.length; i++) {
            boolean result = tree.search(testCases[i]);
            if (result != expectedResults[i]) {
                System.out.println("Test failed for key = " + testCases[i] + ": expected " + expectedResults[i] + ", got " + result);
                allTestsPassed = false;
            } else {
                System.out.println("Test passed for key = " + testCases[i]);
            }
        }

        if (allTestsPassed) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }

        // Printing inorder traversal for verification
        System.out.println("\nInorder traversal:");
        tree.inorder();
    }
}
