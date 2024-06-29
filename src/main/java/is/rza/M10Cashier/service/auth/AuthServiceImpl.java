package is.rza.M10Cashier.service.auth;

import is.rza.M10Cashier.dao.entity.TOTPSessionEntity;
import is.rza.M10Cashier.dao.entity.UserEntity;
import is.rza.M10Cashier.dao.repository.TOTPSessionRepository;
import is.rza.M10Cashier.dao.repository.UserRepository;
import is.rza.M10Cashier.exception.InvalidCredentials;
import is.rza.M10Cashier.exception.NotFound;
import is.rza.M10Cashier.mapper.UserMapper;
import is.rza.M10Cashier.model.dto.LoginDTO;
import is.rza.M10Cashier.model.dto.RegisterDTO;
import is.rza.M10Cashier.model.dto.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TOTPSessionRepository totpSessionRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDTO login(LoginDTO params) {
        var user = userRepository.findByPhone(params.phone())
                .orElseThrow(NotFound::new);

        if (!user.getPassword().equals(params.password())) {
            throw new InvalidCredentials();
        }

        var session = totpSessionRepository.findByPayerUserId(user.getId())
                .orElseThrow(NotFound::new);

        session.setSecret(params.secret());

        totpSessionRepository.save(session);

        return userMapper.mapToDTO(user);
    }

    @Override
    @Transactional
    public UserDTO register(RegisterDTO params) {
        var user = UserEntity
                .builder()
                .phone(params.phone())
                .name(params.name())
                .balance(BigDecimal.TEN)
                .password(params.password())
                .build();

        user = userRepository.save(user);

        var session = TOTPSessionEntity
                .builder()
                .secret(params.secret())
                .payerUser(user)
                .build();

        totpSessionRepository.save(session);

        return userMapper.mapToDTO(user);
    }
}
