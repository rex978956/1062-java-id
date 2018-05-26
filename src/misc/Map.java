package misc;

import enemy.Enemy;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import states.Game;

import java.util.ArrayList;

/**
 * Map instance
 */
public class Map implements TileBasedMap {

    /* Settings */
    private Game game;
    private String name;
    private TiledMap map;
    private Image preview;
    private ArrayList<Integer[]> waveList;
    private int[][] towerList = new int[22][15];
    private int startMoney, killMoney, waveMoney;

    /* Positions */
    private Point base;
    private ArrayList<Point> spawnList;

    /* Health settings */
    private int waveHealthMultiplier;
    private int startHpGround, startHpAir, startHpGroundBoss, startHpAirBoss;

    Map(TiledMap map, Image preview, String name,
        int startMoney, int killMoney, int waveMoney,
        int startHpGround, int startHpAir, int startHpGroundBoss, int startHpAirBoss,
        int waveHealthMultiplier, ArrayList<Point> spawnList, Point base, ArrayList<Integer[]> waveList) {
        this.preview = preview;
        this.map = map;
        this.name = name;
        this.startMoney = startMoney;
        this.killMoney = killMoney;
        this.waveMoney = waveMoney;
        this.startHpGround = startHpGround;
        this.startHpAir = startHpAir;
        this.startHpGroundBoss = startHpGroundBoss;
        this.startHpAirBoss = startHpAirBoss;
        this.waveHealthMultiplier = waveHealthMultiplier;
        this.spawnList = spawnList;
        this.base = base;
        this.waveList = waveList;
    }

    public void resetTowerList() {
        this.towerList = new int[22][15];
    }

    public Integer[] getWaveUnits(int wave) {
        return (wave <= waveList.size()) ? waveList.get(wave - 1) : null;
    }

    public void setTower(int x, int y, boolean isTower) {
        towerList[x][y] = (isTower) ? 1 : 0;
    }

    public boolean isTower(int x, int y) {
        return towerList[x][y] == 1;
    }

    /* Setter And Getter */
    // TODO: Remove Unused Functions
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public Image getPreview() {
        return preview;
    }

    public void setPreview(Image preview) {
        this.preview = preview;
    }

    public ArrayList<Integer[]> getWaveList() {
        return waveList;
    }

    public void setWaveList(ArrayList<Integer[]> waveList) {
        this.waveList = waveList;
    }

    public int[][] getTowerList() {
        return towerList;
    }

    public void setTowerList(int[][] towerList) {
        this.towerList = towerList;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }

    public int getKillMoney() {
        return killMoney;
    }

    public void setKillMoney(int killMoney) {
        this.killMoney = killMoney;
    }

    public int getWaveMoney() {
        return waveMoney;
    }

    public void setWaveMoney(int waveMoney) {
        this.waveMoney = waveMoney;
    }

    public ArrayList<Point> getSpawnList() {
        return spawnList;
    }

    public void setSpawnList(ArrayList<Point> spawnList) {
        this.spawnList = spawnList;
    }

    public Point getBase() {
        return base;
    }

    public void setBase(Point base) {
        this.base = base;
    }

    public int getWaveHealthMultiplier() {
        return waveHealthMultiplier;
    }

    public void setWaveHealthMultiplier(int waveHealthMultiplier) {
        this.waveHealthMultiplier = waveHealthMultiplier;
    }

    public int getStartHpGround() {
        return startHpGround;
    }

    public void setStartHpGround(int startHpGround) {
        this.startHpGround = startHpGround;
    }

    public int getStartHpAir() {
        return startHpAir;
    }

    public void setStartHpAir(int startHpAir) {
        this.startHpAir = startHpAir;
    }

    public int getStartHpGroundBoss() {
        return startHpGroundBoss;
    }

    public void setStartHpGroundBoss(int startHpGroundBoss) {
        this.startHpGroundBoss = startHpGroundBoss;
    }

    public int getStartHpAirBoss() {
        return startHpAirBoss;
    }

    public void setStartHpAirBoss(int startHpAirBoss) {
        this.startHpAirBoss = startHpAirBoss;
    }

    /**
     * Check if the given location is blocked, i.e. blocks movement of
     * the supplied mover.
     *
     * @param context The context describing the path finding at the time of this request
     * @param tx      The x coordinate of the tile we're moving to
     * @param ty      The y coordinate of the tile we're moving to
     * @return True if the location is blocked
     */
    @Override
    public boolean blocked(PathFindingContext context, int tx, int ty) {
        if (map.getTileId(tx, ty, 1) == 0)
            return true;
        if (towerList[tx][ty] != 0)
            return true;
        return false;
    }

    /**
     * Get the cost of moving through the given tile. This can be used to
     * make certain areas more desirable. A simple and valid implementation
     * of this method would be to return 1 in all cases.
     *
     * @param context The context describing the path finding at the time of this request
     * @param tx      The x coordinate of the tile we're moving to
     * @param ty      The y coordinate of the tile we're moving to
     * @return The relative cost of moving across the given tile
     */
    @Override
    public float getCost(PathFindingContext context, int tx, int ty) {
        return 1.0f;
    }

    /**
     * Get the height of the tile map. The slightly odd name is used
     * to distinguish this method from commonly used names in game map.
     *
     * @return The number of tiles down the map
     */
    @Override
    public int getHeightInTiles() {
        return map.getHeight();
    }

    /**
     * Get the width of the tile map. The slightly odd name is used
     * to distinguish this method from commonly used names in game map.
     *
     * @return The number of tiles across the map
     */
    @Override
    public int getWidthInTiles() {
        return map.getWidth();
    }

    public TiledMap getMap() {
        return map;
    }

    /**
     * Notification that the path finder visited a given tile. This is
     * used for debugging new heuristics.
     *
     * @param x The x coordinate of the tile that was visited
     * @param y The y coordinate of the tile that was visited
     */
    @Override
    public void pathFinderVisited(int x, int y) {
    }

    public ArrayList<Enemy> getEntityList(int wave) {
        return null;
    }
}

