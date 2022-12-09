package application;

public enum PieceType {
    RED(1, false), WHITE(-1, false);

    final int moveDir;
    boolean isKing;

    PieceType(int moveDir, boolean isKing) {
        this.moveDir = moveDir;
        this.isKing = isKing;
    }
}