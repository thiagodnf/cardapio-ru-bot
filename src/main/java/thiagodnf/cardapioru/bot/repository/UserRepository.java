package thiagodnf.cardapioru.bot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import thiagodnf.cardapioru.bot.model.User;

public interface UserRepository extends MongoRepository<User, String>{

	public User findByCampus(String campus);
	
	public User findByChatId(String chatId);
}
