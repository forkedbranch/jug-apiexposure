package eu.forkedbranch.jug.apiexposure.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import eu.forkedbranch.jug.apiexposure.models.User;

public class UserService {
	private static UserService INSTANCE;
	private Map<String, User> usersMap = new HashMap<String, User>();

	private UserService() {
	}

	public static synchronized UserService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserService();
		}
		return INSTANCE;
	}

	public void addUser(User user) {
		usersMap.put(user.getId(), user);
	}

	public User getUser(String id) {
		return usersMap.get(id);
	}

	public User deleteUser(String id) {
		User user = usersMap.get(id);
		if (user != null) {
			usersMap.remove(id);
		}
		return user;
	}

	public Collection<User> getUsers() {
		return usersMap.values();
	}
}
