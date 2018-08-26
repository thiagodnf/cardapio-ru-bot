package thiagodnf.cardapioru.bot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){	 
		return userRepository.findAll();
	}
	
	public User findByChatId(String chatId) {
		return userRepository.findByChatId(chatId);
	}
	
	public List<User> findByCampusAndUniversity(String campus, String university) {
		return userRepository.findByCampusAndUniversity(campus, university);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
}
