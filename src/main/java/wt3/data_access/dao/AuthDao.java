package wt3.data_access.dao;

import wt3.data_access.dto.User;

/**
 * Interface of auth logic dao
 * @author dreizer
 * @version 1.0
 */
public interface AuthDao {
    /**
     * Method to login user
     * @param email User email
     * @param password User password
     * @return User found by email and password
     */
    User loginUser(String email, String password);
    /**
     * Method to get register new user
     * @param name User name
     * @param email User email
     * @param password User password
     * @return Result of registration
     */
    void registerUser(String name, String email, String password);
}
