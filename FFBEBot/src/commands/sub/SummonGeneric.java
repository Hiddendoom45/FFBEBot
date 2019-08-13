package commands.sub;

import java.util.List;

import Library.summon.UnitSpecific;
import Library.summon.banner.Banner;

public interface SummonGeneric{
	
	public List<UnitSpecific> doSummon(int num, Banner banner);

}
