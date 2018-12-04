package curse_plus_mod;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import curse_plus_mod.relics.NormalityRelic;

@SpireInitializer
public class CursePlusMod implements EditRelicsSubscriber, EditStringsSubscriber{
    
    public static final String CURSE_PLUS_ASSETS_FOLDER = "curse_plus_mod/resources/img";
    
    public static final String makePath(String resource) {
        return CURSE_PLUS_ASSETS_FOLDER + "/" + resource;
    }
    
    public CursePlusMod() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        @SuppressWarnings("unused")
        CursePlusMod gm = new CursePlusMod();
    }
    
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new NormalityRelic(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings.class, "curse_plus_mod/resources/localization/RelicStrings.json");
    }

}
