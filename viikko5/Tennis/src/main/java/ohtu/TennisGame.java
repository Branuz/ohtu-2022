package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        }
        else {
            player2Score += 1;
        }
    }

    public String getScore() {
        String score = "";

        if (player1Score==player2Score) {
            score = tiedGame();
        }

        else if (player1Score>=4 || player2Score>=4) {
            score = leaderScore();
        }
        else {
            score = gameScore();
        }

        return score;
    }

    private String gameScore() {
        String result = pointStrings(player1Score) + "-" + pointStrings(player2Score);
        
        return result;
    }

    private String leaderScore() {
        String leader = "";
        int difference = player1Score - player2Score;
        
        if (player1Score > player2Score) {
            leader = player1Name;   
        } else {
            leader = player2Name;
        }
        
        if (Math.abs(difference) == 1) {
            return "Advantage " + leader;
            
        } else if (Math.abs(difference) >= 2) {
            return "Win for " + leader;
        }
        
        return "";
    }

    private String tiedGame() {

        switch (player1Score) {
            case 4:
                 return "Deuce";
            default:
                 return pointStrings(player1Score) + "-All";
        }
    }

    private String pointStrings(int score) {

        switch (score) {
            case 0:
                 return "Love";
            case 1:
                 return "Fifteen";
            case 2:
                 return "Thirty";
            case 3:
                 return "Forty";
            default:
                 return "";
        }
    }
}