package de.netos.account.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import de.netos.account.CreateAccountDTO;
import de.netos.account.ModifyAccountDTO;
import de.netos.account.entity.Account;

@Component
public class AccountMapper {

	Account mapJSONToEntity(CreateAccountDTO accountJson) {
		String accountId = DigestUtils.md5Hex(accountJson.getNumber()).toUpperCase();
		return new Account(accountJson.getType(), accountJson.getName(), accountJson.getNumber(), accountId,
				accountJson.getCurrency());
	}

	void mapJSONToEntity(Account entity, ModifyAccountDTO json) {
		entity.setAccountType(json.getType());
		entity.setCurrency(json.getCurrency());
		entity.setName(json.getName());
		entity.setNumber(json.getNumber());
	}
}
