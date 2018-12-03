package curse_plus_mod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EchoPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class NormalityRelic extends CustomRelic {

    private static final int PLAY_LIMIT = 3;

    public NormalityRelic() {
        super("CursePlus:Normality", "", AbstractRelic.RelicTier.SPECIAL, null);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new NormalityRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new EchoPower(AbstractDungeon.player, 1), 3));
        this.counter = 0;
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        AbstractCard prevCard = card;
        if ((this.counter < PLAY_LIMIT) && (card.type != AbstractCard.CardType.CURSE) && card.uuid == prevCard.uuid) {
            this.counter += 1;
            if (this.counter >= PLAY_LIMIT) {
                flash();
            }
        }
    }

    public boolean canPlay(AbstractCard card) {
        if ((this.counter >= PLAY_LIMIT) && (card.type != AbstractCard.CardType.CURSE)) {
            card.cantUseMessage = (this.DESCRIPTIONS[3] + PLAY_LIMIT + this.DESCRIPTIONS[1]);
            return false;
        }
        return true;
    }

    public void onVictory() {
        this.counter = -1;
    }
}
