package amethyst.logger;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLogger implements ModInitializer {

	public static final String MOD_ID = "randomlogger";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private final Random random = new Random();

	@Override
	public void onInitialize() {

		boolean beyblade = random.nextInt(100) == 0;

		String file = beyblade ? "beyblade.txt" : "logsnormal.txt";
		List<String> messages = loadMessages(file);


			LOGGER.error("No beyblades detected :( deploying spinblades instead...");
			LOGGER.error("Spinblades deployed! sending beyblades into void...");
			LOGGER.warn("beyblades have been sent to the void");
			LOGGER.warn("FUCK IT WE B E Y B L A D E- *EXPLOSION.MP3*");

		String message = messages.get(random.nextInt(messages.size()));

		if (beyblade) {
			LOGGER.error(message);
		} else {
			LOGGER.info(message);
		}
	}

	private List<String> loadMessages(String fileName) {

		List<String> messages = new ArrayList<>();

		try {
			InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);

			if (stream == null)
				return messages;

			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			String line;

			while ((line = reader.readLine()) != null) {
				if (!line.isBlank())
					messages.add(line);
			}

			reader.close();

		} catch (Exception e) {
			LOGGER.error("Failed to load {}", fileName, e);
		}

		return messages;
	}
}