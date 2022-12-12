package application.model;

public enum PieceType {
    RED(1), WHITE(-1);

    public final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}