package baseball.umpire;

import baseball.AppConfig;
import baseball.game.Score;
import baseball.game.umpire.Umpire;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static baseball.AppConfig.DIGIT_NUM;

public class UmpireTest {

    Umpire umpire;

    @BeforeEach
    public void BeforeEach(){
        AppConfig appConfig = new AppConfig();
        umpire = appConfig.umpire();
    }

    @Test
    void umpireStrikeCheck(){
        //given
        String ball = "123";
        String batt = "153";
        int compareCount = 0;
        int strikeCount = 0;
        Boolean[] strikeCheckingBoard = new Boolean[DIGIT_NUM];
        Arrays.fill(strikeCheckingBoard, false);

        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(ball.charAt(i) == batt.charAt(i)){
                compareCount++;
            }
        }
        //when
        strikeCheckingBoard = umpire.checkStrike(ball, batt, strikeCheckingBoard);
        //then
        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(strikeCheckingBoard[i] == true){
                strikeCount++;
            }
        }
        Assertions.assertThat(strikeCount).isEqualTo(compareCount);
    }

    @Test
    void umpireBallCheck(){
        //given
        String ball = "011";
        String bat = "110";
        int compareCount = 0;
        int ballCount = 0;

        Boolean[] strikeCheckingBoard = new Boolean[DIGIT_NUM];
        Arrays.fill(strikeCheckingBoard, false);
        Boolean[] ballCheckingBoard = new Boolean[DIGIT_NUM];
        Arrays.fill(ballCheckingBoard, false);
        Boolean[] compareCheckingBoard = new Boolean[DIGIT_NUM];
        Arrays.fill(compareCheckingBoard, false);

        strikeCheckingBoard = umpire.checkStrike(ball, bat, strikeCheckingBoard);

        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(strikeCheckingBoard[i] == true){continue;}
            for(int j = 0 ; j < DIGIT_NUM ; j++){
                if(strikeCheckingBoard[j] == true){continue;}
                if(compareCheckingBoard[j] == true){continue;}
                if(ball.charAt(i) == bat.charAt(j)){
                    compareCheckingBoard[j] = true;
                    break;
                }
            }
        }

        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(compareCheckingBoard[i] == true){
                compareCount++;
            }
        }

        //when
        ballCheckingBoard = umpire.checkBall(ball, bat, strikeCheckingBoard, ballCheckingBoard);

        //then
        for(int i = 0 ; i < DIGIT_NUM ; i++){
            if(ballCheckingBoard[i] == true){
                ballCount++;
            }
        }
        Assertions.assertThat(ballCount).isEqualTo(compareCount);
    }

    @Test
    void umpireHomerunCheck(){}

    @Test
    void umpireNothingCheck(){}

    @Test
    void umpireJudgeCheck(){}
}
