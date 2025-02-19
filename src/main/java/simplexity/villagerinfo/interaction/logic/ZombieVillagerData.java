package simplexity.villagerinfo.interaction.logic;

import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;

@SuppressWarnings("FieldMayBeFinal")
public class ZombieVillagerData {

    private Integer conversionTime;
    private Double currentHealth;
    private Double maxHealth;
    private Villager.Profession profession;
    private Villager.Type villagerType;
    private Boolean isConverting;
    private OfflinePlayer convertingPlayer;

    public ZombieVillagerData(ZombieVillager zombieVillager) {
        AttributeInstance maxHealthAttribute = zombieVillager.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        conversionTime = zombieVillager.getConversionTime();
        currentHealth = zombieVillager.getHealth();
        profession = zombieVillager.getVillagerProfession();
        villagerType = zombieVillager.getVillagerType();
        isConverting = zombieVillager.isConverting();
        convertingPlayer = zombieVillager.getConversionPlayer();
        if (maxHealthAttribute != null) maxHealth = maxHealthAttribute.getValue();
    }

    public Integer getConversionTimeLeftSeconds() {
        return conversionTime / 20;
    }

    public Double getCurrentHealth() {
        return currentHealth;
    }

    public Double getMaxHealth() {
        return maxHealth;
    }

    public Villager.Profession getProfession() {
        return profession;
    }

    public Villager.Type getVillagerType() {
        return villagerType;
    }

    public Boolean isConverting() {
        return isConverting;
    }

    public OfflinePlayer getConvertingPlayer() {
        return convertingPlayer;
    }

}
