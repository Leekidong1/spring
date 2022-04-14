package com.springBatch.www;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/com/springBatch/www2/job_beans4.xml" })
public class ExamSqlToCsvTest {
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static AtomicBoolean isLaunched = new AtomicBoolean(false);

	private JobExecution jobExecution;
	
	@Before
	public void setup() throws Exception {
		if (!isLaunched.getAndSet(true)) {
			jobExecution = jobLauncherTestUtils.launchJob();
		}
	}
	
	@Test
	public void testExitCode() {
		Assert.assertEquals(ExitStatus.COMPLETED.getExitCode(), jobExecution.getExitStatus().getExitCode());
	}
	
	/* output 파일이 생성되었는지 확인한다. 테스트는 더 디테일하면 할수록 좋다  */
	@Test
	public void testOutputFileCreated() {
		File file = new File("com/springBatch/www/output/output.csv");
		Assert.assertTrue(file.exists());
	}
	
}
