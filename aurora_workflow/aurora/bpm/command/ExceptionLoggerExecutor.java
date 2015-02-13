package aurora.bpm.command;

import aurora.bpm.command.sqlje.process_log;
import aurora.database.service.IDatabaseServiceFactory;
import aurora.sqlje.core.ISqlCallStack;

public class ExceptionLoggerExecutor extends AbstractCommandExecutor {
	public static final String TYPE = "EXCEPTION";

	public ExceptionLoggerExecutor(IDatabaseServiceFactory dsf) {
		super(dsf);
	}

	@Override
	public void execute(Command cmd) throws Exception {
		ISqlCallStack callStack = createSqlCallStack();
		try {
			Throwable thr = (Throwable) cmd.getOptions().get("EXCEPTION");
			process_log logger = createProc(process_log.class, callStack);
			Long instance_id = cmd.getOptions().getLong(INSTANCE_ID, -1);
			Long user_id = cmd.getOptions().getLong(USER_ID, -1);
			logger.log(instance_id, user_id, "EXCEPTION", thr.getMessage());
			logger.set_instance_error(instance_id);
			callStack.commit();
		} catch (Exception e) {
			callStack.rollback();
			throw e;
		} finally {
			releaseSqlCallStack(callStack);
		}

	}
}
