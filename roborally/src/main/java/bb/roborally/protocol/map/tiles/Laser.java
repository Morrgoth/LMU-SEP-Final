
package bb.roborally.protocol.map.tiles;

import bb.roborally.protocol.Orientation;

import java.util.ArrayList;

/**
 * @author Philipp Keyzman
 */
public class Laser extends Tile {
    private int count;

    public Laser() {}


    public Laser(String isOnBoard, ArrayList < Orientation > orientations, int count){
            this.setIsOnBoard(isOnBoard);
            this.setOrientations(orientations);
            this.count = count;
        }

        @Override
        public String getType () {
            return "Laser";
        }

        public int getCount () {
            return count;
        }

        public void setCount ( int count){
            this.count = count;
        }
    }


