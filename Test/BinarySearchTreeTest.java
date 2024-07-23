import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {

    BinarySearchTree tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree();
    }

    @Test
    void testInsertAndInorder() {
        int[] values = {50, 30, 20, 40, 70, 60, 80};
        for (int value : values) {
            tree.insert(value);
        }

        // Capture the inorder output
        String inorderOutput = captureInorder(tree);

        // Expected inorder sequence for the given values
        String expectedInorder = "20 30 40 50 60 70 80 ";

        assertEquals(expectedInorder, inorderOutput);
    }

    @Test
    void testSearch() {
        int[] values = {50, 30, 20, 40, 70, 60, 80};
        for (int value : values) {
            tree.insert(value);
        }

        assertTrue(tree.search(50));
        assertTrue(tree.search(30));
        assertTrue(tree.search(20));
        assertTrue(tree.search(40));
        assertTrue(tree.search(70));
        assertTrue(tree.search(60));
        assertTrue(tree.search(80));

        assertFalse(tree.search(90));
        assertFalse(tree.search(10));
    }

    // Helper method to capture inorder output
    private String captureInorder(BinarySearchTree tree) {
        final StringBuilder inorderOutput = new StringBuilder();
        class InorderCapture extends BinarySearchTree {
            void inorderRec(Node root) {
                if (root != null) {
                    inorderRec(root.left);
                    inorderOutput.append(root.key).append(" ");
                    inorderRec(root.right);
                }
            }
        }
        InorderCapture captureTree = new InorderCapture();
        captureTree.root = tree.root;
        captureTree.inorderRec(captureTree.root);
        return inorderOutput.toString();
    }
}

