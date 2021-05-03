package chess.repository;

import chess.dao.GameDao;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameRepository;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.dto.request.GameMoveRequest;
import org.springframework.stereotype.Repository;


@Repository
public class ChessGameRepositoryImpl implements ChessGameRepository {

    private final GameDao gameDao;
    private final TeamRepositoryImpl teamRepositoryImpl;

    public ChessGameRepositoryImpl(final GameDao gameDao, final TeamRepositoryImpl teamRepositoryImpl) {
        this.gameDao = gameDao;
        this.teamRepositoryImpl = teamRepositoryImpl;
    }


    @Override
    public Long create(final ChessGame chessGame) {
        Long gameId = gameDao.create(chessGame);
        teamRepositoryImpl.create(chessGame.getWhiteTeam(), gameId);
        teamRepositoryImpl.create(chessGame.getBlackTeam(), gameId);
        return gameId;
    }

    @Override
    public ChessGame chessGame(final Long gameId) {
        boolean isEnd = gameDao.isEnd(gameId);
        WhiteTeam whiteTeam = teamRepositoryImpl.whiteTeam(gameId);
        BlackTeam blackTeam = teamRepositoryImpl.blackTeam(gameId);
        whiteTeam.setEnemy(blackTeam);
        blackTeam.setEnemy(whiteTeam);

        return new ChessGame(whiteTeam, blackTeam, isEnd);
    }

    @Override
    public void save(final Long gameId, final ChessGame chessGame, final GameMoveRequest moveDto) {
        boolean isEnd = chessGame.isEnd();

        if (isEnd) {
            gameDao.update(gameId, isEnd);
        }

        teamRepositoryImpl.update(gameId, chessGame.getWhiteTeam());
        teamRepositoryImpl.update(gameId, chessGame.getBlackTeam());
        teamRepositoryImpl.move(gameId, moveDto);
    }
}
