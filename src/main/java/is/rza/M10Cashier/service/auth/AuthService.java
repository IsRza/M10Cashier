package is.rza.M10Cashier.service.auth;

import is.rza.M10Cashier.model.dto.LoginDTO;
import is.rza.M10Cashier.model.dto.RegisterDTO;
import is.rza.M10Cashier.model.dto.UserDTO;

public interface AuthService {
    UserDTO login(LoginDTO params);
    UserDTO register(RegisterDTO params);
}
