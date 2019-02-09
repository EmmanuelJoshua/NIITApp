/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem.validation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Spark
 */
public class Validator {

    private static final Map<String, String> USERS = new HashMap<String, String>();

    static {
        USERS.put("NIIT", "NIIT_Agbara");
        USERS.put("NIIT_Amos", "NIIT_Agbara");
    }

    public static boolean validate(String user, String password) {
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
