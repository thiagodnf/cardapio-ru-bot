package thiagodnf.cardapioru.bot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class CommandArgs {
	
	private static Pattern patternForText = Pattern.compile("^\\/([^\\s]+)\\s?(.+)?");
	
	private static String validArguments = "[^a-zA-Z\\s,çÁÉÍÓÚÀÂÊÔÃÕáéíóúàâêôãõ-]";
	
	private String raw;
	
	private String command;
	
	private List<String> args;
	
	public CommandArgs(String raw, String command, List<String> args) {
		this.raw = raw;
		this.command = command;
		this.args = args;
	}

	public CommandArgs(String raw) {
		this(raw, null, new ArrayList<>());
	}

	public String getRaw() {
		return raw;
	}

	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}

	public List<String> getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return "CommandArgs [raw=" + raw + ", command=" + command + ", args=" + args + "]";
	}

	public static CommandArgs parse(String text) {
		
		text = text.toLowerCase();
		
		String command = null;
		List<String> args = new ArrayList<>();
		
		if (text.startsWith("/")) {
			
			// Now create matcher object.
	        Matcher m = patternForText.matcher(text);
	        
	        if (m.matches()) {
	        	command = m.group(1);
	        	
        		String arguments = m.group(2);
        		
        		if(StringUtils.isNotBlank(arguments)) {
        			arguments = arguments.replaceAll(validArguments, "");
	        		
	        		List<String> parts = Arrays.asList(arguments.split(","));
	        		
	        		args = parts
	        			.stream()
	        			.filter(StringUtils::isNotBlank)
	        			.map(String::trim)
	        			.collect(Collectors.toList());
        		}
	        }	        
			
		}
		
		return new CommandArgs(text, command, args);
	}

	public void clearUsernameFromCommand(String botUserName) {
		this.command = this.command.replaceAll("@" + botUserName.toLowerCase(), "");
	}
}
