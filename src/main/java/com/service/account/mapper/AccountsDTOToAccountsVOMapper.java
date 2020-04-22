package com.service.account.mapper;

import com.service.account.model.AccountDAO;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountsDTOToAccountsVOMapper {

    AccountsDTOToAccountsVOMapper MAPPER = Mappers.getMapper(AccountsDTOToAccountsVOMapper.class);

    AccountVO map(AccountDAO accountDAO, Customer customerData);
}
