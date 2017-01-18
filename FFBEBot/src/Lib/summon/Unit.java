package Lib.summon;

import java.util.ArrayList;
import util.Lib;
public enum Unit {
	Vivi("Vivi",new String[]{"https://exviuswiki.com/images/0/0f/Unit-Vivi-3.png","https://exviuswiki.com/images/d/d3/Unit-Vivi-4.png"},3),
	Penelo("Penelo",new String[]{"https://exviuswiki.com/images/6/6f/Unit-Penelo-3.png","https://exviuswiki.com/images/5/5a/Unit-Penelo-4.png"},3),
	Maria("Maria",new String[]{"https://exviuswiki.com/images/f/f3/Unit-Maria-3.png","https://exviuswiki.com/images/2/28/Unit-Maria-4.png"},3),
	Sabin("Sabin",new String[]{"https://exviuswiki.com/images/c/c0/Unit-Sabin-3.png","https://exviuswiki.com/images/e/e3/Unit-Sabin-4.png"},3),
	Shadow("Shadow",new String[]{"https://exviuswiki.com/images/3/3b/Unit-Shadow-3.png","https://exviuswiki.com/images/4/4a/Unit-Shadow-4.png"},3),
	Krile("Krile",new String[]{"https://exviuswiki.com/images/d/da/Unit-Krile-3.png","https://exviuswiki.com/images/a/a9/Unit-Krile-4.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"https://exviuswiki.com/images/6/6d/Unit-Krile-5.png"});
		}
	},
	Kain("Kain",new String[]{"https://exviuswiki.com/images/1/1a/Unit-Kain-3.png","https://exviuswiki.com/images/b/b8/Unit-Kain-4.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"https://exviuswiki.com/images/7/77/Unit-Kain-5.png"});
		}
	},
	Edgar("Edgar",new String[]{"https://exviuswiki.com/images/e/e6/Unit-Edgar-3.png","https://exviuswiki.com/images/a/a8/Unit-Edgar-4.png"},3),
	Fran("Fran",new String[]{"https://exviuswiki.com/images/5/54/Unit-Fran-3.png","https://exviuswiki.com/images/1/1e/Unit-Fran-4.png"},3),
	Shantotto("Shantotto",new String[]{"https://exviuswiki.com/images/9/9c/Unit-Shantotto-3.png","https://exviuswiki.com/images/d/d9/Unit-Shantotto-4.png"},3),
	Rydia("Rydia",new String[]{"https://exviuswiki.com/images/8/89/Unit-Rydia-3.png","https://exviuswiki.com/images/5/56/Unit-Rydia-4.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"https://exviuswiki.com/images/1/15/Unit-Rydia-5.png"});
		}
	},
	Cyan("Cyan",new String[]{"https://exviuswiki.com/images/9/9d/Unit-Cyan-3.png","https://exviuswiki.com/images/6/6c/Unit-Cyan-4.png"},3),
	Clyne("Clyne",new String[]{"https://exviuswiki.com/images/f/f0/Unit-Clyne-3.png","https://exviuswiki.com/images/9/99/Unit-Clyne-4.png"},3),
	Anzelm("Anzelm",new String[]{"https://exviuswiki.com/images/a/a7/Unit-Anzelm-3.png","https://exviuswiki.com/images/0/04/Unit-Anzelm-4.png"},3),
	Luna("Luna",new String[]{"https://exviuswiki.com/images/0/0e/Unit-Luna-3.png","https://exviuswiki.com/images/d/d4/Unit-Luna-4.png"},3),
	Bedile("Bedile",new String[]{"https://exviuswiki.com/images/9/91/Unit-Bedile-3.png","https://exviuswiki.com/images/f/f8/Unit-Bedile-4.png"},3),
	Garland("Garland",new String[]{"https://exviuswiki.com/images/d/d5/Unit-Garland-3.png","https://exviuswiki.com/images/9/91/Unit-Garland-4.png","https://exviuswiki.com/images/0/08/Unit-Garland-5.png"},3),
	Exdeath("Exdeath",new String[]{"https://exviuswiki.com/images/8/8d/Unit-Exdeath-3.png","https://exviuswiki.com/images/4/4b/Unit-Exdeath-4.png","https://exviuswiki.com/images/f/f9/Unit-Exdeath-5.png"},3),
	Kuja("Kuja",new String[]{"https://exviuswiki.com/images/c/c3/Unit-Kuja-3.png","https://exviuswiki.com/images/2/2b/Unit-Kuja-4.png","https://exviuswiki.com/images/4/47/Unit-Kuja-5.png"},3),
	CoD("Cloud of Darkness",new String[]{"https://exviuswiki.com/images/b/bf/Unit-Cloud_of_Darkness-3.png","https://exviuswiki.com/images/8/8b/Unit-Cloud_of_Darkness-4.png","https://exviuswiki.com/images/0/02/Unit-Cloud_of_Darkness-5.png"},3),
	Cecil("Cecil",new String[]{"https://exviuswiki.com/images/7/7b/Unit-Cecil-3.png","https://exviuswiki.com/images/3/3b/Unit-Cecil-4.png","https://exviuswiki.com/images/e/e1/Unit-Cecil-5.png"},3),
	Terra("Terra",new String[]{"https://exviuswiki.com/images/b/bd/Unit-Terra-3.png","https://exviuswiki.com/images/6/66/Unit-Terra-4.png","https://exviuswiki.com/images/e/ec/Unit-Terra-5.png"},3),
	Bartz("Bartz",new String[]{"https://exviuswiki.com/images/7/73/Unit-Bartz-3.png","https://exviuswiki.com/images/7/76/Unit-Bartz-4.png","https://exviuswiki.com/images/5/57/Unit-Bartz-5.png"},3),
	Firion("Firion", new String[]{"https://exviuswiki.com/images/4/4f/Unit-Firion-3.png","https://exviuswiki.com/images/3/3e/Unit-Firion-4.png","https://exviuswiki.com/images/6/64/Unit-Firion-5.png"},3),
	Zidane("Zidane",new String[]{"https://exviuswiki.com/images/b/b0/Unit-Zidane-3.png","https://exviuswiki.com/images/8/8e/Unit-Zidane-4.png","https://exviuswiki.com/images/d/de/Unit-Zidane-5.png"},3),
	Vaan("Vaan",new String[]{"https://exviuswiki.com/images/8/83/Unit-Vaan-3.png","https://exviuswiki.com/images/f/f6/Unit-Vaan-4.png","https://exviuswiki.com/images/f/f6/Unit-Vaan-4.png"},3),
	Duane("Duane",new String[]{"https://exviuswiki.com/images/6/6d/Unit-Duane-3.png","https://exviuswiki.com/images/7/77/Unit-Duane-4.png","https://exviuswiki.com/images/c/ca/Unit-Duane-5.png"},3),
	Cerius("Cerius",new String[]{"https://exviuswiki.com/images/8/88/Unit-Cerius-3.png","https://exviuswiki.com/images/4/47/Unit-Cerius-4.png","https://exviuswiki.com/images/5/58/Unit-Cerius-5.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"https://exviuswiki.com/images/0/02/Unit-Cerius-6.png"});
		}
	},
	Roselia("Roselia",new String[]{"https://exviuswiki.com/images/f/fd/Unit-Roselia-3.png","https://exviuswiki.com/images/9/9c/Unit-Roselia-4.png","https://exviuswiki.com/images/c/cf/Unit-Roselia-5.png"},3),
	Medius("Medius",new String[]{"https://exviuswiki.com/images/b/b4/Unit-Medius-3.png","https://exviuswiki.com/images/3/3e/Unit-Medius-4.png","https://exviuswiki.com/images/d/d5/Unit-Medius-5.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"https://exviuswiki.com/images/8/89/Unit-Medius-6.png"});
		}
	},
	Miyuki("Miyuki",new String[]{"https://exviuswiki.com/images/5/5e/Unit-Miyuki-3.png","https://exviuswiki.com/images/8/8b/Unit-Miyuki-4.png","https://exviuswiki.com/images/e/ed/Unit-Miyuki-5.png"},3),
	Russel("Russel",new String[]{"https://exviuswiki.com/images/d/d6/Unit-Russell-3.png","https://exviuswiki.com/images/0/01/Unit-Russell-4.png"},3),
	Golbez("Golbez",new String[]{"https://exviuswiki.com/images/a/a8/Unit-Golbez-3.png","https://exviuswiki.com/images/3/31/Unit-Golbez-4.png","https://exviuswiki.com/images/f/f2/Unit-Golbez-5.png"},3),
	Galuf("Galuf",new String[]{"https://exviuswiki.com/images/b/bf/Unit-Galuf-3.png","https://exviuswiki.com/images/8/8f/Unit-Galuf-4.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"https://exviuswiki.com/images/0/05/Unit-Galuf-5.png"});
		}
	},
	Xiao("Xiao",new String[]{"https://exviuswiki.com/images/b/b4/Unit-Xiao-3.png","https://exviuswiki.com/images/1/13/Unit-Xiao-4.png","https://exviuswiki.com/images/4/41/Unit-Xiao-5.png"},3),
	Artemios("Artemios",new String[]{"https://exviuswiki.com/images/8/8e/Unit-Artemios-3.png","https://exviuswiki.com/images/1/18/Unit-Artemios-4.png","https://exviuswiki.com/images/0/02/Unit-Artemios-5.png"},3),
	Locke("Locke",new String[]{"https://exviuswiki.com/images/7/7e/Unit-Locke-3.png","https://exviuswiki.com/images/e/e8/Unit-Locke-4.png","https://exviuswiki.com/images/3/3e/Unit-Locke-5.png"},3),
	Leo("Leo",new String[]{"https://exviuswiki.com/images/4/46/Unit-Leo-3.png","https://exviuswiki.com/images/f/f7/Unit-Leo-4.png","https://exviuswiki.com/images/9/95/Unit-Leo-5.png"},3),
	Gilbert("Gilbert",new String[]{"https://exviuswiki.com/images/5/52/Unit-Gilbert-3.png","https://exviuswiki.com/images/b/be/Unit-Gilbert-4.png","https://exviuswiki.com/images/3/30/Unit-Gilbert-5.png"},3),
	Celes("Celes",new String[]{"https://exviuswiki.com/images/b/bf/Unit-Celes-3.png","https://exviuswiki.com/images/2/2d/Unit-Celes-4.png","https://exviuswiki.com/images/0/08/Unit-Celes-5.png"},3),
	Kefka("Kefka",new String[]{"https://exviuswiki.com/images/d/d2/Unit-Kefka-3.png","https://exviuswiki.com/images/3/31/Unit-Kefka-4.png","https://exviuswiki.com/images/b/b4/Unit-Kefka-5.png"},3),
	Rakshasa("Rakshasa",new String[]{"https://exviuswiki.com/images/f/f7/Unit-Rakshasa-3.png","https://exviuswiki.com/images/a/a1/Unit-Rakshasa-4.png","https://exviuswiki.com/images/0/0b/Unit-Rakshasa-5.png"},3),
	Chizuru("Chizuru",new String[]{"https://exviuswiki.com/images/5/50/Unit-Chizuru-4.png","https://exviuswiki.com/images/4/45/Unit-Chizuru-5.png"},4){
		@Override
		public void setup(){
			setUpgrades(new String[]{"https://exviuswiki.com/images/8/80/Unit-Chizuru-6.png"});
		}
	},
	Hayate("Hayate",new String[]{"https://exviuswiki.com/images/6/64/Unit-Hayate-3.png","https://exviuswiki.com/images/e/e8/Unit-Hayate-4.png","https://exviuswiki.com/images/4/40/Unit-Hayate-5.png"},3),
	WoL("Warrior of Light",new String[]{"https://exviuswiki.com/images/7/70/Unit-Warrior_of_Light-4.png","https://exviuswiki.com/images/c/c2/Unit-Warrior_of_Light-5.png"},4),
	Tellah("Tellah",new String[]{"https://exviuswiki.com/images/3/3f/Unit-Tellah-3.png","https://exviuswiki.com/images/c/c4/Unit-Tellah-4.png","https://exviuswiki.com/images/c/c0/Unit-Tellah-5.png"},3),
	Lenna("Lenna",new String[]{"https://exviuswiki.com/images/5/57/Unit-Lenna-3.png","https://exviuswiki.com/images/7/7d/Unit-Lenna-4.png","https://exviuswiki.com/images/b/ba/Unit-Lenna-5.png"},3),
	Amarant("Amarant",new String[]{"https://exviuswiki.com/images/8/83/Unit-Amarant-3.png","https://exviuswiki.com/images/0/06/Unit-Amarant-4.png","https://exviuswiki.com/images/1/12/Unit-Amarant-5.png"},3),
	Lani("Lani",new String[]{"https://exviuswiki.com/images/a/a8/Unit-Lani-3.png","https://exviuswiki.com/images/b/ba/Unit-Lani-4.png"},3),
	Garnet("Garnet",new String[]{"https://exviuswiki.com/images/5/53/Unit-Garnet-4.png","https://exviuswiki.com/images/f/f3/Unit-Garnet-5.png"},4),
	Freya("Freya",new String[]{"https://exviuswiki.com/images/a/a6/Unit-Freya-3.png","https://exviuswiki.com/images/2/28/Unit-Freya-4.png","https://exviuswiki.com/images/f/fc/Unit-Freya-5.png"},3),
	Charlotte("Charlotte",new String[]{"https://exviuswiki.com/images/2/24/Unit-Charlotte-3.png","https://exviuswiki.com/images/e/ed/Unit-Charlotte-4.png","https://exviuswiki.com/images/c/c5/Unit-Charlotte-5.png"},3),
	Ludmille("Ludmille",new String[]{"https://exviuswiki.com/images/3/32/Unit-Ludmille-3.png","https://exviuswiki.com/images/0/07/Unit-Ludmille-4.png","https://exviuswiki.com/images/0/04/Unit-Ludmille-5.png"},3),
	Lightning("Lightning",new String[]{"https://exviuswiki.com/images/a/ab/Unit-Lightning-5.png","https://exviuswiki.com/images/2/25/Unit-Lightning-6.png"},5),
	Deltia("Deltia",new String[]{"https://exviuswiki.com/images/b/ba/Unit-Delita-5.png","https://exviuswiki.com/images/b/be/Unit-Delita-6.png"},5),
	Alma("Alma",new String[]{"https://exviuswiki.com/images/1/11/Unit-Alma-3.png","https://exviuswiki.com/images/0/05/Unit-Alma-4.png","https://exviuswiki.com/images/3/3f/Unit-Alma-5.png"},3),
	Gaffgarion("Gaffgarion",new String[]{"https://exviuswiki.com/images/1/17/Unit-Gaffgarion-4.png","https://exviuswiki.com/images/e/e8/Unit-Gaffgarion-5.png"},4),
	Ramza("Ramza",new String[]{"https://exviuswiki.com/images/1/14/Unit-Ramza-5.png","https://exviuswiki.com/images/5/5b/Unit-Ramza-6.png"},5),
	Arigas("Arigas",new String[]{"https://exviuswiki.com/images/9/91/Unit-Agrias-4.png","https://exviuswiki.com/images/b/b5/Unit-Agrias-5.png"},4),
	Mustadio("Mustadio",new String[]{"https://exviuswiki.com/images/9/94/Unit-Mustadio-3.png","https://exviuswiki.com/images/0/0e/Unit-Mustadio-4.png","https://exviuswiki.com/images/f/fd/Unit-Mustadio-5.png"},3),
	Rosa("Rosa",new String[]{"https://exviuswiki.com/images/2/27/Unit-Rosa-4.png","https://exviuswiki.com/images/b/b9/Unit-Rosa-5.png"},4),
	DKC("Dark Knight Cecil",new String[]{"https://exviuswiki.com/images/8/8e/Unit-Dark_Knight_Cecil-5.png","https://exviuswiki.com/images/1/17/Unit-Dark_Knight_Cecil-6.png"},5),
	Edge("Edge",new String[]{"https://exviuswiki.com/images/c/cd/Unit-Edge-3.png","https://exviuswiki.com/images/7/75/Unit-Edge-4.png","https://exviuswiki.com/images/a/af/Unit-Edge-5.png"},3),
	Arc("Arc",new String[]{"https://exviuswiki.com/images/b/b7/Unit-Arc-3.png","https://exviuswiki.com/images/8/83/Unit-Arc-4.png","https://exviuswiki.com/images/c/ca/Unit-Arc-5.png"},3),
	Ingus("Ingus",new String[]{"https://exviuswiki.com/images/4/4b/Unit-Ingus-3.png","https://exviuswiki.com/images/1/14/Unit-Ingus-4.png","https://exviuswiki.com/images/7/7c/Unit-Ingus-5.png"},3),
	Refia("Refia",new String[]{"https://exviuswiki.com/images/8/8b/Unit-Refia-4.png","https://exviuswiki.com/images/4/42/Unit-Refia-5.png","https://exviuswiki.com/images/a/ae/Unit-Refia-6.png"},4),
	Luneth("Luneth",new String[]{"https://exviuswiki.com/images/2/23/Unit-Luneth-5.png","https://exviuswiki.com/images/e/ef/Unit-Luneth-6.png"},5),
	Faris("Faris",new String[]{"https://exviuswiki.com/images/8/8a/Unit-Faris-3.png","https://exviuswiki.com/images/5/54/Unit-Faris-4.png","https://exviuswiki.com/images/2/25/Unit-Faris-5.png"},3),
	Greg("Gilgamesh",new String[]{"https://exviuswiki.com/images/b/bb/Unit-Gilgamesh-5.png","https://exviuswiki.com/images/2/22/Unit-Gilgamesh-6.png"},5),
	Snow("Snow",new String[]{"https://exviuswiki.com/images/8/85/Unit-Snow-4.png","https://exviuswiki.com/images/b/bd/Unit-Snow-5.png","https://exviuswiki.com/images/d/d1/Unit-Snow-6.png"},4),
	Vanille("Vanille",new String[]{"https://exviuswiki.com/images/3/3c/Unit-Vanille-4.png","https://exviuswiki.com/images/5/5d/Unit-Vanille-5.png"},4),
	Sazh("Sazh",new String[]{"https://exviuswiki.com/images/f/fa/Unit-Sazh-3.png","https://exviuswiki.com/images/0/0b/Unit-Sazh-4.png","https://exviuswiki.com/images/7/7c/Unit-Sazh-5.png"},3),
	Hope("Hope",new String[]{"https://exviuswiki.com/images/3/38/Unit-Hope-4.png","https://exviuswiki.com/images/b/b8/Unit-Hope-5.png"},4),
	Fang("Fang",new String[]{"https://exviuswiki.com/images/8/86/Unit-Fang-3.png","https://exviuswiki.com/images/0/07/Unit-Fang-4.png","https://exviuswiki.com/images/9/9c/Unit-Fang-5.png"},3),
	Mercedes("Mercedes",new String[]{"https://exviuswiki.com/images/7/7d/Unit-Mercedes-4.png","https://exviuswiki.com/images/7/77/Unit-Mercedes-5.png","https://exviuswiki.com/images/b/b6/Unit-Mercedes-6.png"},4),
	Noctis("Noctis",new String[]{"https://exviuswiki.com/images/c/c3/Unit-Noctis-5.png","https://exviuswiki.com/images/a/ab/Unit-Noctis-6.png"},5),
	Elle("Elle",new String[]{"https://exviuswiki.com/images/4/43/Unit-Elle-3.png","https://exviuswiki.com/images/c/c1/Unit-Elle-4.png","https://exviuswiki.com/images/4/4f/Unit-Elle-5.png"},3),
	Luka("Luka",new String[]{"https://exviuswiki.com/images/3/3e/Unit-Luka-4.png","https://exviuswiki.com/images/7/77/Unit-Luka-5.png","https://exviuswiki.com/images/7/75/Unit-Luka-6.png"},4),
	DFina("Dark Fina",new String[]{"https://exviuswiki.com/images/1/1d/Unit-Dark_Fina-5.png","https://exviuswiki.com/images/9/92/Unit-Dark_Fina-6.png"},5);
	
	
	
