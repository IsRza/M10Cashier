package is.rza.M10Cashier.mapper;

import is.rza.M10Cashier.dao.entity.UserEntity;
import is.rza.M10Cashier.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserMapper {
    UserDTO mapToDTO(UserEntity entity);
}
