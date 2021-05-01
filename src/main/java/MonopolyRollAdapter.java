
public class MonopolyRollAdapter {

    public void getMessage() {
        MonopolyRoll getMessages = new MonopolyRoll();
        String userName,
                firstRoll,
                firstDoubleRoll,
                secondRoll,
                secondDoubleRoll,
                jail,
                countRoll,
                totalRolled;

        userName = getMessages.getUserName();
        firstRoll = getMessages.getFirstRoll();
        firstDoubleRoll = getMessages.getFirstDoubleRoll();
        secondRoll = getMessages.getSecondRoll();
        secondDoubleRoll = getMessages.getSecondDoubleRoll();
        jail = getMessages.getJail();
        countRoll = getMessages.getCountRoll();
        totalRolled = getMessages.getTotalRolled();

        String messages = "```" + userName + "\n" + firstRoll + "\n" + firstDoubleRoll + "\n" + secondRoll + "\n" +
                secondDoubleRoll + "\n" + jail + "\n" + countRoll + "\n" + totalRolled + "```";

        getMessages.getEventGlobal().getChannel().sendMessage(messages).queue();
    }
}