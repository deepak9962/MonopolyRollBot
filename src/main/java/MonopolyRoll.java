import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class MonopolyRoll extends ListenerAdapter {
    public static GuildMessageReceivedEvent eventGlobal;
    private static String mUserName;
    private static String mFirstRoll;
    private static String mFirstDoubleRoll;
    private static String mSecondRoll;
    private static String mSecondDoubleRoll;
    private static String mJail;
    private static String mCountRoll;
    private static String mTotalRolled;

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

            String userName = "Roller Name : " + Objects.requireNonNull(event.getMember()).getUser().getName();
            String rolledTotal = "Total : " + monopolyRoll(6);

            getMessages(userName, rolledTotal);
            MonopolyRollAdapter monopolyRollAdapter = new MonopolyRollAdapter();
            monopolyRollAdapter.getMessage();
        }

        if (getMessage.equalsIgnoreCase("!help")) {
            event.getChannel().sendMessage("Just type !roll to activate me -_-, ugh! such headache.").queue();
        }

        if (getMessage.equalsIgnoreCase("!invite")) {
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

        String firstRoll = "";
        String firstDoubleRoll = "";
        String secondRoll = "";
        String secondDoubleRoll = "";
        String jail = "";
        String rollCount = "";

        firstRoll = "Dice 1 : " + diceRoll1 + "\n" + "Dice 2 : " + diceRoll2;

        if (diceRoll1 == diceRoll2) {

            firstDoubleRoll = "Wow It's a Rolling Double!" + "\n" + "Rolled again!";

            while (diceRoll1 == diceRoll2) {

                int diceRoll3 = diceRoll(sides);
                int diceRoll4 = diceRoll(sides);

                diceRoll1 = diceRoll1 + diceRoll3;
                diceRoll2 = diceRoll2 + diceRoll4;
                countRoll += 1;

                secondRoll = "Dice 1 : " + diceRoll3 + "\n" + "Dice 2 : " + diceRoll4;

                if (countRoll == 2) {
                    if (diceRoll3 == diceRoll4) {
                        secondDoubleRoll = "Wow! You got some luck!" + "\n" + "Rolled again!";
                    }
                }

                if (countRoll > 2) {

                    if (diceRoll1 == diceRoll2) {

                        jail = "You choose to go to hell!";
                        rollCount = "Roll Counts : " + countRoll;
                        return -1;
                    }
                }
            }
        }
        total = diceRoll1 + diceRoll2;

        rollCount = "Roll Counts : " + countRoll;
        getMessages(firstRoll, firstDoubleRoll, secondRoll, secondDoubleRoll, jail, rollCount);

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

    public static void getMessages(String userName, String totalRolled) {
        mUserName = userName;
        mTotalRolled = totalRolled;
    }

    public static void getMessages(String firstRoll,
                                   String firstDoubleRoll,
                                   String secondRoll,
                                   String secondDoubleRoll,
                                   String jail,
                                   String countRoll) {

        mFirstRoll = firstRoll;
        mFirstDoubleRoll = firstDoubleRoll;
        mSecondRoll = secondRoll;
        mSecondDoubleRoll = secondDoubleRoll;
        mJail = jail;
        mCountRoll = countRoll;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getFirstRoll() {
        return mFirstRoll;
    }

    public String getFirstDoubleRoll() {
        return mFirstDoubleRoll;
    }

    public String getSecondRoll() {
        return mSecondRoll;
    }

    public String getSecondDoubleRoll() {
        return mSecondDoubleRoll;
    }

    public String getJail() {
        return mJail;
    }

    public String getCountRoll() {
        return mCountRoll;
    }

    public String getTotalRolled() {
        return mTotalRolled;
    }

    public GuildMessageReceivedEvent getEventGlobal() {
        return eventGlobal;
    }
}
