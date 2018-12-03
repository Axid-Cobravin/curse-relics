package curse_plus_mod;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import curse_plus_mod.relics.NormalityRelic;

public class CursePlusMod implements EditRelicsSubscriber{
    
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

}
