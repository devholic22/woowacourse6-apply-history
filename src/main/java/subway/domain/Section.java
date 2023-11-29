package subway.domain;

import subway.domain.repository.StationRepository;

public class Section {

    private final Station startStation;
    private final Station endStation;
    private final int duration;
    private final int time;

    public Section(final Station startStation, final Station endStation, final int duration, final int time) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.duration = duration;
        this.time = time;
    }

    public static Section withValues(final String startStation, final String endStation,
                                     final int duration, final int time) {
        Station start = StationRepository.findByName(startStation);
        Station end = StationRepository.findByName(endStation);
        return new Section(start, end, duration, time);
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public int getDuration() {
        return duration;
    }

    public int getTime() {
        return time;
    }
}
