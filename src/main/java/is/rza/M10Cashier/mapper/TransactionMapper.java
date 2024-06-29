package is.rza.M10Cashier.mapper;

import is.rza.M10Cashier.dao.entity.TransactionEntity;
import is.rza.M10Cashier.model.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface TransactionMapper {
    TransactionDTO mapToDTO(TransactionEntity transaction);
}
