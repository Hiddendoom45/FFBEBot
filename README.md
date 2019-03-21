# FFBEBot

The source code for a discord bot created to retrieve information about the game from the exvius wiki and the reddit wiki


To invite this bot use the following link: https://discordapp.com/oauth2/authorize?&client_id=244615809559822338&scope=bot&permissions=null&response_type=code

The bot only needs basic message read/write permissions to be functional
Optionally you can allow the bot to delete messages with the manage messages permission to cleanup certain commands the may cause clutter. It also needs the read message history permission to find its own messages to delete them.

### Status

Somewhat maintained, summon simulator will continue to be updated but fixes may be slow. 

#### Recent Updates:

- we all work hard for our rainbows
- aliases are interchangable between the runit and unit commands, en,jp names and id can be used to search with both runit and unit (and all releated commands)
- update to runit command and parser for new format for reddit wiki
- update unit command so jp names work and ids (for the base unit) also works
- using gl names with the runit command will get the right unit instead of unit not found
- all banners follow common naming scheme
- update banner name so that the Christine banner from last year is called Christmas2, TODO: create common naming scheme for all banners
- update runit command to be compatible with just links
- update stat display, cleaner and different stats sorted into columns
- update exivus wiki parsing for due to issues caused by wikimedia update
- update to runit, displays links for exvius.gg and famitsu

#### TODO:

- add parsing for exvius.gg and aEnigma's data dump
- more tests to make sure bot is running correctly

### Building / Self Hosting

- The bot is setup to be built with maven. [Maven Download.](https://maven.apache.org/download.cgi) Or use your preferred package manager. 
- Run `mvn --version` to check that you have installed it correctly.;
- Run `mvn clean` to clean up any previous builds and then `mvn package` to build the jar file.
- Note: there will be portions of the code that will be broken as the bot was not initially designed to be easily portable. If a suggestion is sent using `bugreport [bug/suggestion]` or issue opened, then I'll devote more time to this.

### Bugs/Suggestions

- use the `bugreport [bug/suggestion]` command or open a new issue on this repository

##### Other

See `changelog.txt` for the previous changes to the bot prior tracking using git
