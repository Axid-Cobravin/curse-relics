package curse_plus_mod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EchoPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class NormalityRelic extends CustomRelic {

    private static final int PLAY_LIMIT = 6;

    public NormalityRelic() {
        super("CursePlus:Normality", "", AbstractRelic.RelicTier.SPECIAL, null);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new NormalityRelic();
    }

    @Override
    public String getUpdatedDescription()
    {
      if (AbstractDungeon.player != null) {
        return setDescription(AbstractDungeon.player.chosenClass);
      }
      return setDescription(null);
    }
    
    private String setDescription(AbstractPlayer.PlayerClass c)
    {
      return this.DESCRIPTIONS[2] + this.DESCRIPTIONS[0] + PLAY_LIMIT + this.DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new EchoPower(AbstractDungeon.player, 1), 3));
        this.counter = 0;
    }

    public void atTurnStart() {
        this.counter = 0;
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if ((this.counter < PLAY_LIMIT) && (card.type != AbstractCard.CardType.CURSE)) {
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
