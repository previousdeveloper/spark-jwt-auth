package Util;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class IKeyGeneratorImpl implements IKeyGenerator {

    @Override
    public Key getKey() {

        return MacProvider.generateKey();
    }
}
