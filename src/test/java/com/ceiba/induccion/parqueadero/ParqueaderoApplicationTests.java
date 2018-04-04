package com.ceiba.induccion.parqueadero;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.parqueadero.controller.ParqueaderoController;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoApplicationTests {
	
	@Autowired
    private ParqueaderoController parqueaderoController;

	@Test
	public void contextLoads() {
		assertThat(parqueaderoController).isNotNull();
	}

}
