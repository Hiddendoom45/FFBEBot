# FFBEBot

The source code for a discord bot created to retrieve information about the game from the game wiki and the reddit wiki


To invite this bot use : https://discordapp.com/oauth2/authorize?&client_id=244615809559822338&scope=bot&permissions=null&response_type=code

The bot only needs basic message read/write permissions to be functional
Optionally you can allow the bot to delete messages to cleanup certain commands the may cause clutter

### Status

Currently maintained, any bugs that I find should be fixed quickly, not actively adding new features.

#### Recent Updates:

- used color-thief-java to add some colors to some embeds based on the image
- added unit flair image in runit command
- Fix a lot of parse issues with reddit units
- Fix minor parse issues, clean up UnitArt command to use embeds
- `@FFBEBot help` will also bring up help menu
- Can search for unit by ID of base rarity w/ runit
- Minor fixes to mod commands, some annoying messages temporary

#### TODO:

- fully update parsing for exvius wiki and reddit wiki commands
- new features


### Building

- The bot is setup to be built with maven. [Download.](https://maven.apache.org/download.cgi) Or use your preferred package manager. 
- Run `mvn --version` to check that you have installed it correctly.;
- Run `mvn clean` to clean up any previous builds and then `mvn package` to build the jar file.

### Bugs/Suggestions

- use the `bugreport [bug/suggestion]` command or open a new issue on this repository

##### Other

See changelog.txt for the previous changes to the bot
