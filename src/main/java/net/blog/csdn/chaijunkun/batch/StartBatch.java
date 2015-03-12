package net.blog.csdn.chaijunkun.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartBatch {
	
	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-batch.xml");
		JobLauncher launcher = context.getBean(JobLauncher.class);
		Job job = (Job)context.getBean("demoJob");
		Map<String, JobParameter> params = new HashMap<String, JobParameter>();
		params.put("demo_time", new JobParameter(new Date()));
		launcher.run(job, new JobParameters(params));
		context.close();
	}
}
