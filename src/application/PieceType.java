package application;

public enum PieceType {
    RED(1, 0), WHITE(-1, 0);

    final int moveDir;
    int isKing;

    PieceType(int moveDir, int isKing) {
        this.moveDir = moveDir;
        this.isKing = isKing;
    }
}