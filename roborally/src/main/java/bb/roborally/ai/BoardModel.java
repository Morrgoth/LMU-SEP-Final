package bb.roborally.ai;

import bb.roborally.protocol.Orientation;
import bb.roborally.protocol.Position;
import bb.roborally.protocol.map.Board;
import bb.roborally.protocol.map.Cell;
import javafx.util.Pair;

import java.util.ArrayList;

public class BoardModel {

    private final ArrayList<ArrayList<CellModel>> cells = new ArrayList<>();

    public BoardModel(Board board) {
        init(board);
        int x = 0;
        for (ArrayList<Cell> row: board.getCells()) {
            int y = 0;
            for (Cell cell: row) {
                cells.get(x).set(y, new CellModel(new Position(x, y), cell.getTiles()));
                y += 1;
            }

            x += 1;
        }
    }

    private void init(Board board) {
        for (ArrayList<Cell> row: board.getCells()) {
            ArrayList<CellModel> cellModels = new ArrayList<>();
            for (Cell ignored : row) {
                cellModels.add(null);
            }
            cells.add(cellModels);
        }
    }

    public ArrayList<ArrayList<CellModel>> getCells() {
        return cells;
    }

    public int xSize() {
        return cells.size();
    }

    public int ySize() {
        if (cells.size() > 0) {
            return cells.get(0).size();
        }
        return 0;
    }

    public CellModel get(int x, int y) {
        return cells.get(x).get(y);
    }

    public Pair<Position, Orientation> calculateNextState(Program program, Position currentPos, Orientation currentOrientation) {
        Position position = new Position(currentPos);
        Pair<Position, Orientation> currentState = new Pair<>(position, currentOrientation);
        int register = 1;
        eliminateAgains(program);
        for (CardModel card: program.getProgram()) {
            currentState = calculateNextPosition(card, register++, currentPos, currentOrientation);
        }
        return currentState;
    }

    private static void eliminateAgains(Program program) {
        for (int i = 1; i <= program.LENGTH; i++) {
            if (program.get(i).type() == CardModel.CardType.AGAIN) {
                program.set(i, program.get(i - 1));
            } else {
                program.set(i, program.get(i));
            }
        }
    }

    public Pair<Position, Orientation> calculateNextPosition(CardModel cardModel, int register, Position position, Orientation orientation) {
        if (cardModel.type() == CardModel.CardType.BACKUP) {
            return calculateBackUp(register, position, orientation);
        } else if (cardModel.type() == CardModel.CardType.MOVE1) {
            return calculateMove1(register, position, orientation);
        } else if (cardModel.type() == CardModel.CardType.MOVE2) {
            return calculateMove2(register, position, orientation);
        } else if (cardModel.type() == CardModel.CardType.MOVE3) {
            return calculateMove3(register, position, orientation);
        } else if (cardModel.type() == CardModel.CardType.TURN_LEFT) {
            return calculateTurnLeft(register, position, orientation);
        } else if (cardModel.type() == CardModel.CardType.TURN_RIGHT) {
            return calculateTurnRight(register, position, orientation);
        } else if (cardModel.type() == CardModel.CardType.U_TURN) {
            return calculateUTurn(register, position, orientation);
        } else if (cardModel.type() == CardModel.CardType.POWERUP) {
            return calculateActivation(register, position, orientation);
        } else {
            return new Pair<>(position, orientation);
        }
    }

    private Pair<Position, Orientation> calculateBackUp(int register, Position position, Orientation orientation) {

        Position nextPosition = new Position(Position.NAN, Position.NAN);
        if (orientation == Orientation.TOP) {
            nextPosition = new Position(position.getX(), position.getY() + 1);
        } else if (orientation == Orientation.RIGHT) {
            nextPosition = new Position(position.getX() - 1, position.getY());
        } else if (orientation == Orientation.BOTTOM) {
            nextPosition = new Position(position.getX(), position.getY() - 1);
        } else if (orientation == Orientation.LEFT) {
            nextPosition = new Position(position.getX() + 1, position.getY());
        }

        if (nextPosition.isValid() && isOnBoard(nextPosition)) {
            if (!isCellReachableFromDirection(nextPosition, orientation)) {
                nextPosition.set(position);
            }
            if (get(nextPosition.getX(), nextPosition.getY()).hasTile(TileModel.TileType.PIT)) {
                nextPosition.invalidate();
            }
            return calculateActivation(register, nextPosition, orientation);
        } else {
            return new Pair<>(nextPosition, orientation);
        }
    }

