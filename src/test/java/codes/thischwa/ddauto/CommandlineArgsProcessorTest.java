package codes.thischwa.ddauto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandlineArgsProcessorTest {

	final List<String> testArgs = List.of("--swagger.enabled=true");

	@Test
	final void testProcess_swagger() {
		List<String> args = CommandlineArgsProcessor.process(testArgs.toArray(new String[testArgs.size()]));
		assertFalse(args.contains("--swagger.enabled=true"));
		assertFalse(args.contains("--springdoc.api-docs.enabled=false"));

		List<String> newArgs = new ArrayList<>(testArgs);
		newArgs.remove("--swagger.enabled=true");
		args = CommandlineArgsProcessor.process(newArgs.toArray(new String[newArgs.size()]));
		assertTrue(args.contains("--springdoc.api-docs.enabled=false"));
	}

	@Test
	final void testProcess_logback() {
		CommandlineArgsProcessor.workingDir = "target/test-classes/test-files";
		List<String> args = CommandlineArgsProcessor.process(testArgs.toArray(new String[testArgs.size()]));
		assertTrue(args.contains("--logging.config=target/test-classes/test-files/logback.xml"));
	}
}
