package com.springBatch.www;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// batch job 위치 지정
@ContextConfiguration(locations = { "classpath:/com/springBatch/www/job_beans.xml" })
public class BatchTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void testJob() throws Exception {
		final JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		// job이 정상적으로 끝났는지 확인.
		Assert.assertEquals(ExitStatus.COMPLETED.getExitCode(), jobExecution.getExitStatus().getExitCode());
	}
}
