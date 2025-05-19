package Service;

import Model.User;
import dao.UserDAO;

import java.sql.SQLException;

public class UserService {

    public static Integer saveuser(User user) {
        try {
            if (UserDAO.isexist(user.getemail())) {
                return 0;
            } else {
                return UserDAO.save(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
