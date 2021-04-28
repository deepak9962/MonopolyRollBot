import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import javax.security.auth.login.LoginException;
import java.util.Objects;

public class MonopolyRoll extends ListenerAdapter {
    public static GuildMessageReceivedEvent eventGlobal;

    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault(System.getenv().get("TOKEN")).build();
        jda.addEventListener(new MonopolyRoll());

        try {
            while (true) {
                Thread.sleep(60 * 1000);
                eventGlobal.getChannel().sendMessage("").queue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        String[] messages = {"Fuck me! I'm not in the mood right now. -_-",
                "Get Lost I said already. Stop bulling me. '-_-'",
                "Imma MF, leave me alone."};
        eventGlobal = event;
        String getMessage = event.getMessage().getContentRaw();
        if (getMessage.equalsIgnoreCase("!roll")) {

            event.getChannel().sendMessage("Roller Name : " + Objects.requireNonNull(event.getMember()).getUser().getName()).queue();
            event.getChannel().sendMessage("Total : " + monopolyRoll(6)).queue();

        }
        
        if (getMessage.equalsIgnoreCase("!help")) {
            event.getChannel().sendMessage("Just type !roll to activate me -_-, ugh! such headache.").queue();
        }
        
        if (getMessage.equalsIgnoreCase("!invite")) {
            event.getChannel().sendMessage(System.getenv().get("INVITE")).queue();
        }

        if (getMessage.equalsIgnoreCase("HiBot")) {
            event.getChannel().sendMessage(messages[diceRoll(3)]).queue();
        }
    }

    public int monopolyRoll(int sides) {

        int diceRoll1 = diceRoll(sides);
        int diceRoll2 = diceRoll(sides);
        int total;
        int countRoll = 1;

        eventGlobal.getChannel().sendMessage("Dice 1 : " + diceRoll1 + "\n" + "Dice 2 : " + diceRoll2).queue();

        if (diceRoll1 == diceRoll2) {

            eventGlobal.getChannel().sendMessage("Wow It's a Rolling Double!" + "\n" + "Rolled again!").queue();

            while (diceRoll1 == diceRoll2) {

                int diceRoll3 = diceRoll(sides);
                int diceRoll4 = diceRoll(sides);

                diceRoll1 = diceRoll1 + diceRoll3;
                diceRoll2 = diceRoll2 + diceRoll4;
                countRoll += 1;

                eventGlobal.getChannel().sendMessage("Dice 1 : " + diceRoll3 + "\n" + "Dice 2 : " + diceRoll4).queue();

                if (countRoll == 2) {
                    if (diceRoll3 == diceRoll4) {
                        eventGlobal.getChannel().sendMessage("Wow! You got some luck!" + "\n" + "Rolled again!").queue();
                    }
                }

                if (countRoll > 2) {

                    if (diceRoll1 == diceRoll2) {

                        eventGlobal.getChannel().sendMessage("You choose to go to hell!").queue();
                        eventGlobal.getChannel().sendMessage("Roll Counts : " + countRoll).queue();
                        return -1;
                    }
                }
            }
        }
        total = diceRoll1 + diceRoll2;

        eventGlobal.getChannel().sendMessage("Roll Counts : " + countRoll).queue();
        return total;
    }

    public int diceRoll(int sides) {

        double diceRoll = Math.random();
        diceRoll = diceRoll * sides;
        diceRoll = diceRoll + 1;

        return (int) diceRoll;
    }
}
