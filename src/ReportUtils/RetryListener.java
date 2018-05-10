package ReportUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import TestBase.TestSetUp;

public class RetryListener implements IAnnotationTransformer {
	@Override
	public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
		if (retry == null) {
			testannotation.setRetryAnalyzer(TestSetUp.class);
		}
	}
}