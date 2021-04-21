package chess.service;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.repository.ChessGameRepository;
import dto.ChessGameDto;
import dto.MoveDto;
import dto.RoomRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChessGameServiceImpl implements ChessGameService {
    private final ChessGameRepository chessGameRepository;

    public ChessGameServiceImpl(final ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    @Override
    public ChessGameDto move(final Long gameId, final MoveDto moveDto) {
        final ChessGame chessGame = chessGameRepository.chessGame(gameId);

        if (chessGame.move(Position.of(moveDto.getFrom()), Position.of(moveDto.getTo()))) {
            chessGameRepository.save(gameId, chessGame, moveDto);
            return new ChessGameDto(gameId, chessGame);
        }

        throw new IllegalArgumentException("이동할 수 없습니다.");
    }
}
