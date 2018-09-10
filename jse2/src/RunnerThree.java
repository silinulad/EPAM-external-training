import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.factories.DecimalResultFactory;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.utils.TaskExecution;

public class RunnerThree {

	public static void main(String[] args) {
		ResultFactory resultFactory = new DecimalResultFactory();
		TaskExecution.executeTask(resultFactory, Constants.FILE_CSV_DEC);
	}
}
