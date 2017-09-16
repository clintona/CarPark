package au.com.tla.carpark.command;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        String crlf = System.lineSeparator();
        StringBuilder buf = new StringBuilder("Available commands are:");
        buf.append(crlf).append("ENTER {CAR | TRUCK}    (returns an ID needed for EXIT)");
        buf.append(crlf).append("EXIT {CAR | TRUCK} {ID} {duration in hours}");
        buf.append(crlf).append("REPORT");
        buf.append(crlf).append("HELP");
        buf.append(crlf).append("QUIT").append(crlf);
        System.out.println(buf);
    }
}
