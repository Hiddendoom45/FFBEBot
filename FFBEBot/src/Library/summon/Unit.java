package Library.summon;

import java.io.File;
import java.util.ArrayList;

import global.record.Settings;
import util.Lib;
public enum Unit {

	Vivi("Vivi",new String[]{"/0/0f/Unit-Vivi-3.png?version=419e16782194e8124b2ecf9b568504b1","/d/d3/Unit-Vivi-4.png?version=14266c86c343196eab7eb0a94c0d3d8e"},3),
	Penelo("Penelo",new String[]{"/6/6f/Unit-Penelo-3.png?version=09d9048e8715a55add6b2cad019c4d5d","/5/5a/Unit-Penelo-4.png?version=1bbdf7af4f90ebf23d7d3a5b0c241905"},3),
	Maria("Maria",new String[]{"/f/f3/Unit-Maria-3.png?version=de674e91466c8bd8db890d0ebc3cb27b","/2/28/Unit-Maria-4.png?version=9b7198b665b50779c758f3f5488271f3"},3),
	Sabin("Sabin",new String[]{"/c/c0/Unit-Sabin-3.png?version=5fc10b9774aa06e1daf672202985ef9f","/e/e3/Unit-Sabin-4.png?version=a0cd46a92b92a1c3ba311389421181a5"},3),
	Shadow("Shadow",new String[]{"/3/3b/Unit-Shadow-3.png?version=6c3201a5e5a65fe513c2e25563ac6cf8","/4/4a/Unit-Shadow-4.png?version=b5539d267f4cdf935dc7c5fdf4e6cb5d"},3),
	Krile("Krile",new String[]{"/d/da/Unit-Krile-3.png?version=350edffb98abe53f234d1c41c4b9941e","/a/a9/Unit-Krile-4.png?version=8d87ebc2be5620f2fbb398b1c67648a3"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/6/6d/Unit-Krile-5.png?version=a18ad1cd7e3186b70dc07f851f609f12"});
		}
	},
	Kain("Kain",new String[]{"/1/1a/Unit-Kain-3.png?version=9aa1b173e4b30e68f61de81072a0015a","/b/b8/Unit-Kain-4.png?version=6d35291c777c6e8048463b2f2144dcae"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/7/77/Unit-Kain-5.png?version=151a535c8b5b8d55e0f5ea177cb0dd5e"});
		}
	},
	Edgar("Edgar",new String[]{"/e/e6/Unit-Edgar-3.png?version=50b4ed881b53b0831ce4a2a7d8199366","/a/a8/Unit-Edgar-4.png?version=2201446a3ed9fe2d943f7bb314b48715"},3),
	Fran("Fran",new String[]{"/5/54/Unit-Fran-3.png?version=5d2e1964e694891bcf4e70cde831b9a0","/1/1e/Unit-Fran-4.png?version=814cac1f0080ca8ed82af5d060565bcc"},3),
	Shantotto("Shantotto",new String[]{"/9/9c/Unit-Shantotto-3.png?version=d1914e6129110b81c2b60945e312aee3","/d/d9/Unit-Shantotto-4.png?version=472d99817ea108ad9a26804cc24255eb"},3),
	Rydia("Rydia",new String[]{"/8/89/Unit-Rydia-3.png?version=70f0c17c7b1f490a1cc28d511ee8188f","/5/56/Unit-Rydia-4.png?version=95fbd550e9ca5c68bebf988c82631887"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/1/15/Unit-Rydia-5.png?version=595ed15ee2acad12005e836699552a22"});
		}
	},
	Cyan("Cyan",new String[]{"/9/9d/Unit-Cyan-3.png?version=b27e4a75087620c2757bc8d3c4b9c6f1","/6/6c/Unit-Cyan-4.png?version=73f65d9124d4130d479aafab820b813d"},3),
	Clyne("Clyne",new String[]{"/f/f0/Unit-Clyne-3.png?version=a1dd1e36c7a3ed5748dca151e34edeb6","/9/99/Unit-Clyne-4.png?version=50b16d2347421a94e70223bfb3835be3"},3),
	Anzelm("Anzelm",new String[]{"/a/a7/Unit-Anzelm-3.png?version=69b764fd27e6beb490a437755ae9bb5a","/0/04/Unit-Anzelm-4.png?version=3d9d68d437b8242a2bbad1faee19750d"},3),
	Luna("Luna",new String[]{"/0/0e/Unit-Luna-3.png?version=f5f88addca1363a9623378ede2a45073","/d/d4/Unit-Luna-4.png?version=160a4398bd2f2e8602e497430fb14204"},3),
	Bedile("Bedile",new String[]{"/9/91/Unit-Bedile-3.png?version=e6e43d2c252f6c64ea98eda1bcef60ae","/f/f8/Unit-Bedile-4.png?version=add5c8b1abe3be03914fe2c1563c31c2"},3),
	Garland("Garland",new String[]{"/d/d5/Unit-Garland-3.png?version=6e31a8405d2c89d766ae5916164f4600","/9/91/Unit-Garland-4.png?version=62b830e2576e34c3f0c0d8a494236b31","/0/08/Unit-Garland-5.png?version=e1dc44eb4c223170810de7e08e63cd82"},3),
	Exdeath("Exdeath",new String[]{"/8/8d/Unit-Exdeath-3.png?version=e5922897344416bc550e7b9f65f11ffa","/4/4b/Unit-Exdeath-4.png?version=8456ddf8c06b7b3b41a577d9036f7852","/f/f9/Unit-Exdeath-5.png?version=f9f2cd47a3a5fbccd65f0c8023a1473b"},3),
	Kuja("Kuja",new String[]{"/c/c3/Unit-Kuja-3.png?version=3f1ca21d2e7f8c875465ea7723f6827f","/2/2b/Unit-Kuja-4.png?version=b9f366303e30abdbd4a450b30a95ca34","/4/47/Unit-Kuja-5.png?version=6a46a4dba5e89a4b4ce6469218251d7d"},3),
	CoD("Cloud of Darkness",new String[]{"/b/bf/Unit-Cloud_of_Darkness-3.png?version=848f35057bd6fcff43822cde8a1e8def","/8/8b/Unit-Cloud_of_Darkness-4.png?version=4b091b0d01b167acff33f852fb3c1228","/0/02/Unit-Cloud_of_Darkness-5.png?version=a8e53dc9e1d6ecc16849eac2762c2c35"},3),
	Cecil("Cecil",new String[]{"/7/7b/Unit-Cecil-3.png?version=437c947c2db522631b228bed74fcfa01","/3/3b/Unit-Cecil-4.png?version=8275b728c1d0564569ffcfd202a58253","/e/e1/Unit-Cecil-5.png?version=ee583d5773104f251d57f5586fbd2460"},3),
	Terra("Terra",new String[]{"/b/bd/Unit-Terra-3.png?version=111efd65dc8e86253820a988a6e3d91d","/6/66/Unit-Terra-4.png?version=84fd4cf21b204409fae4c3ce198bb0a3","/e/ec/Unit-Terra-5.png?version=931d017cf95b7084c1269dfd784109b7"},3),
	Bartz("Bartz",new String[]{"/7/73/Unit-Bartz-3.png?version=20178907a396de7d2bdf46ad741bdcb7","/7/76/Unit-Bartz-4.png?version=2d6895f054aa9a3339a33a87208f20bc","/5/57/Unit-Bartz-5.png?version=4ef7830079568901a1d0c3aab3d39787"},3),
	Firion("Firion",new String[]{"/4/4f/Unit-Firion-3.png?version=f8fe9ed7d587a8da58d4c83111dc0a9d","/3/3e/Unit-Firion-4.png?version=6f490a68f825c011119b1bcd53363bdb","/6/64/Unit-Firion-5.png?version=1a9fb3c1cccefcee0e305e303e32bc2d"},3),
	Zidane("Zidane",new String[]{"/b/b0/Unit-Zidane-3.png?version=c9cbba2278b212d988fb9f77587456a8","/8/8e/Unit-Zidane-4.png?version=d0d8d6e809fd1cab0085beca7a44234c","/d/de/Unit-Zidane-5.png?version=f19236a9aca8392ba4df8eaeeac96363"},3),
	Vaan("Vaan",new String[]{"/8/83/Unit-Vaan-3.png?version=82db594c5e1b561a09bfef77a974827f","/f/f6/Unit-Vaan-4.png?version=e379cd52a36cc4dd0bd580bd262caa92","/e/e6/Unit-Vaan-5.png?version=c3e76c21ed20e81f771617ba7dc1d7fd"},3),
	Duane("Duane",new String[]{"/6/6d/Unit-Duane-3.png?version=8a69bd78daba7afe970554f3cb26ee19","/7/77/Unit-Duane-4.png?version=5895dc08b8da75d8374940b8ab5907e4","/c/ca/Unit-Duane-5.png?version=e7ababffe9157e036a3d2a57600ffb8d"},3),
	Cerius("Cerius",new String[]{"/8/88/Unit-Cerius-3.png?version=937604c7e1f7de1daa7d9cde84c24959","/4/47/Unit-Cerius-4.png?version=4bed78a7ccd6f4c7f5ea0ae632e771b6","/5/58/Unit-Cerius-5.png?version=07fc63961225f38a9e9829f134ec0f29"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/0/02/Unit-Cerius-6.png?version=8822b16cf2ff564a82f2d938039fe7c6"});
		}
	},
	Roselia("Roselia",new String[]{"/f/fd/Unit-Roselia-3.png?version=1b634a1a183fdb008cd54ef756c02957","/9/9c/Unit-Roselia-4.png?version=852f06d45d9dd7e7a3bb964b765b7d4c","/c/cf/Unit-Roselia-5.png?version=71beeea0141be646a562d84d88cda6a8"},3),
	Medius("Medius",new String[]{"/b/b4/Unit-Medius-3.png?version=44a4b56b58ae66de62f5731e7edd9538","/3/3e/Unit-Medius-4.png?version=618b6b35b7529c950432995d8f49eb70","/d/d5/Unit-Medius-5.png?version=0e6f9667d7937b726c0c39dd96308077"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/8/89/Unit-Medius-6.png?version=a83fab3622d8262cdc48bf27d17dbf43"});
		}
	},
	Miyuki("Miyuki",new String[]{"/5/5e/Unit-Miyuki-3.png?version=ed9aa10823a009f89ee84c55b2488dc2","/8/8b/Unit-Miyuki-4.png?version=f23b0661931d1b75af3d7a8e743f35e8","/e/ed/Unit-Miyuki-5.png?version=09a84ae46eafc9164133297587d22472"},3),
	Russel("Russel",new String[]{"/d/d6/Unit-Russell-3.png?version=f879849b824e4818b7d42e75adfe6434","/0/01/Unit-Russell-4.png?version=3ca9c390ff68fa5adf659c347510ef59"},3),
	Golbez("Golbez",new String[]{"/a/a8/Unit-Golbez-3.png?version=a48ba3ada6fa3fb9031f4c6765fc632e","/3/31/Unit-Golbez-4.png?version=83a882942bab614b54922317bd6f45fe","/f/f2/Unit-Golbez-5.png?version=fb09062c1c02583e1f59cb982da5002d"},3),
	Galuf("Galuf",new String[]{"/b/bf/Unit-Galuf-3.png?version=c4e38de87222b6da8b3a0b6fc9322fac","/8/8f/Unit-Galuf-4.png?version=42e80587e399605681c21aea100d7e1b"},3){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/0/05/Unit-Galuf-5.png?version=3f90877a3f06cd720b85d72993da337b"});
		}
	},
	Xiao("Xiao",new String[]{"/b/b4/Unit-Xiao-3.png?version=eceed3930c356f795303d925e0ebd53f","/1/13/Unit-Xiao-4.png?version=d4ffb593ba32e0a7f4a7b8dd0056362e","/4/41/Unit-Xiao-5.png?version=a13ed2de278340dd74ec7dcecc39674e"},3),
	Artemios("Artemios",new String[]{"/8/8e/Unit-Artemios-3.png?version=53a3f2772e6bf5a041c7339b47187409","/1/18/Unit-Artemios-4.png?version=90ab67be5ea9ec6fa18b5dd8d0c2a255","/0/02/Unit-Artemios-5.png?version=4de9e5a1d334c74a61adaa83b631c9fb"},3),
	Locke("Locke",new String[]{"/7/7e/Unit-Locke-3.png?version=a5ce114388d09d56cca00d89ba77da01","/e/e8/Unit-Locke-4.png?version=e59fe6ace0e5294c97fda290b834609e","/3/3e/Unit-Locke-5.png?version=10b45340509b2cb416b03c6ecc47ffc7"},3),
	Leo("Leo",new String[]{"/4/46/Unit-Leo-3.png?version=67c0fb1baa63da506a21eda0da0f27a7","/f/f7/Unit-Leo-4.png?version=1aa87219410caec0b639d34f4ffe1c19","/9/95/Unit-Leo-5.png?version=0dadb6623a01f9bce8d952589287ccf4"},3),
	Gilbert("Gilbert",new String[]{"/5/52/Unit-Gilbert-3.png?version=6b4036a5a90372075cd488e55f86ea76","/b/be/Unit-Gilbert-4.png?version=6651a53727df99201827159b9f211260","/3/30/Unit-Gilbert-5.png?version=0baafc0e3ae2e8378240ae297ae1bd78"},3),
	Celes("Celes",new String[]{"/b/bf/Unit-Celes-3.png?version=2d4778a8f2047b66b6e7f2058bfe83ef","/2/2d/Unit-Celes-4.png?version=8712ac20003c040a218f709a1f0bce38","/0/08/Unit-Celes-5.png?version=f135db28ee63d37b90b61f0acba28ebd"},3),
	Kefka("Kefka",new String[]{"/d/d2/Unit-Kefka-3.png?version=6196198582e6c70fa5bc105469b7481c","/3/31/Unit-Kefka-4.png?version=c8d590eb5d5ec0870338107bb221a349","/b/b4/Unit-Kefka-5.png?version=f0ee9c90e4067e9d3a3aaca934c62065"},3),
	Rakshasa("Rakshasa",new String[]{"/f/f7/Unit-Rakshasa-3.png?version=997409afef91850a477e38f39265cd8c","/a/a1/Unit-Rakshasa-4.png?version=92173f438bc74915b50f2c0be6786990","/0/0b/Unit-Rakshasa-5.png?version=ae2a8a032cd8ca1a0a81e41c4384d6aa"},3),
	Chizuru("Chizuru",new String[]{"/5/50/Unit-Chizuru-4.png?version=1603c575e9c5d247a2d3f9080c762ef3","/4/45/Unit-Chizuru-5.png?version=5c8e5ae912f2302db502f1db08cfa989"},4){
		@Override
		public void setup(){
			setUpgrades(new String[]{"/8/80/Unit-Chizuru-6.png?version=0ce21ec1e6ce66e25a6ad1f73761070b"});
		}
	},
	Hayate("Hayate",new String[]{"/6/64/Unit-Hayate-3.png?version=5f6e6f04ae2b6f58935acdc718c74490","/e/e8/Unit-Hayate-4.png?version=03b5dc0330b58ce2b24d91cdfdd1c1ac","/4/40/Unit-Hayate-5.png?version=2441d7142c3c6f7856442bc83173b2b0"},3),
	WoL("Warrior of Light",new String[]{"/7/70/Unit-Warrior_of_Light-4.png?version=a082f11294d562e8b1687b15deecff22","/c/c2/Unit-Warrior_of_Light-5.png?version=6eb241f6f628041e0a9f34e730c08faf"},4),
	Tellah("Tellah",new String[]{"/3/3f/Unit-Tellah-3.png?version=9b56fc089453b07ff34602ec8c8c5503","/c/c4/Unit-Tellah-4.png?version=6bb6a35f7ea92c72cccef37fb4a02e98","/c/c0/Unit-Tellah-5.png?version=78eb3b853f0fa65616138f3c6c948da8"},3),
	Lenna("Lenna",new String[]{"/5/57/Unit-Lenna-3.png?version=5f2df9bf4548eba73a1ab31abd13f864","/7/7d/Unit-Lenna-4.png?version=a4bcd6a62ec852c6dbd8b10208c464b2","/b/ba/Unit-Lenna-5.png?version=ab784a2396211368f34b8575ec1ed5b4"},3),
	Amarant("Amarant",new String[]{"/8/83/Unit-Amarant-3.png?version=7ffb648c9cf44102a3e381b8eb972d99","/0/06/Unit-Amarant-4.png?version=91a73f9fb0b5d2b50f5b7c6bd750b182","/1/12/Unit-Amarant-5.png?version=7dc0b2ad74a0202ae7f747d24d6b4d53"},3),
	Lani("Lani",new String[]{"/a/a8/Unit-Lani-3.png?version=7673aeaccb0b8320fb47fefd4330d12f","/b/ba/Unit-Lani-4.png?version=e62ac0c48f767ef5eff1998509474bbf"},3),
	Garnet("Garnet",new String[]{"/5/53/Unit-Garnet-4.png?version=2346700fe5606dee6821818c492ed133","/f/f3/Unit-Garnet-5.png?version=9becf3a4b588576d48581c5f6168e6da"},4),
	Freya("Freya",new String[]{"/a/a6/Unit-Freya-3.png?version=5544096799a6956efcc47f97ecf7e036","/2/28/Unit-Freya-4.png?version=3473d752d09d26cd84bb17da573afa44","/f/fc/Unit-Freya-5.png?version=2b065f45a90cee4b66dc9a314b8fbd8b"},3),
	Charlotte("Charlotte",new String[]{"/2/24/Unit-Charlotte-3.png?version=d57be6a671744810f6dbfed20dc1d635","/e/ed/Unit-Charlotte-4.png?version=79cb85d2fb2caa1f9086279fc3ee3f49","/c/c5/Unit-Charlotte-5.png?version=19b73296aba4d6debc821986776a57ef"},3),
	Ludmille("Ludmille",new String[]{"/3/32/Unit-Ludmille-3.png?version=e4dbb2ee7047f8da68c135876e8ad821","/0/07/Unit-Ludmille-4.png?version=e1faee93d4c63c20ac4160c36d1b51a9","/0/04/Unit-Ludmille-5.png?version=06acb9be35c7c9dbc26fdbf781d3f7c7"},3),
	Lightning("Lightning",new String[]{"/a/ab/Unit-Lightning-5.png?version=76dd27debeb3102d5f83e332ee9fad3d","/2/25/Unit-Lightning-6.png?version=aded6fbaee049aa66dee4fa2acbe278a"},5),
	Deltia("Deltia",new String[]{"/b/ba/Unit-Delita-5.png?version=01340646bff4e81219c949623a4be807","/b/be/Unit-Delita-6.png?version=d35b6c90434337f1a00f7e7269b3432f"},5),
	Alma("Alma",new String[]{"/1/11/Unit-Alma-3.png?version=1a3c96d4ae4e9412916c151e1f8d7057","/0/05/Unit-Alma-4.png?version=2de8696181c054604895e440de36ea52","/3/3f/Unit-Alma-5.png?version=43b078407da42f9bfce167a021b14547"},3),
	Gaffgarion("Gaffgarion",new String[]{"/1/17/Unit-Gaffgarion-4.png?version=571e82b59004a6512495e4d1433bc339","/e/e8/Unit-Gaffgarion-5.png?version=8e2834b219e6d2a62854d2548363d108"},4),
	Ramza("Ramza",new String[]{"/1/14/Unit-Ramza-5.png?version=e221e5191bed7c51929dfb820303ce6b","/5/5b/Unit-Ramza-6.png?version=7e077c5eea40bc4accb5a7666c75a662"},5),
	Arigas("Arigas",new String[]{"/9/91/Unit-Agrias-4.png?version=1f4bf2300fabf967a3fc1b9efecfaf28","/b/b5/Unit-Agrias-5.png?version=7fadbaea915d40a401f4568993da8185"},4),
	Mustadio("Mustadio",new String[]{"/9/94/Unit-Mustadio-3.png?version=636eeda021f8c0dd0c65e16bcf64dcee","/0/0e/Unit-Mustadio-4.png?version=9a6499dd8d3c8c6ea83468dff2affbf2","/f/fd/Unit-Mustadio-5.png?version=60126912028e1ff06fa9b01b37333db6"},3),
	Rosa("Rosa",new String[]{"/2/27/Unit-Rosa-4.png?version=dafe38ce9c827529603bc9edb973b6d6","/b/b9/Unit-Rosa-5.png?version=4b5accd9c1e2602669485611a918c0e8"},4),
	DKC("Dark Knight Cecil",new String[]{"/8/8e/Unit-Dark_Knight_Cecil-5.png?version=53c285a9df669c314ad1d4cb4ed619ad","/1/17/Unit-Dark_Knight_Cecil-6.png?version=e3ae26a12bc287d5e2571b197706f459"},5),
	Edge("Edge",new String[]{"/c/cd/Unit-Edge-3.png?version=1022775ffad1d0951c48bbf5313a94c4","/7/75/Unit-Edge-4.png?version=eaee8ec6e7408c600acb9d2236271d56","/a/af/Unit-Edge-5.png?version=bb356da4741db4b340b6f7d237c17398"},3),
	Arc("Arc",new String[]{"/b/b7/Unit-Arc-3.png?version=3e6706b49b0b58767f6e10be8d45e10b","/8/83/Unit-Arc-4.png?version=977ad3afabd8b553973bb1a1f65fc89f","/c/ca/Unit-Arc-5.png?version=7f1a7e4d588f891a4d67b1c297eb3e81"},3),
	Ingus("Ingus",new String[]{"/4/4b/Unit-Ingus-3.png?version=f2f97c828da8b8fe82c31a68f38f9acd","/1/14/Unit-Ingus-4.png?version=622643bbed45b07774c001e241ecdcd2","/7/7c/Unit-Ingus-5.png?version=731ed35bdeb64d7a07cb83cd8c777f35"},3),
	Refia("Refia",new String[]{"/8/8b/Unit-Refia-4.png?version=68bf244267d1808c92903f8ad958fcfb","/4/42/Unit-Refia-5.png?version=18cb362f30848f1889749e7e7f0dbbce","/a/ae/Unit-Refia-6.png?version=6e330f7fb9d476368e422933e4a91dcf"},4),
	Luneth("Luneth",new String[]{"/2/23/Unit-Luneth-5.png?version=8dcad4fd75e8effc305672192cd34096","/e/ef/Unit-Luneth-6.png?version=5ed860182433c1c383365b27ece29c1b"},5),
	Faris("Faris",new String[]{"/8/8a/Unit-Faris-3.png?version=f0c435764a45bf505d0feb57cfba17b2","/5/54/Unit-Faris-4.png?version=3a8a34a4904a54ac7ec45dfd00b4a190","/2/25/Unit-Faris-5.png?version=9e4e21612897c0a574a832a5432db1c0"},3),
	Greg("Gilgamesh",new String[]{"/b/bb/Unit-Gilgamesh-5.png?version=a0f4779d8071cbe57c0bebca63dfd617","/2/22/Unit-Gilgamesh-6.png?version=55267a2e8a8f10445f82ac8126357529"},5),
	Snow("Snow",new String[]{"/8/85/Unit-Snow-4.png?version=ef27fa9364205908686305a3d5082de0","/b/bd/Unit-Snow-5.png?version=480f2ec78df6e99fc71cb53fffaef8c7","/d/d1/Unit-Snow-6.png?version=a64d464259719b9b4a76ba7769576e64"},4),
	Vanille("Vanille",new String[]{"/3/3c/Unit-Vanille-4.png?version=c4bea2367dd2721c665c85c17a6ff975","/5/5d/Unit-Vanille-5.png?version=aa95947586ca873014f523d1ced9b5fc"},4),
	Sazh("Sazh",new String[]{"/f/fa/Unit-Sazh-3.png?version=b18c7a4093b0885b0d63168c71ebf2f7","/0/0b/Unit-Sazh-4.png?version=e2da48b67f2e447d18d1599cdfc6a2b4","/7/7c/Unit-Sazh-5.png?version=e64619a09e3854ac16c230b8ade47c8b"},3),
	Hope("Hope",new String[]{"/3/38/Unit-Hope-4.png?version=69418b9eab2ab27ff238e37e6bd031aa","/b/b8/Unit-Hope-5.png?version=dda45c4d494dd4ed755862536bb9cd13"},4),
	Fang("Fang",new String[]{"/8/86/Unit-Fang-3.png?version=29fdeda683b4d006e676c169476e1d60","/0/07/Unit-Fang-4.png?version=4dd21e3f90d5370778a41a06a15ba6ed","/9/9c/Unit-Fang-5.png?version=dd51e09fe6cec2b95f4dba71e3ece024"},3),
	Mercedes("Mercedes",new String[]{"/7/7d/Unit-Mercedes-4.png?version=b06fa24c9122f4c30d975a430666b4a9","/7/77/Unit-Mercedes-5.png?version=5af6929ee15b4c7998513a911ff67f83","/b/b6/Unit-Mercedes-6.png?version=78dfb7127879161d732cec9f990b311a"},4),
	Noctis("Noctis",new String[]{"/c/c3/Unit-Noctis-5.png?version=51fbc15713914afeb855abf73c34395e","/a/ab/Unit-Noctis-6.png?version=c5f73bf1a2d52441b1c849e3f0c635a5"},5),
	Elle("Elle",new String[]{"/4/43/Unit-Elle-3.png?version=ba5f9f32427a3ba032b0f73dfb11ff8e","/c/c1/Unit-Elle-4.png?version=8ca9ec862b8526813c17dcf6e58ad5f0","/4/4f/Unit-Elle-5.png?version=c2a378b867af3d1c5c666761d9cd2efb"},3),
	Luka("Luka",new String[]{"/3/3e/Unit-Luka-4.png?version=79d65988be50974d084aa40a2c6ad2cd","/7/77/Unit-Luka-5.png?version=b031f9c68e3b82b5d92e5f05a5e3db46","/7/75/Unit-Luka-6.png?version=e43c86a42c1ed05b40e1329ef9901fe1"},4),
	DFina("Dark Fina",new String[]{"/1/1d/Unit-Dark_Fina-5.png?version=2dc04248867f101c2333093da98ea26b","/9/92/Unit-Dark_Fina-6.png?version=2d53e5945a5ff90c1e481cae190125ce"},5),
	Trey("Trey",new String[]{"/6/6a/Unit-Trey-3.png?version=7127c3e66c681a5385ee0c4db02ea857","/4/49/Unit-Trey-4.png?version=b1fb6c52253daafdc269319baf81cbfb","/3/3a/Unit-Trey-5.png?version=9b17fc1c970ed9182b45efcc6ae6f2c6"},3),
	Jack("Jack",new String[]{"/1/1b/Unit-Jack-3.png?version=442cbc5e5bdefaa0d13a3215c8c3da9f","/b/ba/Unit-Jack-4.png?version=3f54538b155914b6204587bbe75f7335","/0/0e/Unit-Jack-5.png?version=4f365a86d1d0e93b6db64f3335296a5d"},3),
	Seven("Seven",new String[]{"/9/99/Unit-Seven-4.png?version=6545af54be708d95f8f28ca87a937b26","/6/6c/Unit-Seven-5.png?version=c45c5326b879dda21d47e9d4dd103eda","/7/72/Unit-Seven-6.png?version=3b25005027cfbda21216c0029c89581e"},4),
	Ace("Ace",new String[]{"/3/32/Unit-Ace-5.png?version=e847ec4edd7856697597e9c8cc471390","/2/2b/Unit-Ace-6.png?version=1e449b5f5d9075ba605a39c43ac7f077"},5),
	Marie("Marie",new String[]{"/1/13/Unit-Marie-5.png?version=c3e43aa082d0fe96c3170a54051982a0","/7/79/Unit-Marie-6.png?version=5aeeff3822657f94253d55d66d5b63ec"},5),
	Guy("Guy",new String[]{"/d/d3/Unit-Guy-3.png?version=cd8bfc62c61c50b2943a75930855e259","/d/d6/Unit-Guy-4.png?version=a73f8ca36a08a4b89a2475d6ebaea0ba","/3/3b/Unit-Guy-5.png?version=5b8cfc7adf640cfe7d90c78dea7b3edb"},3),
	Leon("Leon",new String[]{"/2/29/Unit-Leon-4.png?version=7ff4f65694ef428eae1edf38057623a1","/a/ab/Unit-Leon-5.png?version=a8ba5fc02f0361a2686777cd1fad07c4"},4),
	Emperor("Emperor",new String[]{"/e/e9/Unit-Emperor-5.png?version=b5c36f0f52aa20a987170aa569d8b881","/e/eb/Unit-Emperor-6.png?version=38fa32a0e4ff39ceebc9e3350b1615c7"},5),
	Olive("Olive",new String[]{"/7/7a/Unit-Olive-5.png?version=9ae2b446b7c70df36d43565017628c26","/8/88/Unit-Olive-6.png?version=a3037af8900eced5156b91c671545017"},5),
	Shine("Shine",new String[]{"/8/80/Unit-Shine-4.png?version=d5f7c8481e9b2d6ecad508906094c77b","/b/b6/Unit-Shine-5.png?version=e77046618ab6efb84426e2ac306cabee","/3/30/Unit-Shine-6.png?version=5ad309bab23addf3cda10ed6b1851fa7"},4),
	Shera("Shera",new String[]{"/a/ae/Unit-Shera-3.png?version=ff3f045c08286e68e5fb80e9d2f966c9","/9/9d/Unit-Shera-4.png?version=1e833b1c95162126de05372ed7fb7a4c","/5/57/Unit-Shera-5.png?version=244dadc03caae527d4485659d1d0e9f4"},3),
	Queen("Queen",new String[]{"/0/08/Unit-Queen-5.png?version=1172e6e643fbdda3cea58b878829d1e3","/7/76/Unit-Queen-6.png?version=5402e9dbb217d6b9172df4b549e7c3da"},5),
	Nine("Nine",new String[]{"/8/85/Unit-Nine-4.png?version=f1199c797fadeb8416e2655881f66172","/c/c9/Unit-Nine-5.png?version=736a4bccf28e95b8f135a1138b0339e0","/c/c5/Unit-Nine-6.png?version=ca3da82b972a9a4859bb00406cd2803f"},4),
	Clinque("Clinque",new String[]{"/7/7c/Unit-Cinque-3.png?version=cb2a63c1ce951b9a19e652c13e4a7369","/c/c9/Unit-Cinque-4.png?version=b583495b70b78258295606127fbbec08","/e/e9/Unit-Cinque-5.png?version=5e58509a8c6e3b1def6797e26a360c64"},3),
	Eight("Eight",new String[]{"/6/66/Unit-Eight-3.png?version=2b1f2ea6f26f2b3c3eca81eaea91a66c","/7/72/Unit-Eight-4.png?version=9bda9c1ae9a33ca72145898909b4401e","/6/65/Unit-Eight-5.png?version=30b32bd862c57e9f101752c59d617b4b"},3),
	Cid("Thunder God",new String[]{"/e/e6/Unit-Orlandeau-5.png?version=54d5582edcaaf714d7f3bcad639f7850","/d/df/Unit-Orlandeau-6.png?version=fafa898324518b8ce88ee2178b9ead8c"},5),
	Soleil("Soleil",new String[]{"/c/c0/Unit-Soleil-4.png?version=5f172cef43d574dce137600b775ae4a9","/1/1b/Unit-Soleil-5.png?version=e53a8f7588752ac6e7f7d60e4f42fb28","/3/33/Unit-Soleil-6.png?version=30edaf1c6fd963a3bb18555f95e9dc29"},4),
	Ovelia("Ovelia",new String[]{"/a/ae/Unit-Ovelia-3.png?version=ea47aab0e6fd26b8d4439e953bba35df","/6/6f/Unit-Ovelia-4.png?version=e675329da768d85868d4d86b25656134","/b/b9/Unit-Ovelia-5.png?version=21f95a268a15b2dce9c95ebea9ce0a4d"},3),
	Lawrence("Lawrence",new String[]{"/e/ef/Unit-Lawrence-3.png?version=7af7979f144a159a7fc736e684551488","/4/40/Unit-Lawrence-4.png?version=88dc6dcee1e052e8e8f90f43386214fb","/b/b8/Unit-Lawrence-5.png?version=c85157387fe715e3ad84393cec7b2a31"},3),
	Fryevia("Fryevia",new String[]{"/2/25/Unit-Fryevia-5.png?version=e1ada43309badf983ef5a64332175d47","/3/32/Unit-Fryevia-6.png?version=62a168b7fbffd791f9ca8d88437a7883"},5),
	Xon("Xon",new String[]{"/d/d0/Unit-Xon-4.png?version=ff831f717ce3246a16b88f62722fd714","/b/bf/Unit-Xon-5.png?version=6382d0946ca9981e541ba75fec595860","/8/88/Unit-Xon-6.png?version=23090d736507ee8a13f900fd29151ca5"},4),
	Aiden("Aiden",new String[]{"/7/7f/Unit-Aiden-3.png?version=24c74341419a0d66c2a90a9e02f1898a","/5/59/Unit-Aiden-4.png?version=43b3542e5a6754e7c650c957e7ead651","/8/8f/Unit-Aiden-5.png?version=913c715cb81ff55ca348ec184bb9a52b"},3),
	TTerra("Trance Terra",new String[]{"/f/ff/Unit-Trance_Terra-5.png?version=b7acc24edbc1280874056359a8932d23","/c/c9/Unit-Trance_Terra-6.png?version=d7db59f3069fd7f0654b167e4608abf5"},5),
	Setzer("Setzer",new String[]{"/8/82/Unit-Setzer-4.png?version=2ead8a1e0965a89a4e60d2f4d01f9509","/8/8e/Unit-Setzer-5.png?version=e2d2da84954caae0d909c76ed74c53f1","/5/58/Unit-Setzer-6.png?version=a968706d3c6747c0b853ff01cb19fdec"},4),
	Gau("Gau",new String[]{"/a/ae/Unit-Gau-3.png?version=86a4248518d92f0f337d53e8ac01eeb2","/a/a5/Unit-Gau-4.png?version=dddcb33201be09f9e0834d39a90f34c6","/7/72/Unit-Gau-5.png?version=86f4ffd16da0c69b45c17b61b29821dd"},3),
	Eileen("Aileen",new String[]{"/2/21/Unit-Aileen-5.png?version=9b68174017ea66b64e26e0881e955356","/4/49/Unit-Aileen-6.png?version=3420bebd48cf5e77a2d71515ab551ce3"},5),
	Soze("Sohze",new String[]{"/e/ee/Unit-Sozhe-4.png?version=8db6e71885c7f7466053db6a98c70696","/d/d4/Unit-Sozhe-5.png?version=11ba5c7a4156cecd2fd7c76b67a0b2a7","/9/94/Unit-Sozhe-6.png?version=2ab7a096496135b2e14aabb6dce439da"},4),
	Heretic("Heltich",new String[]{"/3/36/Unit-Heltich-3.png?version=b9577d4a66090573ad7474a1beef99f1","/6/61/Unit-Heltich-4.png?version=0095dc6e203899b0c12681c1d76deaa5","/2/2b/Unit-Heltich-5.png?version=e9da98267d682e8763fd93db477118df"},3),
	Ulrica("Ulrica",new String[]{"/7/72/Unit-Ulrica-3.png?version=a5e2cc21b6a44a67253397e1420c2777","/b/b6/Unit-Ulrica-4.png?version=5178cf886205f3100e9b7dfdbde05e27","/1/14/Unit-Ulrica-5.png?version=64271ab140d89f4380352476d5374a51"},3),
	Zyrus("Zyrus",new String[]{"/c/cd/Unit-Zyrus-4.png?version=96375c21507153a910b399b0cd6e1106","/b/be/Unit-Zyrus-5.png?version=2b97acb9180b93df0329e56720c356cd","/5/5d/Unit-Zyrus-6.png?version=d1e4d3ca07a9996c30121fc07c12d8a9"},4),
	Reberta("Reberta",new String[]{"/c/c7/Unit-Reberta-5.png?version=ad823b4deac00579f52a72c006fb5050","/b/b0/Unit-Reberta-6.png?version=9c3ad117048631838242100b8f479c9d"},5),
	Sice("Sice",new String[]{"/4/42/Unit-Sice-3.png?version=6d528642bf94c8af9b6f3d077f10a696","/3/30/Unit-Sice-4.png?version=e003871a530bed2eeb5d6b06bc8f75e9","/6/64/Unit-Sice-5.png?version=3422c4a100dd87d801c5014e02786f90"},3),
	King("King",new String[]{"/7/70/Unit-King-4.png?version=206a7a1fa8287ef83c37ab2386ea9454","/1/13/Unit-King-5.png?version=41f076e40dafce9150099c88da069bb5","/e/e5/Unit-King-6.png?version=030958c77b217bddf56e0492b4dd514f"},4),
	Rem("Rem",new String[]{"/a/a5/Unit-Rem-5.png?version=1285024f32d7a0cfb3b606558eb1d5be","/b/b6/Unit-Rem-6.png?version=78b4aaa81fb7e35238607ea6b0366c5e"},5),
	Wilhelm("Wilhelm",new String[]{"/7/76/Unit-Wilhelm-5.png?version=7b75d37764ee05753823efe779b4c0bd","/f/f0/Unit-Wilhelm-6.png?version=bcb94748f8b43efa39514cc5ad525654"},5),
	Grace("Grace",new String[]{"/2/28/Unit-Grace-4.png?version=9ba0fcc216abfb452e8069a5dc34a991","/3/38/Unit-Grace-5.png?version=385c6d33f20a963e199857dcc2f0da87","/8/8c/Unit-Grace-6.png?version=b42e14f3e2bcedc083ccdff734e31aaa"},4),
	Abel("Abel",new String[]{"/8/88/Unit-Abel-3.png?version=b601d10c549c3102df2c00dde3d2d18a","/0/0e/Unit-Abel-4.png?version=c0b20660e52ea56a5bc077829a2fd8a4","/3/35/Unit-Abel-5.png?version=fc406e856cd2610401fb86f254cb9512"},3),
	Jean("Jean",new String[]{"/b/b4/Unit-Jean-3.png?version=338e9add913f87cbf72696f709eb4246","/6/67/Unit-Jean-4.png?version=09c1e48e33c8d6f5d0af07155b870b94","/9/91/Unit-Jean-5.png?version=e0c72c0e5dc193664eb13818287efcf2"},3),
	Camille("Camille",new String[]{"/3/38/Unit-Camille-3.png?version=565d992b9d75093fe10d25413b5ef658","/1/17/Unit-Camille-4.png?version=c93c0bbeba3aa0f3413432ac10b2329b","/e/e4/Unit-Camille-5.png?version=9021a14234fd7bca288d80250b2ee6df"},3),
	Illus("Illias",new String[]{"/4/46/Unit-Ilias-4.png?version=808ce3b0bc6a5c94f7744f2eb6a06fe6","/7/78/Unit-Ilias-5.png?version=09c4bd297a6739af367e40d5ee9688fc","/2/2b/Unit-Ilias-6.png?version=26a69ad6ed168c2fbece9f676ad559b3"},4),
	Amelia("Amelia",new String[]{"/3/34/Unit-Amelia-4.png?version=56cbfa4e443cafa0866cda4b34365253","/5/5c/Unit-Amelia-5.png?version=f8e8c056ee3bf3352b712ffa727659c2","/9/9e/Unit-Amelia-6.png?version=e1ce2684e570cf1cfa1ab90cd13707fe"},4),
	Forren("Fohlen",new String[]{"/b/b0/Unit-Fohlen-5.png?version=4d4dfdc2fa291033a931846156dde031","/f/f3/Unit-Fohlen-6.png?version=7e14ec56e7b9a2a96589020ea8d31951"},5),
	SLid("Summer Lid",new String[]{"/3/3c/Unit-Summer_Lid-3.png?version=ba691eab63eafc28216aa7c18640281d","/d/dc/Unit-Summer_Lid-4.png?version=2c38daec84e679a22bfb7d66c413ba75","/e/e5/Unit-Summer_Lid-5.png?version=f1e200191d5aa1c0c1a3381ec92f8278"},3),
	SFina("Beach Time Fina",new String[]{"/3/39/Unit-Beach_Time_Fina-4.png?version=66155dc803cca45a45af2c3b4cfcc604","/3/37/Unit-Beach_Time_Fina-5.png?version=552e7d3c3c9bbf286c000bc1bb0d29e7","/4/46/Unit-Beach_Time_Fina-6.png?version=b4dbd0d01a190987cecfcaa7ece8f769"},4),
	SMF("Seabreeze Dark Fina",new String[]{"/1/19/Unit-Seabreeze_Dark_Fina-5.png?version=37b0ed934679b49fbdb37473ae5bc7c6","/2/28/Unit-Seabreeze_Dark_Fina-6.png?version=ed2e43f73d377d7399e14b5c5e45dabd"},5),
	Wakka("Wakka",new String[]{"/6/6b/Unit-Wakka-3.png?version=efbf4ac7a8e7427c7dd5ab968c3e415e","/a/a2/Unit-Wakka-4.png?version=55c147cc53f7b483bd7086191ff7559a","/6/68/Unit-Wakka-5.png?version=4f3adcb5206dbd9d4f30cbd093945c53","/d/dd/Unit-Wakka-6.png?version=d7a3485812058b6128568f958e601636"},3),
	Rikku("Rikku",new String[]{"/6/65/Unit-Rikku-4.png?version=493d126df582d79cdfd77dab26bbb81d","/b/b4/Unit-Rikku-5.png?version=ada0d1c6290ee5b3fbc5bb0948a83ddc","/e/ee/Unit-Rikku-6.png?version=f55bd1d5197ef124bfbdcc2794d53ed1"},4),
	Tidus("Tidus",new String[]{"/a/ae/Unit-Tidus-5.png?version=ec2110ed3b54fd76e89c9f131f961995","/0/0d/Unit-Tidus-6.png?version=b8550fa5b7561cb33bca90153233f06d"},5),
	Ashe("Ashe",new String[]{"/3/37/Unit-Ashe-4.png?version=f69dfba0799de78f34e72b2fd142e1b5","/6/65/Unit-Ashe-5.png?version=2213737478b2807c0a40e76f8d3d2e34","/c/c4/Unit-Ashe-6.png?version=0652057350e5f543ecf914cc71259f1e"},4),
	Rasler("Rasler",new String[]{"/4/43/Unit-Rasler-4.png?version=ca9e6fb6b903a82fbc23ba2da336d99b","/7/7a/Unit-Rasler-5.png?version=c3854833fcde3f9ef799ba4af686f134","/a/a2/Unit-Rasler-6.png?version=a8b6de830db15f87be52c169d4bc0ae2"},4),
	Zargabaath("Zargabaath",new String[]{"/b/b9/Unit-Zargabaath-5.png?version=be421c73a3d136192b43f4f4052900e5","/2/2f/Unit-Zargabaath-6.png?version=06575dc66b59f6f6e410089635a97404"},5),
	Lunera("Lunera",new String[]{"/0/0b/Unit-Lunera-5.png?version=9bf19e954174426c43795124b375ec1d","/f/f5/Unit-Lunera-6.png?version=cf1b8d9d18f75d6b2d21dbf48146c98b"},5),
	Bran("Bran",new String[]{"/9/95/Unit-Bran-4.png?version=c98e0c89238ef1df247560d04e28296b","/c/c2/Unit-Bran-5.png?version=8e9fc764a69b4829b48cf9f9367c10d8","/0/0b/Unit-Bran-6.png?version=8b9e78478872c2bd92b57919bd532f73"},4),
	Helena("Helena",new String[]{"/b/bd/Unit-Helena-4.png?version=286743cd201a7cf5cc46bae863dcec99","/0/0c/Unit-Helena-5.png?version=878990ef50e7bb1f40f2cd8ae35c085a","/5/51/Unit-Helena-6.png?version=e14110b62c630107a914aa41de2ac1df"},4),
	Ruggles("Ruggles",new String[]{"/6/6e/Unit-Ruggles-3.png?version=b15b61b10eb0e36a607b5de9da51adeb","/0/0d/Unit-Ruggles-4.png?version=1732fab88eeee95acd9377bf353afdce","/9/98/Unit-Ruggles-5.png?version=7ad87f653152d34de650c8d201321474"},3),
	MercRamza("Mercenary Ramza",new String[]{"/6/61/Unit-Mercenary_Ramza-5.png?version=88ab13a5509a966fe91a34a43c4461a7","/4/45/Unit-Mercenary_Ramza-6.png?version=454aef40268a7370adf7cd981b52d128"},5),
	KDeltia("Knight Deltia",new String[]{"/d/df/Unit-Knight_Delita-5.png?version=29df964b631ef8f97c083697675a67d5","/e/eb/Unit-Knight_Delita-6.png?version=8ab8fe6795f00c229e2bab6c8e3bef80"},5),
	Meliadoul("Meliadoul",new String[]{"/e/e1/Unit-Meliadoul-4.png?version=06d01cc02fae36c8dcd8b13bec10ee12","/6/6b/Unit-Meliadoul-5.png?version=c9931ac259b1f7019ca9ca25f9b60ae1","/d/df/Unit-Meliadoul-6.png?version=4e84d4eeb983d14112e9d321d95c84c7"},4),
	Orran("Orran",new String[]{"/b/bc/Unit-Orran-4.png?version=ad42bded11268de83e7a6f718bb7c5bc","/9/93/Unit-Orran-5.png?version=cde6630b3bed89b3779a163270a428cb","/3/3e/Unit-Orran-6.png?version=5875d540a5e4bd4d3eab87f2ac75e6e9"},4),
	DV("Veritas of the Dark",new String[]{"/7/77/Unit-Veritas_of_the_Dark-5.png?version=8597061a195609b5fb6da6bfdef8bd35","/1/19/Unit-Veritas_of_the_Dark-6.png?version=b145bd8f69b1cea06c5a374c6a99ead8"},5),
	FV("Veritas of the Flame",new String[]{"/c/c3/Unit-Veritas_of_the_Flame-5.png?version=257b035fc9488a005363086635f6e59a","/2/25/Unit-Veritas_of_the_Flame-6.png?version=0b448a781d9ecba91b8ace2abfddbc2f"},5),
	EV("Veritas of the Earth",new String[]{"/e/e1/Unit-Veritas_of_the_Earth-4.png?version=f7b78fbf92722dbdab4c159cec6c92d7","/c/c9/Unit-Veritas_of_the_Earth-5.png?version=e4cda1cd4935f2be5ee5f740ba3f4db0","/e/e0/Unit-Veritas_of_the_Earth-6.png?version=6bd473fde438ff45aeec41ee146b5d59"},4),
	Victoria("Victoria",new String[]{"/7/7a/Unit-Victoria-4.png?version=7ab85a379bcdf5233a7190439d8a6400","/8/80/Unit-Victoria-5.png?version=17fb4bb81c43530db14a4f3ae719d656","/8/85/Unit-Victoria-6.png?version=a259ded545e88adecaf7987bba0994b0"},4),
	Tim("Timothy",new String[]{"/6/6c/Unit-Timothy-3.png?version=d52a0483065a851f7e7f49fbd3dae98e","/5/5d/Unit-Timothy-4.png?version=0ab177388264222de10bc50c63951fae","/1/16/Unit-Timothy-5.png?version=d95bf1675c86748fe2539d9c20036695"},3),
	LV("Vertias of the Light",new String[]{"/7/7c/Unit-Veritas_of_the_Light-5.png?version=a73fe8c4993066b6fbd8651d3837c37f","/c/cd/Unit-Veritas_of_the_Light-6.png?version=c1b6f63e003723b5717295230574bb1a"},5),
	HV("Vertias of the Heavens",new String[]{"/3/38/Unit-Veritas_of_the_Heavens-4.png?version=0bc09107470f32f4fba7236a129778fc","/2/29/Unit-Veritas_of_the_Heavens-5.png?version=a777aef3b2d973f104dcf7d4c66aa49a","/d/d4/Unit-Veritas_of_the_Heavens-6.png?version=5ac94af348d9084663cb72cf0c9a47de"},4),
	WV("Vertias of the Waters",new String[]{"/9/91/Unit-Veritas_of_the_Waters-4.png?version=6ab058473bfd730bdf74aa7354923e2e","/0/04/Unit-Veritas_of_the_Waters-5.png?version=70870a36e9d08bcfd0f4bc72b2efb018","/3/32/Unit-Veritas_of_the_Waters-6.png?version=a67ccca778195d4b438c6c20e346a14e"},4),
	OK("Onion Knight",new String[]{"/0/0f/Unit-Onion_Knight-5.png?version=92d2ad16d8dcdc0f1c9b10954b15ea4b","/6/62/Unit-Onion_Knight-6.png?version=08e7ed660eb10d4799c915e68619df0f"},5),
	Desch("Desch",new String[]{"/5/5e/Unit-Desch-4.png?version=ca04885d2cf6443fe9d8c506c8b8226d","/1/18/Unit-Desch-5.png?version=0cf714cc19f33687f0804278dbc63a49","/5/5c/Unit-Desch-6.png?version=37e46b6a62c63ca4624f17492b3a8c2c"},4),
	Aria("Aria",new String[]{"/f/f2/Unit-Aria-4.png?version=d62e3ef1ceccdbad0f3ae4b0a36dcf83","/b/b4/Unit-Aria-5.png?version=54ebd4199e31043f364f15275d0d023c","/4/4b/Unit-Aria-6.png?version=86001e84ea0c48563ef86d5a7d980e47"},4),
	Sara("Sara",new String[]{"/8/82/Unit-Sara-3.png?version=99ca5d6674b69ad07cb18e26fabb1240","/8/80/Unit-Sara-4.png?version=0e08f2269873cfb27764934efb36bd91","/8/85/Unit-Sara-5.png?version=6e2512bb1a6d20ba3024cdb676d25112"},3),
	Prishe("Prishe",new String[]{"/2/2b/Unit-Prishe-5.png?version=a937d3cae1940dcfd08bdc5fec2c06d5","/a/a2/Unit-Prishe-6.png?version=a81757cc36945fdf33485076dd0c8466"},5),
	Werei("Werei",new String[]{"/6/6e/Unit-Werei-4.png?version=4073ca88512f21f076bb69a3660a94b2","/9/99/Unit-Werei-5.png?version=e766362f25c3bffc1b117c4397cb4591","/8/87/Unit-Werei-6.png?version=e0d7164c990dc86bdd91182d41f5cce1"},4),
	Kupipi("Kupipi",new String[]{"/6/6b/Unit-Kupipi-3.png?version=7c30899671b26b36ed3d412e44e1cc3c","/5/56/Unit-Kupipi-4.png?version=939a06b6c273fafb0314bf33a42fbdd2","/9/9f/Unit-Kupipi-5.png?version=7ee5a75922e954fcde11cf608e00cd6b"},3),
	Ayaka("Ayaka",new String[]{"/4/47/Unit-Ayaka-5.png?version=24b2ab33dc6743beeaa905f29adf5f1f","/f/f1/Unit-Ayaka-6.png?version=80c0ac3151fb5d52fff87c53b4764e19"},5),
	Goken("Goken",new String[]{"/5/50/Unit-Goken-4.png?version=8d7c082ae78bc8376e511d438d94301f","/e/e1/Unit-Goken-5.png?version=70d9b58f94e0bab1c011907224b503db","/d/da/Unit-Goken-6.png?version=66b4ed9443ce998995cd874968ac5629"},4),
	Silvia("Silvia",new String[]{"/3/37/Unit-Silvia-4.png?version=d5394defd1550959ce1460f56def345b","/3/30/Unit-Silvia-5.png?version=2a412969bb3bfebe5b76c4b060938f8e","/d/df/Unit-Silvia-6.png?version=49862a879ebab98e0d600a7f8cacccf6"},4),
	Kamui("Kamui",new String[]{"/d/d6/Unit-Kamui-3.png?version=0b2b808e8212717d8b706ce0157faf96","/c/cc/Unit-Kamui-4.png?version=00881c522d0091bddb4d88670ee32f49","/6/6f/Unit-Kamui-5.png?version=3bdfc51165f992c2739b9b7e4dc7b839"},3),
	Yuri("Yuri",new String[]{"/2/2b/Unit-Yuri-3.png?version=c792cc40f6045c0fee1ef6d472a2e139","/0/0e/Unit-Yuri-4.png?version=8e616e5356a909efbb22e17183e7d9ec","/4/4e/Unit-Yuri-5.png?version=de25657caa2b6683a8ead61781cc1249"},3),
	Nyx("Nyx",new String[]{"/f/f6/Unit-Nyx-5.png?version=3fbfcf46cfbd474206ba9d31b237cad4","/c/c6/Unit-Nyx-6.png?version=adfa04c4422268bdabd7fdbe4ceb0764"},5),
	Crowe("Crowe",new String[]{"/0/00/Unit-Crowe-4.png?version=bf48dac62746dfa6fa00cbc562726706","/e/e8/Unit-Crowe-5.png?version=97a6df0e8c431f33ed1cb217a7cf1a2d","/4/44/Unit-Crowe-6.png?version=bfad0dc78725d8f8b43163919394b36c"},4),
	Glauca("Glauca",new String[]{"/7/7e/Unit-Glauca-4.png?version=2ba317024212f0c5d88f39927cb576a0","/c/c5/Unit-Glauca-5.png?version=964be36ea6a210aeb47ea2f051e4fc7a","/6/68/Unit-Glauca-6.png?version=5a1e1d2ff6b7a98e837c03e3a952685a"},4),
	Libertus("Libertus",new String[]{"/9/9e/Unit-Libertus-3.png?version=40265bf8be03e70b1ad37b5c5f944d7f","/e/e0/Unit-Libertus-4.png?version=1e1db55425cbb8a94539570d42b6fefc","/0/0f/Unit-Libertus-5.png?version=6b671813738ca6c9ac119273ede7ac08"},3),
	Lorraine("Loren",new String[]{"/9/93/Unit-Loren-5.png?version=3701d0eef7b86c1d646eaad54fc6f3c4","/8/8a/Unit-Loren-6.png?version=195c33ea70d6711fd15cff53cd343f8a"},5),
	Chloe("Chloe",new String[]{"/a/a0/Unit-Chloe-4.png?version=460c81214aef9cfd441c1943f911c090","/c/c0/Unit-Chloe-5.png?version=0274b4e4bb73cb0b3a1151592f35ce91","/4/4e/Unit-Chloe-6.png?version=0b5fb106968f1be407721323ab4a0079"},4),
	Amy("Amy",new String[]{"/e/e3/Unit-Amy-3.png?version=c326a865c14d0f08f506a3bdc05ec530","/7/73/Unit-Amy-4.png?version=6e30a655f7c8c73e67c8352bdf199ed0","/a/a2/Unit-Amy-5.png?version=01c04ee4aa4869829d745fec89254b70"},3),
	Barbariccia("Barbariccia",new String[]{"/b/b6/Unit-Barbariccia-5.png?version=67b20b10f73ef0fc27814b99343f62b4","/7/7a/Unit-Barbariccia-6.png?version=d7bda5ae2de8b4459ee55d97b09506d5"},5),
	Cagnazzo("Cagnazzo",new String[]{"/e/ef/Unit-Cagnazzo-4.png?version=8d65967cd6375f9934384c6fef826e8b","/4/42/Unit-Cagnazzo-5.png?version=4542730da03c56845e15270c13cd4699","/2/29/Unit-Cagnazzo-6.png?version=32d5d513742c867392f3289fc38c6d4a"},4),
	Rubicante("Rubicante",new String[]{"/3/3f/Unit-Rubicante-4.png?version=f7f5179cbef2d1fdab2a517077784fac","/1/16/Unit-Rubicante-5.png?version=8f752d62bdc768de2836f49f09018cdd","/d/d4/Unit-Rubicante-6.png?version=d137e9c176b214589859d40808e52c90"},4),
	Scarmiglione("Scarmiglione",new String[]{"/1/1a/Unit-Scarmiglione-3.png?version=3be95c2daec707d38c0c0e91373fed26","/1/11/Unit-Scarmiglione-4.png?version=f970159ecbdd3d3c5616fb1cbbc873ab","/5/53/Unit-Scarmiglione-5.png?version=cad1351939c877c215912b2854f4d1fe"},3),
	Gladiolus("Gladiolus",new String[]{"/6/60/Unit-Gladiolus-5.png?version=360de4e0d0089e25b12ea938fb43b85d","/6/67/Unit-Gladiolus-6.png?version=95e9c71c5931736e3cf0b3985184fadf"},5),
	Cor("Cor",new String[]{"/a/ac/Unit-Cor-4.png?version=d8f50b348980e38193567f565cb78b2a","/f/fe/Unit-Cor-5.png?version=76dcbe1de67a7521f66e0927cfa74f4c","/6/6a/Unit-Cor-6.png?version=95b0f9c5d3205c2d3516165b651ca300"},4),
	Iris("Iris",new String[]{"/4/4f/Unit-Iris-3.png?version=e3e4face24c2bb4b1311a8b273978c91","/1/1f/Unit-Iris-4.png?version=2c10013c95381c6c9257bb920ac0ec66","/6/6a/Unit-Iris-5.png?version=1b255f85b407d68dfc7b08d1cdfed916"},3),
	Duke("Duke",new String[]{"/f/f5/Unit-Duke-5.png?version=f78ae3e6934154531c88be2a572f2af5","/d/d0/Unit-Duke-6.png?version=e2f94c44c6c134b7acacbbfd0f3737b5"},5),
	Olif("Olif",new String[]{"/6/6d/Unit-Olif-4.png?version=0f8f1c63d100f62f575ea490ab9c66a4","/f/f2/Unit-Olif-5.png?version=a1717019dd0c3dc836d2cd51183a7393","/a/aa/Unit-Olif-6.png?version=ec08785c95d8d48df63d3781f424eab9"},4),
	Mystea("Mystea",new String[]{"/1/1b/Unit-Mystea-4.png?version=f103bc5191253a62b78b57a650beadcf","/f/f8/Unit-Mystea-5.png?version=a90f906aac09e6ec433a3f16e31be853","/2/29/Unit-Mystea-6.png?version=e01e8c16dd056ecbb4ba2b02e8bd94c5"},4),
	Charie("Charie",new String[]{"/b/b8/Unit-Charie-3.png?version=c331e5036c6ac09fe21d80eb59acf5e0","/8/81/Unit-Charie-4.png?version=2d9bd952ac9afce97c3ac90ad429be6f","/0/05/Unit-Charie-5.png?version=d3275ca7c21d08dc559ef4109e1248f9","/6/6a/Unit-Charie-6.png?version=e7dcffdb504ac7f9349e5cce65b172a2"},3),
	Ryunan("Ryunan",new String[]{"/8/8c/Unit-Ryunan-3.png?version=20c5b8fca5afebd4039ea61bffccb6a1","/0/06/Unit-Ryunan-4.png?version=fc96dbd58ca281b66b65351eec5a8977","/3/3a/Unit-Ryunan-5.png?version=3e8491fa3e6f53c08543c5abdeff7f6e"},3),
	Roy("Roy",new String[]{"/0/09/Unit-Roy-5.png?version=d7642b795df8493977f0bd25468d53d1","/a/a0/Unit-Roy-6.png?version=4841930fcdc143e51c3555550b2261fe"},5),
	Aura("Aura",new String[]{"/4/49/Unit-Aura-4.png?version=a1be66c77c2f61cf665912f584264f90","/3/3d/Unit-Aura-5.png?version=708a4cd4eb85bc94fcd52f0e776c627a","/2/25/Unit-Aura-6.png?version=af89167c5f0083ec0b5b5f308002c214"},4),
	Guromu("Guromu",new String[]{"/f/f5/Unit-Guromu-3.png?version=512f0363f28c857b7432e438cf7997bf","/c/cb/Unit-Guromu-4.png?version=58f51e4cc55912a01aa88a73b3bae82e","/b/b9/Unit-Guromu-5.png?version=ea738038390cb0377d86038b6e919053"},3),
	Cloud("Cloud",new String[]{"/8/84/Unit-Cloud-5.png?version=8b93580e2f2516b80249a0d365d08731","/9/96/Unit-Cloud-6.png?version=8cc7e0e1cb21c92e08b9753e30ecd491"},5),
	Elfreeda("Elfreeda",new String[]{"/b/b3/Unit-Elfreeda-5.png?version=c7c03c3a8d18b14679531d56944beec6","/d/d2/Unit-Elfreeda-6.png?version=478266e357ae12144bf6dcf7c3c22590"},5),
	William("William",new String[]{"/1/14/Unit-William-4.png?version=fbecdc24ce09bddf0f38d240c79e8a29","/7/74/Unit-William-5.png?version=348fec1f0340076f04d8803b20bae1db","/3/3a/Unit-William-6.png?version=a0629247d1e739780014002cfef101e2"},4),
	Conrad("Conrad",new String[]{"/b/b9/Unit-Conrad-3.png?version=d7f25d39007227d5aa41ba244b6afe57","/d/d6/Unit-Conrad-4.png?version=a6e2332b977718b989d9f372d54a798d","/d/dd/Unit-Conrad-5.png?version=b0226f35ca0d262b6368e3abd0baeeb4"},3),
	CGLasswell("Pyro Glacial Lasswell",new String[]{"/a/a8/Unit-Pyro_Glacial_Lasswell-5.png?version=089b65188a08d97126c05289da37add5","/4/46/Unit-Pyro_Glacial_Lasswell-6.png?version=7e85a819ebf5f2ad606efbd3b94c98dc"},5),
	Jiraiya("Jiraiya",new String[]{"/3/34/Unit-Jiraiya-5.png?version=26dbaeb4fdb643bdf7c284f1af2c9395","/1/15/Unit-Jiraiya-6.png?version=115fb1c23133ba8ab3f7ab2fa0eedf53"},5),
	Kaede("Kaede",new String[]{"/0/0c/Unit-Kaede-4.png?version=3ccd8947762bbc2898c39a650a1d2f57","/5/52/Unit-Kaede-5.png?version=01edfebaed1f46d7b0065e4c82ec5e24","/e/e4/Unit-Kaede-6.png?version=5b3b96839b5a6526d8740e817bb550ab"},4),
	Ohga("Ohga",new String[]{"/5/54/Unit-Ohga-4.png?version=a668ad4f00a892245db17de05ad3aa77","/b/ba/Unit-Ohga-5.png?version=5eb7cb6ec5f677e743002d1ad8a3bfd1","/4/40/Unit-Ohga-6.png?version=b2085878dda82f11f5842fe0d6e7270a"},4),
	Otogiri("Otogiri",new String[]{"/3/31/Unit-Otogiri-3.png?version=8305550cf8e4f0cfac1e29fed87f37da","/f/f2/Unit-Otogiri-4.png?version=e8023ed816904105110280f1d7d19042","/6/6b/Unit-Otogiri-5.png?version=057fa91c96fa559abb8bcd8eec97d9a0"},3),
	Basch("Basch",new String[]{"/e/ea/Unit-Basch-5.png?version=190f04aff0908ff84993d2c4106d77b1","/e/e6/Unit-Basch-6.png?version=17b1d885272fbf810f097bad0d0602db"},5),
	Balthier("Balthier",new String[]{"/7/75/Unit-Balthier-5.png?version=d279d0a59961eea0a07bd63268d462d2","/2/2b/Unit-Balthier-6.png?version=cb5f9f0534f49d48d09775e9888f8705"},5),
	Vayne("Vayne",new String[]{"/1/13/Unit-Vayne-4.png?version=984e076ae97aff63b30e3ecd69216308","/7/78/Unit-Vayne-5.png?version=adcf2a9a9f145d81f107cdef2c50f122","/e/ef/Unit-Vayne-6.png?version=15c4e69f070a29a78b18c0d2f96af596"},4),
	Drace("Drace",new String[]{"/3/38/Unit-Drace-4.png?version=685850337766cb17a58f0685cf66fcfe","/6/6c/Unit-Drace-5.png?version=6d05e2907c82dfbcada7969effc76a11","/d/da/Unit-Drace-6.png?version=3eddfbb71e3005c46ede160375075eec"},4),
	Larsa("Larsa",new String[]{"/d/d7/Unit-Larsa-3.png?version=91317acb8996b2d0ff2e8b52b1a5afeb","/6/64/Unit-Larsa-4.png?version=6006d65987ec6f80555562c91d2e7d7e","/f/f5/Unit-Larsa-5.png?version=4dd6fafd67d26d3c7792c04102dbb3a1"},3),
	CGSakura("Blossom Sage Sakura",new String[]{"/c/c7/Unit-Blossom_Sage_Sakura-5.png?version=8fab3f7085f50246b5be491887a2c75c","/2/27/Unit-Blossom_Sage_Sakura-6.png?version=ca74c800dbdb0050e2c42f247cdbb557"},5),
	Verun("Verun",new String[]{"/a/a3/Unit-Verun-4.png?version=45babbe14bd7ba3a236d576e7b310750","/4/44/Unit-Verun-5.png?version=6ef061f71f3ef386b730e3146b38e125","/b/b9/Unit-Verun-6.png?version=91cc21d27b19886cce62735771996b2c"},4),
	Cedona("Cedona",new String[]{"/c/c3/Unit-Cedona-4.png?version=7b960ebe4f9680203e625a700a9293e7","/d/d7/Unit-Cedona-5.png?version=d8f2b804adcbb1febdd0d05c487a28ad","/b/b9/Unit-Cedona-6.png?version=ce8ef55d707f6d6880c56465ea327ded"},4),
	Aranea("Aranea",new String[]{"/f/f8/Unit-Aranea-5.png?version=d9f5c9ea69d0a6574280e5271f485356","/c/ce/Unit-Aranea-6.png?version=57409387e992e78275fb7668132d1569"},5),
	Prompto("Prompto",new String[]{"/2/20/Unit-Prompto-5.png?version=49909a877a8f301c841e3e81924f5431","/1/1e/Unit-Prompto-6.png?version=1a9d8a096d5c4c02a3ae3569d968791a"},5),
	Beatrix("Beatrix",new String[]{"/f/f4/Unit-Beatrix-5.png?version=06bc311da6b35c7bf61ecbe35c16fa96","/a/ac/Unit-Beatrix-6.png?version=918b2c18cda7538bc08afceb6385262b"},5),
	Eiko("Eiko",new String[]{"/0/0a/Unit-Eiko-5.png?version=4f7ce1abe5b52150aa001ba277a620f9","/e/ec/Unit-Eiko-6.png?version=4c585f07c446ae9d0c0d97dfa6825240"},5),
	Steiner("Steiner",new String[]{"/a/a8/Unit-Steiner-4.png?version=0f4e44ad35e2ae3158e2cb455f4b13d4","/5/58/Unit-Steiner-5.png?version=dafad977cea228187bf221b163323e6b","/e/ee/Unit-Steiner-6.png?version=11d230ab78dd7bfe18bfcf5c8b7692a8"},4),
	BW3("Black Waltz 3",new String[]{"/6/6e/Unit-Black_Waltz_3-3.png?version=90fe6192db8cd90ecf4dee008ff98156","/3/31/Unit-Black_Waltz_3-4.png?version=842d9bbfec7a50eca5f1129d8a219f12","/7/70/Unit-Black_Waltz_3-5.png?version=96a07b1257977b1362da654a5d1bfce6"},3),
	CGFina("Lotus Mage Fina",new String[]{"/e/e8/Unit-Lotus_Mage_Fina-5.png?version=7fc2b6d51be1a937176ac47bd2cf6816","/e/e2/Unit-Lotus_Mage_Fina-6.png?version=3e286664455a248f15678ca9a893171e"},5),
	Kunshira("Kunshira",new String[]{"/e/e1/Unit-Kunshira-5.png?version=0af423eaac704605bae20707c33de49d","/3/3a/Unit-Kunshira-6.png?version=7c21408b6d4691204c27cc71ce1340e0"},5),
	Wadow("Wadow",new String[]{"/3/32/Unit-Wadow-4.png?version=9efeab238954a0ba8c61f64a8053fb35","/e/ef/Unit-Wadow-5.png?version=751751cec7914080611436261ef686ed","/4/4b/Unit-Wadow-6.png?version=b04a534e0f3f7c5bdb03723bb7270acc"},4),
	Erwin("Erwin",new String[]{"/2/2f/Unit-Erwin-3.png?version=6e914817c6271d2dd561d4f9dc04450d","/5/52/Unit-Erwin-4.png?version=7e4e0407f31392a2bab952e7c6089e07","/c/cc/Unit-Erwin-5.png?version=21fad6d5bdebbbfe4c11108ec1af1f1d"},3),
	Yuna("Yuna",new String[]{"/2/27/Unit-Yuna-5.png?version=03dc35a4f72ab97bd155cc3aaf5934b7","/d/d3/Unit-Yuna-6.png?version=4e3258de6090142ae95e720d1ab8eaf8"},5),
	Lulu("Lulu",new String[]{"/c/c4/Unit-Lulu-5.png?version=4bd9566cc8afba4203d2d7dc427904d8","/3/32/Unit-Lulu-6.png?version=e519a3f2ac3e1f2bfc9917becfa9f269"},5),
	Seymour("Seymour",new String[]{"/6/65/Unit-Seymour-4.png?version=dde800cbd6f9fde81889bffe37b5b507","/4/44/Unit-Seymour-5.png?version=ef3150f39e449ab01ba8cbefbd7332da","/a/a6/Unit-Seymour-6.png?version=ba19ef7ec837a3485813dca720347dda"},4),
	CGJake("Nameless Gunner Jake",new String[]{"/9/94/Unit-Nameless_Gunner_Jake-5.png?version=a852b29f4e3a8e552e3f9c027685b66f","/2/20/Unit-Nameless_Gunner_Jake-6.png?version=41f67f1faf761f71c173da428e32c4dd"},5),
	EShera("Emperor Shera",new String[]{"/d/de/Unit-Emperor_Shera-5.png?version=dcc718272a16be37474b481acb05f7f3","/0/09/Unit-Emperor_Shera-6.png?version=bce2ea71ed078e8b7b80d8691d29bddf"},5),
	Ozetta("Ozetta",new String[]{"/1/1e/Unit-Ozetta-4.png?version=e3cdc7434a23b7e3950ddf7d00ff183a","/6/69/Unit-Ozetta-5.png?version=aaa2eb59c33bb2220bf1333ac7a3a04f","/f/f9/Unit-Ozetta-6.png?version=8a82df3daf90189eb5adeb03b0cd3c88"},4),
	Riley("Riley",new String[]{"/a/ae/Unit-Riley-3.png?version=dbafc81073ca8c63ba1cd8bb90d5323f","/5/55/Unit-Riley-4.png?version=c934a3ed86c5185c3e7779aa11c1de6b","/9/91/Unit-Riley-5.png?version=91b156253db2ea804ad58833f7059b20"},3),
	Sephiroth("Sephiroth",new String[]{"/a/a8/Unit-Sephiroth-5.png?version=305aaf8c032ed0401c54d1e1e46c3453","/1/17/Unit-Sephiroth-6.png?version=c7bc677f0390279b77b36538101cae90"},5),
	Lila("Lila",new String[]{"/2/24/Unit-Lila-5.png?version=be61a44181eb6057a619c46d7a9d1d10","/3/36/Unit-Lila-6.png?version=51e62445c7ab729d25401b90f5adb6e3"},5),
	Shylt("Shylt",new String[]{"/8/84/Unit-Shylt-4.png?version=84f4387e0db47bb6d703251e4c2a7fca","/6/6a/Unit-Shylt-5.png?version=042547834110d12bbab8c1144beae1ab","/5/59/Unit-Shylt-6.png?version=2750917c9fbf63c68ded249c4d19ccd0"},4),
	Mim("Mim",new String[]{"/5/51/Unit-Mim-3.png?version=4f1a4af1d00434f149aa7c682406fa29","/c/c7/Unit-Mim-4.png?version=53969747f53c505f5331e45c05991abf","/9/96/Unit-Mim-5.png?version=2f1658dbea92814db5863eebe4ee33e4"},3),
	CGLid("Heavenly Technician Lid",new String[]{"/f/fa/Unit-Heavenly_Technician_Lid-5.png?version=bc813fab82c60bbc204707e720b16323","/0/0a/Unit-Heavenly_Technician_Lid-6.png?version=f0242eb79f0db3347cde1fff273384d3"},5),
	Killian("Killian",new String[]{"/2/2d/Unit-Killian-4.png?version=938232d16cf76774a192626c914b87b6","/f/f3/Unit-Killian-5.png?version=1e11c187fa57b590ca8d962d6aa24bc4","/0/08/Unit-Killian-6.png?version=e60f4e0dc2dcc5ee9177fd826d1f0fdf"},4),
	KRydia("Pure Summoner Rydia",new String[]{"/e/e0/Unit-Pure_Summoner_Rydia-5.png?version=8fb7c285b9999bec48f58e6d621f2f8f","/3/3a/Unit-Pure_Summoner_Rydia-6.png?version=5d808dc60bffcc691616289dea3be6c7"},5),
	HKain("Atoning Dragoon Kain",new String[]{"/0/0d/Unit-Atoning_Dragoon_Kain-5.png?version=8ff78447e609c10a9366d9abe3721846","/0/08/Unit-Atoning_Dragoon_Kain-6.png?version=ce3b741d3b9296d37a6368ea70b6d59b"},5),
	Yang("Yang",new String[]{"/7/73/Unit-Yang-4.png?version=857260b833de7cd3c7da98103c8c5a05","/f/f3/Unit-Yang-5.png?version=69288e6463bd904a114cf123c1f14e77","/7/7e/Unit-Yang-6.png?version=8788342be6116208cd39788ce7b7628f"},4),
	Edward("Edward",new String[]{"/e/ed/Unit-Edward-3.png?version=83e27e2b93b9e4b2a34c1dc5a56d7e65","/d/d4/Unit-Edward-4.png?version=5a3b621e4127dca09eb671ba0f22181d","/7/76/Unit-Edward-5.png?version=69f19047f5e0bf8e54140cad4fff9253"},3),
	CGNichol("Maritime Strategist Nichol",new String[]{"/6/65/Unit-Maritime_Strategist_Nichol-5.png?version=0a22a6266a8ba85a8c101c14be2e5742","/8/89/Unit-Maritime_Strategist_Nichol-6.png?version=357b2bd09a1fdb3d1a0033a8da8bf095"},5),
	Lexa("Lexa",new String[]{"/e/e8/Unit-Lexa-4.png?version=0fd0833348e203690364e84e5f0294b2","/2/25/Unit-Lexa-5.png?version=3f5c1db104939f28bfc3f5189eab0cfa","/4/42/Unit-Lexa-6.png?version=dfad200865c016735174176d824310c6"},4),
	Elbis("Elbis",new String[]{"/f/fa/Unit-Elbis-4.png?version=c6ce6311e089734c05f0e77d109850c4","/2/2a/Unit-Elbis-5.png?version=6cf2b4868fb2e696fecb7485f606019f","/0/0f/Unit-Elbis-6.png?version=06f820a0e0269a6ca0a55c87feeb03a9"},4),
	Merald("Merald",new String[]{"/7/7a/Unit-Merald-3.png?version=accd16d3b60328cb9d434f843d968374","/1/1c/Unit-Merald-4.png?version=8ff1bb6eac19cdf121a1e0693a85c183","/f/f3/Unit-Merald-5.png?version=8b4022cd3677787001b5167250681d1d"},3),
	
	//Limited Units
	Juggler("Juggler",new String[]{"/c/c2/Unit-Juggler-4.png?version=3a13b03756a5ca2541424e34fc6918fe","/1/1d/Unit-Juggler-5.png?version=b1a7e5acd4738d739069f8d6b4563932"},4),
	Thief("Thief",new String[]{"/8/8e/Unit-Thief-4.png?version=8ce7dae96b88c66b7bead054c9a45995","/d/de/Unit-Thief-5.png?version=fec375ed9ecead999e1d194c03891d02"},4),
	Fencer("Fencer",new String[]{"/d/d8/Unit-Fencer-4.png?version=362f99325ceef8968732a8f49374d9b3","/0/02/Unit-Fencer-5.png?version=1c2b5f69281911ce1464ea31d68ae0a5"},4),
	DRain("Demon Rain",new String[]{"/c/c7/Unit-Demon_Rain-5.png?version=e7d8aca36a2cc4a4cef7c5b48b9af8e3","/3/30/Unit-Demon_Rain-6.png?version=8ef21aebd449a531acc171547ae6fd0f"},5),
	DLasswell("Dracu Lasswell",new String[]{"/a/a5/Unit-Dracu_Lasswell-5.png?version=40b4eb078e787d5f498bcc8b979a97a1","/c/c3/Unit-Dracu_Lasswell-6.png?version=d81994a6cf9357430b970e5bdfd9d9e5"},5),
	WWF("White Witch Fina",new String[]{"/a/a2/Unit-White_Witch_Fina-4.png?version=aa9d199e9c09952626d6fc08de86c06c","/4/48/Unit-White_Witch_Fina-5.png?version=8c4a1c82b6fedb7dae28c5fd0c62744b","/0/07/Unit-White_Witch_Fina-6.png?version=29640d666b6eec84884727ced377cd73"},4),
	BCLid("Black Cat Lid",new String[]{"/0/08/Unit-Black_Cat_Lid-3.png?version=44bb6ce33351691f6d52ed2a3e6ca0df","/4/40/Unit-Black_Cat_Lid-4.png?version=7ee8d85a278ebb0eabbc051f6b5d9c8a","/1/1c/Unit-Black_Cat_Lid-5.png?version=726eeed33388fdef994e7f27ebce139e"},3),
	Karl("Karl",new String[]{"/2/2b/Unit-Karl-4.png?version=d2b15d925afdd5fbdff81b84e12bff86","/5/5b/Unit-Karl-5.png?version=8ba5221adb55030068f2746f34a7406a"},4),
	Seria("Seria",new String[]{"/f/f2/Unit-Seria-4.png?version=2d93b6612635f16369c82ad891a8b7c4","/2/2a/Unit-Seria-5.png?version=96ce1921392b62033d5854c71da47294"},4),
	Tilith("Tilith",new String[]{"/c/cf/Unit-Tilith-4.png?version=aab39f1eb3af102b0e982a69aeb4d63b","/f/f3/Unit-Tilith-5.png?version=8e2d3d30592b49f36b9f6bf1b5e79872"},4),
	Elza("Elza",new String[]{"/3/39/Unit-Elza-5.png?version=a74a90a66643d03325a5c3d845cb2a42","/0/08/Unit-Elza-6.png?version=ca6f643b429b7fbad0078519310414cb"},5),
	SRoselia("Santa Roselia",new String[]{"/e/e6/Unit-Santa_Roselia-4.png?version=2e794cb3a258f167f8910b2c853a3e57","/5/54/Unit-Santa_Roselia-5.png?version=89b0722a00c2f477d34745d41ca37d74","/9/98/Unit-Santa_Roselia-6.png?version=93011430284da0b21c4f253b357000d9"},4),
	WKN("White Knight Noel",new String[]{"/1/12/Unit-White_Knight_Noel-5.png?version=d1453e13c630264a7ded5841ac542782","/0/0e/Unit-White_Knight_Noel-6.png?version=69e4eba92941d7648b59bf886a3d960a"},5),
	Ling("Ling",new String[]{"/7/73/Unit-Ling-4.png?version=acf76984888d4f98b453746d6b29fae1","/8/8c/Unit-Ling-5.png?version=726755613a586d93384ae7e840c68952","/6/65/Unit-Ling-6.png?version=8155d78b7025d4dea35d9f2a4db7507c"},4),
	Yun("Yun",new String[]{"/d/d6/Unit-Yun-5.png?version=cc6adeaf9174872f4dc7d9097e2a5ea8","/c/c6/Unit-Yun-6.png?version=18e5696b601b7b165e54da8f6ace6972"},5),
	Popoi("Popoi",new String[]{"/6/6d/Unit-Popoi-3.png?version=aa4d1b9134000aef80fae8487ed0c5ec","/e/eb/Unit-Popoi-4.png?version=ef4ffd5748230c5ef652a8c00a3fbc1b","/8/80/Unit-Popoi-5.png?version=2b6ed30af78a9e00aeac91ca2c00b8c0","/9/9f/Unit-Popoi-6.png?version=23d07e77c7c57a4f3b883b405f9e9737"},3),
	Primm("Primm",new String[]{"/4/42/Unit-Primm-4.png?version=43be8e04f63f03996855ab9535e0d442","/9/99/Unit-Primm-5.png?version=a9fcb36b56eb896166f21f579530ed53","/d/d9/Unit-Primm-6.png?version=af047f2e5a86f34ac5af0fa6893d66e2"},4),
	Randi("Randi",new String[]{"/9/9a/Unit-Randi-5.png?version=2a7b32f9840253a4c872b104ff1e4e8a","/3/33/Unit-Randi-6.png?version=43023da35f257c58e7d0cf7f6a2cc7df"},5),
	CLuna("Cupid Luna",new String[]{"/8/81/Unit-Cupid_Luna-3.png?version=cffd0176f752079d7cf501e68ef7db8f","/c/c9/Unit-Cupid_Luna-4.png?version=2f3f2d87279c98f560ee51a6c7758729","/f/f8/Unit-Cupid_Luna-5.png?version=ed357c1166677164410746f68794f867"},3),
	CArtemois("Cupid Artemios",new String[]{"/9/98/Unit-Cupid_Artemios-4.png?version=7eec6b4e956cb7cd7ffc4b7a3e4f5736","/5/5b/Unit-Cupid_Artemios-5.png?version=59d4ba9606ef3629a2700768f7b8ebdf","/f/f0/Unit-Cupid_Artemios-6.png?version=78521b15b2d1454a4ebc271599f004bd"},4),
	Vargas("Vargas",new String[]{"/f/f6/Unit-Vargas-5.png?version=c992c9e3e45fc51ba4f988bfd43ad36a","/4/49/Unit-Vargas-6.png?version=75436c9535280ae940f9a95fd92202c3"},5),
	N2B("2B",new String[]{"/d/dd/Unit-2B-5.png?version=636791557d0324154fdb07497cee6bca","/4/4f/Unit-2B-6.png?version=d927354d62019f54dc2fb9df22c1cf76"},5),
	N9S("9S",new String[]{"/6/6f/Unit-9S-4.png?version=db380698c0c450106094d2fbd452fb1e","/a/a3/Unit-9S-5.png?version=f4c8ff152bd312f27096ba07486f80e0","/8/8a/Unit-9S-6.png?version=1f2f141a389bb4d5c63d35f4fdc954ce"},4),
	N21O("21O",new String[]{"/6/6a/Unit-21O-3.png?version=773885048ee9db1d74cef32d6aa39e06","/f/f6/Unit-21O-4.png?version=b811a99059f31ba90bae63b01b488da5","/c/c9/Unit-21O-5.png?version=f8ae066d35835eb08644031243a8421a"},3),
	A2("A2",new String[]{"/6/6c/Unit-A2-5.png?version=6cb61c32b76ac430caa67d2f8af4cd11","/6/62/Unit-A2-6.png?version=83419766bc980c3107f78f2157666a8d"},5),
	Eve("Eve",new String[]{"/9/96/Unit-Eve-4.png?version=9d9455b0dda465d2d3a3df092160ddc9","/3/34/Unit-Eve-5.png?version=47f67d92dfee3fc27cb7d44b3b5135e3","/3/39/Unit-Eve-6.png?version=75aebc263ef4b09d6cd26ba1cebc5d55"},4),
	GLSakura("Grim Lord Sakura",new String[]{"f/fd/Unit-Grim_Lord_Sakura-5.png","1/13/Unit-Grim_Lord_Sakura-6.png"},5),
	INichol("Illusionist Nichol",new String[]{"a/ac/Unit-Illusionist_Nichol-4.png","b/b6/Unit-Illusionist_Nichol-5.png","6/6d/Unit-Illusionist_Nichol-6.png"},4),
	PJake("Pirate Jake",new String[]{"f/f7/Unit-Pirate_Jake-4.png","8/82/Unit-Pirate_Jake-5.png","b/b1/Unit-Pirate_Jake-6.png"},4);
	
	
	
	
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
				WV,
				OK,
				Desch,
				Aria,
				Sara,
				Prishe,
				Werei,
				Kupipi,
				Ayaka,
				Goken,
				Silvia,
				Kamui,
				Yuri,
				Nyx,
				Crowe,
				Glauca,
				Libertus,
				Lorraine,
				Chloe,
				Amy,
				Barbariccia,
				Rubicante,
				Cagnazzo,
				Scarmiglione
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
