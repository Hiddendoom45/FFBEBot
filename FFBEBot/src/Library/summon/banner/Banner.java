package Library.summon.banner;

import Library.summon.Awakening;
import Library.summon.Unit;
import util.Lib;
public enum Banner {
	//TODO add proper awakenings to each banner, currently only 1 added
	Release("Release",new Unit[]{},new int[][]{},Lib.concat(new Unit[]{Unit.Rakshasa,Unit.Xiao,Unit.Exdeath,Unit.Bartz,Unit.Firion,Unit.Cecil,Unit.Garland,Unit.Miyuki,Unit.Vaan,Unit.CoD,Unit.Roselia,Unit.Cerius,Unit.Medius,Unit.Gilbert,Unit.Freya,Unit.Garnet},Unit.commons1()),new Awakening[]{},BannerType.ReleaseType),
	Chiz("Chiz, Golbez, Kefka",new Unit[]{Unit.Kefka,Unit.Chizuru,Unit.Golbez},new int[][]{{800,375,25},{250,50},{800,375,25}},Release.pool,null,BannerType.ReleaseType),
	Locke("Locke, Hayate, Artemios",new Unit[]{Unit.Locke,Unit.Hayate,Unit.Artemios},new int[][]{{560,366,33},{560,366,33},{700,366,33}},Lib.concat(Chiz.pool,Chiz.featured),new Awakening[]{},BannerType.ReleaseType),
	WoL("WoL, Lenna, Tellah",new Unit[]{Unit.WoL,Unit.Lenna,Unit.Tellah},new int[][]{{250,25},{1200,150,10},{1200,150,10}},Lib.concat(Locke.pool,Locke.featured),new Awakening[]{},BannerType.ReleaseType),
	FFIX("FFIX",new Unit[]{Unit.Zidane,Unit.Kuja,Unit.Amarant,Unit.Lani,Unit.Vivi},new int[][]{{160,26,10},{160,26,10},{160,26,10},{1000,228},{1000,228}},Lib.concat(WoL.featured,WoL.pool),new Awakening[]{},BannerType.MoreCommons),
	FFVI("FFVI",new Unit[]{Unit.Leo,Unit.Terra,Unit.Celes,Unit.Locke},new int[][]{{640,114,11},{640,114,11},{640,114,11},{640,114,11}},Lib.concat(FFIX.featured,FFIX.pool),new Awakening[]{},BannerType.MoreCommons),
	CD("Crystal Defenders",new Unit[]{Unit.Fencer,Unit.Thief,Unit.Juggler},new int[][]{{228,24},{228,24},{228,24}},Lib.concat(FFVI.pool,new Unit[]{Unit.Leo,Unit.Celes,Unit.Terra}),new Awakening[]{},BannerType.MoreCommons),
	PChoice("Player's choice",new Unit[]{Unit.Chizuru,Unit.WoL,Unit.CoD,Unit.Kefka,Unit.Lenna},new int[][]{{200,12},{200,12},{760,38,3},{760,38,3},{760,38,3}},CD.pool,new Awakening[]{},BannerType.MoreCommons),
	Lightning("Lightning",new Unit[]{Unit.Lightning,Unit.Charlotte,Unit.Ludmille},new int[][]{{50},{1040,285,15},{1040,285,15}},CD.pool,new Awakening[]{},BannerType.MoreCommons),
	FFT1("FFT part 1",new Unit[]{Unit.Deltia,Unit.Gaffgarion,Unit.Alma},new int[][]{{50},{570,3},{2000,150,5}},Lib.concat(Lightning.pool,Lightning.featured),new Awakening[]{},BannerType.Base5),
	FFT2("FFT part 2",new Unit[]{Unit.Ramza,Unit.Arigas,Unit.Mustadio},new int[][]{{50},{570,3},{2000,150,5}},Lib.concat(FFT1.pool,FFT1.featured),new Awakening[]{},BannerType.Base5),
	FF4("FFIV",new Unit[]{Unit.DKC,Unit.Rosa,Unit.Edge,Unit.Cecil},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(FFT2.pool,FFT2.featured),new Awakening[]{Awakening.FFIV},BannerType.Base5),
	Halloween("Halloween",new Unit[]{Unit.DRain,Unit.DLasswell,Unit.WWF,Unit.BCLid},new int[][]{{25},{25},{570,3},{2000,200,5}},Lib.concat(FF4.pool,new Unit[]{Unit.DKC,Unit.Rosa,Unit.Edge}),FF4.include,BannerType.Base5),
	FF3("FF3",new Unit[]{Unit.Luneth,Unit.Refia,Unit.Ingus,Unit.Arc},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Halloween.pool,Halloween.include,BannerType.Base5),
	BF("Brave Frontier",new Unit[]{Unit.Tilith,Unit.Seria,Unit.Karl},new int[][]{{330,3},{330,3},{330,3}},Lib.concat(FF3.pool,FF3.featured),FF3.include,BannerType.Base5),
	DD("Dark Destroyers",new Unit[]{Unit.DKC,Unit.Gaffgarion,Unit.CoD,Unit.Garland,Unit.Duane},new int[][]{{50},{570,3},{640,68,5},{640,68,5},{640,68,5}},BF.pool,BF.include,BannerType.Base5),
	FFV("FFV",new Unit[]{Unit.Greg,Unit.Bartz,Unit.Exdeath,Unit.Faris},new int[][]{{50},{950,160,20},{950,160,20},{950,160,20}},Lib.concat(new Unit[]{Unit.Duane},DD.pool),DD.include,BannerType.Base5),
	FFXIII1("FFXIII part 1",new Unit[]{Unit.Snow,Unit.Vanille,Unit.Sazh},new int[][]{{304,25},{304,25},{2800,19,10}},Lib.concat(new Unit[]{Unit.Greg,Unit.Faris},FFV.pool),FFV.include,BannerType.FFXIIIAjusted),
	FFXIII2("FFXIII part 2",new Unit[]{Unit.Lightning,Unit.Hope,Unit.Fang},new int[][]{{50},{570,3},{2000,150,5}},Lib.concat(FFXIII1.pool, FFXIII1.featured),FFXIII1.include,BannerType.FFXIIIAjusted),
	Noct("Noctis",new Unit[]{Unit.Noctis,Unit.Mercedes,Unit.Chizuru,Unit.Charlotte},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(new Unit[]{Unit.Hope,Unit.Fang},FFXIII2.pool),FFXIII2.include,BannerType.FFXIIIAjusted),
	Christmas("Christmas Exclusive",new Unit[]{Unit.WKN,Unit.SRoselia,Unit.Cerius,Unit.Medius},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(new Unit[]{Unit.Mercedes,Unit.Noctis},Noct.pool),Noct.include,BannerType.FFXIIIAjusted),
	DFina("Dark Fina",new Unit[]{Unit.DFina,Unit.Luka,Unit.Elle},new int[][]{{50},{570,3},{1000,200,5}},Christmas.pool,Christmas.include,BannerType.FFXIIIAjusted),
	FF01("FF type-0 part 1",new Unit[]{Unit.Ace,Unit.Seven,Unit.Jack,Unit.Trey},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(DFina.pool, DFina.featured),DFina.include,BannerType.FFXIIIAjusted),
	SoM("Secret of Mana",new Unit[]{Unit.Randi,Unit.Primm,Unit.Popoi},new int[][]{{50},{570,3},{1000,200,5}},Lib.concat(FF01.pool,FF01.featured),FF01.include,BannerType.FFXIIIAjusted),
	Valentine("Valentine Special",new Unit[]{Unit.Marie,Unit.CArtemois,Unit.CLuna},new int[][]{{50},{570,3},{1000,200,5}},SoM.pool,SoM.include,BannerType.FFXIIIAjusted),
	FFII("FFXII",new Unit[]{Unit.Emperor,Unit.Leon,Unit.Guy,Unit.Firion},new int[][]{{50},{570,3},{1000,200,5},{1000,200,5}},Lib.concat(new Unit[]{Unit.Marie},Valentine.pool),Valentine.include,BannerType.FFXIIIAjusted),
	//gacha 2.0 change
	Olive("Olive Banner",new Unit[]{Unit.Olive,Unit.Shine,Unit.Shera},BannerRates.EvenSplit.rate(),Lib.concat(new Unit[]{Unit.Emperor,Unit.Leon,Unit.Guy},FFII.pool),FFII.include,BannerType.BaseRare),
	FF02("FF type-0 part 2",new Unit[]{Unit.Queen,Unit.Nine,Unit.Eight,Unit.Clinque},BannerRates.D3.rate(),Lib.concat(Olive.featured,Olive.pool),Olive.include,BannerType.BaseRare),
	GilgVan("Gilgamesh Vanquishers",new Unit[]{Unit.Ramza,Unit.Snow,Unit.Arigas,Unit.Exdeath,Unit.Bartz}, BannerRates.D3D4.rate(),Lib.concat(FF02.featured,FF02.pool),FF02.include,BannerType.BaseRare),
	PC2("Second People's Choice",new Unit[]{Unit.Noctis,Unit.Greg,Unit.Refia,Unit.Chizuru,Unit.Cecil,Unit.Zidane},BannerRates.D3D4D5.rate(),GilgVan.pool,GilgVan.include, BannerType.BaseRare),
	FFTP2("FF Tactics part 2",new Unit[]{Unit.Cid,Unit.Soleil,Unit.Ovelia,Unit.Lawrence},BannerRates.D3.rate(),PC2.pool,PC2.include,BannerType.BaseRare),
	Egg("Egg Seekers",new Unit[]{Unit.Fryevia,Unit.Xon,Unit.Aiden},BannerRates.EvenSplit.rate(),Lib.concat(FFTP2.featured,FFTP2.pool),FFTP2.include,BannerType.BaseRare),
	AwkWarriors("Awakened Warriors",new Unit[]{Unit.Lightning,Unit.Mercedes,Unit.Seven,Unit.Medius},BannerRates.D4.rate(),Lib.concat(Egg.featured,Egg.pool),Egg.include,BannerType.BaseRare),
	FFVIP2("FFVI part 2",new Unit[]{Unit.TTerra,Unit.Setzer,Unit.Gau},BannerRates.EvenSplit.rate(),AwkWarriors.pool,AwkWarriors.include,BannerType.BaseRare),
	Eileen("Aileen",new Unit[]{Unit.Eileen,Unit.Soze,Unit.Heretic,Unit.Ulrica},BannerRates.D3.rate(),Lib.concat(FFVIP2.pool,FFVIP2.featured),FFVIP2.include,BannerType.BaseRare),
	DragonKill("Dragon Killers",new Unit[]{Unit.Reberta,Unit.Zyrus,Unit.Firion,Unit.Kain},BannerRates.D3.rate(),Lib.concat(Eileen.pool,Eileen.featured),Eileen.include,BannerType.BaseRare),
	FF03("FF type-0 part 3",new Unit[]{Unit.Rem,Unit.King,Unit.Sice},BannerRates.EvenSplit.rate(),Lib.concat(new Unit[]{Unit.Reberta,Unit.Zyrus},DragonKill.pool),DragonKill.include,BannerType.BaseRare),
	Wilhelm("Wilhelm", new Unit[]{Unit.Wilhelm,Unit.Grace,Unit.Jean,Unit.Abel},BannerRates.D3.rate(),Lib.concat(FF03.featured,FF03.pool),FF03.include,BannerType.BaseRare),
	Gathering("The Gathering",new Unit[]{Unit.Forren,Unit.Amelia,Unit.Illus,Unit.Camille},BannerRates.D4.rate(),Lib.concat(Wilhelm.featured,Wilhelm.pool),Wilhelm.include,BannerType.BaseRare),
	BF2("Brave Frontier 2",new Unit[]{Unit.Vargas,Unit.Tilith,Unit.Karl,Unit.Seria},BannerRates.LS5T4.rate(),Lib.concat(Gathering.pool,Gathering.featured),Gathering.include,BannerType.BaseRare),
	Summer("Summer",new Unit[]{Unit.SMF,Unit.SFina,Unit.SLid},BannerRates.EvenSplit.rate(),BF2.pool,BF2.include, BannerType.BaseRare),
	FFX("FFX",new Unit[]{Unit.Tidus,Unit.Rikku,Unit.Wakka},BannerRates.EvenSplit.rate(),Lib.concat(Summer.pool,Summer.featured),Summer.include, BannerType.BaseRare),
	FFXII("FFXII",new Unit[]{Unit.Zargabaath,Unit.Rasler,Unit.Ashe,Unit.Vaan},BannerRates.D4.rate(),Lib.concat(FFX.pool, FFX.featured),FFX.include,BannerType.BaseRare),
	Promise("A promise beyond time",new Unit[]{Unit.Lunera,Unit.Bran,Unit.Helena,Unit.Ruggles},BannerRates.D4.rate(),Lib.concat(new Unit[]{Unit.Zargabaath,Unit.Rasler,Unit.Ashe},FFXII.pool),FFXII.include,BannerType.BaseRare),
	AwkWarriorsP2("Awakened Warriors Part 2",new Unit[]{Unit.Luneth,Unit.DKC,Unit.Refia,Unit.Rosa,Unit.Cecil,Unit.Exdeath},BannerRates.D3D4D5.rate(),Lib.concat(Promise.pool, Promise.featured),Promise.include,BannerType.BaseRare),
	FFTP3("FF Tactics part 3",new Unit[]{Unit.MercRamza,Unit.KDeltia,Unit.Orran,Unit.Meliadoul,Unit.Alma},BannerRates.D4D5.rate(),AwkWarriorsP2.pool,AwkWarriors.include,BannerType.BaseRare),
	Revenge("Time for Revenge(DV)",new Unit[]{Unit.DV,Unit.FV,Unit.EV,Unit.Victoria,Unit.Tim},BannerRates.D4D5.rate(),Lib.concat(FFTP3.pool,new Unit[]{Unit.MercRamza,Unit.KDeltia,Unit.Orran,Unit.Meliadoul}),FFTP3.include,BannerType.BaseRare),
	Nier("Nier",new Unit[]{Unit.N2B,Unit.A2,Unit.Eve,Unit.N9S,Unit.N21O},BannerRates.D4D5.rate(),Lib.concat(Revenge.pool, Revenge.featured),Revenge.include,BannerType.BaseRare),
	Malboro("Malboro Killers",new Unit[]{Unit.TTerra,Unit.WoL,Unit.Soleil,Unit.Kefka,Unit.CoD},BannerRates.D3D4.rate(),Nier.pool,Nier.include,BannerType.BaseRare),
	Memories("Memories from the Battlefield",new Unit[]{Unit.LV,Unit.HV,Unit.WV,Unit.Hayate,Unit.Charlotte},BannerRates.D3D4.rate(),Malboro.pool,Malboro.include,BannerType.BaseRare),
	FFIII("FFIII",new Unit[]{Unit.OK,Unit.Desch,Unit.Aria,Unit.Sara},BannerRates.D4.rate(),Lib.concat(Memories.pool, new Unit[]{Unit.LV,Unit.HV,Unit.WV}),Memories.include,BannerType.BaseRare),
	DevChoice("Developer's Choice",new Unit[]{Unit.Greg,Unit.Chizuru,Unit.Abel},BannerRates.EvenSplit.rate(),Lib.concat(FFIII.pool, FFIII.featured),FFIII.include,BannerType.BaseRare),
	FFXI("FFXI",new Unit[]{Unit.Prishe,Unit.Werei,Unit.Kupipi,Unit.Shantotto},BannerRates.D3.rate(),DevChoice.pool,DevChoice.include,BannerType.BaseRare),
	Ayaka("Ayaka",new Unit[]{Unit.Ayaka,Unit.Goken,Unit.Silvia,Unit.Kamui,Unit.Yuri},BannerRates.D3D4.rate(),Lib.concat(new Unit[]{Unit.Prishe,Unit.Werei,Unit.Kupipi},FFXI.pool),FFXI.include,BannerType.BaseRare),
	FFXV("FFXV",new Unit[]{Unit.Nyx,Unit.Crowe,Unit.Glauca,Unit.Libertus},BannerRates.D4.rate(),Lib.concat(Ayaka.pool,Ayaka.featured),Ayaka.include,BannerType.BaseRare),
	HalloweenP2_1("Halloween 2 Part 1",new Unit[]{Unit.GLSakura,Unit.INichol,Unit.PJake,Unit.BCLid},BannerRates.D4.rate(),Lib.concat(FFXV.pool, FFXV.featured),FFXV.include,BannerType.BaseRare),
	HalloweenP2_2("Halloween 2 Part 2",new Unit[]{Unit.DLasswell,Unit.DRain,Unit.WWF,Unit.BCLid},BannerRates.D5.rate(),HalloweenP2_1.pool,HalloweenP2_1.include,BannerType.BaseRare),
	Loren("Loren",new Unit[]{Unit.Lorraine,Unit.Chloe,Unit.Amy},BannerRates.EvenSplit.rate(),HalloweenP2_2.pool,HalloweenP2_2.include,BannerType.BaseRare),
	FFIVFiend("FFIV Fiends", new Unit[]{Unit.Barbariccia,Unit.Cagnazzo,Unit.Rubicante,Unit.Scarmiglione},BannerRates.D4.rate(),Lib.concat(Loren.pool, Loren.featured),Loren.include,BannerType.BaseRare),
	Performer("Performer's special",new Unit[]{Unit.KittyAriana,Unit.Ling,Unit.Penelo},BannerRates.EvenSplit.rate(),Lib.concat(FFIVFiend.pool,FFIVFiend.featured),FFIVFiend.include,BannerType.BaseRare),
	//3% rainbow rates
	FFXV2("FFXV 2",new Unit[]{Unit.Noctis,Unit.Gladiolus,Unit.Cor,Unit.Iris},BannerRates.D5.fiveRate(),Performer.pool,Performer.include,BannerType.ThreePercent),
	Duke("Duke",new Unit[]{Unit.Duke,Unit.Olif,Unit.Mystea,Unit.Charie,Unit.Ryunan},BannerRates.D3D4.fiveRate(),Lib.concat(new Unit[]{Unit.Gladiolus,Unit.Cor,Unit.Iris},FFXV2.pool),FFXV2.include,BannerType.ThreePercent),
	Roy("Roy",new Unit[]{Unit.Roy,Unit.Aura,Unit.Guromu},BannerRates.EvenSplit.fiveRate(),Lib.concat(Duke.pool, Duke.featured),Duke.include,BannerType.ThreePercent),
	Cloud("Cloud",new Unit[]{Unit.Cloud,Unit.Elfreeda,Unit.William,Unit.Conrad},BannerRates.D5.fiveRate(),Lib.concat(Roy.pool, Roy.featured),Roy.include,BannerType.ThreePercent),
	EternalWinter("Eternal Winter",new Unit[]{Unit.Christine,Unit.Kryla,Unit.TinkCarrie,Unit.Gilbert},BannerRates.D5.fiveRate(),Lib.concat(Cloud.pool,Cloud.featured),Cloud.include,BannerType.ThreePercent),
	Christmas2("Christmas 2",new Unit[]{Unit.WKN,Unit.SRoselia,Unit.Gilbert},BannerRates.EvenSplit.fiveRate(),EternalWinter.pool,EternalWinter.include,BannerType.ThreePercent),
	KingKnight("King's Knight",new Unit[]{Unit.RayJack,Unit.Kaliva,Unit.Barusa,Unit.Toby},BannerRates.D4.fiveRate(),Christmas2.pool,Christmas2.include,BannerType.ThreePercent),
	CGLasswell("CG Lasswell",new Unit[]{Unit.CGLasswell,Unit.Jiraiya,Unit.Kaede,Unit.Ohga,Unit.Otogiri,Unit.Miyuki},BannerRates.D3D4.fiveRate(),KingKnight.pool,KingKnight.include,BannerType.ThreePercent),
	FFXII2("FFXII 2",new Unit[]{Unit.Basch,Unit.Balthier,Unit.Drace,Unit.Vayne,Unit.Larsa},BannerRates.D4D5.fiveRate(),Lib.concat(new Unit[]{Unit.CGLasswell,Unit.Jiraiya,Unit.Kaede,Unit.Ohga,Unit.Otogiri}, CGLasswell.pool),CGLasswell.include,BannerType.ThreePercent),
	DevChoiceSupport("Developer's Choice(Support)",new Unit[]{Unit.DV,Unit.Ramza,Unit.Crowe,Unit.Desch,Unit.Tim},BannerRates.D4D5.fiveRate(),Lib.concat(FFXII2.pool, FFXII2.featured),FFXII2.include,BannerType.ThreePercent),
	DevChoiceHealer("Developer's Choice(Healer)",new Unit[]{Unit.Ayaka,Unit.Rem,Unit.Luka,Unit.Refia,Unit.Aiden},BannerRates.D4D5.fiveRate(),DevChoiceSupport.pool,DevChoiceSupport.include,BannerType.ThreePercent),
	CGSakura("CG Sakura",new Unit[]{Unit.CGSakura,Unit.Verun,Unit.Cedona,Unit.Roselia,Unit.Ruggles},BannerRates.D4D5.fiveRate(),DevChoiceHealer.pool,DevChoiceHealer.include,BannerType.ThreePercent),
	LoveFest("Festival of Love",new Unit[]{Unit.Chow,Unit.Ang,Unit.Yan,Unit.Miyuki},BannerRates.D5.fiveRate(),Lib.concat(new Unit[]{Unit.CGSakura,Unit.Verun,Unit.Cedona}, CGSakura.pool),CGSakura.include,BannerType.ThreePercent),
	Valentine2("Valentine Special 2",new Unit[]{Unit.Yun,Unit.Ling,Unit.CArtemois,Unit.CLuna},BannerRates.D4.fiveRate(),LoveFest.pool,LoveFest.include,BannerType.ThreePercent),
	FFXV3("FFXV 3",new Unit[]{Unit.Aranea,Unit.Prompto,Unit.Cor,Unit.Iris},BannerRates.D5.fiveRate(), Valentine2.getPool(),Valentine2.include,BannerType.ThreePercent),
	FFIX2("FFIX 2",new Unit[]{Unit.Beatrix,Unit.Eiko,Unit.Steiner,Unit.BW3},BannerRates.D5.fiveRate(), Lib.concat(new Unit[]{Unit.Aranea,Unit.Prompto}, FFXV3.pool),FFXV3.include,BannerType.ThreePercent),
	TombRaider("Tomb Raider",new Unit[]{Unit.EAileen,Unit.DSoleil,Unit.PAbel},BannerRates.EvenSplit.fiveRate(),Lib.concat(FFIX2.featured, FFIX2.pool),FFIX2.include,BannerType.ThreePercent),
	CGFina("CG Fina",new Unit[]{Unit.CGFina,Unit.Kunshira,Unit.Wadow,Unit.Erwin},BannerRates.D5.fiveRate(),TombRaider.pool,TombRaider.include,BannerType.ThreePercent),
	FFXOld("FFX old",new Unit[]{Unit.Tidus,Unit.Rikku,Unit.Wakka},BannerRates.EvenSplit.fiveRate(),Lib.concat(CGFina.pool, CGFina.featured),CGFina.include,BannerType.ThreePercent),
	FFX2("FFX 2",new Unit[]{Unit.Yuna,Unit.Lulu,Unit.Seymour,Unit.Wakka},BannerRates.D5.fiveRate(),FFXOld.pool,FFXOld.include,BannerType.ThreePercent),
	CGJake("CG Jake",new Unit[]{Unit.CGJake,Unit.EShera,Unit.Ozetta,Unit.Camille,Unit.Riley},BannerRates.D3D5.fiveRate(),Lib.concat(new Unit[]{Unit.Yuna,Unit.Lulu,Unit.Seymour}, FFX2.pool),FFX2.include,BannerType.ThreePercent),
	DQ("Dragon Quest",new Unit[]{Unit.Dragonlord,Unit.Orochi,Unit.LMSlime,Unit.KillingMachine,Unit.Golem,Unit.RobbinOod},BannerRates.D3T4.fiveRate(),Lib.concat(new Unit[]{Unit.CGJake,Unit.EShera,Unit.Ozetta,Unit.Riley}, CGJake.pool),CGJake.include,BannerType.ThreePercent),
	Sephiroth("Sephiroth Arrives",new Unit[]{Unit.Sephiroth,Unit.Lila,Unit.Shylt,Unit.Mim},BannerRates.D5.fiveRate(),DQ.pool,DQ.include,BannerType.ThreePercent),
	CGLid("CG Lid",new Unit[]{Unit.CGLid,Unit.Killian,Unit.Ulrica,Unit.Heretic},BannerRates.D3.fiveRate(),Lib.concat(Sephiroth.featured, Sephiroth.pool),Sephiroth.include,BannerType.ThreePercent),
	Cloud2("Cloud Special",new Unit[]{Unit.Cloud,Unit.Elfreeda,Unit.William,Unit.Conrad},BannerRates.D5.fiveRate(),Lib.concat(new Unit[]{Unit.CGLid,Unit.Killian}, CGLid.pool),CGLid.include,BannerType.ThreePercent),
	FFIV2("FFIV 2",new Unit[]{Unit.KRydia,Unit.HKain,Unit.Yang,Unit.Edward},BannerRates.D5.fiveRate(),Cloud2.pool,Cloud2.include,BannerType.ThreePercent),
	KingKnight2("King's Knight 2",new Unit[]{Unit.RayJack,Unit.Kaliva,Unit.Barusa,Unit.Toby},BannerRates.D4.fiveRate(),Lib.concat(FFIV2.featured, FFIV2.pool),FFIV2.include,BannerType.ThreePercent),
	Nichol("Nichol",new Unit[]{Unit.CGNichol,Unit.Elbis,Unit.Lexa,Unit.Merald,Unit.Charie},BannerRates.D3D4.fiveRate(),KingKnight2.pool,KingKnight2.include,BannerType.ThreePercent),
	JC3("Just Cause 3",new Unit[]{Unit.RicoRodriguez,Unit.MarioFrigo,Unit.AnnikaSvennson},BannerRates.EvenSplit.fiveRate(),Lib.concat(new Unit[]{Unit.CGNichol,Unit.Elbis,Unit.Lexa,Unit.Merald},Nichol.pool),Nichol.include,BannerType.ThreePercent),
	FFBEHero1("FFBE Heroes part 1",new Unit[]{Unit.CGLasswell,Unit.CGFina,Unit.CGJake,Unit.CGSakura,Unit.CGNichol,Unit.CGLid,Unit.Chizuru,Unit.Erwin},BannerRates.FFBEHeroes.fiveRate(),JC3.pool,JC3.include,BannerType.ThreePercent),
	FFBEHero2("FFBE Heroes part 2",new Unit[]{Unit.CGJake,Unit.CGSakura,Unit.CGLasswell,Unit.CGFina,Unit.CGNichol,Unit.CGLid,Unit.Vanille,Unit.Tim},BannerRates.FFBEHeroes.fiveRate(),FFBEHero1.pool,FFBEHero1.include,BannerType.ThreePercent),
	FFBEHero3("FFBE Heroes part 3",new Unit[]{Unit.CGNichol,Unit.CGLid,Unit.CGLasswell,Unit.CGFina,Unit.CGJake,Unit.CGSakura,Unit.Rikku,Unit.Jack},BannerRates.FFBEHeroes.fiveRate(),FFBEHero2.pool,FFBEHero2.include,BannerType.ThreePercent),
	Raegen("CG Raegen",new Unit[]{Unit.CGRaegen,Unit.Ryumynui,Unit.Zile,Unit.Lucille},BannerRates.D4.fiveRate(),FFBEHero3.pool,FFBEHero3.include,BannerType.ThreePercent),
	SSJRain("Awakened Rain/Mediena",new Unit[]{Unit.ARain,Unit.Mediena,Unit.Shylt,Unit.Shantotto},BannerRates.D5.fiveRate(),Lib.concat(Raegen.featured,Raegen.pool),Raegen.include,BannerType.ThreePercent),
	SOA("Star Ocean Anamnesis",new Unit[]{Unit.Rena,Unit.Fayt,Unit.Fidel,Unit.Roddick},BannerRates.D5.fiveRate(),Lib.concat(new Unit[]{Unit.ARain,Unit.Mediena},SSJRain.pool),SSJRain.include,BannerType.ThreePercent),
	FFVIII("FFVIII",new Unit[]{Unit.Squall,Unit.Rinoa,Unit.Zell,Unit.Ramira},BannerRates.D5.fiveRate(),SOA.pool,SOA.include,BannerType.ThreePercent),
	Nalu("Nalu",new Unit[]{Unit.Nalu,Unit.Pecciotta,Unit.Shinju,Unit.Ryuka},BannerRates.D4.fiveRate(),Lib.concat(FFVIII.featured, FFVIII.pool),FFVIII.include,BannerType.ThreePercent),
	DeusEx("Deus Ex",new Unit[]{Unit.AJensen,Unit.VMarchenko,Unit.VKoller,Unit.FPritchard},BannerRates.D5.fiveRate(),Lib.concat(Nalu.featured, Nalu.pool),Nalu.include,BannerType.ThreePercent),
	FFXI2("FFXI 2", new Unit[]{Unit.RageShantotto,Unit.ShadowLord,Unit.Joachim,Unit.Kupipi},BannerRates.D5.fiveRate(),DeusEx.pool,DeusEx.include,BannerType.ThreePercent),
	DQ2("Dragon Quest 2",new Unit[]{Unit.Estark,Unit.KillingMachine,Unit.Orochi,Unit.LMSlime,Unit.Golem,Unit.RobbinOod},BannerRates.D3T4.fiveRate(),Lib.concat(new Unit[]{Unit.RageShantotto,Unit.ShadowLord,Unit.Joachim}, FFXI2.pool),FFXI2.include,BannerType.ThreePercent),
	Hyoh("CG Hyou",new Unit[]{Unit.Hyoh,Unit.Shatal,Unit.Domino},BannerRates.EvenSplit.fiveRate(),DQ2.pool,DQ2.include,BannerType.ThreePercent),
	SSSummer("Sand Sea Summer in Fall",new Unit[]{Unit.AlohaLasswell,Unit.SeaNichol,Unit.SummerLuka,Unit.TideSkaha},BannerRates.D5.fiveRate(),Lib.concat(Hyoh.featured, Hyoh.pool),Hyoh.include,BannerType.ThreePercent),
	GLArtP1("Fest Units part 1",new Unit[]{Unit.Malphasie,Unit.Circe},BannerRates.LD5.fiveRate(),Lib.concat(SSSummer.featured, SSSummer.pool),SSSummer.include,BannerType.ThreePercent),
	FFXV4("FFXV 4",new Unit[]{Unit.Ignis,Unit.Ravus,Unit.Libertus},BannerRates.EvenSplit.fiveRate(),Lib.concat(GLArtP1.featured, GLArtP1.pool),GLArtP1.include,BannerType.ThreePercent),
	Citra("Citra",new Unit[]{Unit.Citra,Unit.Macmedi,Unit.Lotti},BannerRates.EvenSplit.fiveRate(),Lib.concat(new Unit[]{Unit.Ignis,Unit.Ravus}, FFXV4.pool),FFXV4.include,BannerType.ThreePercent),
	HalloweenP3_1("Halloween 2018 new",new Unit[]{Unit.Lucifer,Unit.Lilith},BannerRates.LD5.fiveRate(),Lib.concat(Citra.featured, Citra.pool),Citra.include,BannerType.ThreePercent),
	VP("Valkyrie profile triple rainbow BS", new Unit[]{Unit.Lenneth,Unit.VPFreya,Unit.Arngrim,Unit.Lucian,Unit.Jelanda},BannerRates.T5.fiveRate(),HalloweenP3_1.pool,HalloweenP3_1.include,BannerType.ThreePercent),
	FF04("FF type-0 part 4",new Unit[]{Unit.Machina,Unit.Kurasame,Unit.Deuce,Unit.Cater},BannerRates.D5.fiveRate(),VP.pool,VP.include,BannerType.ThreePercent),
	Elephim("Elephim",new Unit[]{Unit.Elephim,Unit.Leopold,Unit.Magna,Unit.Forelsket},BannerRates.D4.fiveRate(),Lib.concat(FF04.featured, FF04.pool),FF04.include,BannerType.ThreePercent),
	Current("Current",new Unit[]{},new int[][]{},Unit.currentPool(),Awakening.values(),BannerType.ThreePercent);
	public String name;
	public Unit[] featured;
	private Unit[] pool;
	public int[][] percent;//percent at each rarity
	public Awakening[] include;
	public BannerType type;
	Banner(String name, Unit[] featured,int[][] percent, Unit[] pool,Awakening[] include,BannerType type){
		this.name=name;
		this.featured=featured;
		this.pool=pool;
		this.percent=percent;
		this.include=include;
		this.type=type;
	}
	Banner(String name, Unit[] featured,int[] percent, Unit[] pool,Awakening[] include,BannerType type){
		this.name=name;
		this.featured=featured;
		this.pool=pool;
		int[][] per=new int[percent.length][];
		for(int i=0;i<per.length;i++){
			per[i]=new int[featured[i].url.length];
			per[i][0]=percent[i];
		}
		this.percent=per;
		this.include=include;
		this.type=type;
	}
	public Unit[] getPool(){
		Unit[] pool=this.pool;
		if(type==BannerType.ReleaseType){
			for(int i=1;i*Unit.commons1().length<(pool.length-(i*Unit.commons1().length))*4;i++){
				pool=Lib.concat(pool, Unit.commons1());
			}
		}
		else if(type==BannerType.MoreCommons){
			for(int i=1;i*Unit.commons2().length<(pool.length-(i*Unit.commons2().length))*4;i++){
				pool=Lib.concat(pool, Unit.commons2());
			}
		}
		return pool;
	}
	public static Banner[] LEBanners(){
		return new Banner[]{Banner.Halloween,
				Banner.BF,
				Banner.Christmas,
				Banner.SoM,
				Banner.Valentine,
				Banner.BF2,
				Banner.Nier,
				Banner.HalloweenP2_1,
				Banner.HalloweenP2_2,
				Banner.Performer,
				Banner.EternalWinter,
				Banner.Christmas2,
				Banner.KingKnight,
				Banner.LoveFest,
				Banner.TombRaider,
				Banner.DQ,
				Banner.KingKnight2,
				Banner.JC3,
				Banner.SOA,
				Banner.DeusEx,
				Banner.DQ2,
				Banner.HalloweenP3_1,
				Banner.VP
				};
	}
	public static boolean LEBanner(Banner banner){
		for(Banner b:LEBanners()){
			if(b.equals(banner)){
				return true;
			}
		}
		return false;
	}
}
