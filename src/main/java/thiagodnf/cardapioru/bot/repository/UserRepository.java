package thiagodnf.cardapioru.bot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import thiagodnf.cardapioru.bot.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByCampus(String campus);

	public User findByChatId(String chatId);

	public List<User> findByUniversityAndCampus(String university, String campus);
}
