package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTE_ID;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddOrderToRouteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddOrderToRouteCommandParser implements Parser<AddOrderToRouteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderToRouteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTE_ID, PREFIX_ORDER_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROUTE_ID, PREFIX_ORDER_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderToRouteCommand.MESSAGE_USAGE));
        }

        Index routeId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ROUTE_ID).get());
        Index orderId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ORDER_ID).get());

        // TODO: Fetch the route and order from here

        return new AddOrderToRouteCommand(routeId, orderId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
