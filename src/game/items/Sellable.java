package game.items;

import game.effects.PurchaseEffect;

import java.util.List;

public interface Sellable {
    List<PurchaseEffect> soldEffects();
}
