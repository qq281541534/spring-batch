package net.blog.csdn.chaijunkun.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.Assert;

public class StepMain implements Tasklet, InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(StepMain.class);
	
	private RetryTemplate retryTemplate;
	
	public RetryTemplate getRetryTemplate() {
		return retryTemplate;
	}

	public void setRetryTemplate(RetryTemplate retryTemplate) {
		this.retryTemplate = retryTemplate;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.state(retryTemplate!=null, "retryTemplate property can not be null");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("主要步骤执行");
		retryTemplate.execute(new RetryCallback<Object, Exception>() {
			private int counter = 0;
			private String msg = "故意的异常";
			@Override
			public Object doWithRetry(RetryContext context) throws Exception {
				while (counter<3) {
					counter++;
					log.error(msg);
					throw new Exception(msg);
				}
				log.info("重试成功");
				return null;
			}
		});
		return RepeatStatus.FINISHED;
	}

}
