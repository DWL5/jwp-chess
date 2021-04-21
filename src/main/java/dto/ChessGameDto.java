package dto;

import chess.domain.ChessGame;

public class ChessGameDto {
    private final Long id;
    private final TeamDto whiteTeam;
    private final TeamDto blackTeam;
    private final boolean isEnd;

    public ChessGameDto(Long id, ChessGame chessGame) {
        this.id = id;
        this.whiteTeam = new TeamDto(chessGame.getWhiteTeam());
        this.blackTeam = new TeamDto(chessGame.getBlackTeam());
        this.isEnd = chessGame.isEnd();
    }

    public Long getId() {
        return id;
    }

    public TeamDto getBlackTeam() {
        return blackTeam;
    }

    public TeamDto getWhiteTeam() {
        return whiteTeam;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
