/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.map.pathing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.util.geometry.Point3D;

/**
 * This {@link PathingService} implementation uses the A* algorithm to determine
 * the best path possible.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ CharacterService.class, WorldService.class })
public class AStarPathingService extends AbstractService implements
		PathingService {
	/**
	 * The logger
	 */
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Path findPath(PositionableObject object, Point3D point) {
		return null;
	}

	// public class Position {
	// private double x;
	//
	// private double y;
	// }
	//
	// public class Node {
	//
	// protected String id;
	// }
	//
	// public class Edge {
	//
	// protected String from;
	//
	// protected String to;
	//
	// }
	//
	// public class Adjacency<N extends Node> {
	// protected N node;
	// protected Set<N> neighbors;
	// }
	//
	// public class Graph<N extends Node, E extends Edge> {
	//
	// protected List<N> nodeList;
	//
	// protected List<E> edgeList;
	//
	// // Index for fast access
	// private Map<String, Adjacency<N>> adjacency;
	//
	// // directed graph or not
	// protected boolean diGraph;
	// }
	//
	// public class NavNode extends Node {
	// protected Position position;
	// protected List<String> extraData;
	// }
	//
	// public class NavEdge extends Edge {
	// protected double cost;
	// }
	//
	// public class NavGraph extends Graph<NavNode, NavEdge> {
	//
	// public void addConnection(String firstId, String secondId) {
	// NavNode node1 = this.getNode(firstId);
	// NavNode node2 = this.getNode(secondId);
	// if (node1 != null && node2 != null) {
	// double cost = this.calcManhattanDistance(node1, node2);
	// NavEdge edge1 = new NavEdge(firstId, secondId, cost);
	// NavEdge edge2 = new NavEdge(secondId, firstId, cost);
	// this.addEdge(edge1);
	// this.addEdge(edge2);
	// }
	// }
	//
	// public void removeConnection(String firstId, String secondId) {
	// NavEdge edge1 = new NavEdge(firstId, secondId);
	// NavEdge edge2 = new NavEdge(secondId, firstId);
	// this.removeEdge(edge1);
	// this.removeEdge(edge2);
	// }
	//
	// public double calcManhattanDistance(NavNode a, NavNode b) {
	// return abs(a.getPosition().getX() - b.getPosition().getX())
	// + abs(a.getPosition().getY() - b.getPosition().getY());
	// }
	// }
	//
	// public class NavGraphLoader {
	//
	// public NavGraphData load(String filePath) {
	// try {
	// String json = this.readFileAsString(filePath);
	// JSONReader reader = new JSONReader();
	// Map map = (Map) reader.read(json);
	// NavGraphData data = new NavGraphData();
	// data.fromJSON(map);
	//
	// return data;
	// } catch (IOException e) {
	// throw new RuntimeException("Cannot read file " + filePath);
	// }
	// }
	//
	// String readFileAsString(String filePath) throws java.io.IOException {
	// BufferedReader reader = new BufferedReader(new InputStreamReader(
	// this.getClass().getResourceAsStream(filePath)));
	// StringBuffer sb = new StringBuffer(4096);
	//
	// String line = reader.readLine();
	// while (line != null) {
	// sb.append(line);
	// line = reader.readLine();
	// }
	//
	// reader.close();
	//
	// return sb.toString();
	// }
	// }
	//
	// public class NavNodeData {
	// private int min;
	// private int max;
	// private NavNode node;
	//
	// public NavNodeData() {
	// }
	//
	// public NavNodeData(int min, int max, NavNode node) {
	// this.min = min;
	// this.max = max;
	// this.node = node;
	// }
	//
	// public int getMin() {
	// return min;
	// }
	//
	// public void setMin(int min) {
	// this.min = min;
	// }
	//
	// public int getMax() {
	// return max;
	// }
	//
	// public void setMax(int max) {
	// this.max = max;
	// }
	//
	// public NavNode getNode() {
	// return node;
	// }
	//
	// public void setNode(NavNode node) {
	// this.node = node;
	// }
	// }
	//
	// public class MatrixPosition {
	//
	// private int row;
	// private int column;
	// }
}
