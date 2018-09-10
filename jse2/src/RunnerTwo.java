import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.factories.XMLResultFactory;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.utils.TaskExecution;

public class RunnerTwo {

	public static void main(String[] args) {
		ResultFactory resultFactory = new XMLResultFactory();
		TaskExecution.executeTask(resultFactory, Constants.FILE_XML);
	}
}