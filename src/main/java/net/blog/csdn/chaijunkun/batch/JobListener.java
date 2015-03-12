package net.blog.csdn.chaijunkun.batch;

import java.util.Date;

import org.joda.time.Interval;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * job listener
 *
 */
public class JobListener implements JobExecutionListener {
	
	private static final Logger log = LoggerFactory.getLogger(JobListener.class);

	private String getTotalTime(Date from, Date to){
		Interval interval = new Interval(from.getTime(), to.getTime());
		Period period = interval.toPeriod();
		String time = null;
		do{
			if (period.getDays()>0){
				time = String.format("%d d. %d h. %d m. %d s. %d ms.", period.getDays(), period.getHours(), period.getMinutes(), period.getSeconds(), period.getMillis());
				break;
			}
			if (period.getHours()>0){
				time = String.format("%d h. %d m. %d s. %d ms.", period.getHours(), period.getMinutes(), period.getSeconds(), period.getMillis());
				break;
			}
			if (period.getMinutes()>0){
				time = String.format("%d m. %d s. %d ms.", period.getMinutes(), period.getSeconds(), period.getMillis());
				break;
			}
			if (period.getSeconds()>0){
				time = String.format("%d s. %d ms.", period.getSeconds(), period.getMillis());
				break;
			}else{
				time = String.format("%d ms.", period.getMillis());
			}
		}while(false);
		return time;
	}
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		jobExecution.setStartTime(new Date());
		log.info("job [{}] start", jobExecution.getJobInstance().getJobName());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		jobExecution.setEndTime(new Date());
		String time = this.getTotalTime(jobExecution.getStartTime(), jobExecution.getEndTime());
		log.info("job [{}] finished, total time: {}", jobExecution.getJobInstance().getJobName(), time);
	}
}
