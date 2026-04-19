package project.kompass.illagerVZombies;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.raid.Raider;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftGiant;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftRaider;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftWitch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobAIListener implements Listener {

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        // 1. Handle ALL RAIDERS (Pillagers, Vindicators, etc.)
        if (event.getEntity() instanceof org.bukkit.entity.Raider bRaider) {
            Raider nmsRaider = ((CraftRaider) bRaider).getHandle();
            nmsRaider.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsRaider, Giant.class, 16.0F, 1.0D, 1.2D));
            nmsRaider.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsRaider, Zombie.class, true));
        }

        // 2. Handle WITCHES (Added Logic)
        else if (event.getEntity() instanceof org.bukkit.entity.Witch bWitch) {
            Witch nmsWitch = ((CraftWitch) bWitch).getHandle();
            // Witches also flee from Giants
            nmsWitch.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsWitch, Giant.class, 16.0F, 1.0D, 1.2D));
            // Witches hunt Zombies
            nmsWitch.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsWitch, Zombie.class, true));
        }

        // 3. Handle ZOMBIES
        else if (event.getEntity() instanceof org.bukkit.entity.Zombie bZombie) {
            Zombie nmsZombie = ((CraftZombie) bZombie).getHandle();
            // Zombies hunt both Raiders and Witches
            nmsZombie.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsZombie, Raider.class, true));
            nmsZombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(nmsZombie, Witch.class, true));
        }

        // 4. Handle GIANTS
        else if (event.getEntity() instanceof org.bukkit.entity.Giant bGiant) {
            Giant nmsGiant = ((CraftGiant) bGiant).getHandle();
            nmsGiant.goalSelector.addGoal(0, new MeleeAttackGoal(nmsGiant, 1.0D, true));

            // Giants hunt both Raiders and Witches
            nmsGiant.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(nmsGiant, Raider.class, true));
            nmsGiant.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsGiant, Witch.class, true));

            if (nmsGiant.getAttribute(Attributes.FOLLOW_RANGE) != null) {
                nmsGiant.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(48.0);
            }
        }
    }
}