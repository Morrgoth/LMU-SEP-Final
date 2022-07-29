package bb.roborally.ai;

import bb.roborally.protocol.Position;
import bb.roborally.protocol.map.tiles.Tile;

import java.util.ArrayList;

/**
 *
 * @author Bence Ament
 */
public class CellModel {

    private final ArrayList<TileModel> tiles = new ArrayList<>();
    private final Position position;

    public CellModel(Position position, ArrayList<Tile> oTiles) {
        this.position = position;
        for (Tile tile: oTiles) {
            tiles.add(new TileModel(tile));
        }
    }

    public void add(TileModel tile) {
        tiles.add(tile);
    }

    public void clear() {
        tiles.clear();
    }

    public ArrayList<TileModel> getTiles() {
        return tiles;
    }

    public TileModel getTile(int i) {
        if (i < tiles.size()) {
            return tiles.get(i);
        }
        return null;
    }

    public TileModel getTile(TileModel.TileType type) {
        for (TileModel tileModel: tiles) {
            if (type == tileModel.getType()) {
                return tileModel;
            }
        }
        return null;
    }

    public boolean hasTile(TileModel.TileType type) {
        return getTile(type) != null;
    }

    public Position getPosition() {
        return position;
    }
}
