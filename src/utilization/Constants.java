package utilization;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayersConstants {
        public static final int WALK = 1;
        public static final int RUNNING = 2;
        public static final int ATTACK = 3;
        public static final int CLIMB = 4;
        public static final int IDLE = 5;
        public static final int PUSH = 6;
        public static final int JUMP = 7;
        public static final int WALK_ATTACK = 8;
        public static final int HURT = 9;
        public static final int DEATH = 10;


        public static int getSpriteAmount(int player_action) {
            switch (player_action) {
                case WALK, RUNNING, PUSH, WALK_ATTACK:
                    return 5;
                case ATTACK, CLIMB, IDLE, HURT:
                    return 3;
                case JUMP, DEATH:
                    return 7;
                default:
                    return 1;
            }
        }
    }
}
