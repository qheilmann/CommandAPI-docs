package test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import createcommands.functionsandtags.Main;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIVersionHandler;
import dev.jorel.commandapi.MockCommandAPIBukkit;
import dev.jorel.commandapi.MockCommandAPIPlugin;
import org.junit.jupiter.api.BeforeEach;

class LoadMockCommandAPI {
    test
    // #region loadMockCommandAPIExample
    @BeforeEach
    public void setUp() {
        // Set up MockBukkit server
        ServerMock server = MockBukkit.mock();

        // Load the CommandAPI plugin
        MockCommandAPIPlugin.load(config -> config
            .missingExecutorImplementationMessage("This command cannot be run by %S")
        );

        // Load our plugin
        MockBukkit.load(Main.class);
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
        // Reset for a clean slate next test
        MockBukkit.unmock();
    }
    // #endregion loadMockCommandAPIExample

    class CustomExample {
        // #region loadCustomCommandAPIPlatformImplementationExample
        public class CustomMockCommandAPIBukkit extends MockCommandAPIBukkit {
            // Implement a method that usually throws `UnimplementedMethodException`
            @Override
            public void reloadDataPacks() {
                CommandAPI.logInfo("Simulating data pack reload");
                // Further logic
            }
        }

        @BeforeEach
        public void setUp() {
            // Set up MockBukkit server
            MockBukkit.mock();

            // Tell the CommandAPI to use your custom platform implementation
            CommandAPIVersionHandler.usePlatformImplementation(new CustomMockCommandAPIBukkit());

            // Load CommandAPI and your plugin as mentioned above...
        }
        // #endregion loadCustomCommandAPIPlatformImplementationExample
    }
}
