package com.springBatch.www;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/*Tasklet 구현 : step이 실행하면 batch에서 execute 메소드 실행*/
public class BatchTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		System.out.println("작업 시작~!!!");
		/*이 곳에 원하는 작업 진행*/
		System.out.println("작업 완료~!!!");
		return null; // execute에서 null return 의미는 RepeatStatus:FINISHED 반환(반복 없이 한 번만 실행됨)
	}
	
}
