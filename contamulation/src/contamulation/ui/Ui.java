package contamulation.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import contamulation.app.simulation.Simulation;
import contamulation.tools.Lang;

public class Ui
{
	/**
	 * The main entry point of the application, unless --no-ui was specified.
	 */
	public static void run()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(Lang.string(Lang.Key.WELCOME));
		System.out.println(Lang.string(Lang.Key.SELECT_FIRST));
		selectFile(sc);
		
		boolean exit = false;
		while(!exit)
		{
			System.out.println(Lang.string(Lang.Key.SELECT_ACTION));
			System.out.println("1. " + Lang.string(Lang.Key.RUN_SIM));
			System.out.println("2. " + Lang.string(Lang.Key.CONFIGURE_SIM));
			System.out.println("3. " + Lang.string(Lang.Key.EDIT_SIM));
			
			System.out.println("0. " + Lang.string(Lang.Key.EXIT));
			switch(sc.nextLine())
			{
			case "1": UiSim.open(sc); break;
			case "2": UiConfig.open(sc); break;
			case "3": UiEdit.open(sc); break;			

			case "0": exit = true; break;
			default: System.out.println(Lang.string(Lang.Key.INVALID_OPTION)); break;
			}
		}
		sc.close();
	}
	
	/**
	 * Routine dedicated to loading the working directory.
	 * @param sc scanner used by the runtime.
	 */
	private static void selectFile(Scanner sc)
	{
		System.out.println(Lang.string(Lang.Key.DIR_PATH));
		System.out.println(Lang.string(Lang.Key.USE_NEWLINE));
		String str = sc.nextLine();
		Path dir = Paths.get(str);
		while(!dir.toFile().exists() || !dir.toFile().isDirectory())
		{
			System.out.println(Lang.string(Lang.Key.INVALID_PATH));
			System.out.println(Lang.string(Lang.Key.DIR_PATH));

			str = sc.nextLine();
			dir = Paths.get(str);
		}
		Path prop = Paths.get(dir.toString(), "sim.properties");
		Properties properties = new Properties();
		try
		{
			if(prop.toFile().exists())
				properties.load(Files.newBufferedReader(prop));
		}
		catch (IOException e)
		{
			System.out.println(Lang.string(Lang.Key.INVALID_FILE));
		}
		Simulation.INSTANCE.loadConfig(prop.getParent(), properties);
	}
	
//	private static void newSim(Properties prop)
//	{
//		prop.put("disease-dir", "disease");
//		prop.put("disease-format", ".csv");
//		prop.put("city-size", "city-size.curve.csv");
//		prop.put("jobs", "list.jobs.csv");
//		prop.put("jobs-distribution", "jobs-distribution.curve.csv");
//		prop.put("buildings", "list.buildings.csv");
//		prop.put("buildings-distribution", "buildings-distribution.curve.csv");
//		prop.put("behavior", "list.behavior.csv");
//		prop.put("behavior-dir", "behavior");
//		prop.put("behavior-format", ".csv");
//	}
	
//	private static Supplier<String> makeRbGetter(String key)
//	{
//		return () -> Lang.string(key);
//	}
//	
//	private static Consumer<Scanner> actionCloseMenu(Menu menu)
//	{
//		return (sc) -> menu.close();
//	}
//	
//	private static Consumer<Scanner> actionOpenMenu()
//	{
//		return (sc) -> Ui.current.close();
//	}
	
//	private Consumer<scanner> actionOpenMenu(M)
}
