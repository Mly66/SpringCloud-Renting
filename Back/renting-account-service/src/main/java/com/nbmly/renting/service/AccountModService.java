package com.nbmly.renting.service;

import java.util.Set;

public interface AccountModService {
    void saveAccountCollection(String key, String value);

    void delAccountCollection(String key, String value);

    Set<String> getAccountCollection(String key);

    boolean isAccountCollectionValue(String key, String value);
}
