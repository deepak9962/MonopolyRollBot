import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import javax.security.auth.login.LoginException;
import java.util.Objects;

public class MonopolyRoll extends ListenerAdapter {
    public static GuildMessageReceivedEvent eventGlobal;
    private String prefix = "!";

    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault(System.getenv().get("TOKEN")).build();
        jda.addEventListener(new MonopolyRoll());

        try {
            while (true) {
                Thread.sleep(60 * 15000);
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
        if (getMessage.equalsIgnoreCase(prefix + "roll")) {

            event.getChannel().sendMessage("Roller Name : " + Objects.requireNonNull(event.getMember()).getUser().getName()).queue();
            event.getChannel().sendMessage("Total : " + monopolyRoll(6)).queue();

        }

        if (getMessage.equalsIgnoreCase(prefix + "guessTheNumber")) {
            guessTheNumber();
        }
        
        if (getMessage.equalsIgnoreCase(prefix + "help")) {
            event.getChannel().sendMessage("Just type !roll or !guessTheNumber to activate me -_-, ugh! such headache.").queue();
        }
        
        if (getMessage.equalsIgnoreCase(prefix + "invite")) {
            event.getChannel().sendMessage(System.getenv().get("INVITE")).queue();
        }

        if (getMessage.equalsIgnoreCase("HiBot")) {
            event.getChannel().sendMessage(messages[randomMessages(3)]).queue();
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

    public int randomMessages(int arraySize) {

        double randomNumber = Math.random();
        randomNumber = randomNumber * arraySize;

        return (int) randomNumber;
    }

    public void guessTheNumber() {

        boolean hasWin = false;
        eventGlobal.getChannel().sendMessage("Hi! I have guess the random number for you! \n Try to guess it.").queue();
        int randomNumber = (int) (Math.random() * 100) + 1;

        for (int i = 10; i > 0; i--) {

            int guess;
            eventGlobal.getChannel().sendMessage("You have " + i + " guess(es) left.").queue();
            String getInput = eventGlobal.getMessage().getContentRaw();
            guess = Integer.parseInt(getInput);

            if (randomNumber < guess) {
                eventGlobal.getChannel().sendMessage("Number is less than " + guess + " Try again.").queue();
            } else if (randomNumber > guess) {
                eventGlobal.getChannel().sendMessage("Number is greater than " + guess + " Try again.").queue();
            } else {
                hasWin = true;
                break;
            }
        }
        if (hasWin) {
            eventGlobal.getChannel().sendMessage("CORRECT... YOU WON! '-_-'").queue();
        } else {
            eventGlobal.getChannel().sendMessage("GAME OVER! YOU LOSE! HAHA").queue();
            eventGlobal.getChannel().sendMessage("The number was " + randomNumber).queue();
        }
    }
}