    private Pair<Position, Orientation> calculateMove1(int register, Position position, Orientation orientation) {

        Position nextPosition = new Position(Position.NAN, Position.NAN);
        if (orientation == Orientation.TOP) {
            nextPosition = new Position(position.getX(), position.getY() - 1);
        } else if (orientation == Orientation.RIGHT) {
            nextPosition = new Position(position.getX() + 1, position.getY());
        } else if (orientation == Orientation.BOTTOM) {
            nextPosition = new Position(position.getX(), position.getY() + 1);
        } else if (orientation == Orientation.LEFT) {
            nextPosition = new Position(position.getX() - 1, position.getY());
        }

        if (nextPosition.isValid() && isOnBoard(nextPosition)) {
            if (!isCellReachableFromDirection(nextPosition, orientation)) {
                nextPosition.set(position);
            }
            if (get(nextPosition.getX(), nextPosition.getY()).hasTile(TileModel.TileType.PIT)) {
                nextPosition.invalidate();
            }
            return calculateActivation(register, nextPosition, orientation);
        } else {
            return new Pair<>(nextPosition, orientation);
        }
    }

    private Pair<Position, Orientation> calculateMove2(int register, Position position, Orientation orientation) {
        Position nextPosition = new Position(Position.NAN, Position.NAN);
        for (int counter = 0; counter < 2; counter++) {
            if (orientation == Orientation.TOP) {
                nextPosition = new Position(position.getX(), position.getY() - 1);
            } else if (orientation == Orientation.RIGHT) {
                nextPosition = new Position(position.getX() + 1, position.getY());
            } else if (orientation == Orientation.BOTTOM) {
                nextPosition = new Position(position.getX(), position.getY() + 1);
            } else if (orientation == Orientation.LEFT) {
                nextPosition = new Position(position.getX() - 1, position.getY());
            }

            if (nextPosition.isValid() && isOnBoard(nextPosition)) {
                if (!isCellReachableFromDirection(nextPosition, orientation)) {
                    nextPosition.set(position);
                }
                if (get(nextPosition.getX(), nextPosition.getY()).hasTile(TileModel.TileType.PIT)) {
                    nextPosition.invalidate();
                }
            }
        }
        return calculateActivation(register, nextPosition, orientation);
    }

    private Pair<Position, Orientation> calculateMove3(int register, Position position, Orientation orientation) {
        Position nextPosition = new Position(Position.NAN, Position.NAN);
        for (int counter = 0; counter < 3; counter++) {
            if (orientation == Orientation.TOP) {
                nextPosition = new Position(position.getX(), position.getY() - 1);
            } else if (orientation == Orientation.RIGHT) {
                nextPosition = new Position(position.getX() + 1, position.getY());
            } else if (orientation == Orientation.BOTTOM) {
                nextPosition = new Position(position.getX(), position.getY() + 1);
            } else if (orientation == Orientation.LEFT) {
                nextPosition = new Position(position.getX() - 1, position.getY());
            }

            if (nextPosition.isValid() && isOnBoard(nextPosition)) {
                if (!isCellReachableFromDirection(nextPosition, orientation)) {
                    nextPosition.set(position);
                }
                if (get(nextPosition.getX(), nextPosition.getY()).hasTile(TileModel.TileType.PIT)) {
                    nextPosition.invalidate();
                }
            }
        }
        return calculateActivation(register, nextPosition, orientation);
    }

    private Pair<Position, Orientation> calculateTurnLeft(int register, Position position, Orientation orientation) {
        if (orientation == Orientation.TOP) {
            return calculateActivation(register, position, Orientation.LEFT);
        } else if (orientation == Orientation.RIGHT) {
            return calculateActivation(register, position, Orientation.TOP);
        } else if (orientation == Orientation.BOTTOM) {
            return calculateActivation(register, position, Orientation.RIGHT);
        } else if (orientation == Orientation.LEFT) {
            return calculateActivation(register, position, Orientation.BOTTOM);
        }
        return null;
    }

    private Pair<Position, Orientation> calculateTurnRight(int register, Position position, Orientation orientation) {
        if (orientation == Orientation.TOP) {
            return calculateActivation(register, position, Orientation.RIGHT);
        } else if (orientation == Orientation.RIGHT) {
            return calculateActivation(register, position, Orientation.BOTTOM);
        } else if (orientation == Orientation.BOTTOM) {
            return calculateActivation(register, position, Orientation.LEFT);
        } else if (orientation == Orientation.LEFT) {
            return calculateActivation(register, position, Orientation.TOP);
        }
        return null;
    }