	public String name;
	public String[] url;
	public int base;
	public String[] upgradeurl=new String[]{};
	Unit(String name, String[] url,int base){
		this.name=name;
		this.url=url;
		this.base=base;
		setup();
	}
	public void setup(){
		
	}
	public void setUpgrades(String[] upgrades){
		upgradeurl=upgrades;
	}
	public String unitName(){
	 return name;
	}
	public String[] imgurl() {
		return url;
	}
	public int baseRarity() {
		return base;
	}
	public boolean hasRarity(int rarity){
		if(rarity>=base&&rarity<base+url.length){
			return true;
		}
		return false;
	}
	public boolean hasUpgrade(int rarity,int upgrade){
		//upgrade is # of upgrades to consider aka awakenings, currently 1 for 6*/5* awakenings, no unit has gotten 2 yet
		if(rarity>=base+url.length&&rarity<url.length+base+upgrade){
			return true;
		}
		return false;
	}
	public String getRarity(int rarity){
		String rare="";
		if(hasRarity(rarity)){
			rare=url[rarity-base];
		}
		return rare;
	}
	public int getRarityIndex(int rarity){
		if(hasRarity(rarity)){
			return rarity-base;
		}
		return -1;
	}
	public int getUpgradeIndex(int rarity,Awakening[] awakening){
		if(hasUpgrade(rarity,awakening)){
			return rarity-base-url.length;
		}
		return -1;
	}
	public String getUpgrade(int rarity,int upgrade){
		String rare="";
		if(hasUpgrade(rarity,upgrade)){
			rare=upgradeurl[rarity-base-url.length];
		}
		return rare;
	}
	public boolean hasUpgrade(int rarity,Awakening[] awakening){
		for(Awakening a:awakening){
			for(int i=0;i<a.units.length;i++){
				if(a.units[i].name.equals(name)){
					return hasUpgrade(rarity,a.rarityAwakened[i]);
				}
			}
		}
		return false;
	}
	public static void main(String[] args){
		System.out.println(Kain.upgradeurl[0]);
	}
	public static Unit[] commons1(){
		return new Unit[]{
				Penelo,
				Sabin,
				Shadow,
				Krile,
				Kain,
				Edgar,
				Fran,
				Shantotto,
				Rydia,
				Cyan,
				Clyne,
				Anzelm,
				Luna,
				Bedile
		};
	}
	public static Unit[] commons2(){
		return Lib.concat(commons1(), new Unit[]{
			Vivi,
			Lani
		});
	}
	public static Unit[] currentPool(){
		
		return Lib.concat(Unit.commons1(),new Unit[]{
				Garland,
				Exdeath,
				Kuja,
				CoD,
				Cecil,
				Terra,
				Bartz,
				Firion,
				Zidane,
				Vaan,
				Duane,
				Cerius,
				Roselia,
				Medius,
				Miyuki,
				Russel,
				Golbez,
				Galuf,
				Xiao,
				Artemios,
				Locke,
				Leo,
				Gilbert,
				Celes,
				Kefka,
				Rakshasa,
				Chizuru,
				Hayate,
				WoL,
				Tellah,
				Lenna,
				Amarant,
				Garnet,
				Freya,
				Charlotte,
				Ludmille,
				Lightning,
				Deltia,
				Alma,
				Gaffgarion,
				Ramza,
				Arigas,
				Mustadio,
				Rosa,
				DKC,
				Edge,
				Arc,
				Ingus,
				Refia,
				Luneth,
				Faris,
				Greg,
				Snow,
				Vanille,
				Sazh,
				Hope,
				Fang,
				Mercedes,
				Noctis,
				Elle,
				Luka,
				DFina
		});
	}
	public static Unit[] base3(Unit[] pool){
		ArrayList<Unit> base3=new ArrayList<Unit>();
		for(Unit u:pool){
			if(u.base==3){
				base3.add(u);
			}
		}
		return (Unit[])base3.toArray();
	}
	public static Unit[] base3(){
		return base3(Unit.values());
	}
	public static Unit[] base4(Unit[] pool){
		ArrayList<Unit> base4=new ArrayList<Unit>();
		for(Unit u:pool){
			if(u.base==4){
				base4.add(u);
			}
		}
		return (Unit[])base4.toArray();
	}
	public static Unit[] base4(){
		return base4(Unit.values());
	}
	public static Unit[] base5(Unit[] pool){
		ArrayList<Unit> base5=new ArrayList<Unit>();
		for(Unit u:pool){
			if(u.base==5){
				base5.add(u);
			}
		}
		return (Unit[])base5.toArray();
	}
	public static Unit[] base5(){
		return base5(Unit.values());
	}
}
