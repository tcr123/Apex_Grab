package com.example.loginassignment;

import java.util.ArrayList;

public class DijkstraMap {

    private static final int NO_PARENT = -1;

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    private int startVertex;
    private int nVertices;
    private double[] shortestDistances;
    private final double[][] adjacencyMatrix = {
            // SLUMLAKES
            {0,2.3,1.2,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            // CONTAINEMENT
            {2.3,0,0,0,0,0,0,0,0,0,0,2.7,0,0,0,0,0},
            // RUNOFF
            {1.2,0,0,0,1.2,0,0,0,0,0,0,0,0,0,0,0,0},
            // THE PIT
            {1,0,0,0,0,1.1,0,0,0,0,0,0,0,0,0,0,0},
            // AIRBASE
            {0,0,1.2,0,0,1.4,2.3,0,0,0,0,0,0,0,0,0,0},
            // BUNKER
            {0,0,0,1.1,1.4,0,0,2.3,0,0,0,0,0,0,0,0,0},
            // THUNDERDOME
            {0,0,0,0,2.3,0,0,1.2,0,2.8,0,0,0,0,0,0,0},
            // SKULL TOWN
            {0,0,0,0,0,2.3,1.2,0,1.3,0,0,0,0,0,0,0,0},
            // MARKET
            {0,0,0,0,0,0,0,1.3,0,0,0,2,0,0,0,0,0},
            // WATER TREATMENT
            {0,0,0,0,0,0,2.8,0,0,0,2.2,0,0,0,0,0,0},
            // REPULSOR
            {0,0,0,0,0,0,0,0,0,2.2,0,1.9,0,0,0,2.3,0},
            // THE CAGE
            {0,2.7,0,0,0,0,0,0,2,0,1.9,0,3.1,0,0,0,1.2},
            // ARTILLERY
            {0,0,0,0,0,0,0,0,0,0,0,3.1,0,3.5,0,0,0},
            // RELAY
            {0,0,0,0,0,0,0,0,0,0,0,0,3.5,0,1.7,0,0},
            // WETLANDS
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1.7,0,1.9,2.2},
            // SWAMPS
            {0,0,0,0,0,0,0,0,0,0,2.3,0,0,0,1.9,0,0},
            // HYDRO DAM
            {0,0,0,0,0,0,0,0,0,0,0,1.2,0,0,2.2,0,0}
    };
    private boolean[] added;
    private int[] parents;

    public DijkstraMap(int startVertex) {
        this.startVertex = startVertex;
        this.nVertices = adjacencyMatrix[0].length;
        this.shortestDistances = new double[nVertices];
        this.added = new boolean[nVertices];
        this.parents = new int[nVertices];
    }

    public void dijkstra()
    {
        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < this.nVertices;
             vertexIndex++)
        {
            this.shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            this.added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[this.startVertex] = 0;

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++)
        {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            double shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                if (!added[vertexIndex] &&
                        shortestDistances[vertexIndex] <
                                shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                double edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }

        // printSolution(startVertex, shortestDistances, parents);
    }

    /* debugging */

    // A utility function to print
    // the constructed distances
    // array and shortest paths
    private static void printSolution(int startVertex,
                                      double[] distances,
                                      int[] parents)
    {
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static void printPath(int currentVertex,
                                  int[] parents)
    {
        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }

    public double getDistance(int vertex) {
        return this.shortestDistances[vertex];
    }

    public void getPath(ArrayList<Location> path, int currentVertex) {
        if (currentVertex == NO_PARENT) {
            return;
        }

        getPath(path, parents[currentVertex]);
        String location = LocationKey.PointOrder(currentVertex);
        path.add(LocationKey.Coordinate(location));
    }

    public static void main(String argvs[]) {
        DijkstraMap map = new DijkstraMap(0);
        map.dijkstra();

        System.out.println();
        System.out.println(LocationKey.PointOrder(0) + " go to " +  LocationKey.PointOrder(5) + " : " + map.getDistance(5));

        ArrayList<Location> path = new ArrayList<>();
        map.getPath(path, 5);

        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i).getX() + " " + path.get(i).getY());
        }

        System.out.println();
    }
}


