package dao;

public interface LoginDAO {
    boolean validate(String email, String password);
}
