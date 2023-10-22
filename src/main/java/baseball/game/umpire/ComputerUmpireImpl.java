package baseball.game.umpire;

import baseball.game.Score;

import java.util.Arrays;

import static baseball.AppConfig.DIGIT_NUM;

public class ComputerUmpireImpl implements Umpire {

    Boolean[] strikeCheckingBoard = new Boolean[DIGIT_NUM];
    Boolean[] ballCheckingBoard = new Boolean[DIGIT_NUM];
    Boolean homerunChecker = false;
    Boolean nothingChecker = false;

    public ComputerUmpireImpl() {
        Arrays.fill(strikeCheckingBoard, false);
        Arrays.fill(ballCheckingBoard, false);
    }

    @Override
    public Boolean[] checkStrike(String ball, String bat, Boolean[] strikeCheckingBoard) {
        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(ball.charAt(i) == bat.charAt(i)){
                strikeCheckingBoard[i] = true;
            }
        }
        return strikeCheckingBoard;
    }

    @Override
    public Boolean[] checkBall(String ball, String bat, Boolean[] strikeCheckingBoard, Boolean[] ballCheckingBoard) {

        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(strikeCheckingBoard[i] == true){continue;}
            for(int j = 0 ; j < DIGIT_NUM ; j++){
                if(strikeCheckingBoard[j] == true){continue;}
                if(ballCheckingBoard[j] == true){continue;}
                if(ball.charAt(i) == bat.charAt(j)){
                    ballCheckingBoard[j] = true;
                    break;
                }
            }
        }
        return ballCheckingBoard;
    }

    @Override
    public Score judge(Boolean[] strikeCheckingBoard, Boolean[] ballCheckingBoard, Score score) {
        int strike = 0;
        int ball = 0;
        boolean homerun = false;
        boolean nothing = false;

        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(strikeCheckingBoard[i] == true){
                strike++;
            }
        }
        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(ballCheckingBoard[i] == true){
                ball++;
            }
        }
        if(strike == DIGIT_NUM){
            homerun = true;
        }
        if(strike == 0 && ball == 0){
            nothing = true;
        }

        score.setStrikeCount(strike);
        score.setBallCount(ball);
        score.setHomerunCount(homerun);
        score.setNothingCount(nothing);

        return score;
    }
}
