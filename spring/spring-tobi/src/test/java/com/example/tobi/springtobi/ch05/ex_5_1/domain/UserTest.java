package com.example.tobi.springtobi.ch05.ex_5_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void upgradeLevel() {
        Level[] values = Level.values();
        for (Level level : values) {
            if (level.nextLevel() == null) continue;
            user.setLevel(level);
            user.upgradeLevel();
            assertEquals(user.getLevel(), level.nextLevel());
        }
    }

    @Test
    void cannotUpgradeLevel() {
        Level[] values = Level.values();
        for (Level level : values) {
            User user = new User();
            user.setLevel(level);

            if (level.nextLevel() == null) {
                assertThrows(IllegalStateException.class, () -> {
                    user.upgradeLevel();
                });
            } else {
                user.upgradeLevel();  // Ensure it doesn't throw for other levels
            }
        }
    }

}