package com.springBatch.www1;

import org.springframework.batch.item.ItemProcessor;

/*
 *  Person의 fullname을 lastname과 firstname으로 쪼갠다.
 * */
public class PersonProcessor implements ItemProcessor<Person, Person> {
	
	private static final String SPACE = " ";
	
	@Override
	public Person process(Person person) throws Exception {
		String[] names = person.getFullName().split(SPACE);
		person.setFirstName(names[0]);
		person.setLastName(names[1]);
		return person;
	}
}
