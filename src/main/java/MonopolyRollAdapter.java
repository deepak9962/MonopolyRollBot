
public class MonopolyRollAdapter {

    public void getMessage() {
        MonopolyRoll getMessages = new MonopolyRoll();
        String userName,
                firstRoll,
                firstDoubleRoll,
                secondRoll,
                secondDoubleRoll,
                thirdRoll,
                jail,
                countRoll,
                totalRolled;

        userName = getMessages.getUserName();
        firstRoll = getMessages.getFirstRoll();
        firstDoubleRoll = getMessages.getFirstDoubleRoll();
        secondRoll = getMessages.getSecondRoll();
        secondDoubleRoll = getMessages.getSecondDoubleRoll();
        thirdRoll = getMessages.getSecondRoll();
        jail = getMessages.getJail();
        countRoll = getMessages.getCountRoll();
        totalRolled = getMessages.getTotalRolled();

        String messages = "```" + "\n" + userName + "\n" + firstRoll + "\n" + firstDoubleRoll + "\n" + secondRoll + "\n" +
                secondDoubleRoll + "\n" + thirdRoll + "\n" + jail + "\n" + countRoll + "\n" + totalRolled + "\n" + "```";

        getMessages.getEventGlobal().getChannel().sendMessage(messages).queue();
    }
}
