package test.thiagodnf.cardapioru.bot.commands;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.before;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.cardapioru.bot.commands.RuAlertasCommand;
import thiagodnf.cardapioru.bot.services.MessageService;
import thiagodnf.cardapioru.bot.services.UserService;

//@RunWith(SpringRunner.class)
@RunWith(OleasterRunner.class)
public class RuAlertasCommandTest {
	
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public RuAlertasCommand ruAlertasCommand() {
        	System.out.println("get");
        	return new RuAlertasCommand();
        }
    }
	
	@MockBean
    private UserService userService;
	
	@MockBean
    private MessageService messageService;
	
	@Autowired
    private RuAlertasCommand ruAlertasCommand;
	
	@Before
	public void setUp() {
		System.out.println("setUp");
		Mockito.when(userService.findAll()).thenReturn(Arrays.asList());
	}
	
	
	@RunWith(SpringRunner.class)
    public static class JUnitSpringRunner {
		
		@TestConfiguration
	    static class EmployeeServiceImplTestContextConfiguration {
	  
	        @Bean
	        public RuAlertasCommand ruAlertasCommand() {
	        	System.out.println("get");
	        	return new RuAlertasCommand();
	        }
	    }
    }
	
	
	
	
	
	
	@Test
	public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {
		
		
	}
	
	{
		
		before(() -> {
			JUnitCore.runClasses(JUnitSpringRunner.class);
		});
		
		
		describe("Creating an instance of this class", () -> {
			
			it("throws an exception when the constructor is called", () -> {
				System.out.println(ruAlertasCommand);
			});
		});
	}
	
}
