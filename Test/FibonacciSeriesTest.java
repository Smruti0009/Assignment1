public class FibonacciSeriesTest {
    public static void main(String[] args) {
        // Test cases
        int[] testCases = {0, 1, 2, 3, 4, 5, 10, 15};
        int[] expectedResults = {0, 1, 1, 2, 3, 5, 55, 610};

        boolean allTestsPassed = true;

        for (int i = 0; i < testCases.length; i++) {
            int result = FibonacciSeries.fibonacci(testCases[i]);
            if (result != expectedResults[i]) {
                System.out.println("Test failed for n = " + testCases[i] + ": expected " + expectedResults[i] + ", got " + result);
                allTestsPassed = false;
            } else {
                System.out.println("Test passed for n = " + testCases[i]);
            }
        }

        if (allTestsPassed) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
