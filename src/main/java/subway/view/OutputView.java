package subway.view;

import subway.domain.dto.CommandResponse;
import java.util.List;

public interface OutputView {

    void printMainScreen(final List<CommandResponse> commands);
    void askCommand();
    void printPathCommands(final List<CommandResponse> commands);
    void askStartStation();
    void askEndStation();
    void printExceptionMessage(final String message);
}
