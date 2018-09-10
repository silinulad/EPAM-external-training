import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.utils.TaskExecution;

public class RunnerOne {
	
	public static void main(String[] args) {
		ResultFactory resultFactory = new ResultFactory();
		TaskExecution.executeTask(resultFactory, Constants.FILE_CSV);
	}
}