    private Pair<Position, Orientation> calculateUTurn(int register, Position position, Orientation orientation) {
        if (orientation == Orientation.TOP) {
            return calculateActivation(register, position, Orientation.BOTTOM);
        } else if (orientation == Orientation.RIGHT) {
            return calculateActivation(register, position, Orientation.LEFT);
        } else if (orientation == Orientation.BOTTOM) {
            return calculateActivation(register, position, Orientation.TOP);
        } else if (orientation == Orientation.LEFT) {
            return calculateActivation(register, position, Orientation.RIGHT);
        }
        return null;
    }

    private boolean isOnBoard(Position position) {
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() < ySize() && position.getY() < xSize();
    }

    private boolean isCellReachableFromDirection(Position position, Orientation incoming) {
        CellModel currentCell = get(position.getX(), position.getY());
        if (currentCell.hasTile(TileModel.TileType.WALL)) {
            TileModel wall = currentCell.getTile(TileModel.TileType.WALL);
            Orientation entry = null;
            if (incoming == Orientation.LEFT) {
                entry = Orientation.RIGHT;
            } else if (incoming == Orientation.RIGHT) {
                entry = Orientation.LEFT;
            } else if (incoming == Orientation.TOP) {
                entry = Orientation.BOTTOM;
            } else if (incoming == Orientation.BOTTOM) {
                entry = Orientation.TOP;
            }
            return wall.getOrientations().contains(entry);

        }
        return true;
    }

    private Pair<Position, Orientation> calculateActivation(int register, Position position, Orientation orientation) {
        return calculateConveyorBelt(register, position, orientation);
    }

    private Pair<Position, Orientation> calculateConveyorBelt(int register, Position position, Orientation orientation) {
        CellModel currentCell = get(position.getX(), position.getY());
        if (currentCell.hasTile(TileModel.TileType.CONVEYOR_BELT)) {
            TileModel belt = currentCell.getTile(TileModel.TileType.CONVEYOR_BELT);
            int speed = belt.getSpeed();
            Orientation direction = belt.getOrientations().get(0);
            Position nextPosition = new Position(Position.NAN, Position.NAN);
            if (direction == Orientation.TOP) {
                nextPosition = new Position(position.getX(), position.getY() - speed);
            } else if (direction == Orientation.RIGHT) {
                nextPosition = new Position(position.getX() + speed, position.getY());
            } else if (direction == Orientation.BOTTOM) {
                nextPosition = new Position(position.getX(), position.getY() + speed);
            } else if (direction == Orientation.LEFT) {
                nextPosition = new Position(position.getX() - speed, position.getY());
            }
            return new Pair<>(nextPosition, orientation);
        }
        return calculatePushPanel(register, position, orientation);
    }

    private Pair<Position, Orientation> calculatePushPanel(int register, Position position, Orientation orientation) {

        CellModel currentCell = get(position.getX(), position.getY());
        if (currentCell.hasTile(TileModel.TileType.PUSH_PANEL)) {
            TileModel pushPanel = currentCell.getTile(TileModel.TileType.PUSH_PANEL);
            if (pushPanel.getRegisters().contains(register)) {
                Orientation pushDirection = pushPanel.getOrientations().get(0);
                Position nextPosition = new Position(Position.NAN, Position.NAN);
                if (pushDirection == Orientation.TOP) {
                    nextPosition = new Position(position.getX(), position.getY() - 1);
                } else if (pushDirection == Orientation.RIGHT) {
                    nextPosition = new Position(position.getX() + 1, position.getY());
                } else if (pushDirection == Orientation.BOTTOM) {
                    nextPosition = new Position(position.getX(), position.getY() + 1);
                } else if (pushDirection == Orientation.LEFT) {
                    nextPosition = new Position(position.getX() - 1, position.getY());
                }
                return new Pair<>(nextPosition, orientation);
            } else {
                return new Pair<>(position, orientation);
            }
        }
        return new Pair<>(position, orientation);
    }

    public boolean isProgramValid(Program program) {
        return !isFirstRegisterAgain(program);
    }

    private boolean isFirstRegisterAgain(Program program) {
        return program.get(1).type() == CardModel.CardType.AGAIN;
    }

    public Position findNextCheckpoint(int checkpointsReached) {
        int nextCheckpoint = checkpointsReached + 1;
        for (ArrayList<CellModel> row: cells) {
            for (CellModel cellModel: row) {
                if (cellModel.hasTile(TileModel.TileType.CHECK_POINT)) {
                    TileModel checkpoint = cellModel.getTile(TileModel.TileType.CHECK_POINT);
                    if (checkpoint.getCount() == nextCheckpoint) {
                        return cellModel.getPosition();
                    }
                }
            }
        }
        return null;
    }
}
