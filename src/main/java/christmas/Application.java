package christmas;

import christmas.controller.EventController;
import christmas.model.policy.ChristmasPolicy;
import christmas.model.policy.DiscountPolicy;
import christmas.model.policy.SpecialDayPolicy;
import christmas.model.policy.WeekDayPolicy;
import christmas.model.policy.WeekEndPolicy;
import christmas.view.input.ConsoleInputView;
import christmas.view.input.InputView;
import christmas.view.output.ConsoleOutputView;
import christmas.view.output.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();
        List<DiscountPolicy> policies = List.of(
                ChristmasPolicy.getInstance(),
                SpecialDayPolicy.getInstance(),
                WeekDayPolicy.getInstance(),
                WeekEndPolicy.getInstance()
        );

        EventController controller = new EventController(inputView, outputView, policies);

        controller.start();
    }
}
