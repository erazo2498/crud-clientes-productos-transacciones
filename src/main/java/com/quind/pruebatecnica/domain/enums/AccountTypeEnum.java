package com.quind.pruebatecnica.domain.enums;

import com.quind.pruebatecnica.domain.exceptions.InvalidValueException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.quind.pruebatecnica.configuration.Constants.ACCOUNT_TYPE_INVALID_MESSAGE;

public enum AccountTypeEnum {
    SAVING_ACCOUNT("53","Cuenta de ahorro"),
    CHECKING_ACCOUNT("33", "Cuenta corriente");

    private final String accountTypeIdentifier;
    private final String accountTypeDescription;
    AccountTypeEnum(String accountTypeIdentifier, String accountTypeDescription) {
        this.accountTypeIdentifier = accountTypeIdentifier;
        this.accountTypeDescription = accountTypeDescription;
    }

    private static final Map<String, AccountTypeEnum> map;

    static {
        map = new HashMap<>();
        for (AccountTypeEnum accountTypeEnum: AccountTypeEnum.values()) {
            map.put(accountTypeEnum.getAccountTypeIdentifier(),  accountTypeEnum);
        }
    }

    public static AccountTypeEnum findByAccountTypeEnumKey(String accountTypeIdentifier) {
        return Optional.ofNullable(map.get(accountTypeIdentifier))
                .orElseThrow(() -> new InvalidValueException(ACCOUNT_TYPE_INVALID_MESSAGE));
    }

    public String getAccountTypeDescription() {
        return accountTypeDescription;
    }

    public String getAccountTypeIdentifier() {
        return accountTypeIdentifier;
    }
}
