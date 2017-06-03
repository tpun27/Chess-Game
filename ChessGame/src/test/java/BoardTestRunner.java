import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class BoardTestRunner {
    public static void main(String[] args) {
        Result testResult = JUnitCore.runClasses(ChessTestSuite.class);

        for (Failure testFailure : testResult.getFailures()) {
            System.out.println(testFailure.toString());
        }

        System.out.println(testResult.wasSuccessful());
    }
}
