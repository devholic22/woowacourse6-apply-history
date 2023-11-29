package subway.domain;

import subway.domain.repository.StationRepository;

public class Section {

    private final Station startStation;
    private final Station endStation;
    private final int distance;
    private final int time;

    public Section(final Station startStation, final Station endStation, final int distance, final int time) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.distance = distance;
        this.time = time;
    }

    public static Section withValues(final String startStation, final String endStation,
                                     final int duration, final int time) {
        Station start = StationRepository.findByName(startStation);
        Station end = StationRepository.findByName(endStation);
        return new Section(start, end, duration, time);
    }

    public String getStartStation() {
        return startStation.getName();
    }

    public String getEndStation() {
        return endStation.getName();
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
