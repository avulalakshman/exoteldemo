package com.cube.pod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ccube.pod.ExotelpodApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExotelpodApplication.class)
@WebAppConfiguration
public class ExotelpodApplicationTests {

	@Test
	public void contextLoads() {
	}

}
