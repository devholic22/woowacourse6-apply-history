package subway.domain;

import static subway.domain.command.PathCommand.MINIMUM_DISTANCE;
import static subway.domain.command.PathCommand.MINIMUM_TIME;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.command.PathCommand;
import subway.domain.repository.SectionRepository;
import subway.domain.repository.StationRepository;
import java.util.List;

public class PathManager {

    private final WeightedMultigraph<String, DefaultWeightedEdge> graph;

    private PathManager(final WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    public static PathManager createDefault() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            graph.addVertex(station.getName());
        }
        return new PathManager(graph);
    }

    public void findPath(final Station startStation, final Station endStation, final PathCommand condition) {
        if (condition == MINIMUM_DISTANCE) {
            initWeightWithDuration();
        }
        if (condition == MINIMUM_TIME) {
            initWeightWithTime();
        }
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List vertexList = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList();
        // System.out.println("vertexList = " + vertexList);
    }

    private void initWeightWithDuration() {
        List<Section> sections = SectionRepository.sections();
        for (Section section : sections) {
            DefaultWeightedEdge edge = graph.addEdge(section.getStartStation(), section.getEndStation());
            graph.setEdgeWeight(edge, section.getDistance());
        }
    }

    private void initWeightWithTime() {
        List<Section> sections = SectionRepository.sections();
        for (Section section : sections) {
            DefaultWeightedEdge edge = graph.addEdge(section.getStartStation(), section.getEndStation());
            graph.setEdgeWeight(edge, section.getTime());
        }
    }
}
