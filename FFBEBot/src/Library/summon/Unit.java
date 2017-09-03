package Library.summon;

import java.io.File;
import java.util.ArrayList;

import global.record.Settings;
import util.Lib;
public enum Unit {
	Vivi("Vivi",new String[]{"/0/0f/Unit-Vivi-3.png","/d/d3/Unit-Vivi-4.png"},3),
	Penelo("Penelo",new String[]{"/6/6f/Unit-Penelo-3.png","/5/5a/Unit-Penelo-4.png"},3),
	Maria("Maria",new String[]{"/f/f3/Unit-Maria-3.png","/2/28/Unit-Maria-4.png"},3),
	Sabin("Sabin",new String[]{"/c/c0/Unit-Sabin-3.png","/e/e3/Unit-Sabin-4.png"},3),
	Shadow("Shadow",new String[]{"/3/3b/Unit-Shadow-3.png","/4/4a/Unit-Shadow-4.png"},3),
	Krile("Krile",new String[]{"/d/da/Unit-Krile-3.png","/a/a9/Unit-Krile-4.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/6/6d/Unit-Krile-5.png"});
		}
	},
	Kain("Kain",new String[]{"/1/1a/Unit-Kain-3.png","/b/b8/Unit-Kain-4.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/7/77/Unit-Kain-5.png"});
		}
	},
	Edgar("Edgar",new String[]{"/e/e6/Unit-Edgar-3.png","/a/a8/Unit-Edgar-4.png"},3),
	Fran("Fran",new String[]{"/5/54/Unit-Fran-3.png","/1/1e/Unit-Fran-4.png"},3),
	Shantotto("Shantotto",new String[]{"/9/9c/Unit-Shantotto-3.png","/d/d9/Unit-Shantotto-4.png"},3),
	Rydia("Rydia",new String[]{"/8/89/Unit-Rydia-3.png","/5/56/Unit-Rydia-4.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/1/15/Unit-Rydia-5.png"});
		}
	},
	Cyan("Cyan",new String[]{"/9/9d/Unit-Cyan-3.png","/6/6c/Unit-Cyan-4.png"},3),
	Clyne("Clyne",new String[]{"/f/f0/Unit-Clyne-3.png","/9/99/Unit-Clyne-4.png"},3),
	Anzelm("Anzelm",new String[]{"/a/a7/Unit-Anzelm-3.png","/0/04/Unit-Anzelm-4.png"},3),
	Luna("Luna",new String[]{"/0/0e/Unit-Luna-3.png","/d/d4/Unit-Luna-4.png"},3),
	Bedile("Bedile",new String[]{"/9/91/Unit-Bedile-3.png","/f/f8/Unit-Bedile-4.png"},3),
	Garland("Garland",new String[]{"/d/d5/Unit-Garland-3.png","/9/91/Unit-Garland-4.png","/0/08/Unit-Garland-5.png"},3),
	Exdeath("Exdeath",new String[]{"/8/8d/Unit-Exdeath-3.png","/4/4b/Unit-Exdeath-4.png","/f/f9/Unit-Exdeath-5.png"},3),
	Kuja("Kuja",new String[]{"/c/c3/Unit-Kuja-3.png","/2/2b/Unit-Kuja-4.png","/4/47/Unit-Kuja-5.png"},3),
	CoD("Cloud of Darkness",new String[]{"/b/bf/Unit-Cloud_of_Darkness-3.png","/8/8b/Unit-Cloud_of_Darkness-4.png","/0/02/Unit-Cloud_of_Darkness-5.png"},3),
	Cecil("Cecil",new String[]{"/7/7b/Unit-Cecil-3.png","/3/3b/Unit-Cecil-4.png","/e/e1/Unit-Cecil-5.png"},3),
	Terra("Terra",new String[]{"/b/bd/Unit-Terra-3.png","/6/66/Unit-Terra-4.png","/e/ec/Unit-Terra-5.png"},3),
	Bartz("Bartz",new String[]{"/7/73/Unit-Bartz-3.png","/7/76/Unit-Bartz-4.png","/5/57/Unit-Bartz-5.png"},3),
	Firion("Firion", new String[]{"/4/4f/Unit-Firion-3.png","/3/3e/Unit-Firion-4.png","/6/64/Unit-Firion-5.png"},3),
	Zidane("Zidane",new String[]{"/b/b0/Unit-Zidane-3.png","/8/8e/Unit-Zidane-4.png","/d/de/Unit-Zidane-5.png"},3),
	Vaan("Vaan",new String[]{"/8/83/Unit-Vaan-3.png","/f/f6/Unit-Vaan-4.png","/f/f6/Unit-Vaan-4.png"},3),
	Duane("Duane",new String[]{"/6/6d/Unit-Duane-3.png","/7/77/Unit-Duane-4.png","/c/ca/Unit-Duane-5.png"},3),
	Cerius("Cerius",new String[]{"/8/88/Unit-Cerius-3.png","/4/47/Unit-Cerius-4.png","/5/58/Unit-Cerius-5.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/0/02/Unit-Cerius-6.png"});
		}
	},
	Roselia("Roselia",new String[]{"/f/fd/Unit-Roselia-3.png","/9/9c/Unit-Roselia-4.png","/c/cf/Unit-Roselia-5.png"},3),
	Medius("Medius",new String[]{"/b/b4/Unit-Medius-3.png","/3/3e/Unit-Medius-4.png","/d/d5/Unit-Medius-5.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/8/89/Unit-Medius-6.png"});
		}
	},
	Miyuki("Miyuki",new String[]{"/5/5e/Unit-Miyuki-3.png","/8/8b/Unit-Miyuki-4.png","/e/ed/Unit-Miyuki-5.png"},3),
	Russel("Russel",new String[]{"/d/d6/Unit-Russell-3.png","/0/01/Unit-Russell-4.png"},3),
	Golbez("Golbez",new String[]{"/a/a8/Unit-Golbez-3.png","/3/31/Unit-Golbez-4.png","/f/f2/Unit-Golbez-5.png"},3),
	Galuf("Galuf",new String[]{"/b/bf/Unit-Galuf-3.png","/8/8f/Unit-Galuf-4.png"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/0/05/Unit-Galuf-5.png"});
		}
	},
	Xiao("Xiao",new String[]{"/b/b4/Unit-Xiao-3.png","/1/13/Unit-Xiao-4.png","/4/41/Unit-Xiao-5.png"},3),//TODO add upgrade
	Artemios("Artemios",new String[]{"/8/8e/Unit-Artemios-3.png","/1/18/Unit-Artemios-4.png","/0/02/Unit-Artemios-5.png"},3),
	Locke("Locke",new String[]{"/7/7e/Unit-Locke-3.png","/e/e8/Unit-Locke-4.png","/3/3e/Unit-Locke-5.png"},3),
	Leo("Leo",new String[]{"/4/46/Unit-Leo-3.png","/f/f7/Unit-Leo-4.png","/9/95/Unit-Leo-5.png"},3),
	Gilbert("Gilbert",new String[]{"/5/52/Unit-Gilbert-3.png","/b/be/Unit-Gilbert-4.png","/3/30/Unit-Gilbert-5.png"},3),
	Celes("Celes",new String[]{"/b/bf/Unit-Celes-3.png","/2/2d/Unit-Celes-4.png","/0/08/Unit-Celes-5.png"},3),
	Kefka("Kefka",new String[]{"/d/d2/Unit-Kefka-3.png","/3/31/Unit-Kefka-4.png","/b/b4/Unit-Kefka-5.png"},3),
	Rakshasa("Rakshasa",new String[]{"/f/f7/Unit-Rakshasa-3.png","/a/a1/Unit-Rakshasa-4.png","/0/0b/Unit-Rakshasa-5.png"},3),
	Chizuru("Chizuru",new String[]{"/5/50/Unit-Chizuru-4.png","/4/45/Unit-Chizuru-5.png"},4){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/8/80/Unit-Chizuru-6.png"});
		}
	},
	Hayate("Hayate",new String[]{"/6/64/Unit-Hayate-3.png","/e/e8/Unit-Hayate-4.png","/4/40/Unit-Hayate-5.png"},3),
	WoL("Warrior of Light",new String[]{"/7/70/Unit-Warrior_of_Light-4.png","/c/c2/Unit-Warrior_of_Light-5.png"},4),
	Tellah("Tellah",new String[]{"/3/3f/Unit-Tellah-3.png","/c/c4/Unit-Tellah-4.png","/c/c0/Unit-Tellah-5.png"},3),
	Lenna("Lenna",new String[]{"/5/57/Unit-Lenna-3.png","/7/7d/Unit-Lenna-4.png","/b/ba/Unit-Lenna-5.png"},3),
	Amarant("Amarant",new String[]{"/8/83/Unit-Amarant-3.png","/0/06/Unit-Amarant-4.png","/1/12/Unit-Amarant-5.png"},3),
	Lani("Lani",new String[]{"/a/a8/Unit-Lani-3.png","/b/ba/Unit-Lani-4.png"},3),
	Garnet("Garnet",new String[]{"/5/53/Unit-Garnet-4.png","/f/f3/Unit-Garnet-5.png"},4),
	Freya("Freya",new String[]{"/a/a6/Unit-Freya-3.png","/2/28/Unit-Freya-4.png","/f/fc/Unit-Freya-5.png"},3),
	Charlotte("Charlotte",new String[]{"/2/24/Unit-Charlotte-3.png","/e/ed/Unit-Charlotte-4.png","/c/c5/Unit-Charlotte-5.png"},3),
	Ludmille("Ludmille",new String[]{"/3/32/Unit-Ludmille-3.png","/0/07/Unit-Ludmille-4.png","/0/04/Unit-Ludmille-5.png"},3),
	Lightning("Lightning",new String[]{"/a/ab/Unit-Lightning-5.png","/2/25/Unit-Lightning-6.png"},5),
	Deltia("Deltia",new String[]{"/b/ba/Unit-Delita-5.png","/b/be/Unit-Delita-6.png"},5),
	Alma("Alma",new String[]{"/1/11/Unit-Alma-3.png","/0/05/Unit-Alma-4.png","/3/3f/Unit-Alma-5.png"},3),
	Gaffgarion("Gaffgarion",new String[]{"/1/17/Unit-Gaffgarion-4.png","/e/e8/Unit-Gaffgarion-5.png"},4),
	Ramza("Ramza",new String[]{"/1/14/Unit-Ramza-5.png","/5/5b/Unit-Ramza-6.png"},5),
	Arigas("Arigas",new String[]{"/9/91/Unit-Agrias-4.png","/b/b5/Unit-Agrias-5.png"},4),
	Mustadio("Mustadio",new String[]{"/9/94/Unit-Mustadio-3.png","/0/0e/Unit-Mustadio-4.png","/f/fd/Unit-Mustadio-5.png"},3),
	Rosa("Rosa",new String[]{"/2/27/Unit-Rosa-4.png","/b/b9/Unit-Rosa-5.png"},4),
	DKC("Dark Knight Cecil",new String[]{"/8/8e/Unit-Dark_Knight_Cecil-5.png","/1/17/Unit-Dark_Knight_Cecil-6.png"},5),
	Edge("Edge",new String[]{"/c/cd/Unit-Edge-3.png","/7/75/Unit-Edge-4.png","/a/af/Unit-Edge-5.png"},3),
	Arc("Arc",new String[]{"/b/b7/Unit-Arc-3.png","/8/83/Unit-Arc-4.png","/c/ca/Unit-Arc-5.png"},3),
	Ingus("Ingus",new String[]{"/4/4b/Unit-Ingus-3.png","/1/14/Unit-Ingus-4.png","/7/7c/Unit-Ingus-5.png"},3),
	Refia("Refia",new String[]{"/8/8b/Unit-Refia-4.png","/4/42/Unit-Refia-5.png","/a/ae/Unit-Refia-6.png"},4),
	Luneth("Luneth",new String[]{"/2/23/Unit-Luneth-5.png","/e/ef/Unit-Luneth-6.png"},5),
	Faris("Faris",new String[]{"/8/8a/Unit-Faris-3.png","/5/54/Unit-Faris-4.png","/2/25/Unit-Faris-5.png"},3),
	Greg("Gilgamesh",new String[]{"/b/bb/Unit-Gilgamesh-5.png","/2/22/Unit-Gilgamesh-6.png"},5),
	Snow("Snow",new String[]{"/8/85/Unit-Snow-4.png","/b/bd/Unit-Snow-5.png","/d/d1/Unit-Snow-6.png"},4),
	Vanille("Vanille",new String[]{"/3/3c/Unit-Vanille-4.png","/5/5d/Unit-Vanille-5.png"},4),
	Sazh("Sazh",new String[]{"/f/fa/Unit-Sazh-3.png","/0/0b/Unit-Sazh-4.png","/7/7c/Unit-Sazh-5.png"},3),
	Hope("Hope",new String[]{"/3/38/Unit-Hope-4.png","/b/b8/Unit-Hope-5.png"},4),
	Fang("Fang",new String[]{"/8/86/Unit-Fang-3.png","/0/07/Unit-Fang-4.png","/9/9c/Unit-Fang-5.png"},3),
	Mercedes("Mercedes",new String[]{"/7/7d/Unit-Mercedes-4.png","/7/77/Unit-Mercedes-5.png","/b/b6/Unit-Mercedes-6.png"},4),
	Noctis("Noctis",new String[]{"/c/c3/Unit-Noctis-5.png","/a/ab/Unit-Noctis-6.png"},5),
	Elle("Elle",new String[]{"/4/43/Unit-Elle-3.png","/c/c1/Unit-Elle-4.png","/4/4f/Unit-Elle-5.png"},3),
	Luka("Luka",new String[]{"/3/3e/Unit-Luka-4.png","/7/77/Unit-Luka-5.png","/7/75/Unit-Luka-6.png"},4),
	DFina("Dark Fina",new String[]{"/1/1d/Unit-Dark_Fina-5.png","/9/92/Unit-Dark_Fina-6.png"},5),
	Trey("Trey", new String[]{"/6/6a/Unit-Trey-3.png","/4/49/Unit-Trey-4.png","/3/3a/Unit-Trey-5.png"},3),
	Jack("Jack",new String[]{"/1/1b/Unit-Jack-3.png","/b/ba/Unit-Jack-4.png","/0/0e/Unit-Jack-5.png"},3),
	Seven("Seven",new String[]{"/9/99/Unit-Seven-4.png","/6/6c/Unit-Seven-5.png","/7/72/Unit-Seven-6.png"},4),
	Ace("Ace",new String[]{"/3/32/Unit-Ace-5.png","/2/2b/Unit-Ace-6.png"},5),
	Marie("Marie",new String[]{"/1/13/Unit-Marie-5.png","/7/79/Unit-Marie-6.png"},6),
	Guy("Guy",new String[]{"/d/d3/Unit-Guy-3.png","/d/d6/Unit-Guy-4.png","/3/3b/Unit-Guy-5.png"},3),
	Leon("Leon",new String[]{"/2/29/Unit-Leon-4.png","/a/ab/Unit-Leon-5.png"},4),
	Emperor("Emperor",new String[]{"/e/e9/Unit-Emperor-5.png","/e/eb/Unit-Emperor-6.png"},5),
	Olive("Olive",new String[]{"/7/7a/Unit-Olive-5.png","/8/88/Unit-Olive-6.png"},5),
	Shine("Shine",new String[]{"/8/80/Unit-Shine-4.png","/b/b6/Unit-Shine-5.png","/3/30/Unit-Shine-6.png"},4),
	Shera("Shera",new String[]{"/a/ae/Unit-Shera-3.png","/9/9d/Unit-Shera-4.png","/5/57/Unit-Shera-5.png"},3),
	Queen("Queen",new String[]{"0/08/Unit-Queen-5.png","7/76/Unit-Queen-6.png"},5),
	Nine("Nine",new String[]{"8/85/Unit-Nine-4.png","c/c9/Unit-Nine-5.png","c/c5/Unit-Nine-6.png"},4),
	Clinque("Clinque",new String[]{"7/7c/Unit-Cinque-3.png","c/c9/Unit-Cinque-4.png","e/e9/Unit-Cinque-5.png"},3),
	Eight("Eight",new String[]{"6/66/Unit-Eight-3.png","7/72/Unit-Eight-4.png","6/65/Unit-Eight-5.png"},3),
	Cid("Thunder God",new String[]{"e/e6/Unit-Orlandeau-5.png","d/df/Unit-Orlandeau-6.png"},5),
	Soleil("Soleil",new String[]{"c/c0/Unit-Soleil-4.png","1/1b/Unit-Soleil-5.png","3/33/Unit-Soleil-6.png"},4),
	Ovelia("Ovelia",new String[]{"a/ae/Unit-Ovelia-3.png","6/6f/Unit-Ovelia-4.png","b/b9/Unit-Ovelia-5.png"},3),
	Lawrence("Lawrence",new String[]{"e/ef/Unit-Lawrence-3.png","4/40/Unit-Lawrence-4.png","b/b8/Unit-Lawrence-5.png"},3),
	Fryevia("Fryevia",new String[]{"2/25/Unit-Fryevia-5.png","3/32/Unit-Fryevia-6.png"},5),
	Xon("Xon",new String[]{"d/d0/Unit-Xon-4.png","b/bf/Unit-Xon-5.png","8/88/Unit-Xon-6.png"},4),
	Aiden("Aiden",new String[]{"7/7f/Unit-Aiden-3.png","5/59/Unit-Aiden-4.png","8/8f/Unit-Aiden-5.png"},3),
	TTerra("Trance Terra",new String[]{"f/ff/Unit-Trance_Terra-5.png","c/c9/Unit-Trance_Terra-6.png"},5),
	Setzer("Setzer",new String[]{"8/82/Unit-Setzer-4.png","8/8e/Unit-Setzer-5.png","5/58/Unit-Setzer-6.png"},4),
	Gau("Gau",new String[]{"a/ae/Unit-Gau-3.png","a/a5/Unit-Gau-4.png","7/72/Unit-Gau-5.png"},3),
	Eileen("Aileen",new String[]{"2/21/Unit-Aileen-5.png","4/49/Unit-Aileen-6.png"},5),
	Soze("Sohze", new String[]{"e/ee/Unit-Sozhe-4.png","d/d4/Unit-Sozhe-5.png","9/94/Unit-Sozhe-6.png"},4),
	Heretic("Heltich", new String[]{"3/36/Unit-Heltich-3.png","6/61/Unit-Heltich-4.png","2/2b/Unit-Heltich-5.png"},3),
	Ulrica("Ulrica",new String[]{"7/72/Unit-Ulrica-3.png","b/b6/Unit-Ulrica-4.png","1/14/Unit-Ulrica-5.png"},3),
	Zyrus("Zyrus",new String[]{"c/cd/Unit-Zyrus-4.png","b/be/Unit-Zyrus-5.png","5/5d/Unit-Zyrus-6.png"},4),
	Reberta("Reberta",new String[]{"c/c7/Unit-Reberta-5.png","b/b0/Unit-Reberta-6.png"},5),
	Sice("Sice",new String[]{"4/42/Unit-Sice-3.png","3/30/Unit-Sice-4.png","6/64/Unit-Sice-5.png"},3),
	King("King",new String[]{"7/70/Unit-King-4.png","1/13/Unit-King-5.png","e/e5/Unit-King-6.png"},4),
	Rem("Rem",new String[]{"a/a5/Unit-Rem-5.png","b/b6/Unit-Rem-6.png"},5),
	Wilhelm("Wilhelm", new String[]{"7/76/Unit-Wilhelm-5.png","f/f0/Unit-Wilhelm-6.png"},5),
	Grace("Grace",new String[]{"2/28/Unit-Grace-4.png","3/38/Unit-Grace-5.png","8/8c/Unit-Grace-6.png"},4),
	Abel("Abel",new String[]{"8/88/Unit-Abel-3.png","0/0e/Unit-Abel-4.png","3/35/Unit-Abel-5.png"},3),
	Jean("Jean",new String[]{"b/b4/Unit-Jean-3.png","6/67/Unit-Jean-4.png","9/91/Unit-Jean-5.png"},3),
	Camille("Camille",new String[]{"3/38/Unit-Camille-3.png","1/17/Unit-Camille-4.png","e/e4/Unit-Camille-5.png"},3),
	Illus("Illias",new String[]{"4/46/Unit-Ilias-4.png","7/78/Unit-Ilias-5.png","2/2b/Unit-Ilias-6.png"},4),
	Amelia("Amelia",new String[]{"3/34/Unit-Amelia-4.png","5/5c/Unit-Amelia-5.png","9/9e/Unit-Amelia-6.png"},4),
	Forren("Fohlen",new String[]{"b/b0/Unit-Fohlen-5.png","f/f3/Unit-Fohlen-6.png"},5),
	SLid("Summer Lid",new String[]{"3/3c/Unit-Summer_Lid-3.png","d/dc/Unit-Summer_Lid-4.png","e/e5/Unit-Summer_Lid-5.png"},3),
	SFina("Beach Time Fina",new String[]{"3/39/Unit-Beach_Time_Fina-4.png","3/37/Unit-Beach_Time_Fina-5.png","4/46/Unit-Beach_Time_Fina-6.png"},4),
	SMF("Seabreeze Dark Fina",new String[]{"1/19/Unit-Seabreeze_Dark_Fina-5.png","2/28/Unit-Seabreeze_Dark_Fina-6.png"},5),
	Wakka("Wakka",new String[]{"6/6b/Unit-Wakka-3.png","a/a2/Unit-Wakka-4.png","6/68/Unit-Wakka-5.png"},3),
	Rikku("Rikku",new String[]{"6/65/Unit-Rikku-4.png","b/b4/Unit-Rikku-5.png","e/ee/Unit-Rikku-6.png"},4),
	Tidus("Tidus",new String[]{"a/ae/Unit-Tidus-5.png","0/0d/Unit-Tidus-6.png"},5),
	Ashe("Ashe",new String[]{"3/37/Unit-Ashe-4.png","6/65/Unit-Ashe-5.png?","c/c4/Unit-Ashe-6.png"},4),
	Rasler("Rasler",new String[]{"4/43/Unit-Rasler-4.png","7/7a/Unit-Rasler-5.png","a/a2/Unit-Rasler-6.png"},4),
	Zargabaath("Zargabaath",new String[]{"b/b9/Unit-Zargabaath-5.png","2/2f/Unit-Zargabaath-6.png"},5),
	Lunera("Lunera",new String[]{"0/0b/Unit-Lunera-5.png","f/f5/Unit-Lunera-6.png"},5),
	Bran("Bran",new String[]{"9/95/Unit-Bran-4.png","c/c2/Unit-Bran-5.png","0/0b/Unit-Bran-6.png"},4),
	Helena("Helena",new String[]{"b/bd/Unit-Helena-4.png","0/0c/Unit-Helena-5.png","5/51/Unit-Helena-6.png"},4),
	Ruggles("Ruggles",new String[]{"6/6e/Unit-Ruggles-3.png","0/0d/Unit-Ruggles-4.png","9/98/Unit-Ruggles-5.png"},3),
	MercRamza("Mercenary Ramza",new String[]{"6/61/Unit-Mercenary_Ramza-5.png","4/45/Unit-Mercenary_Ramza-6.png"},5),
	KDeltia("Knight Deltia",new String[]{"d/df/Unit-Knight_Delita-5.png","e/eb/Unit-Knight_Delita-6.png"},5),
	Meliadoul("Meliadoul",new String[]{"e/e1/Unit-Meliadoul-4.png","6/6b/Unit-Meliadoul-5.png","d/df/Unit-Meliadoul-6.png"},4),
	Orran("Orran",new String[]{"b/bc/Unit-Orran-4.png","9/93/Unit-Orran-5.png","3/3e/Unit-Orran-6.png"},4),
	DV("Veritas of the Dark",new String[]{"7/77/Unit-Veritas_of_the_Dark-5.png","1/19/Unit-Veritas_of_the_Dark-6.png"},5),
	FV("Veritas of the Flame",new String[]{"c/c3/Unit-Veritas_of_the_Flame-5.png","2/25/Unit-Veritas_of_the_Flame-6.png"},5),
	EV("Veritas of the Earth",new String[]{"e/e1/Unit-Veritas_of_the_Earth-4.png","c/c9/Unit-Veritas_of_the_Earth-5.png","e/e0/Unit-Veritas_of_the_Earth-6.png"},4),
	Victoria("Victoria",new String[]{"7/7a/Unit-Victoria-4.png","8/80/Unit-Victoria-5.png","8/85/Unit-Victoria-6.png"},4),
	Tim("Timothy",new String[]{"6/6c/Unit-Timothy-3.png","5/5d/Unit-Timothy-4.png","1/16/Unit-Timothy-5.png"},3),
	LV("Vertias of the Light",new String[]{"7/7c/Unit-Veritas_of_the_Light-5.png","c/cd/Unit-Veritas_of_the_Light-6.png"},5),
	HV("Vertias of the Heavens",new String[]{"3/38/Unit-Veritas_of_the_Heavens-4.png","2/29/Unit-Veritas_of_the_Heavens-5.png","d/d4/Unit-Veritas_of_the_Heavens-6.png"},4),
	WV("Vertias of the Waters",new String[]{"9/91/Unit-Veritas_of_the_Waters-4.png","0/04/Unit-Veritas_of_the_Waters-5.png","3/32/Unit-Veritas_of_the_Waters-6.png"},4),
	OK("Onion Knight",new String[]{"0/0f/Unit-Onion_Knight-5.png","6/62/Unit-Onion_Knight-6.png"},5),
	Desch("Desch",new String[]{"5/5e/Unit-Desch-4.png","1/18/Unit-Desch-5.png","5/5c/Unit-Desch-6.png"},4),
	Aria("Aria", new String[]{"f/f2/Unit-Aria-4.png","b/b4/Unit-Aria-5.png","4/4b/Unit-Aria-6.png"},4),
	Sara("Sara",new String[]{"8/82/Unit-Sara-3.png","8/80/Unit-Sara-4.png","8/85/Unit-Sara-5.png"},3),
	
	//Limited Units
	Juggler("Juggler",new String[]{"c/c2/Unit-Juggler-4.png","1/1d/Unit-Juggler-5.png"},4),
	Thief("Thief",new String[]{"8/8e/Unit-Thief-4.png","d/de/Unit-Thief-5.png"},4),
	Fencer("Fencer",new String[]{"d/d8/Unit-Fencer-4.png","0/02/Unit-Fencer-5.png"},4),
	DRain("Demon Rain",new String[]{"c/c7/Unit-Demon_Rain-5.png","3/30/Unit-Demon_Rain-6.png"},5),
	DLasswell("Dracu Lasswell",new String[]{"a/a5/Unit-Dracu_Lasswell-5.png?","c/c3/Unit-Dracu_Lasswell-6.png"},5),
	WWF("Traffic Cone Fina",new String[]{"a/a2/Unit-White_Witch_Fina-4.png","4/48/Unit-White_Witch_Fina-5.png","0/07/Unit-White_Witch_Fina-6.png"},4),
	BCLid("Black Cat Lid",new String[]{"0/08/Unit-Black_Cat_Lid-3.png","4/40/Unit-Black_Cat_Lid-4.png","1/1c/Unit-Black_Cat_Lid-5.png"},3),
	Karl("Karl",new String[]{"2/2b/Unit-Karl-4.png","5/5b/Unit-Karl-5.png"},4),
	Seria("Seria",new String[]{"f/f2/Unit-Seria-4.png","2/2a/Unit-Seria-5.png"},4),
	Tilith("Tilith",new String[]{"c/cf/Unit-Tilith-4.png","f/f3/Unit-Tilith-5.png"},4),
	Elza("Elza",new String[]{"3/39/Unit-Elza-5.png","0/08/Unit-Elza-6.png"},5),
	SRoselia("Santa Roselia",new String[]{"e/e6/Unit-Santa_Roselia-4.png","5/54/Unit-Santa_Roselia-5.png","9/98/Unit-Santa_Roselia-6.png"},4),
	WKN("White Knight Noel",new String[]{"1/12/Unit-White_Knight_Noel-5.png","0/0e/Unit-White_Knight_Noel-6.png"},5),
	Ling("Ling",new String[]{"7/73/Unit-Ling-4.png","8/8c/Unit-Ling-5.png","6/65/Unit-Ling-6.png"},4),
	Yun("Yun",new String[]{"d/d6/Unit-Yun-5.png","c/c6/Unit-Yun-6.png"},5),
	Popoi("Popoi",new String[]{"6/6d/Unit-Popoi-3.png","e/eb/Unit-Popoi-4.png","8/80/Unit-Popoi-5.png"},3),
	Primm("Primm",new String[]{"4/42/Unit-Primm-4.png","9/99/Unit-Primm-5.png","d/d9/Unit-Primm-6.png"},4),
	Randi("Randi",new String[]{"9/9a/Unit-Randi-5.png","3/33/Unit-Randi-6.png"},5),
	CLuna("Cupid Luna",new String[]{"/8/81/Unit-Cupid_Luna-3.png","/c/c9/Unit-Cupid_Luna-4.png","/f/f8/Unit-Cupid_Luna-5.png?"},3),
	CArtemois("Cupid Artemois",new String[]{"/9/98/Unit-Cupid_Artemios-4.png","/5/5b/Unit-Cupid_Artemios-5.png","/f/f0/Unit-Cupid_Artemios-6.png"},4),
	Vargas("Vargas",new String[]{"f/f6/Unit-Vargas-5.png","4/49/Unit-Vargas-6.png"},5),
	N2B("2B",new String[]{"d/dd/Unit-2B-5.png","4/4f/Unit-2B-6.png"},5),
	N9S("9S",new String[]{"6/6f/Unit-9S-4.png","a/a3/Unit-9S-5.png","8/8a/Unit-9S-6.png"},4),
	N21O("21O",new String[]{"6/6a/Unit-21O-3.png","f/f6/Unit-21O-4.png","c/c9/Unit-21O-5.png"},3),
	A2("A2",new String[]{"6/6c/Unit-A2-5.png","6/62/Unit-A2-6.png"},5),
	Eve("Eve",new String[]{"9/96/Unit-Eve-4.png","3/34/Unit-Eve-5.png","3/39/Unit-Eve-6.png"},4);
	
	
	
	
	public String name;
	public String[] url;
	public int base;
	public String[] upgradeurl=new String[]{};
	Unit(String name, String[] url,int base){
		this.name=name;
		this.url=url;
		this.base=base;
		setup();
		for(int i=0;i<this.url.length;i++){
			this.url[i]=Settings.ExvicusIMGURL+this.url[i];
		}
	}
	public void setup(){
		
	}
	public void setUpgrades(String[] upgrades){
		for(int i=0;i<upgrades.length;i++){
			upgrades[i]=Settings.ExvicusIMGURL+upgrades[i];
		}
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
	public String toString(){
		return name;
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
		if(!(awakening==null)){
			for(Awakening a:awakening){
				for(int i=0;i<a.units.length;i++){
					if(a.units[i].name.equals(name)){
						return hasUpgrade(rarity,a.rarityAwakened[i]);
					}
				}
			}
		}
		return false;
	}
	public File getImageLocation(int rarity){
		return new File("units/"+name+"/"+rarity+".png");
	}
	public static void main(String[] args){
		System.out.println(Unit.valueOf("WoL"));
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
		
		return Lib.concat(Unit.commons2(),new Unit[]{
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
				DFina,
				Trey,
				Jack,
				Seven,
				Ace,
				Marie,
				Guy,
				Leon,
				Emperor,
				Olive,
				Shine,
				Shera,
				Queen,
				Nine,
				Clinque,
				Eight,
				Cid,
				Soleil,
				Ovelia,
				Lawrence,
				Fryevia,
				Xon,
				Aiden,
				TTerra,
				Setzer,
				Gau,
				Eileen,
				Soze,
				Heretic,
				Ulrica,
				Zyrus,
				Reberta,
				Wilhelm,
				Grace,
				Abel,
				Jean,
				Camille,
				Illus,
				Forren,
				SLid,
				SFina,
				SMF,
				Wakka,
				Rikku,
				Tidus,
				Ashe,
				Rasler,
				Zargabaath,
				Lunera,
				Bran,
				Helena,
				Ruggles,
				MercRamza,
				KDeltia,
				Meliadoul,
				Orran,
				DV,
				FV,
				EV,
				Victoria,
				Tim,
				LV,
				HV,
				WV
		});
	}
	//Placeholders for attack, mage/tank, support banners, which will be concacted to form current pool
	public static Unit[] attackPool(){
		return new Unit[]{
				
		};
	}
	public static Unit[] mageTankPool(){
		return new Unit[]{
				
		};
	}
	public static Unit[] supportPool(){
		return new Unit[]{
				
		};
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
