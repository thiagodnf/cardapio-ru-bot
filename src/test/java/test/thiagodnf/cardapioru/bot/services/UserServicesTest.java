package test.thiagodnf.cardapioru.bot.services;

import static com.mscharhag.oleaster.matcher.Matchers.expect;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.repository.UserRepository;
import thiagodnf.cardapioru.bot.services.UserService;

@RunWith(SpringRunner.class)
public class UserServicesTest {

	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
        	return new UserService();
        }
    }
	
	@Autowired
    private UserService userService;
	
	@MockBean
    private UserRepository userRepository;
	
	private List<User> users = Arrays.asList(new User("0"), new User("1"));
	
	@Before
	public void setUp() {
		Mockito.when(userRepository.findAll()).thenReturn(users);
	    Mockito.when(userRepository.findByChatId("0")).thenReturn(users.get(0));
	    Mockito.when(userRepository.findByChatId("1")).thenReturn(users.get(1));
	    Mockito.when(userRepository.save(users.get(0))).thenReturn(users.get(0));
	    Mockito.when(userRepository.findByUniversityAndCampus("ufpr", "central")).thenReturn(Arrays.asList((users.get(1))));
	}	
	
	@Test
	public void when_calling_findAll_method_it_should_return_non_empty_array(){
		expect(userService.findAll().isEmpty()).toBeFalse();
		expect(userService.findAll().get(0).getChatId()).toMatch("0");
		expect(userService.findAll().get(1).getChatId()).toMatch("1");
	}
	
	@Test
	public void when_calling_findByChatId_method_it_should_return_the_correct_user(){
		expect(userService.findByChatId("1").getChatId()).toMatch("1");
	}
	
	@Test
	public void when_calling_findByChatId_method_it_should_return_null_with_invalid_chatId(){
		expect(userService.findByChatId("10")).toBeNull();
	}
	
	@Test
	public void when_calling_findByCampusAndUniversity_method_it_should_return_non_empty_array(){
		expect(userService.findByUniversityAndCampus("ufpr", "central").isEmpty()).toBeFalse();
	}
	
	@Test
	public void when_calling_findByCampusAndUniversity_method_with_invalid_args_it_should_return_empty(){
		expect(userService.findByUniversityAndCampus("ufpr", "c").isEmpty()).toBeTrue();
		expect(userService.findByUniversityAndCampus("uf", "central").isEmpty()).toBeTrue();
		expect(userService.findByUniversityAndCampus("uf", "c").isEmpty()).toBeTrue();
	}
	
	@Test
	public void when_calling_save_method_it_should_return_the_same_object(){
		expect(userService.save(users.get(0))).toBeNotNull();
	}

}
