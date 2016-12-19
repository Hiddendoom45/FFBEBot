package commands;

import global.record.Log;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import util.Lib;

public class Summon implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		Log.log("FORBIDDEN", "Summon wrath evoked by "+event.getAuthorName());
		event.getChannel().sendTyping();
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Lib.sendMessage(event, "S̴̨̡̡͔̝͈̹̠̤͙̤͔̀̊̂̉̅̋̆̎̑̐̊̓̚̚ŭ̶̧̟̩̪̣̺̍̓̈̅̋͂͋͑͛͐͛͘m̵̧̨͍̖͛̉̆͒͒̔͂̇̕̚͝͝m̴̥̳̻̔̃̀̀͗̓̿͑̾͑̀͂̇͘ṏ̴̫̰́͒́͌̉̆̐͘͘̕n̶̼̭̓͋̇̑͒̊̈́ ̸̡͎̯͔̑͑͌͋͛͜ͅŞ̵̨̰͖͓̽̈̒̀͘͝í̵̮̪̝͐̌̔̋͗̐m̶̡̛̭̥̫̜͍̺̲̳̑̏̎̃̍̈̀͛̃̕̚u̴̧͖̼̱͕̮̓̔̎̑l̴͙̗̺̹̙̞͓͙͓̫̪̪̥̠̀̌a̵̺͖͗̿͝ţ̵̧̢̛͔̤̦̦͙͈̺͓̺͈̝̀̎̉͌̏̀̈̐͋͐̀̋̎͜õ̸͙̦̼̤͚͖̜̜̗̭̬͕͇̹̞̎̆̌̓̉̑̕͝r̵̡̡̼̱̤̖͎͔͇̞̻̳̆̅͂̅̾̂̒͂̈́̏̈́͜͝͝͝?̶̛̻͈̪̓̈͐͑́̾̅̈́͑̀́̿̆ ̵̨̢͍͔͚̺̦̳͕͚͓̮̪̩́̌͋͑̔̀̅͛͗̀̈W̷̛͎̟̫̜͕̮̣͇̬̙̲̲̟̹̃͋̄͑͒̍͒̋̋͋͆́͆͘ḫ̷̹̘͉̦̝̻̘̾̽̋͐͛̾͐̐͛̊̓͊̒̚ą̶͈̤̟̤̓͂̔̈̔͌͗́̔͒̐͊̅̉͘t̵̤̺͎̣̭͕̜̯̏̉͗̀̋̓͗̐͋̄̈̇̚ ̷̢̨̧̡̙͓̯̰͈̱̫̤͈̭̂̈́̊̈̍͊͆̿͝ṣ̵̗̻̯͎̮̮̯́̎̃̄̉̒̒͝ũ̴͈̳͙̙̯̯͎̮̽̓̉͜m̴͉̝̭͉͍̘̱̈́̐̿̆͊̽͒̀̒̓̕m̴̢̨̧͚̥̖̰͈̪̫͈̠̹̀͑̄̂͋̏͐̏̚̚̚͜ͅo̵̺͖̫̎̌͂̉͐͌n̵̡̛̩̮͇̬͎͈͑̔̋̒͒̓͂̔̔̂̓̕͘͘ ̷̡̧̨̗̲̟͈̮̼͍͍̝͕̃̓͜s̵̯̜̟̮̗̖̳̭̾͌̈́̈́̊̆͜į̴̫͓̺̠̹̪̄̏́̃̽̉͐̌͒͐̅͌̄̾̍m̷̡̦̻̯̰̍͑̎̄͊ͅu̵̥͎̗͖̣̤̥̿̊͌͂̏̚͜͝l̶̢̘̹̲̈̃̄̀̑̈́̈̑͘͜͜ä̶̢̗̟̲̜̮͇͖̳̩̥́̎͘̚t̶̜͓̉̓̈́̈́̃ȍ̵̹̟͊́̆̏̉̓̐͆̕r̴̪͓̪̰̘̜̰͍̗̓͑͊́̑̿̏̈͊́̅̄̌?̸̧̮͍͎̙̹͖͔̠͒̎̏͌̈́̈́͊̀̾̕͝");
	}

	@Override
	public void help(MessageReceivedEvent event) {
		Lib.sendMessage(event, "Ș̴̦́͗̅́̈̐̒̂̓̑̇̈́͠u̴̧̡̼̦͕̣̮͉̼̝̼͖̍̊m̷̧̦̖̭͐̑̈́͛m̷͇͚̆̌̈́̈́̕͝͝o̴̝͆̈́̎͆̆̒͌͘ņ̷̨͉̟̈̇̒̾͗ị̸̫͓̭̖͚͚̪̣̫̱̄͊n̵̨̟͓͈̭̤̅̉͛͌̂̌͋͊͘͠͝g̸̛̛̼̭͌̾͒̽̽̾͋͘͘͝ ̶̡̟͉̪̮̫̮͙̣͉̺̭͖̗̀̂̐d̸̹̉͊̾̽̐̑̈́̉o̵̡̜̻͔͇̦̰̹̺̼̖̞̮̿̂̓̈̑̃̀̈́̑͒̃͜͝e̸̢̪̹͎̹͕̗͆̋̑́̐́̾̚̚͝͠ͅͅs̷͈͙̠̝̘̄͊͐̽͂̑͆̌̽͛̓̚͜͝n̶̡̛̘̹͖̫͔̄̔̌̌́̍̽̆̈́̃͘͜͝͝'̸̼̠̉́͛̒͘͠t̵̨̲̟̻͓͎̳͓̲̹̦̐̽̊͛̋͆͠ ̶͕̍̒̔̋̏͂̔̏͗͋̅͆͌͛͗e̷̢͚̱̬̹͚͇̘͎̝̣͇͖̋̇̍x̶̡̧̱̠̘̩̰̮̳̐͌́̋͆̑̒̇̐͝ï̷̡̝̦͇͉̬̠̣̭̙̜̮̼͇̐́̀́̏̂͊͋̌̎̋̈́͂͘s̷͚̄͊̉͋̌͌̑͒̓̿͂́̊͝t̶̨͎̣̼̯̠͓̣͈̞̗̗̱͆̽̿͑͊͋̅̈́͒̅̎̑͝͠ͅ ̷̧̹͕̙̠̕̕ḫ̶̖̙̋̀͗̐͘é̵̺͍̞͎͈̐̃̈̒͌͆͐̆̋̀͝͝ŗ̷̢̻͔̲͕̟̳̬̙̭̘͒̃͊́̀̄̕͜͜͝ͅë̷̢̢̫͇̟̤͈̰́");
		
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
