package Util;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;


public class IKeyGeneratorImpl implements IKeyGenerator {

    @Override
    public Key getKey() {

        return MacProvider.generateKey();
    }
}
