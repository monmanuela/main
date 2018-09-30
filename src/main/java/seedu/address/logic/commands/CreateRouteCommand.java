package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

public class CreateRouteCommand extends RouteCommand {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an empty route.\n"
            + "Example: /route " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Empty route created.";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        return new CommandResult("Executed create route command.");
    }

}
