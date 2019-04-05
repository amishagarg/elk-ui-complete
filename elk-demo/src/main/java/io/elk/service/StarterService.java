package io.elk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StarterService {

	
	public void welcome()
	{
		System.out.println("Application Starting with 555 BHP");
	}
	
}
