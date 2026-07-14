package com.conkcreets.reelresources.registers;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags 
{
    public static class Items 
    {
        public static final TagKey<Item> FISH = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("reelresources", "fish"));

        public static final TagKey<Item> ROE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("reelresources", "roe"));
    }

}
