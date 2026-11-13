package com.affan.frontend.commands;

import com.affan.frontend.objects.Player;
import com.affan.frontend.systems.BulletManager;

public interface Command {
    void execute(Player player, BulletManager bulletManager);
}
