package net.blog.csdn.chaijunkun.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepBranch2 implements Tasklet {
	
	private static final Logger log = LoggerFactory.getLogger(StepBranch2.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("走向了branch 2");
		return RepeatStatus.FINISHED;
	}

}
