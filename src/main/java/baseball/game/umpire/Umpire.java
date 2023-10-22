package baseball.game.umpire;

import baseball.game.Score;

public interface Umpire {
    Boolean[] checkStrike(String ball, String bat, Boolean[] strikeCheckingBoard);
    Boolean[] checkBall(String ball, String bat, Boolean[] ballCheckingBoard);
    Boolean checkHomerun(Boolean[] strikeCheckingBoard);
    Boolean checkNothing(Boolean[] strikeCheckingBoard, Boolean[] ballCheckingBoard);
    Score judge(
            Boolean[] strikeCheckingBoard,
            Boolean[] ballCheckingBoard,
            Boolean homerunCount,
            Boolean nothingCount
    );
}
