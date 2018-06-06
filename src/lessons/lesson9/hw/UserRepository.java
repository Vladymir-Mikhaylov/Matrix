package lessons.lesson9.hw;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public String[] getUserNames() {

        int counter = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                counter++;
            }
        }
        String[] userNames = new String[counter];
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                userNames[i] = users[i].getName();
            }
        }
        return userNames;
    }

    public long[] getUserIds() {
        int counter = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                counter++;
            }
        }
        long[] userIds = new long[counter];
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                userIds[i] = users[i].getId();
            }
        }
        return userIds;
    }

    public String getUserNameById(long id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getId() == id) {
                    return users[i].getName();
                }
            }
        }
        return null;
    }

    public User getUserByName(String name) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getName() == name) {
                    return users[i];
                }
            }
        }
        return null;
    }

    private User findById(long id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getId() == id) {
                    return users[i];
                }
            }
        }
        return null;
    }

    public User getUserBySessionId(String sessionId) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getSessionId() == sessionId) {
                    return users[i];
                }
            }
        }
        return null;
    }

    public User save (User user){
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return user;
            }
            if (users[i].getId() == user.getId()) {
                return null;
            }
        }
        return null;
    }

    public User update (User user){
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getId() == user.getId()) {
                    users[i] = user;
                    return user;
                }
            }
        }
        return null;
    }

    public void delete (long id){
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getId() == id) {
                    users[i] = null;
                }
            }
        }
    }
}
