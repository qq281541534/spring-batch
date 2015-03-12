package net.blog.csdn.chaijunkun.batch;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepListener implements StepExecutionListener {
	
	private static final Logger log = LoggerFactory.getLogger(StepListener.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		log.info("========[{}]-start========", stepExecution.getStepName());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		log.info("========[{}]-end========", stepExecution.getStepName());
		log.info("随机决定当前步骤执行状态");
		Random rand = new Random();
		boolean isCompleted = rand.nextBoolean();
		if (isCompleted){
			return ExitStatus.COMPLETED;
		}else{			
			return ExitStatus.FAILED;
		}
	}

}
