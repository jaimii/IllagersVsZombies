package project.kompass.illagerVZombies;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;

import org.bukkit.craftbukkit.entity.CraftPiglin;
import org.bukkit.craftbukkit.entity.CraftPiglinBrute;
import org.bukkit.craftbukkit.entity.CraftGiant;
import org.bukkit.craftbukkit.entity.CraftRaider;
import org.bukkit.craftbukkit.entity.CraftZombie;
import org.bukkit.craftbukkit.entity.CraftWitch;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobAIListener implements Listener {

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        // 1. Handle ALL RAIDERS
        if (event.getEntity() instanceof org.bukkit.entity.Raider bRaider) {
            Raider nmsRaider = ((CraftRaider) bRaider).getHandle();

            //FEAR GOALS
            nmsRaider.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsRaider, Giant.class, 24.0F, 1.0D, 1.2D));
            nmsRaider.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsRaider, Warden.class, 16.0F, 1.0D, 1.2D));

            //ATTACK GOALS
            nmsRaider.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsRaider, Zombie.class, true));
            nmsRaider.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsRaider, Piglin.class, true));
            nmsRaider.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsRaider, PiglinBrute.class, true));
        }

        // 2. Handle WITCHES
        else if (event.getEntity() instanceof org.bukkit.entity.Witch bWitch) {
            Witch nmsWitch = ((CraftWitch) bWitch).getHandle();

            //FEAR GOALS
            nmsWitch.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsWitch, Giant.class, 24.0F, 1.0D, 1.2D));
            nmsWitch.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsWitch, Warden.class, 16.0F, 1.0D, 1.2D));
            nmsWitch.goalSelector.addGoal(2, new AvoidEntityGoal<>(nmsWitch, Zombie.class, 16.0F, 1.0D, 1.2D));

            //ATTACK GOALS
            nmsWitch.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsWitch, Piglin.class, true));
            nmsWitch.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsWitch, PiglinBrute.class, true));
        }

        // 3. Handle ZOMBIES
        else if (event.getEntity() instanceof org.bukkit.entity.Zombie bZombie) {
            Zombie nmsZombie = ((CraftZombie) bZombie).getHandle();

            //FEAR GOALS
            nmsZombie.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsZombie, Raider.class, true));
            nmsZombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(nmsZombie, Witch.class, true));
        }

        // 4. Handle GIANTS
        else if (event.getEntity() instanceof org.bukkit.entity.Giant bGiant) {
            Giant nmsGiant = ((CraftGiant) bGiant).getHandle();

            //ATTACK GOALS
            nmsGiant.goalSelector.addGoal(0, new MeleeAttackGoal(nmsGiant, 1.0D, true));
            nmsGiant.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(nmsGiant, Raider.class, true));
            nmsGiant.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsGiant, Witch.class, true));
            nmsGiant.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(nmsGiant, Piglin.class, true));
            nmsGiant.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(nmsGiant, PiglinBrute.class, true));

            if (nmsGiant.getAttribute(Attributes.FOLLOW_RANGE) != null) {
                nmsGiant.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(48.0);
            }
        }
        // 5. Handle PIGLINS
        else if (event.getEntity() instanceof org.bukkit.entity.Piglin bPiglin) {
            bPiglin.setImmuneToZombification(true);
            Piglin nmsPiglin = ((CraftPiglin) bPiglin).getHandle();

            // FEAR GOALS
            nmsPiglin.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsPiglin, Giant.class, 24.0F, 1.0D, 1.2D));
            nmsPiglin.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsPiglin, Warden.class, 16.0F, 1.0D, 1.2D));
            nmsPiglin.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsPiglin, Zombie.class,  16.0F, 1.0D, 1.2D));

            // ATTACK GOALS
            nmsPiglin.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsPiglin, Raider.class, true));
            nmsPiglin.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(nmsPiglin, Witch.class, true));
        }

        // 6. Handle PIGLIN BRUTES
        else if (event.getEntity() instanceof org.bukkit.entity.PiglinBrute bBrute) {
            bBrute.setImmuneToZombification(true);
            PiglinBrute nmsBrute = ((CraftPiglinBrute) bBrute).getHandle();

            // FEAR GOALS
            nmsBrute.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsBrute, Giant.class, 24.0F, 1.0D, 1.2D));
            nmsBrute.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsBrute, Warden.class, 16.0F, 1.0D, 1.2D));
            nmsBrute.goalSelector.addGoal(1, new AvoidEntityGoal<>(nmsBrute, Zombie.class,  16.0F, 1.0D, 1.2D));

            // ATTACK GOALS
            nmsBrute.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(nmsBrute, Raider.class, true));
            nmsBrute.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(nmsBrute, Witch.class, true));
        }
    }
}