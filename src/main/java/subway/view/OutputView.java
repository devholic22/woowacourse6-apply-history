package subway.view;

import subway.domain.dto.CommandResponse;
import java.util.List;

public interface OutputView {

    void printMainScreen(final List<CommandResponse> commands);
    void askInitCommand();
    void printExceptionMessage(final String message);
}
