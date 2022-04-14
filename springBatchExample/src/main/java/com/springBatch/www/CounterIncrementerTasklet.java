package com.springBatch.www;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

/*
 *  JobExecutionContext에서 MY.COUNTER 값을 꺼내오고, 콘솔에 출력 한 다음, 카운터 + 1의 값을 저장한다. 
 * */
public class CounterIncrementerTasklet implements Tasklet {
	
	private static final String MY_COUNTER_KEY = "MY.COUNTER";
	private static final int DEFAULT_VALUE = 0;
	
	private int counter;
	
	public void setCounter(final int counter) {
		this.counter = counter;
	}
	
	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		
		// 1. JobExecutionContext를 access 하는 방법.
		final ExecutionContext jobExecutionContext = chunkContext.getStepContext() // 1. step context 가져오기
																 .getStepExecution() // 2. step 실행
																 .getJobExecution() // 3. job 실행
																 .getExecutionContext(); // 4. 실행 context 가져오기
		
		// 2. setter를 통해 값이 지정되지 않은 경우 JobExecutionContext에서 값을 불러온다.
		// 3. jobExecutionContext에 MY.COUNTER라는 키/페어가 없을 경우 0을 기본 값으로 가진다.
		if (counter == 0) {
			counter = jobExecutionContext.getInt(MY_COUNTER_KEY, DEFAULT_VALUE);
		}
		
		// 4. console에 카운터 값 출력.
		System.out.println("카운터 :" + counter);
		
		// 5. counter + 1의 값을 jobExecutionContext에 저장
		jobExecutionContext.put(MY_COUNTER_KEY, counter + 1);
		
		return RepeatStatus.FINISHED;
	}
	
}
