package com.authoriza.identityaccess.account.infrastructure.service.hasher.apache;

import com.authoriza.identityaccess.account.domain.service.Hasher;
import com.authoriza.shared.domain.service.Service;
import org.apache.commons.codec.digest.DigestUtils;

@Service
public class ApacheCommonsHasher implements Hasher {
    @Override
    public String hash(String original) {
        return DigestUtils.sha256Hex(original);
    }
}
